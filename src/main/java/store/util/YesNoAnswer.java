package store.util;

import store.controller.Exceptions;

import java.util.List;

public class YesNoAnswer {

    private final List<String> possibleAnswers = List.of("Y", "N", "y", "n");

    public boolean isPositive(String answer){
        return (answer.equals("Y") || answer.equals("y"));
    }

    public void validate(String answer){
        if (!possibleAnswers.contains(answer.trim())){
            throw new IllegalArgumentException(Exceptions.INVALID_FORMAT.getMessage());
        }
    }

}
