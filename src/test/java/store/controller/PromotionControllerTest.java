package store.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.convenienceStore.Item;
import store.convenienceStoreHeadOffice.Promotion;
import store.view.MockInputProvider;

import java.time.LocalDateTime;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class PromotionControllerTest {
    private PromotionController promotionController;
    private static final LocalDateTime PROMOTION_START_DATE = LocalDateTime.of(2024, 11, 9, 0, 0);
    private static final LocalDateTime PROMOTION_END_DATE = LocalDateTime.of(2024, 12, 9, 0, 0);

    @Test
    @DisplayName("프로모션 적용 상품을 구매할 때 최종 구매 수량과 무료 증정 개수를 정상적으로 반환하는지 확인한다.")
    void verifyFinalPurchaseQuantityAndFreeItemCountForPromotion(){
        //given
        promotionController = new PromotionController(new MockInputProvider("Y"));
        Item generalItem = new Item("콜라", 1000, 10);
        Item promotionItem = new Item("콜라", 1000, 7);
        Promotion promotion = new Promotion("탄산2+1", 2, 1, PROMOTION_START_DATE, PROMOTION_END_DATE);
        promotionItem.setPromotion(promotion);

        //when
        PromotionDTO expected = new PromotionDTO(10, 2);
        PromotionDTO dto = promotionController.getFreeItemCount(generalItem, promotionItem, 10);

        //then
        assertThat(dto.getFreeQuantity()).isEqualTo(expected.getFreeQuantity());
        assertThat(dto.getQuantityToBuy()).isEqualTo(expected.getQuantityToBuy());
    }

    @Test
    @DisplayName("프로모션 수량이 부족하더라도 일반 수량으로 구매할 수 있음을 확인한다.")
    void verifyPurchaseQuantityAndFreeItemCount(){
        //given
        promotionController = new PromotionController(new MockInputProvider("Y"));
        Item generalItem = new Item("콜라", 1000, 10);
        Item promotionItem = new Item("콜라", 1000, 0);
        Promotion promotion = new Promotion("탄산2+1", 2, 1, PROMOTION_START_DATE, PROMOTION_END_DATE);
        promotionItem.setPromotion(promotion);

        //when
        PromotionDTO expected = new PromotionDTO(10, 0);
        PromotionDTO dto = promotionController.getFreeItemCount(generalItem, promotionItem, 10);

        //then
        assertThat(dto.getFreeQuantity()).isEqualTo(expected.getFreeQuantity());
        assertThat(dto.getQuantityToBuy()).isEqualTo(expected.getQuantityToBuy());
    }

    @Test
    @DisplayName("무료 증정 상품을 하나 더 가져올 경우 최종 구매 수량이 1 증가하는지 확인한다.")
    void verifyPurchaseQuantityIncreasesWhenAddingFreeItem(){
        //given
        promotionController = new PromotionController(new MockInputProvider("y"));
        Item generalItem = new Item("콜라", 1000, 10);
        Item promotionItem = new Item("콜라", 1000, 10);
        Promotion promotion = new Promotion("탄산2+1", 2, 1, PROMOTION_START_DATE, PROMOTION_END_DATE);
        promotionItem.setPromotion(promotion);

        //when
        PromotionDTO expected = new PromotionDTO(9, 3);
        PromotionDTO dto = promotionController.getFreeItemCount(generalItem, promotionItem, 8);

        //then
        assertThat(dto.getFreeQuantity()).isEqualTo(expected.getFreeQuantity());
        assertThat(dto.getQuantityToBuy()).isEqualTo(expected.getQuantityToBuy());
    }

    @Test
    @DisplayName("구매하고자 하는 상품의 수량이 부족한 경우 예외를 발생시킨다.")
    void verifyExceptionThrownWhenInsufficientStockForPurchase(){
        //given
        promotionController = new PromotionController(new MockInputProvider("y"));
        Item generalItem = new Item("콜라", 1000, 1);
        Item promotionItem = new Item("콜라", 1000, 1);
        Promotion promotion = new Promotion("탄산2+1", 2, 1, PROMOTION_START_DATE, PROMOTION_END_DATE);
        promotionItem.setPromotion(promotion);

        assertSimpleTest(() ->
                assertThatThrownBy(() -> promotionController.getFreeItemCount(generalItem, promotionItem, 10))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage(Exceptions.INSUFFICIENT_STOCK_QUANTITY.getMessage())
        );
    }

    @Test
    @DisplayName("수량 부족으로 프로모션이 적용되지 않는 경우 구매를 거절할 때 예외를 발생시킨다.")
    void verifyExceptionThrownWhenCancelPurchase(){
        //given
        promotionController = new PromotionController(new MockInputProvider("N"));
        Item generalItem = new Item("콜라", 1000, 10);
        Item promotionItem = new Item("콜라", 1000, 5);
        Promotion promotion = new Promotion("탄산2+1", 2, 1, PROMOTION_START_DATE, PROMOTION_END_DATE);
        promotionItem.setPromotion(promotion);

        assertSimpleTest(() ->
                assertThatThrownBy(() -> promotionController.getFreeItemCount(generalItem, promotionItem, 10))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage(Exceptions.CANCEL_PURCHASE.getMessage())
        );
    }

}
