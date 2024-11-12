package store.controller;

import store.convenienceStore.Item;
import store.message.Exceptions;
import store.util.YesNoAnswerValidator;

import store.view.InputProvider;
import store.view.InputView;
import store.view.OutputView;

public class PromotionController {
    private final InputView inputView;
    private final OutputView outputView = new OutputView();
    private int countToBuyForPromotion;
    private int promotionThreshold;
    private int quantityToBuy;

    public PromotionController(InputProvider inputProvider){
        this.inputView = new InputView(inputProvider);
    }

    public PurchasingItem getPurchasingItem(Item generalItem, Item promotionItem, int quantityToBuy) {
        countToBuyForPromotion = promotionItem.getPromotion().getNumberOfItemToBuy();
        promotionThreshold = countToBuyForPromotion + promotionItem.getPromotion().getNumberOfItemToGet();
        this.quantityToBuy = quantityToBuy;
        if (canAddFreePromotionItem(promotionItem.getItemQuantity())) {
            handleAdditionalItemOption(generalItem.getItemName());
        }
        int freeItemQuantity = getFreeItemQuantity(promotionItem.getItemQuantity(), generalItem.getItemName());
        return new PurchasingItem(promotionItem, this.quantityToBuy, freeItemQuantity);
    }

    private boolean canAddFreePromotionItem(int promotionQuantity) {
        return ( (this.quantityToBuy + 1) % promotionThreshold == 0
                && promotionQuantity >= this.quantityToBuy + 1);
    }

    private void handleAdditionalItemOption(String itemName) {
        if (askCustomerToAddItem(itemName, 1)) this.quantityToBuy += 1;
    }

    private boolean askCustomerToAddItem(String itemName, int additionalQuantity) {
        try {
            String answer = inputView.getAnswerToBringMoreItem(itemName, additionalQuantity);
            return YesNoAnswerValidator.isPositive(answer);
        } catch (IllegalArgumentException e) {
            outputView.outputExceptionMessage(Exceptions.INVALID_INPUT.getMessage());
            return askCustomerToAddItem(itemName, additionalQuantity);
        }
    }

    private int getFreeItemQuantity(int promotionQuantity, String itemName) {
        int freeItemQuantity = (this.quantityToBuy / (countToBuyForPromotion + 1));
        if (hasEnoughPromotionStock(freeItemQuantity, promotionQuantity)) {
            return freeItemQuantity;
        }
        return handleInsufficientPromotionStock(promotionQuantity, itemName);
    }

    private boolean hasEnoughPromotionStock(int freeItemQuantity, int promotionQuantity) {
        return (promotionQuantity >= freeItemQuantity * promotionThreshold);
    }

    private int handleInsufficientPromotionStock(int promotionQuantity, String itemName) {
        int freeItemQuantity = promotionQuantity / promotionThreshold;
        int remainingQuantity = this.quantityToBuy - freeItemQuantity * promotionThreshold;
        boolean willProceedWithoutPromotion = askToProceedWithoutPromotion(itemName, remainingQuantity);
        if (!willProceedWithoutPromotion) {
            this.quantityToBuy -= remainingQuantity;
            freeItemQuantity = 0;
        }
        return freeItemQuantity;
    }

    private boolean askToProceedWithoutPromotion(String itemName, int quantity) {
        try {
            String answer = inputView.getAnswerToContinueBuying(itemName, quantity);
            return YesNoAnswerValidator.isPositive(answer);
        } catch (IllegalArgumentException e) {
            outputView.outputExceptionMessage(Exceptions.INVALID_INPUT.getMessage());
            return askToProceedWithoutPromotion(itemName, quantity);
        }
    }

}
