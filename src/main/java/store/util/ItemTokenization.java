package store.util;

import store.message.Exceptions;

import java.util.Arrays;
import java.util.List;

public class ItemTokenization {

    private static final String DELIMITER = ",";

    public List<String> tokenize(String input){
        if (input.isEmpty()){
            throw new IllegalArgumentException(Exceptions.INVALID_INPUT.getMessage());
        }
        return Arrays.stream(input.split(DELIMITER))
                .map(String::trim)
                .toList();

    }

}
