package store.controller;

import store.convenienceStore.Item;
import store.util.YesNoAnswerValidator;

import store.view.InputProvider;
import store.view.InputView;

public class PromotionController {
    InputView inputView;
    YesNoAnswerValidator validator = new YesNoAnswerValidator();

    public PromotionController(InputProvider inputProvider){
        this.inputView = new InputView(inputProvider);
    }

    public PromotionDTO getFreeItemCount(Item generalItem, Item promotionItem, int quantityToBuy){
        if (promotionItem == null){
            return new PromotionDTO(quantityToBuy, 0);
        }

        int updatedQuantityToBuy = quantityToBuy;
        int generalQuantity = generalItem.getItemQuantity();
        int promotionQuantity = promotionItem.getItemQuantity();
        int totalQuantity = generalQuantity + promotionQuantity;
        int countToBuyForPromotion = promotionItem.getPromotion().getNumberOfItemToBuy();
        String itemName = generalItem.getItemName();

        // 먼저 프로모션과 일반 재고 합친 수가 구매 수량보다 많거나 같은지 확인
        validateEnoughQuantity(totalQuantity, quantityToBuy);

        // 재고는 충분히 있을 때 진행
        if (canGetOneFree(quantityToBuy, countToBuyForPromotion)){ // 무료 증정 받을 수 있을 때
            if (getAnswerToBringMoreItem(inputView.getAnswerToBringMoreItem(itemName,1))){ // 하나 더 가져올 건지 물어본다
                updatedQuantityToBuy += 1; // 가져오면 총 수량 증가시킨다
                validateEnoughQuantity(totalQuantity, updatedQuantityToBuy); // 다시 수량 충분한지 확인한다 - 불충분 시 예외
            }
        }

        int freeQuantity = getQuantityPromotionApplied(updatedQuantityToBuy, promotionQuantity, countToBuyForPromotion, itemName);
        return new PromotionDTO(updatedQuantityToBuy, freeQuantity); // 증정 개수
    }

    // 전체 수량과 구매 수량 비교 - 전체 수량 부족 시 예외
    private void validateEnoughQuantity(int totalQuantity, int quantityToBuy){
        if (quantityToBuy > totalQuantity){
            throw new IllegalArgumentException(Exceptions.INSUFFICIENT_STOCK_QUANTITY.getMessage());
        }
    }

    // 하나 무료로 증정받을 수 있는지 확인
    private boolean canGetOneFree(int quantityToBuy, int countToBuyForPromotion){
        return (quantityToBuy % (countToBuyForPromotion+1) == countToBuyForPromotion);
    }

    private int getQuantityPromotionApplied(int quantityToBuy, int promotionQuantity, int countToBuyForPromotion, String itemName){
        // 받아야 하는 무료 증정 개수
        int freeQuantity = (quantityToBuy / (countToBuyForPromotion+1));

        // 그런데 무료 증정 개수까지 해서 프로모션 수량이 충분한가
        if (hasEnoughPromotionQuantity(freeQuantity, promotionQuantity, countToBuyForPromotion)){
            return freeQuantity;
        }

        // 만약 프로모션 수량 부족할 때 - 프로모션 수량에 맞춰 받을 수 있는 무료 증정 개수를 구한다
        freeQuantity = promotionQuantity/(countToBuyForPromotion+1);

        // 프로모션 적용 없이 구매해야 하는 상품 개수
        int quantityCanNotAppliedPromotion = quantityToBuy - freeQuantity*(countToBuyForPromotion+1);

        // 근데 이때는 메시지 출력해야 함 - 프로모션 적용 안 되는데도 결제할 거냐고
        String answer = inputView.getAnswerToContinueBuying(itemName, quantityCanNotAppliedPromotion);
        if (getAnswerToBringMoreItem(answer)){ // 구매한다 답했을 때
            return freeQuantity; // 반환
        }
        throw new IllegalArgumentException(Exceptions.CANCEL_PURCHASE.getMessage()); // 구매 안 한다고 했을 때 -> 처음부터 다시 입력하셈
    }

    private boolean getAnswerToBringMoreItem(String answer){
        validator.validateAnswer(answer);
        return validator.isPositiveAnswer(answer);
    }

    private boolean hasEnoughPromotionQuantity(int freeQuantity, int promotionQuantity, int countToBuyForPromotion){
        return (promotionQuantity >= freeQuantity*(countToBuyForPromotion+1));
    }


}
