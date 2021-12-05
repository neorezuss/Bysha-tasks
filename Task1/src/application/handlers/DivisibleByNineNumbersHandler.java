package application.handlers;

import application.ArrayOperations;

import java.util.Arrays;

public class DivisibleByNineNumbersHandler implements Handler {
    @Override
    public String handle(ArrayOperations arrayOperations) {
        StringBuilder divisibleByNineNumbers = new StringBuilder("Числа, которые делятся на 9: ");
        if (arrayOperations.getDivisibleByXNumbers(9).length != 0) {
            divisibleByNineNumbers.append(Arrays.toString(arrayOperations.getDivisibleByXNumbers(9)));
        } else divisibleByNineNumbers.append("-");
        return divisibleByNineNumbers.toString();
    }
}
