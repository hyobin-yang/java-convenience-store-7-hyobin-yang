package store.convenienceStore;


import store.message.Exceptions;

public class ItemParser {
    private final static String ITEM_FORMAT_REGEX = "^\\[\\s*[^\\]-]+\\s*-[1-9]\\d*\\s*\\]$";

    private final String itemName;
    private final int itemQuantity;

    public ItemParser(String item){
        String itemRemovedBrackets = validateWithSquareBrackets(item);
        String[] parsedWithHyphen = parseWithHyphen(itemRemovedBrackets);
        itemName = parsedWithHyphen[0].trim();
        itemQuantity = Integer.parseInt(parsedWithHyphen[1].trim());
    }

    private String[] parseWithHyphen(String item){
        return item.split("-");
    }

    private String validateWithSquareBrackets(String item){
        if (!item.matches(ITEM_FORMAT_REGEX)){
            throw new IllegalArgumentException(Exceptions.INVALID_INPUT.getMessage());
        }
        return item.replaceAll("[\\[\\]]", "");
    }

    public String getName(){
        return itemName;
    }

    public int getQuantity(){
        return itemQuantity;
    }

}
