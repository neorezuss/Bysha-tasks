package application.handlers;

import application.ArrayOperations;

import java.util.Arrays;

public class DivisibleByThreeNumbersHandler implements Handler {
    @Override
    public String handle(ArrayOperations arrayOperations) {
        StringBuilder divisibleByThreeNumbersHandler = new StringBuilder("Числа, которые делятся на 3: ");
        if (arrayOperations.getDivisibleByXNumbers(3).length != 0) {
            divisibleByThreeNumbersHandler.append(Arrays.toString(arrayOperations.getDivisibleByXNumbers(3)));
        } else divisibleByThreeNumbersHandler.append("-");
        return divisibleByThreeNumbersHandler.toString();
    }
}
