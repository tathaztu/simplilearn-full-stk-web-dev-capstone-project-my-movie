import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Movie } from '../classes/movie';
import { Purchase } from '../classes/purchase';
import { PurchaseItem } from '../classes/purchase-item';
import { MovieService } from '../services/movie.service';
import { TicketsService } from '../services/tickets.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-purchase-items',
  templateUrl: './purchase-items.component.html',
  styleUrls: ['./purchase-items.component.css']
})
export class PurchaseItemsComponent implements OnInit {

  selectedItems!:Map<Movie, Map<string, number>>;
  urlArray:any;

  purchaseItems:PurchaseItem[] = [];
  flTotal:number;

  constructor(
    public ticketService:TicketsService,
    private movieService:MovieService,
    private userService:UserService,
    private router: Router
  ) {
    
    this.selectedItems = ticketService.selectedItems!

    console.log('Purchase Items - Constructor\n' + this.selectedItems)


    this.flTotal = 0;

    this.urlArray = Array(this.selectedItems.keys.length);

    let movies:Movie[] = [];

    for(let movie of this.selectedItems.keys()){
      movies.push(movie);
    }

    for(let i=0; i< movies.length; i++){

      this.movieService.getImage(movies[i].strMoviePicPath).subscribe ({
        
        next: (response) => {
          
            this.getImageFromBlob(response,  i);

            let movieTicketSelections:Map<string, number> = this.selectedItems.get(movies[i])!;

            for(let entry of movieTicketSelections.entries()){
              this.flTotal += movies[i].flTicketPrice * entry[1];

              this.purchaseItems.push(new PurchaseItem(0, movies[i].longMovieId, entry[0], entry[1]))
            }
        }
      })
    };
  }

  ngOnInit(): void {
  }

  private getImageFromBlob(image: Blob, nIdx:number) {
    if (image && image.size > 0) {
      let reader = new FileReader();

      reader.addEventListener("load", () => {
        this.urlArray[nIdx] = reader.result;
      }, false);

      reader.readAsDataURL(image);
    }
  }

  completePurchase(){
    if(!this.userService.loggedInUser){
      this.router.navigate(['login'])
    } else {
      console.log("calling ticket service ts - START")
      console.log(this.purchaseItems)
      console.log(this.userService.loggedInUser!) 
      console.log(this.flTotal)     
      this.ticketService
        .completePurchase(this.purchaseItems, this.userService.loggedInUser!, this.flTotal)
        .subscribe({
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
      console.log("calling ticket service ts - END")
    }
  }


}
