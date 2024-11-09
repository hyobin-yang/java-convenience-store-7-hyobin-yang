package convenienceStoreHeadOffice;

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

    //TODO: 아이템 삭제, 수정 로직
}
