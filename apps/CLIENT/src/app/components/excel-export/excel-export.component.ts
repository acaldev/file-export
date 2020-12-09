import { Component, OnInit } from '@angular/core';
import { EmployeeService, ExcelService } from 'src/app/services';
import { PaginationRequest, PaginationResponse } from './models';

@Component({
  selector: 'app-excel-export',
  templateUrl: './excel-export.component.html',
  styleUrls: ['./excel-export.component.scss']
})
export class ExcelExportComponent implements OnInit {

  public pagination: PaginationRequest;

  constructor(
    private employeeService: EmployeeService, 
    private excelService: ExcelService
  ) { 
    this.pagination = { page: 1, size: 100 };
  }

  ngOnInit(): void {
  }

  public generateExcel(){
    const startDate =  new Date();
    console.info('GENERANDO EXCEL A LAS: '+(new Date()).getHours()+':'+(new Date()).getMinutes()+':'+(new Date()).getSeconds());
    this.employeeService.get(this.pagination).subscribe((_value: PaginationResponse) => {
      console.info('RESPUESTA DE BACKEND A LAS: '+(new Date()).getHours()+':'+(new Date()).getMinutes()+':'+(new Date()).getSeconds());
      this.excelService.exportAsExcelFile(_value.content, 'Employee_page_'+this.pagination.page+'_size_'+this.pagination.size).then(() => {
        const endDate = new Date();
        console.info('EXCEL GENERADO A LAS: '+(new Date()).getHours()+':'+(new Date()).getMinutes()+':'+(new Date()).getSeconds());
        console.info('¡¡ TIEMPO !!: '+ (endDate.getTime() - startDate.getTime()) / 1000+ ' seg');
      });
    });
  }

}
