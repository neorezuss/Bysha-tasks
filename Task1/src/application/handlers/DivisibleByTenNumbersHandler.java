package application.handlers;

import application.ArrayOperations;

import java.util.Arrays;

public class DivisibleByTenNumbersHandler implements Handler {
    @Override
    public String handle(ArrayOperations arrayOperations) {
        StringBuilder divisibleByTenNumbers = new StringBuilder("Числа, которые делятся на 10: ");
        if (arrayOperations.getDivisibleByXNumbers(10).length != 0) {
            divisibleByTenNumbers.append(Arrays.toString(arrayOperations.getDivisibleByXNumbers(10)));
        } else divisibleByTenNumbers.append("-");
        return divisibleByTenNumbers.toString();
    }
}
