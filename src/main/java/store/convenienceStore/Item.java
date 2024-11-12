package store.convenienceStore;

import store.convenienceStoreHeadOffice.SupplyingItem;
import store.message.Exceptions;

public class Item extends SupplyingItem {

    private int itemQuantity;

    public Item(String name, long price, int quantity) {
        super(name, price);
        validatePrice(price);
        validateQuantity(quantity);
        this.itemQuantity = quantity;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int quantity){
        itemQuantity = quantity;
    }

    public void updateItemQuantity(int updatedQuantity){
        this.itemQuantity = updatedQuantity;
    }

    private void validatePrice(long price){
        if (price < 0){
            throw new IllegalArgumentException(Exceptions.INVALID_INPUT.getMessage());
        }
    }

    private void validateQuantity(int quantity){
        if (quantity < 0){
            throw new IllegalArgumentException(Exceptions.INVALID_INPUT.getMessage());
        }
    }

}


