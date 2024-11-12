package store.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import store.message.Exceptions;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class YesNoAnswerValidatorTest {

    @ParameterizedTest
    @DisplayName("대답이 긍정이면 true를 반환한다.")
    @ValueSource(strings = {"Y", "y"})
    void isPositiveAnswer(String answer){
        YesNoAnswerValidator.isPositive(answer);
    }

    @ParameterizedTest
    @DisplayName("올바른 긍정의 대답을 입력하면 true값을 반환한다. 공백도 허용한다.")
    @ValueSource(strings = {"Y\n", "y  "})
    void shouldReturnTrueWhenAnswerHasRightFormatAndBlank(String answer){
        YesNoAnswerValidator.isPositive(answer);
    }

    @ParameterizedTest
    @DisplayName("올바른 부정의 대답을 입력하면 false값을 반환한다. 공백도 허용한다.")
    @ValueSource(strings = {"N", "n"})
    void shouldReturnTrueWhenAnswerIsNegative(String answer){
        assertThat(YesNoAnswerValidator.isPositive(answer)).isFalse();
    }

    @ParameterizedTest
    @DisplayName("Y, N, y, n 이외의 값이 들어오면 예외를 발생시킨다.")
    @ValueSource(strings = {"Yes", "No", "Y/"})
    void shouldThrowExceptionWhenAnswerHasWrongFormat(String answer){
        assertSimpleTest(() ->
                assertThatThrownBy(() -> YesNoAnswerValidator.isPositive(answer))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage(Exceptions.INVALID_FORMAT.getMessage())
        );
    }

}
