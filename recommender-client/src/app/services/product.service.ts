import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Constants } from '../shared/classes/constants';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(
    private http: HttpClient
  ) { }

  save(data:any):Observable<any>{
    return this.http.post(Constants.API_ENDPOINT + "products", data);
  }

  getOne(id:number):Observable<any>{
    return this.http.get(Constants.API_ENDPOINT + "products/" + id);
  }

  getBulk(data:any):Observable<any>{
    return this.http.post(Constants.API_ENDPOINT + "products/bulk", data);
  }

  getAll():Observable<any>{
    return this.http.get(Constants.API_ENDPOINT + "products");
  }
}
