import { Injectable } from '@angular/core';
import * as FileSaver from 'file-saver';
import * as XLSX from 'xlsx';const EXCEL_TYPE = 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8';
const EXCEL_EXTENSION = '.xlsx';

@Injectable()
export class ExcelService {

  constructor() { }

  public exportAsExcelFile(json: any[], excelFileName: string): Promise<any> {  
    return new Promise((resolve,reject)=> { 
      try {
        const worksheet: XLSX.WorkSheet = XLSX.utils.json_to_sheet(json);  
        const workbook: XLSX.WorkBook = { Sheets: { 'data': worksheet }, SheetNames: ['data'] };  
        const excelBuffer: any = XLSX.write(workbook, { bookType: 'xlsx', type: 'array' });  
        this.saveAsExcelFile(excelBuffer, excelFileName).then(() => {
          resolve({success :true}); 
        })
      } catch (e) {
        reject(e);
      }
    });
  }

  private saveAsExcelFile(buffer: any, fileName: string): Promise<any>  {
    return new Promise((resolve,reject)=> { 
      try{
        const data: Blob = new Blob([buffer], {type: EXCEL_TYPE});   
        FileSaver.saveAs(data, fileName + '_export_' + new  Date().getTime() + EXCEL_EXTENSION);
        resolve({success :true}); 
      } catch (e) {
        reject(e);
      }
    });
  }
  
}