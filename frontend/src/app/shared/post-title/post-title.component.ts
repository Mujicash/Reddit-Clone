import { Component, OnInit, Input } from '@angular/core';
import { PostModel } from '../post-model';
import { faComments } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-post-title',
  templateUrl: './post-title.component.html',
  styleUrls: ['./post-title.component.css']
})
export class PostTitleComponent implements OnInit {

  @Input() data: PostModel[];
  faComments = faComments;

  constructor() { 
    this.data = [];
  }

  ngOnInit(): void {
  }

  goToPost(id: number): void {

  }

}
