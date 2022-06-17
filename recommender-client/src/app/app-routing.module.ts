import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProductDetailComponent } from './entities/product-detail/product-detail.component';
import { ProductsComponent } from './entities/products/products.component';
import { RecommendedComponent } from './entities/recommended/recommended.component';

const routes: Routes = [
  {
    path: '',
    component: ProductsComponent
  },
  {
    path: ':id',
    component: ProductDetailComponent
  },
  {
    path: 'recommended',
    component: RecommendedComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
