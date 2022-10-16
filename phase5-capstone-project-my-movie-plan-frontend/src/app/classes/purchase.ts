import { PurchaseItem } from "./purchase-item";

export class Purchase {
    constructor (
        public longPurchaseId:number,
        public strUserId:string,
        public flTotalPrice:number,
        public listPurchasedItems:PurchaseItem[]
    ) {
        console.log("Printing Purchase Object - " + JSON.stringify(this)); 
    }
}
