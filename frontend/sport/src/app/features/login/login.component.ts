import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../../core/services/auth.service';
import { Route, Router } from '@angular/router';
import { LoginRequest } from '../../core/models/login-request.model';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  constructor(private authService:AuthService,private router:Router){}
  loginForm=new FormGroup({
    email:new FormControl('',[Validators.required,Validators.email]),
    password:new FormControl('',[Validators.required])

  })
  login():void{
    if(this.loginForm.invalid){
      this.loginForm.markAllAsTouched();
      return;
    }
    if(this.loginForm.value.email&&this.loginForm.value.password){
    const request:LoginRequest={
      email:this.loginForm.value.email,
      password:this.loginForm.value.password
    }
    this.authService.login(request).subscribe({
      next:(response)=>{
        this.authService.saveToken(response.token);
        this.router.navigate(['/home']);
      },
      error:(error)=>{
        console.error(error);
      }
    })
  }
}
}
