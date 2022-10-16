import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Movie } from '../classes/movie';
import { PurchaseItem } from '../classes/purchase-item';
import { MovieService } from '../services/movie.service';
import { TicketsService } from '../services/tickets.service';

@Component({
  selector: 'app-movie-list',
  templateUrl: './movie-list.component.html',
  styleUrls: ['./movie-list.component.css']
})
export class MovieListComponent implements OnInit {

  strSearch?:string;

  urlArray:any;
  movieList:Movie[] = [];

  constructor(
    public movieService:MovieService,
    public ticketService:TicketsService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.searchMovies();
  }

  ngOnInit(): void {

  }


  private getImageFromBlob(image: Blob, idx: number) {
    if (image && image.size > 0) {
      let reader = new FileReader();

      reader.addEventListener("load", () => {
        this.urlArray[idx] = reader.result;
      }, false);

      reader.readAsDataURL(image);
    }
  }

  processRowClick(id:number) {
    this.router.navigate(['movie', id])
  }

  searchMovies(){
    console.log(`Movie List: Search Text is ${this.strSearch}`);

    let observableObj = 
      this.strSearch?.trim() ? 
      this.movieService.searchMovie(this.strSearch!) :
      this.movieService.getAllMovies();

      observableObj.subscribe ({
        next: (res) => {
          this.movieList = res
          if (null != this.movieList) {
            this.urlArray = Array(this.movieList.length);

            for(let i=0; i<this.movieList.length; i++){
              this.movieService.getImage(this.movieList[i].strMoviePicPath).subscribe ({
                next: (response) => {
                  this.getImageFromBlob(response, i);
                }
              })
            };
          }
        }
      });
  }

  add2Cart(event: any, nIdx:number, strShowtime:string): void {
    event.stopPropagation();
    console.log('Showtime is ' + strShowtime);

    const movieSelected = this.movieList[nIdx];
    this.ticketService.addItem(movieSelected, strShowtime);
  }

}
