import { HttpClient, HttpErrorResponse, HttpHeaders, HttpParams, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';


export interface IRequestOptions {
    headers?: HttpHeaders | {
        [header: string]: string | string[];
    };
    params?: HttpParams | {
        [param: string]: string | string[];
    };
    reportProgress?: boolean;
    responseType?: 'json';
    withCredentials?: boolean;
    observe?: any;
    body?: any;
}

export interface IBaseEntity {
    id?: number | string;
}
@Injectable({
    providedIn: 'root'
})
export class BaseHttpService<Entity extends IBaseEntity> {

    protected defaultRequestOptions: IRequestOptions;
    protected SERVER_URL: string;
    protected endPoint: string;

    constructor(public http: HttpClient) {
        this.SERVER_URL = 'http://localhost:8080/api/v0/';
        this.defaultRequestOptions = {
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Headers': '*',
                'Access-Control-Allow-Origin': '*'
            }
        };
    }

    public getSingle(id: string | number, options?: IRequestOptions): Observable<Entity> {
        const url = this.SERVER_URL + this.endPoint + '/' + id;
        return this.http.get(url, options || this.defaultRequestOptions).pipe(
            map(
                (res: HttpResponse<any>) => res.body.result as Entity
            ),
            catchError(
                (error: HttpErrorResponse) => throwError(error.error)
            )
        );
    }

    public getList(options?: IRequestOptions): Observable<Entity[]> {
        const url = this.SERVER_URL + this.endPoint;
        return this.http.get<Entity[]>(url, options || this.defaultRequestOptions).pipe(
            catchError(
                (error: HttpErrorResponse) => throwError(error.error)
            )
        );
    }
}
