package store.view;

public class InputView{
    private final InputProvider inputProvider;

    public InputView(InputProvider inputProvider){
        this.inputProvider = inputProvider;
    }

    public String inputPurchaseRequest(){
        System.out.println("\n구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])");
        return inputProvider.getInput();
    }

    public String getAnswerToBringMoreItem(String itemName, int quantity){
        System.out.printf("\n현재 %s은(는) %d개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)\n", itemName, quantity);
        return inputProvider.getInput();
    }

    public String getAnswerToContinueBuying(String itemName, int quantity){
        System.out.printf("\n현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)\n", itemName, quantity);
        return inputProvider.getInput();
    }

    public String getAnswerToGetMembershipDiscount(){
        System.out.println("멤버십 할인을 받으시겠습니까? (Y/N)");
        return inputProvider.getInput();
    }

    public String getAnswerToPurchaseOtherItems(){
        System.out.println("감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)");
        return inputProvider.getInput();
    }

}
