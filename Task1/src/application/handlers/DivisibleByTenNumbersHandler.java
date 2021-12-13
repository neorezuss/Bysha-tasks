package application.handlers;

import java.util.List;
import java.util.stream.Collectors;

public class DivisibleByTenNumbersHandler implements Handler {
    @Override
    public List<Integer> handle(List<Integer> numbers) {
        return numbers.stream().filter(this::isDivisibleByTen).collect(Collectors.toList());
    }

    private boolean isDivisibleByTen(int number) {
        return number % 10 == 0;
    }
}
