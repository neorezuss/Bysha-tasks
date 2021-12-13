package application.handlers;

import java.util.List;
import java.util.stream.Collectors;

public class FibonacciNumbersHandler implements Handler {
    @Override
    public Object handle(List<Integer> numbers) {
        return numbers.stream().filter(this::isFibonacci).collect(Collectors.toList());
    }

    private boolean isFibonacci(int number) {
        return (Math.sqrt(5 * number * number + 4) % 1 == 0 || Math.sqrt(5 * number * number - 4) % 1 == 0) && number > 0;
    }
}
