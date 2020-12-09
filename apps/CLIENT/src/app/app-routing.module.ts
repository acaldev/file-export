import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ExcelExportComponent } from './components/excel-export/excel-export.component';

const routes: Routes = [
  { path: '', component: ExcelExportComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
