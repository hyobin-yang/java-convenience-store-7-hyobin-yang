package store.controller;

import store.convenienceStore.Item;
import store.message.Exceptions;
import store.util.YesNoAnswer;

import store.view.InputProvider;
import store.view.InputView;
import store.view.OutputView;

public class PromotionController {
    private final InputView inputView;
    private final OutputView outputView = new OutputView();
    private final YesNoAnswer validator = new YesNoAnswer();
    private int countToBuyForPromotion;
    private int promotionThreshold;
    private int quantityToBuy;

    public PromotionController(InputProvider inputProvider, InputView inputView){
        this.inputView = new InputView(inputProvider);
    }

    public PromotionDTO getPromotionDTO( Item generalItem, Item promotionItem, int quantityToBuy) {
        int totalStock = calculateTotalStock(generalItem, promotionItem);
        this.countToBuyForPromotion = promotionItem.getPromotion().getNumberOfItemToBuy();
        this.promotionThreshold = countToBuyForPromotion + promotionItem.getPromotion().getNumberOfItemToGet();
        this.quantityToBuy = quantityToBuy;

        if (canAddFreePromotionItem(promotionItem.getItemQuantity())) {
            handleAdditionalItemOption(generalItem.getItemName());
        }

        int freeItemQuantity = getFreeItemQuantity(promotionItem.getItemQuantity(), generalItem.getItemName());
        return new PromotionDTO(generalItem, this.quantityToBuy, freeItemQuantity);
    }

    private int calculateTotalStock(Item generalItem, Item promotionItem) {
        return generalItem.getItemQuantity() + promotionItem.getItemQuantity();
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
            validator.validate(answer);
            return validator.isPositive(answer);
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
            validator.validate(answer);
            return validator.isPositive(answer);
        } catch (IllegalArgumentException e) {
            outputView.outputExceptionMessage(Exceptions.INVALID_INPUT.getMessage());
            return askToProceedWithoutPromotion(itemName, quantity);
        }
    }

}
