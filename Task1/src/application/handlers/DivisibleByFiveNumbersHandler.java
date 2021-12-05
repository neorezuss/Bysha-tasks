package application.handlers;

import application.ArrayOperations;

import java.util.Arrays;

public class DivisibleByFiveNumbersHandler implements Handler {
    @Override
    public String handle(ArrayOperations arrayOperations) {
        StringBuilder divisibleByFiveNumbers = new StringBuilder("Числа, которые делятся на 5: ");
        if (arrayOperations.getDivisibleByXNumbers(5).length != 0) {
            divisibleByFiveNumbers.append(Arrays.toString(arrayOperations.getDivisibleByXNumbers(5)));
        } else divisibleByFiveNumbers.append("-");
        return divisibleByFiveNumbers.toString();
    }
}
