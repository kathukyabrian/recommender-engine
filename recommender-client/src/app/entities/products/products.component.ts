import { Component, OnInit } from '@angular/core';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  products!: Array<any>;

  error: boolean = false;

  errorMessage: string = "";

  constructor(private productService: ProductService) { }

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

  ngOnInit(): void {
    this.getAllProducts();
  }

}
