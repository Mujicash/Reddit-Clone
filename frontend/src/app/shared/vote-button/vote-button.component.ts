import { Component, OnInit, Input } from '@angular/core';
import { PostModel } from '../post-model';
import { faArrowUp, faArrowDown } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-vote-button',
  templateUrl: './vote-button.component.html',
  styleUrls: ['./vote-button.component.css']
})
export class VoteButtonComponent implements OnInit {

  @Input() post: PostModel;
  faArrowUp = faArrowUp;
  faArrowDown = faArrowDown;
  upvoteColor: string;
  downvoteColor: string;

  constructor() { 
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
  }

  ngOnInit(): void {
  }

  upvotePost() {

  }

  downvotePost() {

  }

}
