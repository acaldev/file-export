import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PaginationRequest, PaginationResponse } from 'src/app/components/excel-export/models';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  constructor(private http: HttpClient) { }

  private configUrl = 'http://api.app.test/employees/';

  public get(request: PaginationRequest): Observable<PaginationResponse> {
    return this.http.get<PaginationResponse>(this.configUrl+'?page='+request.page+'&size='+request.size);
  }

}
