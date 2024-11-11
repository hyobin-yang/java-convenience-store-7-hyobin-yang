package store.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class OutputViewTest {
    OutputView outputView = new OutputView();
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Test
    @DisplayName("프로모션이 적용되는 상품 정보와 포맷팅된 가격이 정상적으로 출력하는지 확인한다.")
    void verifyOutputItemStockWhenHavePromotion(){
        //given
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        // when
        outputView.outputItemStock("콜라", 1000, 10, "탄산2+1");

        //then
        assertThat(outContent.toString().trim()).isEqualTo("- 콜라 1,000원 10개 탄산2+1");
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("프로모션이 적용되지 않는 상품 정보와 포맷팅된 가격이 정상적으로 출력하는지 확인한다.")
    void verifyOutputItemStockWhenNotHavePromotion(){
        //given
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        // when
        outputView.outputItemStock("양주", 130000, 5, "");

        //then
        assertThat(outContent.toString().trim()).isEqualTo("- 양주 130,000원 5개");
        System.setOut(originalOut);
    }
}
