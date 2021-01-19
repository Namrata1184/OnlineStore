import { Component } from '@angular/core';
import { Router } from '@angular/router';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'admin-panel';

  constructor(private router:Router){

  }
  ngOnInit(): void {
  
  }

  onLogout(){
    window.onbeforeunload = function () {
      sessionStorage.clear();
      return ''
    }
    sessionStorage.removeItem('name')
    sessionStorage.removeItem('role')
    sessionStorage.removeItem('user')
    sessionStorage.removeItem('isActive')
    this.router.navigate(['/login'])

  }

}
