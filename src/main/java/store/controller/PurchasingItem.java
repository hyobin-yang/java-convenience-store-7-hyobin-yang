package store.controller;

import store.convenienceStore.Item;
import store.convenienceStore.ItemInventory;

public class PurchasingItem {
    private Item item;
    private int quantityToBuy;
    private int freeQuantity;

    protected PurchasingItem(Item item, int quantityToBuy, int freeQuantity){
        this.item = item;
        this.quantityToBuy = quantityToBuy;
        this.freeQuantity = freeQuantity;
    }

    protected String getItemName(){
        return item.getItemName();
    }

    protected int getQuantityToBuy(){
        return quantityToBuy;
    }

    protected int getFreeQuantity(){
        return freeQuantity;
    }

    public long getTotalPrice(){
        return item.getItemPrice() * quantityToBuy;
    }

    public long getPromotionDiscount(){
        return freeQuantity * item.getItemPrice();
    }

    public long getTotalPriceAppliedPromotion(){
        if (item.getPromotion() != null){
            return freeQuantity
                    * item.getItemPrice()
                    * (item.getPromotion().getNumberOfItemToBuy() + item.getPromotion().getNumberOfItemToGet());
        }
        return 0;
    }
}
