import { Injectable } from '@angular/core';
import { Notes } from './Notes'
import { HttpClient, HttpErrorResponse } from '@angular/common/http'
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw'

@Injectable()
export class DataService {
  public notes = [];
  public id = 0;
  public deleteId;

  constructor(private http:HttpClient) { }

  getData()
  {
    return this.http.get<Notes>('http://localhost:8080/notes/get')  
      .catch(this.errorHandler);   
   }

   errorHandler(error:HttpErrorResponse){
      return Observable.throw(error.message||null);
}
}
