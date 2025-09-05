import { CartItem } from "./cart-item.model"

export interface Order {
    userId: number,
    cartId: number,
    cartItems: CartItem[],
    total: number,
    payment:string
}
