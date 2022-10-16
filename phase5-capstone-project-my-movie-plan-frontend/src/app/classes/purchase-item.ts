export class PurchaseItem {

	constructor(
        public longPurchaseItemId : number,
        public longMovieId : number,
        public ltShowTime : string,
        public nQuantity:number
    ) {
        console.log("Printing Purchase Item Object - " + JSON.stringify(this)); 
    }
}
