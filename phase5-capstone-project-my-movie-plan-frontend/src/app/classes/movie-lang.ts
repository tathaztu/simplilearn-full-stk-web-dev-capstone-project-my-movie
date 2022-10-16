export class MovieLang {

    constructor(
        public strLangCode: string,
        public strLangName: string,
        public strDescription: string
    ) {
        console.log("Printing Movie Lang Object - " + JSON.stringify(this)); 
    }
}
