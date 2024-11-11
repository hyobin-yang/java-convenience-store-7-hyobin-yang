package store.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import store.message.Exceptions;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class YesNoAnswerTest {

    private final YesNoAnswer answerValidator = new YesNoAnswer();

    @ParameterizedTest
    @DisplayName("올바른 값이 들어오면 true값을 반환한다. 공백도 허용한다.")
    @ValueSource(strings = {"Y", "N", "y", "n"})
    void shouldReturnTrueWhenAnswerHasRightFormat(String answer){
        answerValidator.validate(answer);
    }

    @ParameterizedTest
    @DisplayName("올바른 값이 들어오면 true값을 반환한다. 공백도 허용한다.")
    @ValueSource(strings = {"Y ", " N ", "y  ", "   n    "})
    void shouldReturnTrueWhenAnswerHasRightFormatAndBlank(String answer){
        answerValidator.validate(answer);
    }

    @ParameterizedTest
    @DisplayName("올바른 값이 들어오면 true값을 반환한다. 공백도 허용한다.")
    @ValueSource(strings = {"Y\n", "N\t"}) //TODO: 정규표현식 관리
    void shouldReturnTrueWhenAnswerHasRightFormatAndBlank2(String answer){
        answerValidator.validate(answer);
    }

    @ParameterizedTest
    @DisplayName("올바른 값이 들어오면 true값을 반환한다. 공백도 허용한다.")
    @ValueSource(strings = {"Y", "y"})
    void shouldReturnTrueWhenAnswerIsPositive(String answer){
        assertThat(answerValidator.isPositive(answer)).isTrue();
    }

    @ParameterizedTest
    @DisplayName("올바른 값이 들어오면 true값을 반환한다. 공백도 허용한다.")
    @ValueSource(strings = {"N", "n"})
    void shouldReturnFalseWhenAnswerIsNegative(String answer){
        assertThat(answerValidator.isPositive(answer)).isFalse();
    }

    @ParameterizedTest
    @DisplayName("Y, N, y, n 이외의 값이 들어오면 예외를 발생시킨다.")
    @ValueSource(strings = {"Yes", "No", "Y/"})
    void shouldThrowExceptionWhenAnswerHasWrongFormat(String answer){
        assertSimpleTest(() ->
                assertThatThrownBy(() -> answerValidator.validate(answer))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage(Exceptions.INVALID_FORMAT.getMessage()) // TODO: 예외메시지 출력 확인
        );
    }

}
