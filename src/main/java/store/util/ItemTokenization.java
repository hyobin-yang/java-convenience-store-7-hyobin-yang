package store.util;

import java.util.Arrays;
import java.util.List;

public class ItemTokenization {
    private static final String DELIMITER = ",";

    public static List<String> tokenize(String input){
        return Arrays.stream(input.split(DELIMITER))
                .map(String::trim)
                .toList();
    }
}
