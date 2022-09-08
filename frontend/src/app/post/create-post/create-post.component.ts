import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { CreatePostModel } from './create-post.payload';
import { Router } from '@angular/router';
import { SubredditModel } from 'src/app/subreddit/subreddit-model.payload';
import { SubredditService } from 'src/app/subreddit/subreddit.service';
import { PostService } from 'src/app/shared/post.service';
import { throwError } from 'rxjs';

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.css']
})
export class CreatePostComponent implements OnInit {

  createPostForm: FormGroup;
  postRequest: CreatePostModel;
  subreddits: Array<SubredditModel>

  constructor(private router: Router, private subredditService: SubredditService, private postService: PostService) { 
    this.createPostForm = new FormGroup({
      postName: new FormControl('', Validators.required),
      subredditName: new FormControl('', Validators.required),
      url: new FormControl('', Validators.required),
      description: new FormControl('', Validators.required)
    });

    this.postRequest = {
      postName: '',
      subredditName: '',
      url: '',
      description: ''
    }

    this.subreddits = [];
  }

  ngOnInit(): void {
    this.subredditService.getAllSubreddits().subscribe(data => {
      this.subreddits = data;
    });
  }

  createPost() {
    this.postRequest.postName = this.createPostForm.get('postName')?.value;
    this.postRequest.subredditName = this.createPostForm.get('subredditName')?.value;
    this.postRequest.url = this.createPostForm.get('url')?.value;
    this.postRequest.description = this.createPostForm.get('description')?.value;

    this.postService.createPost(this.postRequest).subscribe({
      next: (value) => this.router.navigateByUrl('/'),
      error: (error) => throwError(() => error)
    });
  }

  discardPost() {
    this.router.navigateByUrl('/');
  }

}
