package store.convenienceStoreHeadOffice;

import java.util.ArrayList;
import java.util.List;

public class HeadOffice {
    private final List<SupplyingItem> supplyingItems = new ArrayList<>();
    private final List<Promotion> ongoingPromotions = new ArrayList<>();

    public void addSupplyingItem(SupplyingItem item){
        supplyingItems.add(item);
    }

    public void addOngoingPromotion(Promotion promotion){
        ongoingPromotions.add(promotion);
    }

    public Promotion getPromotion(String promotionName){
        for (Promotion promotion : ongoingPromotions) {
            if (promotion.getName().equals(promotionName)){
                return promotion;
            }
        }
        return null;
    }
}
