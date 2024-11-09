package convenienceStoreHeadOffice;

public class SupplyingItem {

    private final String itemName;
    private final long itemPrice;
    private Promotion promotion = null;

    public SupplyingItem(final String itemName, final long itemPrice){
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public long getItemPrice() {
        return itemPrice;
    }

    public void setPromotion(Promotion promotion){
        this.promotion = promotion;
    }

    public Promotion getPromotion(){
        return promotion;
    }

    public void initializePromotion(){
        this.promotion = null;
    }

}
