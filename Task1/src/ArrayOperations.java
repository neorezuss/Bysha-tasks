import javafx.util.Pair;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ArrayOperations {
    private int[] numbers;

    public ArrayOperations(int[] numbers) {
        this.numbers = numbers;
    }

    public int[] getEvenNumbers() {
        return Arrays.stream(numbers).filter(number -> isEven(number)).toArray();
    }

    private boolean isEven(int number) {
        return number % 2 == 0;
    }

    public int[] getOddNumbers() {
        return Arrays.stream(numbers).filter(number -> !isEven(number)).toArray();
    }

    public int[] getDivisibleByXNumbers(int x) {
        return Arrays.stream(numbers).filter(number -> isDivisibleByX(number, x)).toArray();
    }

    private boolean isDivisibleByX(int number, int x) {
        return number % x == 0;
    }

    public int getGcdOfNumbers() {
        Pair<Integer, Integer> firstTwoPositiveNumbers = getFirstTwoPositiveNumbers(numbers);
        int firstPositiveNumber = firstTwoPositiveNumbers.getKey();
        int secondPositiveNumber = firstTwoPositiveNumbers.getValue();
        return gcd(firstPositiveNumber, secondPositiveNumber);
    }

    public int getLcmOfNumbers() {
        Pair<Integer, Integer> firstTwoPositiveNumbers = getFirstTwoPositiveNumbers(numbers);
        int firstPositiveNumber = firstTwoPositiveNumbers.getKey();
        int secondPositiveNumber = firstTwoPositiveNumbers.getValue();
        return lcm(firstPositiveNumber, secondPositiveNumber);
    }

    private Pair<Integer, Integer> getFirstTwoPositiveNumbers(int[] numbers) {
        int counter = 0;
        int firstPositiveNumber = -1;
        int secondPositiveNumber = -1;
        for (int i = 0; i < numbers.length; i++) {
            if (counter >= 2) {
                break;
            }
            if (numbers[i] > 0) {
                if (counter == 0) {
                    firstPositiveNumber = numbers[i];
                } else {
                    secondPositiveNumber = numbers[i];
                }
                counter++;
            }
        }
        return new Pair(firstPositiveNumber, secondPositiveNumber);
    }

    private int gcd(int a, int b) {
        if (a != -1 && b != -1) {
            return b == 0 ? a : gcd(b, a % b);
        }
        return -1;
    }

    private int lcm(int a, int b) {
        if (a != -1 && b != -1) {
            return a / gcd(a, b) * b;
        }
        return -1;
    }

    public int[] getPrimeNumbers() {
        return Arrays.stream(numbers).filter(number -> isPrime(number)).toArray();
    }

    private boolean isPrime(int number) {
        int temp;
        if (number < 1) {
            return false;
        }
        for (int i = 2; i <= number / 2; i++) {
            temp = number % i;
            if (temp == 0) {
                return false;
            }
        }
        return true;
    }

    public int[] getHappyNumbers() {
        return Arrays.stream(numbers).filter(number -> isHappyNumber(number)).toArray();
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

    public int[] getFibonacciNumbers() {
        return Arrays.stream(numbers).filter(number -> isFibonacci(number)).toArray();
    }

    private boolean isFibonacci(int number) {
        return (Math.sqrt(5 * number * number + 4) % 1 == 0 || Math.sqrt(5 * number * number - 4) % 1 == 0) && number > 0;
    }

    public int[] getPalindromeNumbers() {
        return Arrays.stream(numbers).filter(number -> isPalindrome(number)).toArray();
    }

    private boolean isPalindrome(int number) {
        String numberToString = String.valueOf(number);
        return numberToString.equals(new StringBuilder(numberToString).reverse().toString());
    }

    public int getDecimalPeriod() {
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
            return Integer.valueOf(decimalPeriod.toString());
        } else {
            return -1;
        }
    }

    private Pair<Integer, Integer> getFirstTwoConsecutivePositiveNumbers(int[] numbers) {
        int firstPositiveNumber = -1;
        int secondPositiveNumber = -1;
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > 0 && numbers[i - 1] > 0) {
                firstPositiveNumber = numbers[i - 1];
                secondPositiveNumber = numbers[i];
                break;
            }
        }
        return new Pair(firstPositiveNumber, secondPositiveNumber);
    }

    public String getPascalTriangle() {
        int firstPositiveNumber = -1;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] > 0) {
                firstPositiveNumber = numbers[i];
                break;
            }
        }
        if (firstPositiveNumber != -1) {
            int element;
            StringBuilder pascalTriangle = new StringBuilder();
            for (int y = 0; y < firstPositiveNumber; y++) {
                for (int i = 0; i < firstPositiveNumber - y; i++) {
                    pascalTriangle.append("   ");
                }
                for (int x = 0; x <= y; x++) {
                    element = factorial(y) / (factorial(x) * factorial(y - x));
                    pascalTriangle.append("   " + element + " ");
                }
                pascalTriangle.append("\n");
            }
            return pascalTriangle.toString();
        } else {
            return "Не удалось построить треугольник Паскаля! В массиве нет положительных чисел!";
        }
    }

    private int factorial(int number) {
        if (number < 0) {
            return -1;
        }
        if(number < 2){
            return 1;
        }
        return number*factorial(number - 1);
    }
}
