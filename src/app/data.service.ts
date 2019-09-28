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
    let json = JSON.stringify({"id1":id1,"datas":datas,"isDone":isDone});
        let params = 'json=' + json;
  return this.http.post(`${this.baseUrl}/post`, params);
  }

}
