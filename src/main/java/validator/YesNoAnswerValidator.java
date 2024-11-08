package validator;

import java.util.List;

public class YesNoAnswerValidator {

    private final List<String> possibleAnswers = List.of("Y", "N", "y", "n");

    public boolean validateAnswer(String answer){
        String answerToBeChecked = answer.trim();
        if (possibleAnswers.contains(answerToBeChecked)){
            return true;
        }
        throw new IllegalArgumentException(""); //TODO: 메시지
    }

}
