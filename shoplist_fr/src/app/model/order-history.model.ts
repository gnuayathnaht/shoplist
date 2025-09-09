
import { OrderItem } from './order-item.model';
import { User } from './user.model';

export interface OrderHistory {
  id: number;
  userId: number;
  total: number;
  orderDateTime: string;
  items: OrderItem[];
  user: User;
}
