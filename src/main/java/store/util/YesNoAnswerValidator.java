package store.util;

import store.message.Exceptions;

import java.util.List;

public class YesNoAnswerValidator {

    private static final List<String> POSSIBLE_ANSWERS = List.of("Y", "N", "y", "n");

    public static boolean isPositive(String answer){
        validate(answer);
        return (answer.equals("Y") || answer.equals("y"));
    }

    private static void validate(String answer){
        if (!POSSIBLE_ANSWERS.contains(answer.trim())){
            throw new IllegalArgumentException(Exceptions.INVALID_FORMAT.getMessage());
        }
    }

}
