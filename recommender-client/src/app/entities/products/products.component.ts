import { Component, OnInit } from '@angular/core';
import { ProductService } from 'src/app/services/product.service';
import { ProfileService } from 'src/app/services/profile.service';
import { Constants } from 'src/app/shared/classes/constants';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  products!: Array<any>;

  recommendedProducts!: Array<any>;

  error: boolean = false;

  errorMessage: string = "";

  constructor(
    private productService: ProductService,
    private profileService: ProfileService
  ) { }

  getAllProducts() {
    this.productService.getAll().subscribe(
      {
        next: (res) => {
          console.log(res)
          this.products = res;
        },
        error: (err) => {
          this.error = true;
          this.errorMessage = err.error.statusReason;
        }
      }
    );
  }

  getRecommendedProducts() {
    let globalUserId = Number(localStorage.getItem(Constants.USER_KEY));
    this.profileService.getOneProfile(globalUserId).subscribe(
      {
        next: (res) => {
          
          this.recommendedProducts = res.recommendations;
        },
        error: (err) => {

        }
      }
    )
  }

  ngOnInit(): void {
    this.getAllProducts();
    this.getRecommendedProducts();
  }

}
