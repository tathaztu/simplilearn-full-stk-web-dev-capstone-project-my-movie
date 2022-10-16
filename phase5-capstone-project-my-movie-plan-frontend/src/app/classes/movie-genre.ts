export class MovieGenre {

    constructor(
        public strGenreCode: string,
        public strGenreName: string,
        public strDescription: string
    ) {
        console.log("Printing Movie Genre Object - " + JSON.stringify(this)); 
    }

}
