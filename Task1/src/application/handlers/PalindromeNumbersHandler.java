package application.handlers;

import java.util.List;
import java.util.stream.Collectors;

public class PalindromeNumbersHandler implements Handler {
    @Override
    public List<Integer> handle(List<Integer> numbers) {
        return numbers.stream().filter(this::isPalindrome).collect(Collectors.toList());
    }

    private boolean isPalindrome(int number) {
        String numberToString = String.valueOf(number);
        return numberToString.equals(new StringBuilder(numberToString).reverse().toString());
    }
}
