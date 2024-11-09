package convenienceStore;

import convenienceStoreHeadOffice.Promotion;

import java.util.ArrayList;
import java.util.List;

public class ItemInventory {

    private final List<Item> itemInventory;

    public ItemInventory(){
        this.itemInventory = new ArrayList<>();
    }

    public void addItem(Item item){
        validateItemDuplication(item);
        itemInventory.add(item);
    }

    public List<Item> getItemInventory(){
        return itemInventory;
    }

    private void validateItemDuplication(Item item){
        for (Item addedItem : itemInventory){
            if (addedItem.getItemName().equals(item.getItemName()) &&
                    isDuplicatedPromotion(addedItem.getPromotion(), item.getPromotion())){
                throw new IllegalArgumentException("이미 존재하는 상품");
            }
        }
    }

    private boolean isDuplicatedPromotion(Promotion addedItemPromotion, Promotion itemPromotion){
        return ((addedItemPromotion == null && itemPromotion == null) ||
                (addedItemPromotion != null && itemPromotion != null));
    }
}
