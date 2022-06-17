import { Component, OnInit } from '@angular/core';
import { Constants } from 'src/app/shared/classes/constants';

@Component({
  selector: 'app-recommended',
  templateUrl: './recommended.component.html',
  styleUrls: ['./recommended.component.css']
})
export class RecommendedComponent implements OnInit {

  recommendedProducts!: Array<any>;

  constructor() { }


  loadProducts() {

    let allRecommendedProducts = localStorage.getItem(Constants.EVENTS_VALUE_KEY);

    console.log(JSON.parse(allRecommendedProducts + ""));

  }
  ngOnInit(): void {
    this.loadProducts();
  }

}
