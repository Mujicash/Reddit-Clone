import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PostModel } from './post-model';
import { CreatePostModel } from '../post/create-post/create-post.payload';


@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private http: HttpClient) { }

  getAllPosts(): Observable<Array<PostModel>> {
    return this.http.get<Array<PostModel>>('http://localhost:8080/api/post');
  }

  createPost(postRequest: CreatePostModel): Observable<any> {
    return this.http.post('http://localhost:8080/api/post', postRequest);
  }
  getPost(id: number): Observable<PostModel> {
    return this.http.get<PostModel>('http://localhost:8080/api/post/'+id);
  }

  getAllPostsByUser(name: string): Observable<Array<PostModel>> {
    return this.http.get<Array<PostModel>>('http://localhost:8080/api/post/by-user/'+name);
  }
}
