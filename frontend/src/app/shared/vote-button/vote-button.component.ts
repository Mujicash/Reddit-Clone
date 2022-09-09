import { Component, OnInit, Input } from '@angular/core';
import { PostModel } from '../post-model';
import { faArrowUp, faArrowDown } from '@fortawesome/free-solid-svg-icons';
import { VoteModel } from './vote-payload';
import { VoteType } from './vote-type';
import { VoteService } from '../vote.service';
import { AuthService } from 'src/app/auth/shared/auth.service';
import { PostService } from '../post.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-vote-button',
  templateUrl: './vote-button.component.html',
  styleUrls: ['./vote-button.component.css']
})
export class VoteButtonComponent implements OnInit {

  @Input() post: PostModel;
  voteRequest: VoteModel;
  faArrowUp = faArrowUp;
  faArrowDown = faArrowDown;
  upvoteColor: string;
  downvoteColor: string;

  constructor(private voteService: VoteService, private authService: AuthService, 
    private postService: PostService, private toastr: ToastrService) { 
    this.upvoteColor = '';
    this.downvoteColor = '';
    this.post = {
      id: 0,
      postName: '',
      url: '',
      description: '',
      voteCount: 0,
      username: '',
      subredditName: '',
      commentCount: 0,
      duration: '',
    }

    this.voteRequest = {
      postId: 0,
      voteType: VoteType.UPVOTE
    }
  }

  ngOnInit(): void {
    //this.updateVoteDetails();
  }

  upvotePost() {
    this.voteRequest.voteType = VoteType.UPVOTE;
    this.vote();
    this.downvoteColor = '';
  }

  downvotePost() {
    this.voteRequest.voteType = VoteType.DOWNVOTE;
    this.vote();
    this.upvoteColor = '';
  }

  private vote() {
    this.voteRequest.postId = this.post.id;
    this.voteService.vote(this.voteRequest).subscribe({
      complete: () => this.updateVoteDetails(),
      error: (error) => this.toastr.error(error.error.message)
    });
  }

  private updateVoteDetails() {
    this.postService.getPost(this.post.id).subscribe(post => {
      this.post = post;
    })
  }

}
