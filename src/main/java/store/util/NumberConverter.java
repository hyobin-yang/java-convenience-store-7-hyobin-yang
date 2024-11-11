package store.util;

import store.message.Exceptions;

public class NumberConverter {

    public int convertToNumber(String input){
        try{
            return Integer.parseInt(input);
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException(Exceptions.INVALID_INPUT.getMessage());
        }
    }
}
