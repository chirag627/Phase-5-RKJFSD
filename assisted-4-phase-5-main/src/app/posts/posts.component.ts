import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { map } from 'rxjs/operators';
import { PostData } from './postdata.model';

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.css'],
})
export class PostsComponent implements OnInit {
  fetchedPosts: PostData[] = [];
  backendurl = 'http://localhost:3000/posts';
  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.fetchPosts();
  }

  onCreatePost(postData: { title: string; content: string }, form: NgForm) {
    this.http.post(this.backendurl, postData).subscribe((responseData) => {
      console.log(responseData);
      this.fetchPosts();
      form.reset();
    });
  }
  onFetchPosts() {
    this.fetchPosts();
  }

  onDeletePost(id: number) {
    this.http.delete(this.backendurl + '/' + id).subscribe((response) => {
      console.log('Post deleted: ' + response);
      this.fetchPosts();
    });
  }

  fetchPosts() {
    this.http
      .get(this.backendurl)
      .pipe(
        map((responseData) => {
          const postsArray: PostData[] = [];
          for (const key in responseData) {
            var x = { ...responseData[key] };
            postsArray.push(x);
          }
          return postsArray;
        })
      )
      .subscribe((posts) => {
        this.fetchedPosts = posts;
      });
  }
}
