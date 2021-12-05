package application.handlers;

import application.ArrayOperations;

import java.util.Arrays;

public class HappyNumbersHandler implements Handler {
    @Override
    public String handle(ArrayOperations arrayOperations) {
        StringBuilder happyNumbers = new StringBuilder("Счастливые числа: ");
        if (arrayOperations.getHappyNumbers().length != 0) {
            happyNumbers.append(Arrays.toString(arrayOperations.getHappyNumbers()));
        } else happyNumbers.append("-");
        return happyNumbers.toString();
    }
}
