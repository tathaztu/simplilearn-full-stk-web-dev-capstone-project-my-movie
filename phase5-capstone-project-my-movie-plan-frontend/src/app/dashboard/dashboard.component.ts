import { Component, OnInit } from '@angular/core';
import { Movie } from '../classes/movie';
import { MovieService } from '../services/movie.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  urlArray:any;
  movieList:Movie[] = [];
  
  constructor(
    public movieService:MovieService
  ) {
    
  }

  ngOnInit(): void {
    this.searchMovies();
  }


  searchMovies(){

    let observableObj = this.movieService.getAllMovies();

      observableObj.subscribe ({
        next: (res) => {
          this.movieList = res
          console.log("From Dashboard ts - " + JSON.stringify(this.movieList));
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

  private getImageFromBlob(image: Blob, idx: number) {
    if (image && image.size > 0) {
      let reader = new FileReader();

      reader.addEventListener("load", () => {
        this.urlArray[idx] = reader.result;
      }, false);

      reader.readAsDataURL(image);
    }
  }

}
