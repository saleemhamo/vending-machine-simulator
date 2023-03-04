import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { VendingMachine } from 'src/app/main/_models/vending-machine.model';
import { BaseHttpService, IRequestOptions } from '../base.http.service';

@Injectable({
  providedIn: 'root',
})
export class VendingMachineHttpService extends BaseHttpService<any> {
  endPoint = 'vending-machine';

  constructor(http: HttpClient) {
    super(http);
  }

  public getVendingMachineDetails(
    options?: IRequestOptions
  ): Observable<VendingMachine> {
    const url = this.SERVER_URL + this.endPoint;
    return this.http.get(url, options || this.defaultRequestOptions).pipe(
      map((res: any) => {
        debugger;
        return res.result as VendingMachine;
      }),
      catchError((error: HttpErrorResponse) => throwError(error.error))
    );
  }

  public startPurchase(options?: IRequestOptions): Observable<any> {
    const url = this.SERVER_URL + this.endPoint + '/purchase';
    return this.http.get(url, options || this.defaultRequestOptions).pipe(
      map((res: any) => {
        debugger;
        return res.result as any;
      }),
      catchError((error: HttpErrorResponse) => throwError(error.error))
    );
  }

  public processPurchase(
    purchaseAction: any, options?: IRequestOptions
  ): Observable<any> {
    const url = this.SERVER_URL + this.endPoint + '/purchase';
    return this.http.post(url,purchaseAction, options || this.defaultRequestOptions).pipe(
      map((res: any) => {
        debugger;
        return res.result as any;
      }),
      catchError((error: HttpErrorResponse) => throwError(error.error))
    );
  }
}
