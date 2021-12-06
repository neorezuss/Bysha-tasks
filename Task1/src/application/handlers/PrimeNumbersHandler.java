package application.handlers;

import java.util.List;
import java.util.stream.Collectors;

public class PrimeNumbersHandler implements Handler {
    @Override
    public List<Integer> handle(List<Integer> numbers) {
        return numbers.stream().filter(this::isPrime).collect(Collectors.toList());
    }

    private boolean isPrime(int number) {
        int temp;
        if (number < 1) {
            return false;
        }
        for (int i = 2; i <= number / 2; i++) {
            temp = number % i;
            if (temp == 0) {
                return false;
            }
        }
        return true;
    }
}
