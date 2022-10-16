import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Movie } from '../classes/movie';
import { Purchase } from '../classes/purchase';
import { PurchaseItem } from '../classes/purchase-item';
import { User } from '../classes/user';

@Injectable({
  providedIn: 'root'
})
export class TicketsService {
  
  selectedItems?:Map<Movie, Map<string, number>>;

  nTotalTickets = 0;

  constructor(
    private httpClient: HttpClient
  ) {
  }



  getTicketCount(){
    return this.nTotalTickets
  }

  addItem(movie: Movie, ltShowTime: string){
    let mapForMovie = this.selectedItems?.get(movie);
    

    if(undefined === mapForMovie){
      mapForMovie = new Map<string, number>();
      mapForMovie.set(ltShowTime, 1);

      if(undefined === this.selectedItems){
        this.selectedItems = new Map<Movie, Map<string, number>>();
      }

      this.selectedItems!.set(movie, mapForMovie);
    } else {
      let nTicketsForShowtime = mapForMovie.get(ltShowTime) ?? 0;
      mapForMovie.set(ltShowTime, ++nTicketsForShowtime);
    }

    ++this.nTotalTickets;
    console.log(this.selectedItems)
    console.log(this.nTotalTickets)
  }

  removeItem(movie: Movie, ltShowTime: string) {
    let mapForMovie = this.selectedItems?.get(movie);

    let nTicketsForShowtime = mapForMovie!.get(ltShowTime);

    if (nTicketsForShowtime == 1) {
      mapForMovie?.delete(ltShowTime)
    } else {
      mapForMovie?.set(ltShowTime, --nTicketsForShowtime!);
    }

    --this.nTotalTickets;
  }

  clearSelections(){
    this.selectedItems = undefined;
    this.nTotalTickets = 0;
  }

  completePurchase(purchaseItems:PurchaseItem[], user:User, flTotal:number){
    
    let purchase = new Purchase(0, user.strUserId, flTotal, purchaseItems);

    console.log("JSON: Stringify from ticket.service - " + JSON.stringify(purchase));
    console.log("Waiting for service response...")
    
    return this.httpClient.post<Purchase>('http://localhost:8080/purchase/save', purchase);
  }
}
