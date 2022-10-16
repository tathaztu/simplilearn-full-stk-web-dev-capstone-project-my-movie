import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { LoginComponent } from './login/login.component';
import { MovieListComponent } from './movie-list/movie-list.component';
import { MovieComponent } from './movie/movie.component';
import { PurchaseItemsComponent } from './purchase-items/purchase-items.component';
import { RegisterComponent } from './register/register.component';

const routes: Routes = [
  {path: '', component: DashboardComponent},
  {path:'login', component:LoginComponent},
  {path:'register', component:RegisterComponent},
  {path: 'movie/:id', component:MovieComponent},
  {path: 'movie-list', component:MovieListComponent},
  {path: 'movie-list/:strSearch', component:MovieListComponent},
  {path: 'checkout', component:PurchaseItemsComponent}
];

@NgModule({
  // declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
