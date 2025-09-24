
import { OrderItem } from './order-item.model';
import { User } from './user.model';

export interface OrderHistory {
  orderId: number;
  total: number;
  orderDateTime: string;
  items: OrderItem[];
  user: User;
  
}
