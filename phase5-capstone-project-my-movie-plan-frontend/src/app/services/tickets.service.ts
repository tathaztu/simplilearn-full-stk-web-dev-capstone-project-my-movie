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
    private ticketService:TicketsService,
    private httpClient: HttpClient,
    private router: Router
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
    this.httpClient.post<Purchase>('http://localhost:8080/purchase/save', purchase).subscribe({
      next: (response) => {
        let purchase:Purchase = response;
        console.log('Reponse after calling save P U R C H A S E API - ' + purchase)
        this.ticketService.clearSelections();
        alert('Thank you for your business');
        this.router.navigate([''])
      },
      error: (err) => {
        console.log(err)
      }
    });
    console.log("Response Complete.")
  }
}
