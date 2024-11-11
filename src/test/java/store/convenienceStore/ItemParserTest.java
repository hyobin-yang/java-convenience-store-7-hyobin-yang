package store.convenienceStore;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.convenienceStoreHeadOffice.ItemParser;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ItemParserTest {

    @Test
    @DisplayName("구매할 상품 정보가 올바르게 입력되었을 시 상품명:수량 형태의 map으로 반환한다.")
    void testValidItemInputReturnsMap(){
        //given
        String item = "[사이다-2]";
        ItemParser parsedItem = new ItemParser(item);

        //when
        String name = parsedItem.getName();
        int quantity = parsedItem.getQuantity();

        //then
        assertThat(name).isEqualTo("사이다");
        assertThat(quantity).isEqualTo(2);
    }

    @Test
    @DisplayName("구매할 상품명은 대괄호와 하이픈을 제외한 모든 문자가 들어올 수 있다.")
    void testValidItemInputReturnsMap2(){
        //given
        String item = "[나폴리맛피아의 밤*티라미수-2]";

        ItemParser parsedItem = new ItemParser(item);
        String name = parsedItem.getName();
        int quantity = parsedItem.getQuantity();

        //then
        assertThat(name).isEqualTo("나폴리맛피아의 밤*티라미수");
        assertThat(quantity).isEqualTo(2);
    }

    @Test
    @DisplayName("구매할 상품명에는 숫자가 포함될 수 있다.")
    void testValidItemInputReturnsMap3(){
        //given
        String item = "[가나초코우유300ml-10]";

        //when
        ItemParser parsedItem = new ItemParser(item);
        String name = parsedItem.getName();
        int quantity = parsedItem.getQuantity();

        //then
        assertThat(name).isEqualTo("가나초코우유300ml");
        assertThat(quantity).isEqualTo(10);
    }

}
