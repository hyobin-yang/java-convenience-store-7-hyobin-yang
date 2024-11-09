package store.view;

public class InputView{
    private final InputProvider inputProvider;

    public InputView(InputProvider inputProvider){
        this.inputProvider = inputProvider;
    }

    public String getAnswerToBringMoreItem(String itemName, int quantity){
        System.out.printf("현재 %s은(는) %d개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)\n", itemName, quantity);
        return inputProvider.getInput();
    }

    public String getAnswerToContinueBuying(String itemName, int quantity){
        System.out.printf("현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)\n", itemName, quantity);
        return inputProvider.getInput();
    }

}
