package application.handlers;

import java.util.List;
import java.util.stream.Collectors;

public class OddNumbersHandler implements Handler {
    @Override
    public List<Integer> handle(List<Integer> numbers) {
        return numbers.stream().filter(this::isOdd).collect(Collectors.toList());
    }

    private boolean isOdd(int number) {
        return number % 2 != 0;
    }
}
