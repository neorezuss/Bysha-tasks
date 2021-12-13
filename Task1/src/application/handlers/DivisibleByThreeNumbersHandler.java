package application.handlers;

import java.util.List;
import java.util.stream.Collectors;

public class DivisibleByThreeNumbersHandler implements Handler {
    @Override
    public List<Integer> handle(List<Integer> numbers) {
        return numbers.stream().filter(this::isDivisibleByThree).collect(Collectors.toList());
    }

    private boolean isDivisibleByThree(int number) {
        return number % 3 == 0;
    }
}
