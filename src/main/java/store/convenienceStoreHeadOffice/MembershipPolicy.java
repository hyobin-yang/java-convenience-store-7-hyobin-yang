package store.convenienceStoreHeadOffice;

import store.message.Exceptions;

public enum MembershipPolicy {

    MEMBERSHIP_POLICY(30, 8_000);

    private final double discountRate;
    private final int maximumPrice;

    MembershipPolicy(int discountRate, int maximumPrice){
        this.discountRate = discountRate;
        this.maximumPrice = maximumPrice;
    }

    public long discountPrice(long price){
        validatePrice(price);
        long discountedPrice = (long)(price * (discountRate/100));
        if (discountedPrice < maximumPrice){
            return discountedPrice;
        }
        return maximumPrice;
    }

    private void validatePrice(long price){
        if (price < 0){
            throw new IllegalArgumentException(Exceptions.INVALID_INPUT.getMessage());
        }
    }

}
