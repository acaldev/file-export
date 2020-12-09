import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { ExcelExportComponent } from './components/excel-export/excel-export.component';
import { FormsModule } from '@angular/forms';
import { EmployeeService, ExcelService } from './services';

@NgModule({
  declarations: [
    AppComponent,
    ExcelExportComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    ExcelService,
    EmployeeService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
