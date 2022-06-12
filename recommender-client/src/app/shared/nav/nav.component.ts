import { Component, OnInit } from '@angular/core';
import { EventsService } from 'src/app/services/events.service';
import { Constants } from '../classes/constants';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {


  constructor(
    private eventsService: EventsService
  ) { }

  processEvents() {
    let processEventsButton = document.getElementById("processButton");
    if (processEventsButton) {
      processEventsButton.innerHTML = "Processing";
    }
    this.eventsService.process().subscribe(
      {
        next: (res) => {
          localStorage.setItem(Constants.RECOMMENDATIONS_KEY, JSON.stringify(res));
        },
        error: (err) => { },
        complete: () => {
          if (processEventsButton) {
            processEventsButton.innerHTML = "Process Events";
          }
        }
      }
    )
  }

  ngOnInit(): void {
  }

}
