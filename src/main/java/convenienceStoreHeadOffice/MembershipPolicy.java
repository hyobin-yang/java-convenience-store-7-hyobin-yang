package convenienceStoreHeadOffice;

public enum MembershipPolicy {

    MEMBERSHIP_POLICY(30, 8_000);

    private final double discountRate;
    private final int maximumPrice;

    MembershipPolicy(int discountRate, int maximumPrice){
        this.discountRate = discountRate;
        this.maximumPrice = maximumPrice;
    }

    public int discountPrice(int price){
        validatePrice(price);
        int discountedPrice = (int)(price * (discountRate/100));
        if (discountedPrice < maximumPrice){
            return discountedPrice;
        }
        return maximumPrice;
    }

    private void validatePrice(int price){
        if (price < 0){
            throw new IllegalArgumentException("");
        }
    }

}
