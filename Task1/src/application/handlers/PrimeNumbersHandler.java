package application.handlers;

import application.ArrayOperations;

import java.util.Arrays;

public class PrimeNumbersHandler implements Handler {
    @Override
    public String handle(ArrayOperations arrayOperations) {
        StringBuilder primeNumbers = new StringBuilder("Простые числа: ");
        if (arrayOperations.getPrimeNumbers().length != 0) {
            primeNumbers.append(Arrays.toString(arrayOperations.getPrimeNumbers()));
        } else primeNumbers.append("-");
        return primeNumbers.toString();
    }
}
