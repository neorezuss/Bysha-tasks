package application.handlers;

import application.ArrayOperations;

import java.util.Arrays;

public class EvenNumbersHandler implements Handler {
    @Override
    public String handle(ArrayOperations arrayOperations) {
        StringBuilder evenNumbers = new StringBuilder("Четные числа: ");
        if (arrayOperations.getEvenNumbers().length != 0) {
            evenNumbers.append(Arrays.toString(arrayOperations.getEvenNumbers()));
        } else evenNumbers.append("-");
        return evenNumbers.toString();
    }
}
