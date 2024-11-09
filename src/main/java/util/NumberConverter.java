package util;

public class NumberConverter {

    public int convertToNumber(String input){
        try{
            return Integer.parseInt(input);
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException(""); //TODO: 예외처리
        }
    }
}
