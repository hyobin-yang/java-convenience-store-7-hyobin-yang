package store.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CommaParserTest {

    @Test
    @DisplayName("입력 문자열을 콤마를 구분자로 나누고 리스트에 저장한다.")
    void testParsingWithComma(){
        //given
        String toBeParsed = "[사이다-2], [콜라-1]";

        //when
        List<String> parsedItem = ItemTokenization.tokenize(toBeParsed);
        List<String> expected = List.of("[사이다-2]", "[콜라-1]"); //TODO: 이 위치가 맞나?

        //then
        assertThat(parsedItem).isEqualTo(expected);
    }

    @Test
    @DisplayName("공백이 포함된 경우 공백을 제거하고 리스트에 추가한다.")
    void testParingWithCommaAndTrimming(){
        //given
        String toBeParsed = "  [사이다-2] , [콜라-1]   ";

        //when
        List<String> parsedItem = ItemTokenization.tokenize(toBeParsed);
        List<String> expected = List.of("[사이다-2]", "[콜라-1]"); //TODO: 이 위치가 맞나?

        //then
        assertThat(parsedItem).isEqualTo(expected);
    }
}
