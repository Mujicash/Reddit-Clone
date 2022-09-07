import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from '../shared/auth.service';
import { SignupRequestPayload } from './signup-request.payload';


@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  signupForm: FormGroup;
  signupRequest: SignupRequestPayload;

  constructor(private authService: AuthService, private router: Router, private toastr: ToastrService) {
    this.signupRequest = {
      username: '',
      email: '',
      password: ''
    };

    this.signupForm = new FormGroup({
      username: new FormControl('', Validators.required),
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', Validators.required)
    });
  }

  ngOnInit(): void {
    this.signupForm = new FormGroup({
      username: new FormControl('', Validators.required),
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', Validators.required)
    });
  }

  signup() {
    this.signupRequest.username = this.signupForm.get('username')?.value;
    this.signupRequest.email = this.signupForm.get('email')?.value;
    this.signupRequest.password = this.signupForm.get('password')?.value;
    console.log(this.signupRequest);

    this.authService.signup(this.signupRequest).subscribe({
      complete: () => this.router.navigate(['/login'], {queryParams: {registered: 'true'}}),
      error: (error) => this.toastr.error('Registered failed! Please try again')
    });
  }

}
