package store.controller;

import store.convenienceStore.Item;

public class PromotionDTO {

    private Item item;
    private int quantityToBuy;
    private int freeQuantity;

    // item에는 일반 아이템 저장됨
    protected PromotionDTO(Item item, int quantityToBuy, int freeQuantity){
        this.item = item;
        this.quantityToBuy = quantityToBuy;
        this.freeQuantity = freeQuantity;
    }

    protected Item getItem(){
        return item;
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

    public long getPriceCanApplyMembership(){
        return getTotalPrice() - getPromotionDiscount();
    }
}
