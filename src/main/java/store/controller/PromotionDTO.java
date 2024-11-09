package store.controller;

public class PromotionDTO {

    private int quantityToBuy;
    private int freeQuantity;

    public PromotionDTO(int quantityToBuy, int freeQuantity){
        this.quantityToBuy = quantityToBuy;
        this.freeQuantity = freeQuantity;
    }

    public void setFreeQuantity(int freeQuantity) {
        this.freeQuantity = freeQuantity;
    }

    public void setQuantityToBuy(int quantityToBuy) {
        this.quantityToBuy = quantityToBuy;
    }

    public int getQuantityToBuy(){
        return quantityToBuy;
    }

    public int getFreeQuantity(){
        return freeQuantity;
    }
}
