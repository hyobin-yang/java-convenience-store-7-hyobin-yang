package parser;

import java.util.Arrays;
import java.util.List;

public class ParserWithComma {

    private static final String DELIMITER = ",";

    public List<String> parse(String tobeParsedWithComma){
        if (tobeParsedWithComma.isEmpty()){
            throw new IllegalArgumentException("");
        }
        return Arrays.stream(tobeParsedWithComma.split(DELIMITER))
                .map(String::trim)
                .toList();
    }

}
