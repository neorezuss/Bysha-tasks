package application.handlers;

import javafx.util.Pair;

import java.util.List;

public class DecimalPeriodHandler implements Handler {
    @Override
    public Integer handle(List<Integer> numbers) {
        Pair<Integer, Integer> firstTwoPositiveNumbers = getFirstTwoConsecutivePositiveNumbers(numbers);
        int firstPositiveNumber = firstTwoPositiveNumbers.getKey();
        int secondPositiveNumber = firstTwoPositiveNumbers.getValue();
        if (firstPositiveNumber != -1 && secondPositiveNumber != -1) {
            int m, n, r, l, t, i;
            m = firstPositiveNumber % secondPositiveNumber;
            n = secondPositiveNumber;
            r = m;
            for (i = 0; i < n; i++) {
                r = (r * 10) % n;
            }
            t = r;
            l = 0;
            do {
                r = (r * 10) % n;
                l++;
            } while (r != t);
            t = r = m;
            StringBuilder decimalPeriod = new StringBuilder();
            for (i = 0; i < l; i++) {
                r = (r * 10) % n;
            }
            for (i = 0; r != t; i++) {
                r = (r * 10) % n;
                t = (t * 10) % n;
            }
            for (i = 0; i < l; i++) {
                decimalPeriod.append(t * 10 / n);
                t = (t * 10) % n;
            }
            return Integer.parseInt(decimalPeriod.toString());
        } else {
            return -1;
        }
    }

    private Pair<Integer, Integer> getFirstTwoConsecutivePositiveNumbers(List<Integer> numbers) {
        int firstPositiveNumber = -1;
        int secondPositiveNumber = -1;
        for (int i = 1; i < numbers.size(); i++) {
            if (numbers.get(i) > 0 && numbers.get(i - 1) > 0) {
                firstPositiveNumber = numbers.get(i - 1);
                secondPositiveNumber = numbers.get(i);
                break;
            }
        }
        return new Pair(firstPositiveNumber, secondPositiveNumber);
    }
}
