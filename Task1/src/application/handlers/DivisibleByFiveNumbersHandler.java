package application.handlers;

import java.util.List;
import java.util.stream.Collectors;

public class DivisibleByFiveNumbersHandler implements Handler {
    @Override
    public List<Integer> handle(List<Integer> numbers) {
        return numbers.stream().filter(this::isDivisibleByFive).collect(Collectors.toList());
    }

    private boolean isDivisibleByFive(int number) {
        return number % 5 == 0;
    }
}
