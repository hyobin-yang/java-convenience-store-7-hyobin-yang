package store.convenienceStoreHeadOffice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.message.Exceptions;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class MembershipPolicyTest {

    private final MembershipPolicy membershipPolicy = MembershipPolicy.MEMBERSHIP_POLICY;

    @Test
    @DisplayName("입력된 가격에 30%의 멤버십 할인이 적용되어 할인되는 금액을 잘 반환하는지 확인한다.")
    void checkDiscountPrice(){
        //given
        long price = 10_000;
        int expectedDiscountPrice = 3_000;

        //when
        long discountPrice = membershipPolicy.discountPrice(price);

        // then
        assertThat(discountPrice).isEqualTo(expectedDiscountPrice);
    }

    @Test
    @DisplayName("30% 할인을 적용한 할인 금액이 최대 할인 가능 금액인 8,000원 이상일 때 최종 할인 금액이 8,000인지 확인한다.")
    void checkMaximumDiscountPrice(){
        //given
        int price = 30_000;
        int expectedDiscountedPrice = 8_000;

        //when
        long discountedPrice = membershipPolicy.discountPrice(price);

        // then
        assertThat(discountedPrice).isEqualTo(expectedDiscountedPrice);
    }

    @Test
    @DisplayName("입력된 가격이 0인 경우 0을 그대로 반환한다.")
    void shouldReturnZeroWhenPriceIsZero(){
        //given
        int price = 0;
        int expectedDiscountedPrice = 0;

        //when
        long discountedPrice = membershipPolicy.discountPrice(price);

        // then
        assertThat(discountedPrice).isEqualTo(expectedDiscountedPrice);
    }

    @Test
    @DisplayName("파라미터로 받은 가격이 0 이하일 때 예외를 발생시킨다.")
    void checkPriceIsNotBelowZero(){
        assertSimpleTest(() ->
                assertThatThrownBy(() -> membershipPolicy.discountPrice(-1))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage(Exceptions.INVALID_INPUT.getMessage())
        );

    }

}
