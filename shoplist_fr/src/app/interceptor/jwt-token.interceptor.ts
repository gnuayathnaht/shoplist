import { HttpHandler, HttpInterceptor, HttpInterceptorFn, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthServiceService } from '../services/auth-service.service';



@Injectable()
export class JwtInterceptor implements HttpInterceptor {
  constructor(private authService: AuthServiceService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    const token = this.authService.getToken();
    if (token) {
      const cloned = req.clone({
        setHeaders: { Authorization: `Bearer ${token}` }
      });
      return next.handle(cloned);
    }
    return next.handle(req);
  }
}