import { Injectable } from '@angular/core';
import { Notes } from './Notes'
import { HttpClient, HttpErrorResponse , HttpHeaders} from '@angular/common/http'
import { Headers, Http, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw'

@Injectable()
export class DataService {
  public notes = [];
  public id = 4;
  public deleteId;
  public baseUrl = 'http://localhost:8080/notes'
  
  private headers = new Headers({'Content-Type': 'application/json'}); 
  private headers1 = new Headers({'Content-Type': 'application/x-wwww-form-urlencoded'})
  constructor(private http:Http , private http1:HttpClient) { }

  getData()
  {
    return this.http1.get<Notes>('http://localhost:8080/notes/get')  
      .catch(this.errorHandler);   
   }

   errorHandler(error:HttpErrorResponse){
      return Observable.throw(error.message||null);
}
changeData(id1)
{
  // return this.http.delete('http://localhost:8080/weather/delete?id1='+id1)  
  //     .catch(this.errorHandler);   

      const url = `${this.baseUrl}/delete/`+id1;
      return this.http.delete(url, {headers:this.headers})
      .toPromise()
      .then(() => null)
      .catch(this.errorHandler);
}

 addNotes(datas,id1,isDone)
  {
   this.id += id1;
    id1 = this.id;
    const url = `${this.baseUrl}/post/id1=${id1}&datas=${datas}&isDone=${isDone}`
  return this.http.post(url, {headers:this.headers1});
  }

  updateData(id1,datas,isDone)
  {
  // return this.http.delete('http://localhost:8080/weather/delete?id1='+id1)  
  //     .catch(this.errorHandler);   

      const url = `${this.baseUrl}/patch/id1=${id1}&datas=${datas}`;
      return this.http.patch(url, {headers:this.headers1})
      .toPromise()
      .then(() => null)
      .catch(this.errorHandler);
  }
}
