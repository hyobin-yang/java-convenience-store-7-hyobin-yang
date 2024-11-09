package store.convenienceStore;

import convenienceStore.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class ItemTest {

    @Test
    @DisplayName("상품의 가격이 0 이하일 때 예외가 발생한다.")
    void throwExceptionWhenPriceIsBelowZero(){
        //given
        String name  = "콜라";
        long price = -1;
        int quantity = 10;

        //when&then
        assertThatThrownBy(() -> new Item(name, price, quantity))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 가격 형식");
    }

    @Test
    @DisplayName("상품의 수량이 0 이하일 때 예외가 발생한다.")
    void throwExceptionWhenQuantityIsBelowZero(){
        //given
        String name  = "콜라";
        long price = 1_000;
        int quantity = -1;

        //when&then
        assertThatThrownBy(() -> new Item(name, price, quantity))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 수량 형식");
    }

}
