package application.handlers;

import java.util.List;
import java.util.stream.Collectors;

public class EvenNumbersHandler implements Handler {
    @Override
    public List<Integer> handle(List<Integer> numbers) {
        return numbers.stream().filter(this::isEven).collect(Collectors.toList());
    }

    private boolean isEven(int number) {
        return number % 2 == 0;
    }
}
