package application.handlers;

import application.ArrayOperations;

import java.util.Arrays;

public class OddNumbersHandler implements Handler {
    @Override
    public String handle(ArrayOperations arrayOperations) {
        StringBuilder oddNumbers = new StringBuilder("Нечетные числа: ");
        if (arrayOperations.getOddNumbers().length != 0) {
            oddNumbers.append(Arrays.toString(arrayOperations.getOddNumbers()));
        } else oddNumbers.append("-");
        return oddNumbers.toString();
    }
}
