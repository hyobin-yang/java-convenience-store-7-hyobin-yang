package store.convenienceStoreHeadOffice;

import java.time.LocalDateTime;

public class Promotion {

    private final String promotionName;
    private final int numberOfItemToBuy;
    private final int numberOfItemToGet;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    public Promotion(String promotionName, int buy, int get, LocalDateTime startDate, LocalDateTime endDate){
        this.promotionName = promotionName;
        this.numberOfItemToBuy = buy;
        this.numberOfItemToGet = get;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getName(){
        return promotionName;
    }

    public int getNumberOfItemToBuy(){
        return numberOfItemToBuy;
    }

    public int getNumberOfItemToGet(){
        return numberOfItemToGet;
    }

    public boolean isTodayWithinPromotionPeriod(LocalDateTime today){
        return (today.isAfter(startDate) && today.isBefore(endDate));
    }

}



