import { CanDeactivateFn } from '@angular/router';
import { Observable } from 'rxjs';

// export const unsavedChangesGuard: CanDeactivateFn<unknown> = (component, currentRoute, currentState, nextState) => {
//   return true;
// };

export interface CanComponentDeactivate {
  canDeactivate?: () => boolean | Observable<boolean>;
}

// Functional guard
export const unsavedChangesGuard: CanDeactivateFn<CanComponentDeactivate> = (component) => {
  if (component.canDeactivate) {
    return component.canDeactivate();
  }
  return true; 
};

