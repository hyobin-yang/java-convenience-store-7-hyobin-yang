package store.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import util.NumberConverter;


import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class NumberConverterTest {

    private final NumberConverter converter = new NumberConverter();

    @Test
    @DisplayName("입력된 문자열을 정수로 정상적으로 반환하는지 확인한다.")
    void convertStringToIntegerCorrectly(){
        //given
        String input = "1";
        int expected = 1;

        //when
        int convertedNumber = converter.convertToNumber(input);

        //then
        assertThat(convertedNumber).isEqualTo(expected);
    }

    @Test
    @DisplayName("입력된 문자열을 정수로 정상적으로 반환하는지 확인한다.")
    void convertStringToIntegerCorrectly2(){
        //given
        String input = "-1";
        int expected = -1;

        //when
        int convertedNumber = converter.convertToNumber(input);

        //then
        assertThat(convertedNumber).isEqualTo(expected);
    }

    @Test
    @DisplayName("입력된 문자열을 정수로 정상적으로 반환하는지 확인한다.")
    void convertStringToIntegerCorrectly3(){
        //given
        String input = "0";
        int expected = 0;

        //when
        int convertedNumber = converter.convertToNumber(input);

        //then
        assertThat(convertedNumber).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("입력된 문자열을 정수로 정상적으로 반환하는지 확인한다.")
    @ValueSource(strings = {"a", "1.2", "1 1", "1-"})
    void shouldThrowExceptionWhenInvalidInput(String input){
        assertSimpleTest(() ->
                assertThatThrownBy(() -> converter.convertToNumber(input))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("") // TODO: 예외메시지 출력 확인
        );
    }


}
