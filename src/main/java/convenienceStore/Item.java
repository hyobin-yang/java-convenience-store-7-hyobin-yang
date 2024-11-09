package convenienceStore;

import convenienceStoreHeadOffice.SupplyingItem;

public class Item extends SupplyingItem {

    private int quantity;

    public Item(String name, long price, int quantity) {
        super(name, price);
        validatePrice(price);
        validateQuantity(quantity);
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    private void validatePrice(long price){
        if (price < 0){
            throw new IllegalArgumentException("잘못된 가격 형식");
        }
    }

    private void validateQuantity(int quantity){
        if (quantity < 0){
            throw new IllegalArgumentException("잘못된 수량 형식");
        }
    }

}

