package application.handlers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class HappyNumbersHandler implements Handler {
    @Override
    public List<Integer> handle(List<Integer> numbers) {
        return numbers.stream().filter(this::isHappyNumber).collect(Collectors.toList());
    }

    private boolean isHappyNumber(int number) {
        Set<Integer> uniqueNum = new HashSet<>();
        while (uniqueNum.add(number)) {
            int value = 0;
            while (number > 0) {
                value += Math.pow(number % 10, 2);
                number /= 10;
            }
            number = value;
        }
        return number == 1;
    }
}
