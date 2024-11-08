package validator;


import java.util.Map;

public class EachItemParser {

    // 공백 포함 X
    //TODO: 리팩터링 - 책임 분리
    private final static String ITEM_FORMAT_REGEX = "^\\[\\s*[^\\]-]+\\s*-[1-9]\\d*\\s*\\]$";

    public Map<String, Integer> parseEachItem(String item){
        String itemRemovedBrackets = validateWithSquareBrackets(item);
        String[] parsedWithHyphen = parseWithHyphen(itemRemovedBrackets);
        String itemName = parsedWithHyphen[0].trim();
        int itemQuantity = Integer.parseInt(parsedWithHyphen[1].trim());
        return Map.of(itemName, itemQuantity);
    }

    private String[] parseWithHyphen(String item){
        String[] parsedWithHyphen = item.split("-");
        return parsedWithHyphen;
    }

    private String validateWithSquareBrackets(String item){
        if (!item.matches(ITEM_FORMAT_REGEX)){
            throw new IllegalArgumentException("");
        }
        return item.replaceAll("[\\[\\]]", "");
    }

}
