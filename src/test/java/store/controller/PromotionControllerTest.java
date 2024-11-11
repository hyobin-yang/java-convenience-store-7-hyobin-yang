package store.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.convenienceStore.Item;
import store.convenienceStoreHeadOffice.Promotion;
import store.view.MockInputProvider;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class PromotionControllerTest {
    private PromotionController promotionController;
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
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
        PurchasingItem expected = new PurchasingItem(promotionItem, 10, 2);
        PurchasingItem dto = promotionController.getPurchasingItem(generalItem, promotionItem, 10);

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
        PurchasingItem expected = new PurchasingItem(promotionItem, 10, 0);
        PurchasingItem dto = promotionController.getPurchasingItem(generalItem, promotionItem, 10);

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
        PurchasingItem expected = new PurchasingItem(promotionItem, 9, 3);
        PurchasingItem dto = promotionController.getPurchasingItem(generalItem, promotionItem, 8);

        //then
        assertThat(dto.getFreeQuantity()).isEqualTo(expected.getFreeQuantity());
        assertThat(dto.getQuantityToBuy()).isEqualTo(expected.getQuantityToBuy());
    }

    @Test
    @DisplayName("프로모션 할인이 적용되지 않을 경우 안내 메시지를 출력한다.")
    void shouldDisplayMessageWhenPromotionNotApplied(){
        //given & when
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        promotionController = new PromotionController(new MockInputProvider("n"));

        Item generalItem = new Item("콜라", 1000, 1);
        Item promotionItem = new Item("콜라", 1000, 1);
        Promotion promotion = new Promotion("탄산2+1", 2, 1, PROMOTION_START_DATE, PROMOTION_END_DATE);

        promotionItem.setPromotion(promotion);
        promotionController.getPurchasingItem(generalItem, promotionItem, 10);

        //then
        assertThat(outContent.toString().trim()).isEqualTo("현재 콜라 10개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)");
        System.setOut(originalOut);
    }

}
