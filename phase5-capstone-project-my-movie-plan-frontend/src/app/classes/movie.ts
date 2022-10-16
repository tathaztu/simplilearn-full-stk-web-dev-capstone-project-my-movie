import { Showtime } from "./showtime";

export class Movie {
	
    longMovieId!:number;

	strGenreCode!:string;

	strLangCode!:string;

	strMovieName!:string;

	strDesc!:string;

	strStarring!:string;

	strDirectedBy!:string;

	flTicketPrice!:number;

	bActive!:boolean;

	strMoviePicPath!:string;

	listShowTimes!:Showtime[];
}
