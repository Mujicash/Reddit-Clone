import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CommentModel } from './comment.payload';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(private http: HttpClient) { }

  getAllCommentsForPost(postId: number): Observable<CommentModel[]> {
    return this.http.get<CommentModel[]>('http://localhost:8080/api/comments/post/'+postId);
  }

  postComment(commentRequest: CommentModel): Observable<any> {
    return this.http.post('http://localhost:8080/api/comments', commentRequest);
  }
  
  getAllCommentsByUser(name: string): Observable<CommentModel[]>  {
    return this.http.get<CommentModel[]>('http://localhost:8080/api/comments/user/'+name);
  }
}
