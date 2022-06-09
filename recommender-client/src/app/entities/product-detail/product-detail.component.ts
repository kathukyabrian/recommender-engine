import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { EventsService } from 'src/app/services/events.service';
import { ProductService } from 'src/app/services/product.service';
import { Constants } from 'src/app/shared/classes/constants';

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
    private productService: ProductService,
    private eventService: EventsService
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


  addEventValueToList(eventId: number) {
    // get current value from session
    let events: Array<any> = JSON.parse(sessionStorage.getItem(Constants.EVENTS_VALUE_KEY) + "");
    if (!events) {
      events = [];
    }
    events.push(eventId)

    sessionStorage.setItem(Constants.EVENTS_VALUE_KEY, JSON.stringify(events))

    // get event list length and check if it matches threshold
    if (events.length == Constants.EVENT_THRESHOLD) {
      let arrayStr = events.join(',');
      this.pushEvent(arrayStr, 1, 1, 1);

      // empty the session storage
      sessionStorage.removeItem(Constants.EVENTS_VALUE_KEY);
    } else {
      console.log("Not yet full");
    }
  }
  pushEvent(events: any, applicationId: number, userId: number, globalUserId: number) {

    let event = {
      applicationId: applicationId,
      eventType: "PRODUCT_VIEWS",
      eventValue: events,
      userId: userId,
      globalUserId: globalUserId
    }

    this.eventService.save(event).subscribe(
      {
        next: (res) => {
          console.log(res);
        },
        error: (err) => {
          console.log(err);
        }
      }
    );

  }

  ngOnInit(): void {

    let id = Number(this.route.snapshot.paramMap.get("id"));

    this.loadOneProduct(id);

    this.addEventValueToList(id);

  }

}
