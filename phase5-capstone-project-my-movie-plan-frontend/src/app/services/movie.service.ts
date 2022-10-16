import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Movie } from '../classes/movie';
import { MovieGenre } from '../classes/movie-genre';
import { MovieLang } from '../classes/movie-lang';

@Injectable({
  providedIn: 'root'
})
export class MovieService {

  movieGenres:MovieGenre[] = [];
  movieLanguages:MovieLang[] = [];

  constructor(private httpClient:HttpClient) { 
    console.log("Calling WS, get Movie Genres")
    this.getMovieGenres().subscribe (
      data => this.movieGenres = data
    );

    console.log("Calling WS, get Movie Languages")
    this.getMovieLanguages().subscribe (
      data => this.movieLanguages = data
    )
  }

  getAllMovies(){
    return this.httpClient.get<Movie[]>('http://localhost:8080/movie/all');
  }

  getMovieByID(id:number) {
    return this.httpClient.get<Movie>(`http://localhost:8080/movie/find/${id}`);
  }

  getMovieGenres() {
    return this.httpClient.get<MovieGenre[]>('http://localhost:8080/movie/genres/all');
  }

  getMovieLanguages() {
    return this.httpClient.get<MovieLang[]>('http://localhost:8080/movie/languages/all');
  }

  searchMovie(strSearchString: string){
    return this.httpClient.get<Movie[]>(`http://localhost:8080/movie/by-keyword/${strSearchString}`);
  }

  saveMovie(movie:Movie, file: File) {
    var formData: any = new FormData();
    formData.append("file", file);
    formData.append("strMovie", JSON.stringify(movie));
    
    this.httpClient.post('http://localhost:8080/movie/save', formData).subscribe({
      next: (response) => {
        console.log('Reponse after calling save movie API - ' + response)
      },
      error: (err) => {
        console.log(err)
      }
    })
  }

  getImage(strImageName:string): Observable<Blob> {
    //Make a call to Sprinf Boot to get the Image Bytes.
    let queryParams = new HttpParams().append("imageName", strImageName)

    // this.httpClient.get('http://localhost:8080/movie/getImage', {params:queryParams})

    // this.httpClient.get(`http://localhost:8080/movie/getImage/${strImageName}`)
    //   .subscribe(
    //     res => {
    //       console.log("Printing Resp - Start")
    //       console.log(res)
    //       console.log("Printing Resp - End")
    //       let base64Data = res;
    //       return 'data:image/jpeg;base64,' + base64Data;
    //     }
    //   );
    let strUrl = console.log(`http://localhost:8080/movie/getImage/${strImageName}`)
    return this.httpClient.get(`http://localhost:8080/movie/getImage/${strImageName}`, { responseType: 'blob' });
    
  }
}
