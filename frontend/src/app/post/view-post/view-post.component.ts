import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CommentModel } from 'src/app/comment/comment.payload';
import { CommentService } from 'src/app/comment/comment.service';
import { PostModel } from 'src/app/shared/post-model';
import { PostService } from 'src/app/shared/post.service';

@Component({
  selector: 'app-view-post',
  templateUrl: './view-post.component.html',
  styleUrls: ['./view-post.component.css']
})
export class ViewPostComponent implements OnInit {

  post: PostModel;
  postId: number;
  commentForm: FormGroup;
  comments: CommentModel[];
  commentRequest: CommentModel;

  constructor(private postService: PostService, private activateRoute: ActivatedRoute,
    private commentService: CommentService, private router: Router) {
    this.comments = [];
    this.commentForm = new FormGroup({
      text: new FormControl('', Validators.required)
    });

    this.postId = this.activateRoute.snapshot.params['id'];
    this.commentRequest = {
      text: '',
      username: '',
      postId: this.postId
    };

    this.post = {
      id: 0,
      postName: '',
      url: '',
      description: '',
      voteCount: 0,
      username: '',
      subredditName: '',
      commentCount: 0,
      duration: ''
    }
  }

  ngOnInit(): void {
    this.getPostById();
    this.getCommentsForPost();
  }

  postComment() {
    this.commentRequest.text = this.commentForm.get('text')?.value;
    this.commentService.postComment(this.commentRequest).subscribe({
      complete: () => {
        this.commentForm.get('text')?.setValue('');
        this.getCommentsForPost();
      }
    })
  }

  private getPostById() {
    this.postService.getPost(this.postId).subscribe(data => {
      this.post = data;
    });
  }

  private getCommentsForPost() {
    this.commentService.getAllCommentsForPost(this.postId).subscribe(data => {
      this.comments = data;
    });
  }

}
