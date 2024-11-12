package store;

import store.convenienceStore.Item;
import store.convenienceStore.ItemInventory;
import store.convenienceStoreHeadOffice.HeadOffice;
import store.convenienceStoreHeadOffice.Promotion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataProvider {

    private HeadOffice headOffice;
    private ItemInventory itemInventory;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public DataProvider(HeadOffice headOffice, ItemInventory itemInventory) {
        this.headOffice = headOffice;
        this.itemInventory = itemInventory;
    }

    public void providePromotionData(String filePath) {
        try (var br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String name = data[0];
                int buy = Integer.parseInt(data[1]);
                int get = Integer.parseInt(data[2]);
                LocalDateTime startDate = LocalDate.parse(data[3], formatter).atStartOfDay();
                LocalDateTime endDate = LocalDate.parse(data[4], formatter).atStartOfDay();

                Promotion promotion = new Promotion(name, buy, get, startDate, endDate);
                headOffice.addOngoingPromotion(promotion);
            }
        } catch (IOException e) {
            System.err.println("파일을 읽는 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    public void provideProductData(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String name = data[0];
                long price = Long.parseLong(data[1]);
                int quantity = Integer.parseInt(data[2]);
                String promotionName = data[3];

                Promotion promotion = headOffice.getPromotion(promotionName);

                Item item = new Item(name, price, quantity);
                item.setPromotion(promotion);

                itemInventory.addItem(item);
            }
        } catch (IOException e) {
            System.err.println("파일을 읽는 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
}
