package application.handlers;

import application.ArrayOperations;

import java.util.Arrays;

public class FibonacciNumbersHandler implements Handler {
    @Override
    public String handle(ArrayOperations arrayOperations) {
        StringBuilder fibonacciNumbers = new StringBuilder("Числа Фибоначчи: ");
        if (arrayOperations.getFibonacciNumbers().length != 0) {
            fibonacciNumbers.append(Arrays.toString(arrayOperations.getFibonacciNumbers()));
        } else fibonacciNumbers.append("-");
        return fibonacciNumbers.toString();
    }
}
