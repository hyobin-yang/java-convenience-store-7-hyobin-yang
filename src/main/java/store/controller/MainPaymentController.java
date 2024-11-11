package store.controller;

import store.convenienceStore.ChosenItem;
import store.convenienceStore.ItemParser;
import store.convenienceStore.Item;
import store.convenienceStore.ItemInventory;
import store.message.Exceptions;
import store.util.ItemTokenization;
import store.util.YesNoAnswerValidator;
import store.view.InputProvider;
import store.view.InputView;
import store.view.OutputView;

import camp.nextstep.edu.missionutils.DateTimes;

import java.util.ArrayList;
import java.util.List;

public class MainPaymentController {
    private final InputView inputView;
    private final ItemInventory itemInventory;
    private final OutputView outputView = new OutputView();

    private final PromotionController promotionController;
    private final ReceiptController receiptController;

    private List<PurchasingItem> itemToPurchaseInventory;

    public MainPaymentController(InputProvider inputProvider, ItemInventory itemInventory) {
        this.inputView = new InputView(inputProvider);
        this.itemInventory = itemInventory;
        this.promotionController = new PromotionController(inputProvider);
        this.receiptController = new ReceiptController();
    }

    public void startPaymentProcess() {
        outputView.outputWelcomeMessage();
        displayCurrentItemStock();
        inputItemsToPurchase();
        receiptController.printReceipt(itemToPurchaseInventory, itemInventory, willApplyMembershipDiscount());
        if (shouldContinuePurchasing()) {startPaymentProcess();}
    }

    private void displayCurrentItemStock() {
        outputView.outputItemStockMessage();
        for (Item item : itemInventory.getItemInventory()) {
            String promotionName = "";
            if (item.getPromotion() != null) {
                promotionName = item.getPromotion().getName();
            }
            outputView.outputItemStock(item.getItemName(), item.getItemPrice(),
                    item.getItemQuantity(), promotionName);
        }
    }

    private void inputItemsToPurchase() {
        itemToPurchaseInventory = new ArrayList<>();
        try {
            String input = inputView.inputPurchaseRequest();
            initPendingItemInventory(ItemTokenization.tokenize(input));
        } catch (IllegalArgumentException e) {
            outputView.outputExceptionMessage(Exceptions.INVALID_FORMAT.getMessage());
            startPaymentProcess();
        }
    }

    private void initPendingItemInventory(List<String> tokenizedItems) {
        List<ChosenItem> pendingItems = new ArrayList<>();
        try{
            for (String itemToken : tokenizedItems) {
                ItemParser parsedItem = new ItemParser(itemToken);
                String itemName = parsedItem.getName();
                int quantityToBuy = parsedItem.getQuantity();
                validateItemExistence(itemName);
                pendingItems.add(new ChosenItem(itemName, quantityToBuy));
            }
        } catch (IllegalArgumentException e){
            outputView.outputExceptionMessage(Exceptions.INVALID_INPUT.getMessage());
            startPaymentProcess();
        }
        getItemsAppliedPromotion(pendingItems);
    }

    private void validateItemExistence(String itemName){
        if (itemInventory.getGeneralItem(itemName) == null){
            outputView.outputExceptionMessage(Exceptions.DOES_NOT_EXIST_ITEM.getMessage());
            startPaymentProcess();
        }
    }

    private void getItemsAppliedPromotion(List<ChosenItem> pendingItems) {
        List<PurchasingItem> purchasingItems = new ArrayList<>();
        for (ChosenItem pendingItem : pendingItems) {
            if (isValidPromotionItem(pendingItem.itemName())) {
                purchasingItems.add(applyPromotion(pendingItem.itemName(), pendingItem.quantityToBuy()));
                continue;
            }
            purchasingItems.add(createStandardPurchase(pendingItem));
        }
        itemToPurchaseInventory = purchasingItems;
    }

    private boolean willApplyMembershipDiscount() {
        try {
            String answer = inputView.getAnswerToGetMembershipDiscount();
            return YesNoAnswerValidator.isPositive(answer);
        } catch (IllegalArgumentException e) {
            outputView.outputExceptionMessage(Exceptions.INVALID_INPUT.getMessage());
            return willApplyMembershipDiscount();
        }
    }

    private PurchasingItem createStandardPurchase(ChosenItem pendingItem) {
        Item generalItem = itemInventory.getGeneralItem(pendingItem.itemName());
        validateStock(generalItem.getItemQuantity(), pendingItem.quantityToBuy());
        return new PurchasingItem(generalItem, pendingItem.quantityToBuy(), 0);
    }

    private boolean isValidPromotionItem(String itemName) {
        Item promotionItem = itemInventory.getPromotionItem(itemName);
        if (promotionItem == null) {
            return false;
        }
        if (!isOngoingPromotion(promotionItem)){
            carryOverStockToGeneral(itemName);
            return false;
        }
        return true;
    }

    private void carryOverStockToGeneral(String itemName) {
        Item promotionItem = itemInventory.getPromotionItem(itemName);
        Item generalItem = itemInventory.getGeneralItem(itemName);
        generalItem.setItemQuantity(generalItem.getItemQuantity() + promotionItem.getItemQuantity());
        promotionItem.setItemQuantity(0);
    }

    private boolean isOngoingPromotion(Item promotionItem) {
        return promotionItem.getPromotion().isTodayWithinPromotionPeriod(DateTimes.now());
    }

    private PurchasingItem applyPromotion(String itemName, int quantityToBuy) {
        Item generalItem = itemInventory.getGeneralItem(itemName);
        Item promotionItem = itemInventory.getPromotionItem(itemName);
        int totalStock = generalItem.getItemQuantity() + promotionItem.getItemQuantity();
        try{
            validateStock(totalStock, quantityToBuy);
        } catch (IllegalArgumentException e){
            startPaymentProcess();
        }
        return promotionController.getPurchasingItem(generalItem, promotionItem, quantityToBuy);
    }

    private void validateStock(int currentStock, int quantityToBuy) {
        if (quantityToBuy > currentStock) {
            outputView.outputExceptionMessage(Exceptions.EXCEED_STOCK_QUANTITY.getMessage());
            throw new IllegalArgumentException();
        }
    }

    private boolean shouldContinuePurchasing() {
        try {
            String answer = inputView.getAnswerToPurchaseOtherItems();
            return YesNoAnswerValidator.isPositive(answer);
        } catch (IllegalArgumentException e) {
            outputView.outputExceptionMessage(Exceptions.INVALID_INPUT.getMessage());
            return shouldContinuePurchasing();
        }
    }
}
