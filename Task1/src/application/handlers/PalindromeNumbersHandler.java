package application.handlers;

import application.ArrayOperations;

import java.util.Arrays;

public class PalindromeNumbersHandler implements Handler {
    @Override
    public String handle(ArrayOperations arrayOperations) {
        StringBuilder palindromeNumbers = new StringBuilder("Числа-палиндромы: ");
        if (arrayOperations.getPalindromeNumbers().length != 0) {
            palindromeNumbers.append(Arrays.toString(arrayOperations.getPalindromeNumbers()));
        } else palindromeNumbers.append("-");
        return palindromeNumbers.toString();
    }
}
