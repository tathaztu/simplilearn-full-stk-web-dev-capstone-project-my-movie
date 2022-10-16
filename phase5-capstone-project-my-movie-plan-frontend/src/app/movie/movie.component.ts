import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Movie } from '../classes/movie';
import { Showtime } from '../classes/showtime';
import { MovieService } from '../services/movie.service';

@Component({
  selector: 'app-movie',
  templateUrl: './movie.component.html',
  styleUrls: ['./movie.component.css']
})
export class MovieComponent {

  movie:Movie;

  fileImage?:File;
  url: any;

  constructor(
    public movieService:MovieService,
    private route: ActivatedRoute,
    private router: Router
  ) { 
    this.movie = new Movie();
    
    const id = this.route.snapshot.params['id'];
    if(id != 0){
      movieService.getMovieByID(id).subscribe(
        data => {
          console.log(data)
          this.movie = data;
          // this.url = this.movieService.getImage(this.movie.strMoviePicPath);
          this.movieService.getImage(this.movie.strMoviePicPath).subscribe ({
            next: (response) => {
              this.getImageFromBlob(response);
            }
          })
        }
      )
    }
  }

  selectFile(event: any) {
    this.fileImage = event.target.files[0];

    var reader = new FileReader();
    reader.readAsDataURL(event.target.files[0]);

    reader.onload = (_event) => {
      this.url = reader.result;
    }
  }

  // isAdd() {
  //   console.log("calling isAdd " + this.movie.longMovieId)
  //   return 0 == this.movie.longMovieId
  // }

  saveMovie() {
    console.log(JSON.stringify(this.movie));
    this.movieService.saveMovie(this.movie, this.fileImage!)
  }

  private getImageFromBlob(image: Blob) {
    if (image && image.size > 0) {
      let reader = new FileReader();

      reader.addEventListener("load", () => {
        this.url = reader.result;
      }, false);

      reader.readAsDataURL(image);
    }
  }

  addShowTime() {
    console.log(this.movie.listShowTimes)
    if (null == this.movie.listShowTimes) {
      this.movie.listShowTimes = Array();
    }
    console.log(this.movie.listShowTimes)
    this.movie.listShowTimes.push({ltShowTime:''});
    console.log(this.movie.listShowTimes)
  }


}
