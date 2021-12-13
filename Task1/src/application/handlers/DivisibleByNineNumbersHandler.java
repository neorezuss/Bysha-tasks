package application.handlers;

import java.util.List;
import java.util.stream.Collectors;

public class DivisibleByNineNumbersHandler implements Handler {
    @Override
    public List<Integer> handle(List<Integer> numbers) {
        return numbers.stream().filter(this::isDivisibleByNine).collect(Collectors.toList());
    }

    private boolean isDivisibleByNine(int number) {
        return number % 9 == 0;
    }
}
