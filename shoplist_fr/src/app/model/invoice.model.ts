import { OrderItem } from "./order-item.model";

export interface Invoice {
  orderId: number;
  userName: string;
  userEmail: string;
  items : OrderItem[];
  total: number;
}
