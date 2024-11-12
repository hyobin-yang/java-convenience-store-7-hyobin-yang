package store.convenienceStore;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.message.Exceptions;

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
                .hasMessage(Exceptions.INVALID_INPUT.getMessage());
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
                .hasMessage(Exceptions.INVALID_INPUT.getMessage());
    }

}
