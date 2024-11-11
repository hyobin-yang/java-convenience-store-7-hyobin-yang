package store.convenienceStore;

public class PendingItem {

    private String itemName;
    private int quantityToBuy;

    public PendingItem(String itemName, int quantityToBuy){
        this.itemName = itemName;
        this.quantityToBuy = quantityToBuy;
    }

    public String getItemName(){
        return itemName;
    }

    public int getQuantityToBuy(){
        return quantityToBuy;
    }

}
