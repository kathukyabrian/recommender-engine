import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {

  product!: any;

  error: boolean = false;

  errorText: string = "";

  constructor(
    private route: ActivatedRoute,
    private productService: ProductService
  ) { }

  loadOneProduct(id: number) {

    this.productService.getOne(id).subscribe(
      {
        next: (res) => {
          this.product = res;
        },
        error: (err) => {
          this.error = true;
          this.errorText = err.error.statusReason;
        }
      }
    )

  }

  ngOnInit(): void {

    let id = Number(this.route.snapshot.paramMap.get("id"));

    this.loadOneProduct(id);

    // send event here

  }

}
