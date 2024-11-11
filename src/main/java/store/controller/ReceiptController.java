package store.controller;

import store.convenienceStore.Item;
import store.convenienceStore.ItemInventory;
import store.convenienceStoreHeadOffice.MembershipPolicy;
import store.view.OutputView;

import java.text.DecimalFormat;
import java.util.List;

public class ReceiptController {

    private final OutputView outputView = new OutputView();
    private final DecimalFormat decimalFormat = new DecimalFormat("#,###");
    private String freeItemDetails = "";
    private ItemInventory itemInventory;

    private int totalItemQuantityToPay = 0;
    private long totalOriginalPrice = 0;
    private long totalPromotionDiscount = 0;
    private long finalPaymentAmount = 0;

    public void printReceipt(List<PromotionDTO> itemToPurchaseInventory, ItemInventory itemInventory, boolean applyMembershipDiscount) {
        this.itemInventory = itemInventory;

        outputView.outputReceiptMessage();

        for (PromotionDTO item : itemToPurchaseInventory) {
            processPurchasedItem(item);
            renderFreeItemDetails(item);
            updatePaymentInfo(item);
            deductPurchasedQuantity(itemInventory, item.getItemName(), item.getQuantityToBuy());
        }
        long membershipDiscountAmount = calculateMembershipDiscount(applyMembershipDiscount, totalOriginalPrice, totalPromotionDiscount);
        finalPaymentAmount = calculateFinalPayment(membershipDiscountAmount);
        displayReceiptSummary(membershipDiscountAmount);
    }

    private void processPurchasedItem(PromotionDTO item) {
        outputView.outputPurchasedItem(item.getItemName(), item.getQuantityToBuy(), item.getTotalPrice());
    }

    private void renderFreeItemDetails(PromotionDTO item) {
        if (item.getFreeQuantity() > 0) {
            renderFreeItemDetails(item.getItemName(), item.getFreeQuantity());
        }
    }

    private void updatePaymentInfo(PromotionDTO item) {
        totalItemQuantityToPay += (item.getQuantityToBuy() - item.getFreeQuantity());
        //totalItemQuantityToPay += item.getQuantityToBuy();
        totalOriginalPrice += item.getTotalPrice();
        totalPromotionDiscount += item.getPromotionDiscount();
    }

    private long calculateMembershipDiscount(long eligiblePrice) {
        return MembershipPolicy.MEMBERSHIP_POLICY.discountPrice(eligiblePrice);
    }

    private long calculateFinalPayment(long membershipDiscountAmount) {
        return totalOriginalPrice - totalPromotionDiscount - membershipDiscountAmount;
    }

    private void renderFreeItemDetails(String itemName, int quantity) {
        freeItemDetails += itemName + "\t\t" + quantity + "\n";
    }

    private void deductPurchasedQuantity(ItemInventory itemInventory, String itemName, int quantity) {
        Item promotionItem = itemInventory.getPromotionItem(itemName);
        Item generalItem = itemInventory.getGeneralItem(itemName);
        if (promotionItem == null) {
            deductFromGeneralItem(generalItem, quantity);
            return;
        }
        deductFromPromotionItem(promotionItem, generalItem, quantity);
    }

    private void deductFromPromotionItem(Item promotionItem, Item generalItem, int quantity) {
        if (promotionItem.getItemQuantity() >= quantity) {
            itemInventory.deductItemQuantity(promotionItem, quantity);
            return;
        }
        int remainingQuantity = quantity - promotionItem.getItemQuantity();
        itemInventory.deductItemQuantity(promotionItem, promotionItem.getItemQuantity());
        itemInventory.deductItemQuantity(generalItem, remainingQuantity);
    }

    private void deductFromGeneralItem(Item generalItem, int quantity) {
        itemInventory.deductItemQuantity(generalItem, quantity);
    }

    private long calculateMembershipDiscount(boolean applyMembershipDiscount, long totalOriginalPrice, long totalPromotionDiscount) {
        if (applyMembershipDiscount) {
            return calculateMembershipDiscount(totalOriginalPrice - totalPromotionDiscount);
        }
        return 0;
    }

    private void displayReceiptSummary(long membershipDiscountAmount) {
        outputView.outputPromotionMessage();
        outputView.outputFreeItem(freeItemDetails);
        outputView.outputTotalPrices(
                totalItemQuantityToPay,
                formatPrice(totalOriginalPrice),
                formatPrice(totalPromotionDiscount),
                formatPrice(membershipDiscountAmount),
                formatPrice(finalPaymentAmount)
        );
    }

    private String formatPrice(long price) {
        return decimalFormat.format(price);
    }
}


