import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AuthService } from '../shared/auth.service';
import { LoginRequest } from './login-request.payload';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  loginRequest: LoginRequest;
  registerSuccessMessage: string;
  isError: boolean;

  constructor(private authService: AuthService, private activatedRoute: ActivatedRoute, private router: Router, private toastr: ToastrService) {
    this.registerSuccessMessage = '';
    this.isError = false;
    this.loginRequest = {
      username: '',
      password: ''
    }

    this.loginForm = new FormGroup({
      username: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required)
    });
  }

  ngOnInit(): void {
    this.loginForm = new FormGroup({
      username: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required)
    });

    this.activatedRoute.queryParams
      .subscribe(params => {
        if (params['registered'] !== undefined && params['registered'] === 'true') {
          this.toastr.success('Signup Successful');
          this.registerSuccessMessage = 'Please Check your inbox for activation email '
            + 'activate your account before you Login!';
        }
      });
  }

  login() {
    this.loginRequest.username = this.loginForm.get('username')?.value;
    this.loginRequest.password = this.loginForm.get('password')?.value;

    this.authService.login(this.loginRequest).subscribe(data => {
      if (data) {
        this.isError = false;
        this.router.navigateByUrl('/');
        this.toastr.success('Login successful');
      } else {
        this.isError = true;
      }
    });
  }

}
