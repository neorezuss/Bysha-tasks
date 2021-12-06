package application.handlers;

import javafx.util.Pair;

import java.util.List;

public class LCMHandler implements Handler {
    @Override
    public Integer handle(List<Integer> numbers) {
        Pair<Integer, Integer> firstTwoPositiveNumbers = getFirstTwoPositiveNumbers(numbers);
        return lcm(firstTwoPositiveNumbers.getKey(), firstTwoPositiveNumbers.getValue());
    }

    private Pair<Integer, Integer> getFirstTwoPositiveNumbers(List<Integer> numbers) {
        int counter = 0;
        int firstPositiveNumber = -1;
        int secondPositiveNumber = -1;
        for (int i = 0; i < numbers.size(); i++) {
            if (counter >= 2) {
                break;
            }
            if (numbers.get(i) > 0) {
                if (counter == 0) {
                    firstPositiveNumber = numbers.get(i);
                } else {
                    secondPositiveNumber = numbers.get(i);
                }
                counter++;
            }
        }
        return new Pair<>(firstPositiveNumber, secondPositiveNumber);
    }

    private int lcm(int a, int b) {
        if (a != -1 && b != -1) {
            return a / gcd(a, b) * b;
        }
        return -1;
    }

    private int gcd(int a, int b) {
        if (a != -1 && b != -1) {
            return b == 0 ? a : gcd(b, a % b);
        }
        return -1;
    }
}
