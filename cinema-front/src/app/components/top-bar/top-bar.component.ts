import { Component, OnInit } from '@angular/core';
import { appRoutesPaths } from '../../app.routes.paths';

@Component({
  selector: 'app-top-bar',
  templateUrl: './top-bar.component.html',
  styleUrls: ['./top-bar.component.css']
})
export class TopBarComponent implements OnInit {
  private routes = appRoutesPaths;

  constructor() {
  }

  ngOnInit() {
  }

}
