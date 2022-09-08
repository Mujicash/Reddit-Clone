import { Component, OnInit } from '@angular/core';
import { SubredditModel } from '../subreddit-model.payload';
import { SubredditService } from '../subreddit.service';
import { throwError } from 'rxjs';

@Component({
  selector: 'app-list-subreddits',
  templateUrl: './list-subreddits.component.html',
  styleUrls: ['./list-subreddits.component.css']
})
export class ListSubredditsComponent implements OnInit {

  subreddits: Array<SubredditModel>;

  constructor(private service: SubredditService) { 
    this.subreddits = [];
  }

  ngOnInit(): void {
    this.service.getAllSubreddits().subscribe(data => {
      this.subreddits = data;
    })
  }

}
