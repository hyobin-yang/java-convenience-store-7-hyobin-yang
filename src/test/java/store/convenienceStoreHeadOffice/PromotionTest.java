package store.convenienceStoreHeadOffice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PromotionTest {
    private Promotion promotion;

    @BeforeEach
    void initializePromotion(){
        LocalDateTime startDate = LocalDateTime.of(2024, 11, 9, 0, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2024, 12, 9, 0, 0, 0);
        promotion = new Promotion("콜라", 2, 1, startDate, endDate);
    }

    @Test
    @DisplayName("오늘 날짜가 프로모션 기간 내에 있을 때 true를 반환한다.")
    void returnTrueIfTodayIsWithinPromotionPeriod(){
        //given
        LocalDateTime today = LocalDateTime.of(2024, 11, 10, 0,0, 0);

        //when
        boolean isWithinPeriod = promotion.isTodayWithinPromotionPeriod(today);

        //then
        assertThat(isWithinPeriod).isTrue();
    }

    @Test
    @DisplayName("오늘 날짜가 프로모션 기간 전일 때 false를 반환한다.")
    void returnFalseIfTodayIsBeforePromotionPeriod(){
        //given
        LocalDateTime today = LocalDateTime.of(2024, 10, 9, 0,0, 0);

        //when
        boolean isWithinPeriod = promotion.isTodayWithinPromotionPeriod(today);

        //then
        assertThat(isWithinPeriod).isFalse();
    }

    @Test
    @DisplayName("오늘 날짜가 프로모션 기간 이후일 때 false를 반환한다.")
    void returnFalseIfTodayIsAfterPromotionPeriod(){
        //given
        LocalDateTime today = LocalDateTime.of(2024, 12, 9, 0,0, 0);

        //when
        boolean isWithinPeriod = promotion.isTodayWithinPromotionPeriod(today);

        //then
        assertThat(isWithinPeriod).isFalse();
    }
}
