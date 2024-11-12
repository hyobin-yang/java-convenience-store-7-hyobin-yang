package store;

import store.controller.MainPaymentController;
import store.convenienceStore.ItemInventory;
import store.convenienceStoreHeadOffice.HeadOffice;
import store.view.ConsoleInputProvider;

public class Application {
    public static void main(String[] args) {
        HeadOffice headOffice = new HeadOffice();
        ItemInventory itemInventory = new ItemInventory();

        DataProvider dataProvider = new DataProvider(headOffice, itemInventory);
        dataProvider.providePromotionData("src/main/resources/promotions.md");
        dataProvider.provideProductData("src/main/resources/products.md");

        MainPaymentController controller = new MainPaymentController(new ConsoleInputProvider(), itemInventory);
        controller.startPaymentProcess();

    }
}
