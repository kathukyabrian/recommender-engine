import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Constants } from '../shared/classes/constants';

@Injectable({
  providedIn: 'root'
})
export class EventsService {

  constructor(
    private http: HttpClient
  ) { }

  save(data: any): Observable<any> {
    return this.http.post(Constants.API_ENDPOINT + "events", data);
  }

  bulkSave(data: any): Observable<any> {
    return this.http.post(Constants.API_ENDPOINT + "events/bulk-save", data);
  }

  process(): Observable<any> {
    return this.http.get(Constants.API_ENDPOINT + "events/process");
  }
  
}
