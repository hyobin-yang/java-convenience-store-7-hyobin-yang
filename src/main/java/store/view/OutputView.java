package store.view;

import java.text.DecimalFormat;

public class OutputView {

    private final static String HYPHEN = "-";
    DecimalFormat decimalFormat = new DecimalFormat("#,###");

    public void outputExceptionMessage(String exceptionMessage){
        System.out.println(exceptionMessage);
    }

    public void outputWelcomeMessage(){
        System.out.println("안녕하세요. W편의점입니다.");
    }

    public void outputItemStockMessage(){
        System.out.println("현재 보유하고 있는 상품입니다.\n");
    }

    public void outputItemStock(String name, long price, int quantity, String promotionName){
        String formattedPrice = decimalFormat.format(price);
        if (quantity == 0) {
            System.out.printf(HYPHEN + " %s %s원 재고 없음 %s\n", name, formattedPrice, promotionName);
            return;
        }
        System.out.printf(HYPHEN + " %s %s원 %s개 %s\n", name, formattedPrice, quantity, promotionName);
    }

    public void outputReceiptMessage(){
        System.out.println("==============W 편의점================");
        System.out.println("상품명\t\t수량\t\t금액");
    }

    public void outputPurchasedItem(String itemName, int itemQuantity, long totalPrice){
        System.out.printf("%s\t\t%d\t\t%s\n", itemName, itemQuantity, decimalFormat.format(totalPrice));
    }

    public void outputPromotionMessage(){
        System.out.println("=============증\t\t정===============");
    }

    public void outputFreeItem(String freeItems){
        System.out.print(freeItems);
    }

    public void outputTotalPrices(int quantity, String totalPayment, String promotionDiscount, String membershipDiscount, String actualPayment){
        System.out.println("====================================");
        System.out.printf("총구매액\t\t%d\t\t%s\n" +
                "행사할인\t\t\t\t-%s\n" +
                "멤버십할인\t\t\t\t-%s\n" +
                "내실돈\t\t\t\t %s\n\n", quantity, totalPayment, promotionDiscount, membershipDiscount, actualPayment);
    }

}
