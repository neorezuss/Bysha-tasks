import java.util.HashSet;
import java.util.Set;

public class ArrayOperations {
    private int[] numbers;

    public ArrayOperations(int[] numbers) {
        this.numbers = numbers;
    }

    public String getEvenNumbers() {
        StringBuilder evenNumbers = new StringBuilder("Четные числа: ");
        for (int number : numbers) {
            if (number % 2 == 0) {
                evenNumbers.append(number + ", ");
            }
        }
        if (evenNumbers.length() == "Четные числа: ".length()) {
            evenNumbers.append(" - ");
        } else {
            evenNumbers.setLength(evenNumbers.length() - 2);
        }
        return evenNumbers.toString();
    }

    public String getOddNumbers() {
        StringBuilder oddNumbers = new StringBuilder("Нечетные числа: ");
        for (int number : numbers) {
            if (number % 2 == 1 || number % 2 == -1) {
                oddNumbers.append(number + ", ");
            }
        }
        if (oddNumbers.length() == "Нечетные числа: ".length()) {
            oddNumbers.append("-");
        } else {
            oddNumbers.setLength(oddNumbers.length() - 2);
        }
        return oddNumbers.toString();
    }

    public String getDivisibleByXNumbers(int x) {
        StringBuilder divisibleByXNumbers = new StringBuilder("Числа, которые делятся на " + x + ": ");
        for (int number : numbers) {
            if (number % x == 0) {
                divisibleByXNumbers.append(number + ", ");
            }
        }
        if (divisibleByXNumbers.length() == ("Числа, которые делятся на " + x + ": ").length()) {
            divisibleByXNumbers.append("-");
        } else {
            divisibleByXNumbers.setLength(divisibleByXNumbers.length() - 2);
        }
        return divisibleByXNumbers.toString();
    }

    public String getGcdOfNumbers() {
        int[] firstTwoPositiveNumbers = getFirstTwoPositiveNumbers(numbers);
        int firstPositiveNumber = firstTwoPositiveNumbers[0];
        int secondPositiveNumber = firstTwoPositiveNumbers[1];
        if (firstPositiveNumber != -1 && secondPositiveNumber != -1) {
            return "НОД чисел " + firstPositiveNumber + " и " + secondPositiveNumber +
                    " равен " + gcd(firstPositiveNumber, secondPositiveNumber);
        }
        return "Массив содержит менее двух положительных чисел, посчитать НОД не удалось!";
    }

    public String getLcmOfNumbers() {
        int[] firstTwoPositiveNumbers = getFirstTwoPositiveNumbers(numbers);
        int firstPositiveNumber = firstTwoPositiveNumbers[0];
        int secondPositiveNumber = firstTwoPositiveNumbers[1];
        if (firstPositiveNumber != -1 || secondPositiveNumber != -1) {
            return "НОК чисел " + firstPositiveNumber + " и " + secondPositiveNumber +
                    " равен " + lcm(firstPositiveNumber, secondPositiveNumber);
        }
        return "Массив содержит менее двух положительных чисел, посчитать НОК не удалось!";
    }

    private int[] getFirstTwoPositiveNumbers(int[] numbers) {
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
        return new int[]{firstPositiveNumber, secondPositiveNumber};
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    private int lcm(int a, int b) {
        return a / gcd(a, b) * b;
    }

    public String getPrimeNumbers() {
        StringBuilder primeNumbers = new StringBuilder("Простые числа: ");
        for (int number : numbers) {
            if (isPrime(number)) {
                primeNumbers.append(number + ", ");
            }
        }
        if (primeNumbers.length() == "Простые числа: ".length()) {
            primeNumbers.append("-");
        } else {
            primeNumbers.setLength(primeNumbers.length() - 2);
        }
        return primeNumbers.toString();
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

    public String getHappyNumbers() {
        StringBuilder happyNumbers = new StringBuilder("Cчастливые числа: ");
        for (int number : numbers) {
            if (isHappyNumber(number)) {
                happyNumbers.append(number + ", ");
            }
        }
        if (happyNumbers.length() == "Cчастливые числа: ".length()) {
            happyNumbers.append("-");
        } else {
            happyNumbers.setLength(happyNumbers.length() - 2);
        }
        return happyNumbers.toString();
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

    public String getFibonacciNumbers() {
        StringBuilder fibonacciNumbers = new StringBuilder("Числа Фибоначчи: ");
        for (int number : numbers) {
            if (isFibonacci(number)) {
                fibonacciNumbers.append(number + ", ");
            }
        }
        if (fibonacciNumbers.length() == "Числа Фибоначчи: ".length()) {
            fibonacciNumbers.append("-");
        } else {
            fibonacciNumbers.setLength(fibonacciNumbers.length() - 2);
        }
        return fibonacciNumbers.toString();
    }

    private boolean isFibonacci(int number) {
        return (Math.sqrt(5 * number * number + 4) % 1 == 0 || Math.sqrt(5 * number * number - 4) % 1 == 0) && number > 0;
    }

    public String getPalindromeNumbers() {
        StringBuilder palindromeNumbers = new StringBuilder("Числа-палиндромы: ");
        for (int number : numbers) {
            if (isPalindrome(number)) {
                palindromeNumbers.append(number + ", ");
            }
        }
        if (palindromeNumbers.length() == "Числа-палиндромы: ".length()) {
            palindromeNumbers.append("-");
        } else {
            palindromeNumbers.setLength(palindromeNumbers.length() - 2);
        }
        return palindromeNumbers.toString();
    }

    private boolean isPalindrome(int number) {
        String numberToString = String.valueOf(number);
        return numberToString.equals(new StringBuilder(numberToString).reverse().toString());
    }

    public String getDecimalPeriod() {
        int[] firstTwoPositiveNumbers = getFirstTwoConsecutivePositiveNumbers(numbers);
        int firstPositiveNumber = firstTwoPositiveNumbers[0];
        int secondPositiveNumber = firstTwoPositiveNumbers[1];
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
            StringBuilder decimalPeriod = new StringBuilder("Период десятичной дроби p = m/n для ");
            decimalPeriod.append("m = " + firstPositiveNumber + " и n = " + secondPositiveNumber + ": ");
            decimalPeriod.append((int) firstPositiveNumber / secondPositiveNumber + ".");
            for (i = 0; i < l; i++) {
                r = (r * 10) % n;
            }
            for (i = 0; r != t; i++) {
                decimalPeriod.append(t * 10 / n);
                r = (r * 10) % n;
                t = (t * 10) % n;
            }
            decimalPeriod.append('(');
            for (i = 0; i < l; i++) {
                decimalPeriod.append(t * 10 / n);
                t = (t * 10) % n;
            }
            decimalPeriod.append(')');
            return decimalPeriod.toString();
        } else {
            return "Период дроби посчитать не удалось! Массив не содержит двух положительных чисел, расположенных подряд!";
        }
    }

    private int[] getFirstTwoConsecutivePositiveNumbers(int[] numbers) {
        int firstPositiveNumber = -1;
        int secondPositiveNumber = -1;
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > 0 && numbers[i - 1] > 0) {
                firstPositiveNumber = numbers[i - 1];
                secondPositiveNumber = numbers[i];
                break;
            }
        }
        return new int[]{firstPositiveNumber, secondPositiveNumber};
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
        int result = 1;
        for (int i = 1; i <= number; i++) {
            result = result * i;
        }
        return result;
    }
}
