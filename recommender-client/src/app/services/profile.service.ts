import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Constants } from '../shared/classes/constants';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  constructor(
    private http: HttpClient
  ) { }

  getOneProfile(globalUserId: number):Observable<any>{
    return this.http.get(Constants.API_ENDPOINT + "profiles/" + globalUserId);
  }
}
