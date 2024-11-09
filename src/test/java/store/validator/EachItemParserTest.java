package store.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import convenienceStore.EachItemParser;

import java.util.Map;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class EachItemParserTest {

    private final EachItemParser itemParser = new EachItemParser();

    @Test
    @DisplayName("구매할 상품 정보가 올바르게 입력되었을 시 상품명:수량 형태의 map으로 반환한다.")
    void testValidItemInputReturnsMap(){
        //given
        String item = "[사이다-2]";

        //when
        Map<String, Integer> parsedItem = itemParser.parseEachItem(item);
        Map<String, Integer> expected = Map.of("사이다", 2); //TODO: 이 위치가 맞나?

        //then
        assertThat(parsedItem).isEqualTo(expected);
    }

    @Test
    @DisplayName("구매할 상품명은 대괄호와 하이픈을 제외한 모든 문자가 들어올 수 있다.")
    void testValidItemInputReturnsMap2(){
        //given
        String item = "[나폴리맛피아의 밤*티라미수-2]";

        //when
        Map<String, Integer> parsedItem = itemParser.parseEachItem(item);
        Map<String, Integer> expected = Map.of("나폴리맛피아의 밤*티라미수", 2); //TODO: 이 위치가 맞나?

        //then
        assertThat(parsedItem).isEqualTo(expected);
    }

    @Test
    @DisplayName("구매할 상품명에는 숫자가 포함될 수 있다.")
    void testValidItemInputReturnsMap3(){
        //given
        String item = "[가나초코우유300ml-10]";

        //when
        Map<String, Integer> parsedItem = itemParser.parseEachItem(item);
        Map<String, Integer> expected = Map.of("가나초코우유300ml", 10); //TODO: 이 위치가 맞나?

        //then
        assertThat(parsedItem).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("입력 형식을 준수하지 않는 경우 예외를 발생시킨다.")
    @ValueSource(strings = {"[사이다-2", "[사이다]", "[사이다-]", "사이다-2]",
                                    "[2]", "[사이다-0]", "[사이다-1개]",
                                    "[사이다:2]", "[사이다--2]", "사이다-2",
                                    "(사이다-2)", "{사이다-2}", "[-2]", "[]", "[사이다-2 3]"})

    void shouldThrowException(String item){
        assertSimpleTest(() ->
                assertThatThrownBy(() -> itemParser.parseEachItem(item))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("") // TODO: 예외메시지 출력 확인
        );
    }

}
