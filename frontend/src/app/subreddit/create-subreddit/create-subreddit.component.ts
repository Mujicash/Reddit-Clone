import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { SubredditModel } from '../subreddit-model.payload';
import { SubredditService } from '../subreddit.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-subreddit',
  templateUrl: './create-subreddit.component.html',
  styleUrls: ['./create-subreddit.component.css']
})
export class CreateSubredditComponent implements OnInit {

  createSubredditForm: FormGroup;
  subredditModel: SubredditModel;


  constructor(private router: Router, private subredditService: SubredditService) { 
    this.createSubredditForm = new FormGroup({
      title: new FormControl('', Validators.required),
      description: new FormControl('', Validators.required)
    });

    this.subredditModel = {
      name: '',
      description: ''
    };
  }

  ngOnInit(): void {
  }

  createSubreddit() {
    this.subredditModel.name = this.createSubredditForm.get('title')?.value;
    this.subredditModel.description = this.createSubredditForm.get('description')?.value;
    
    this.subredditService.createSubreddit(this.subredditModel).subscribe({
      complete: () => this.router.navigateByUrl('list-subreddits'),
      error: (error) => console.error('Error ocurred ', error)
    });
  }

  discard() {
    this.router.navigateByUrl('/');
  }

}
