import { Component, OnInit } from '@angular/core';
import { DataService } from '../data.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Notes } from '../Notes'
@Component({
  selector: 'app-notes',
  templateUrl: './notes.component.html',
  styleUrls: ['./notes.component.css']
})
export class NotesComponent implements OnInit {

  public notes =[];
  public sendData;
  public enable = true;
  public disable = false;
  constructor(private data:DataService,private router:Router,private route : ActivatedRoute) { }

  ngOnInit() {
   this.sendData =  this.data.getData();
   this.data.getData().subscribe((data:Notes[])=>{
    for(let listItem of data)
     this.notes.push(listItem);
  });
  }

}
