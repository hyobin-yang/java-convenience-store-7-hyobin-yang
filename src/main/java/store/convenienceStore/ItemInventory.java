package store.convenienceStore;

import java.util.ArrayList;
import java.util.List;

public class ItemInventory {

    private final List<Item> itemInventory;

    public ItemInventory(){
        this.itemInventory = new ArrayList<>();
    }

    public void addItem(Item newItem){
        validateItemDuplication(newItem);
        itemInventory.add(newItem);
    }

    public List<Item> getItemInventory(){
        return itemInventory;
    }

    private void validateItemDuplication(Item item){
        for (Item addedItem : itemInventory){
            if (isSameItem(addedItem, item)){
                throw new IllegalArgumentException("[ERROR]: 이미 존재하는 상품입니다.");
            }
        }
    }

    public void deductItemQuantity(Item targetItem, int quantityToDeduct){
        for (Item item : itemInventory){
            if (isSameItem(item, targetItem)){
                item.updateItemQuantity(item.getItemQuantity() - quantityToDeduct);
            }
        }
    }

    public boolean isSameItem(Item firstItem, Item secondItem){
        if (!firstItem.getItemName().equals(secondItem.getItemName())){
            return false;
        }
        return ((firstItem.getPromotion() == null && secondItem.getPromotion() == null)
                || (firstItem.getPromotion() != null && secondItem.getPromotion() != null));
    }

    public Item getGeneralItem(String itemName){
        for (Item item : itemInventory){
            if (item.getItemName().equals(itemName) && item.getPromotion() == null){
                return item;
            }
        }
        return null;
    }

    public Item getPromotionItem(String itemName){
        for (Item item : itemInventory){
            if (item.getItemName().equals(itemName) && item.getPromotion() != null){
                return item;
            }
        }
        return null;
    }
}















