package store.convenienceStore;

import store.message.Exceptions;
import store.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class ItemInventory {

    private final List<Item> itemInventory;
    OutputView outputView = new OutputView();

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
                outputView.outputExceptionMessage(Exceptions.ALREADY_EXIST_ITEM.getMessage());
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















