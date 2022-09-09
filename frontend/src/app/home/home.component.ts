import { Component, OnInit } from '@angular/core';
import { PostService } from '../shared/post.service';
import { PostModel } from '../shared/post-model';
import { AuthService } from '../auth/shared/auth.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  posts$: Array<PostModel> = [];
  isLoggedIn: boolean;

  constructor(private service: PostService, private authService: AuthService) { 
    this.isLoggedIn = this.authService.isLoggedIn();

    if (this.isLoggedIn) {
      this.service.getAllPosts().subscribe(post => {
        this.posts$ = post;
      });
    }
  }

  ngOnInit(): void {
  }

}
