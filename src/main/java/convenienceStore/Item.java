package convenienceStore;

import convenienceStoreHeadOffice.SupplyingItem;

public class Item extends SupplyingItem {

    private int quantity;

    public Item(String itemName, long itemPrice, int quantity) {
        super(itemName, itemPrice);
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

}

