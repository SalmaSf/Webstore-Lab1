package org.example.webstore.bo;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private List<CartItem> items = new ArrayList<>();

    public void addItem(Item item) {
        for (CartItem cartItem : items) {
            if (cartItem.getItem().getId() == item.getId()) {
                cartItem.increaseQuantity();
                return;
            }
        }
        items.add(new CartItem(item));
    }

    public List<CartItem> getItems() {
        return items;
    }

    public double getTotalPrice() {
        return items.stream().mapToDouble(CartItem::getTotalPrice).sum();
    }

    public void clear() {
        items.clear();
    }
    public int getItemCount() {
        return items.stream().mapToInt(CartItem::getQuantity).sum();
    }

}

