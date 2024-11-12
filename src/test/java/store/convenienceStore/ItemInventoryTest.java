package store.convenienceStore;

import store.convenienceStoreHeadOffice.Promotion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.message.Exceptions;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class ItemInventoryTest {
    private ItemInventory itemInventory;
    private static final LocalDateTime PROMOTION_START_DATE = LocalDateTime.of(2024, 11, 9, 0, 0);
    private static final LocalDateTime PROMOTION_END_DATE = LocalDateTime.of(2024, 12, 9, 0, 0);

    @BeforeEach
    void initializeItemInventory(){
        itemInventory = new ItemInventory();
    }

    @Test
    @DisplayName("아이템 인벤토리에 아이템이 정상적으로 추가되는지 확인한다.")
    void addItemCorrectly(){
        //given
        Item item = new Item("콜라", 1000, 10);

        //when
        itemInventory.addItem(item);
        List<Item> updatedItemInventory = itemInventory.getItemInventory();
        boolean hasGivenItem = updatedItemInventory.contains(item);

        //then
        assertThat(hasGivenItem).isTrue();
    }

    @Test
    @DisplayName("서로 다른 상품을 아이템 인벤토리에 추가하려는 경우는 정상 작동한다.")
    void addItemsCorrectly(){
        //given
        Item item1 = new Item("콜라", 1000, 10);
        Item item2 = new Item("사이다", 1000, 10);

        //when&then
        itemInventory.addItem(item1);
        itemInventory.addItem(item2);
    }

    @Test
    @DisplayName("동일한 상품을 아이템 인벤토리에 추가하려는 경우에 예외가 발생한다.")
    void throwExceptionWhenTryToDuplicatedItem(){
        //given
        Item item1 = new Item("콜라", 1000, 10);
        Item item2 = new Item("콜라", 1000, 10);

        //when
        itemInventory.addItem(item1);

        //then
        assertThatThrownBy(() -> itemInventory.addItem(item2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Exceptions.ALREADY_EXIST_ITEM.getMessage());
    }

    @Test
    @DisplayName("상품명이 동일하더라도 프로모션 정책이 다르면 인벤토리에 정상적으로 추가된다")
    void addItemsCorrectlyWhenPromotionIsDifferent(){
        //given
        Item item1 = new Item("콜라", 1000, 10);
        Item item2 = new Item("콜라", 1000, 10);

        //when
        Promotion promotion = new Promotion("반짝할인", 1, 1, PROMOTION_START_DATE, PROMOTION_END_DATE);
        item1.setPromotion(promotion);

        //then
        itemInventory.addItem(item1);
        itemInventory.addItem(item2);
    }

    @Test
    @DisplayName("동일한 상품명에 동일한 프로모션을 적용한 아이템은 추가할 수 없다")
    void throwExceptionWhenTryToDuplicatedPromotionItem(){
        //given
        Item item1 = new Item("콜라", 1000, 10);
        Item item2 = new Item("콜라", 1000, 10);

        //when
        Promotion promotion = new Promotion("반짝할인", 1, 1, PROMOTION_START_DATE, PROMOTION_END_DATE);
        item1.setPromotion(promotion);
        item2.setPromotion(promotion);

        itemInventory.addItem(item1);

        //then
        assertThatThrownBy(() -> itemInventory.addItem(item2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Exceptions.ALREADY_EXIST_ITEM.getMessage());
    }

    @Test
    @DisplayName("각 상품은 단 하나의 프로모션만 적용될 수 있다.")
    void throwExceptionWhenAppliedPromotionIsOverOne(){
        //given
        Item item1 = new Item("콜라", 1000, 10);
        Item item2 = new Item("콜라", 1000, 10);

        //when
        Promotion promotion1 = new Promotion("반짝할인", 1, 1, PROMOTION_START_DATE, PROMOTION_END_DATE);
        Promotion promotion2 = new Promotion("MD추천상품", 2, 1, PROMOTION_START_DATE, PROMOTION_END_DATE);

        item1.setPromotion(promotion1);
        item2.setPromotion(promotion2);

        itemInventory.addItem(item1);

        //then
        assertThatThrownBy(() -> itemInventory.addItem(item2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Exceptions.ALREADY_EXIST_ITEM.getMessage());
    }

    @Test
    @DisplayName("주어진 두 상품의 이름과 적용 프로모션이 같다면 true를 반환한다.")
    void returnTrueIfItemsAreSame1(){
        //given
        Item item1 = new Item("콜라", 1000, 10);
        Item item2 = new Item("콜라", 1000, 10);

        //when
        boolean isSame = itemInventory.isSameItem(item1, item2);

        //then
        assertThat(isSame).isTrue();
    }

    @Test
    @DisplayName("주어진 두 상품의 이름이 같아도 프로모션 적용 여부가 다르다면 false를 반환한다.")
    void returnFalseIfItemsAreDifferent(){
        //given
        Item item1 = new Item("콜라", 1000, 10);
        Item item2 = new Item("콜라", 1000, 10);

        //when
        Promotion promotion = new Promotion("반짝할인", 1, 1, PROMOTION_START_DATE, PROMOTION_END_DATE);
        item1.setPromotion(promotion);

        itemInventory.addItem(item1);
        itemInventory.addItem(item2);

        boolean isSame = itemInventory.isSameItem(item1, item2);

        //then
        assertThat(isSame).isFalse();
    }

}
