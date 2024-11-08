package store.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import validator.AnswerValidator;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class AnswerValidatorTest {

    private final AnswerValidator answerValidator = new AnswerValidator();

    @ParameterizedTest
    @DisplayName("올바른 값이 들어오면 true값을 반환한다. 공백도 허용한다.")
    @ValueSource(strings = {"Y", "N", "y", "n"})
    void shouldReturnTrueWhenAnswerHasRightFormat(String answer){
        // when
        boolean isRightAnswer = answerValidator.validateAnswer(answer); //TODO: 변수명 수정

        // then
        assertThat(isRightAnswer).isEqualTo(true); // TODO: 다른 비교형식 있는지 확인
    }

    @ParameterizedTest
    @DisplayName("올바른 값이 들어오면 true값을 반환한다. 공백도 허용한다.")
    @ValueSource(strings = {"Y ", " N ", "y  ", "   n    "})
    void shouldReturnTrueWhenAnswerHasRightFormatAndBlank(String answer){
        // when
        boolean isRightAnswer = answerValidator.validateAnswer(answer);

        // then
        assertThat(isRightAnswer).isEqualTo(true); // TODO: 다른 비교형식 있는지 확인
    }

    @ParameterizedTest
    @DisplayName("올바른 값이 들어오면 true값을 반환한다. 공백도 허용한다.")
    @ValueSource(strings = {"Y\n", "N\t"}) //TODO: 정규표현식 관리
    void shouldReturnTrueWhenAnswerHasRightFormatAndBlank2(String answer){
        // when
        boolean isRightAnswer = answerValidator.validateAnswer(answer);

        // then
        assertThat(isRightAnswer).isEqualTo(true); // TODO: 다른 비교형식 있는지 확인
    }

    @ParameterizedTest
    @DisplayName("Y, N, y, n 이외의 값이 들어오면 예외를 발생시킨다.")
    @ValueSource(strings = {"Yes", "No", "Y/"})
    void shouldThrowExceptionWhenAnswerHasWrongFormat(String answer){
        assertSimpleTest(() ->
                assertThatThrownBy(() -> answerValidator.validateAnswer(answer))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("") // TODO: 예외메시지 출력 확인
        );
    }

}
