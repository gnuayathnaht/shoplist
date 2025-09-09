import { OrderItem } from "./order-item.model";

export interface OrderDetail{
orderId : number;
dateTime : string;
items : OrderItem[];
total : number;
}
