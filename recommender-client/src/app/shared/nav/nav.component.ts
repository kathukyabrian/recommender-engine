import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EventsService } from 'src/app/services/events.service';
import { Constants } from '../classes/constants';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {

  constructor(
    private router: Router,
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
          this.router.navigate(['']);
        },
        error: (err) => {
          if (processEventsButton) {
            processEventsButton.innerHTML = "";
          }
        },
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
