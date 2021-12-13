package application;

import application.handlers.*;

import java.util.*;

public class Application {
    private List<Integer> numbers;
    private Scanner consoleInputScanner;
    private Map<Operation, String> outputMap;

    {
        consoleInputScanner = new Scanner(System.in);
        numbers = listInput();
        System.out.println(numbers.toString());

        outputMap = new EnumMap<>(Operation.class);
        outputMap.put(Operation.EVEN_NUMBERS, evenNumbers(new EvenNumbersHandler()));
        outputMap.put(Operation.ODD_NUMBERS, oddNumbers(new OddNumbersHandler()));
        outputMap.put(Operation.DIVISIBLE_BY_3_NUMBERS, divisibleByThreeNumbers(new DivisibleByThreeNumbersHandler()));
        outputMap.put(Operation.DIVISIBLE_BY_9_NUMBERS, divisibleByNineNumbers(new DivisibleByNineNumbersHandler()));
        outputMap.put(Operation.DIVISIBLE_BY_5_NUMBERS, divisibleByFiveNumbers(new DivisibleByFiveNumbersHandler()));
        outputMap.put(Operation.DIVISIBLE_BY_10_NUMBERS, divisibleByTenNumbers(new DivisibleByTenNumbersHandler()));
        outputMap.put(Operation.GCD, gcd(new GCDHandler()));
        outputMap.put(Operation.LCM, lcm(new LCMHandler()));
        outputMap.put(Operation.PRIME_NUMBERS, primeNumbers(new PrimeNumbersHandler()));
        outputMap.put(Operation.HAPPY_NUMBERS, happyNumbers(new HappyNumbersHandler()));
        outputMap.put(Operation.FIBONACCI_NUMBERS, fibonacciNumbers(new FibonacciNumbersHandler()));
        outputMap.put(Operation.PALINDROME_NUMBERS, palindromeNumbers(new PalindromeNumbersHandler()));
        outputMap.put(Operation.DECIMAL_PERIOD, decimalPeriod(new DecimalPeriodHandler()));
        outputMap.put(Operation.PASCAL_TRIANGLE, pascalTriangle(new PascalTriangleHandler()));
    }

    public void start() {
        System.out.println(outputMap.get(Operation.EVEN_NUMBERS));
        System.out.println(outputMap.get(Operation.ODD_NUMBERS));
        System.out.println(outputMap.get(Operation.DIVISIBLE_BY_3_NUMBERS));
        System.out.println(outputMap.get(Operation.DIVISIBLE_BY_9_NUMBERS));
        System.out.println(outputMap.get(Operation.DIVISIBLE_BY_5_NUMBERS));
        System.out.println(outputMap.get(Operation.DIVISIBLE_BY_10_NUMBERS));
        System.out.println(outputMap.get(Operation.GCD));
        System.out.println(outputMap.get(Operation.LCM));
        System.out.println(outputMap.get(Operation.PRIME_NUMBERS));
        System.out.println(outputMap.get(Operation.HAPPY_NUMBERS));
        System.out.println(outputMap.get(Operation.FIBONACCI_NUMBERS));
        System.out.println(outputMap.get(Operation.PALINDROME_NUMBERS));
        System.out.println(outputMap.get(Operation.DECIMAL_PERIOD));
        System.out.println(outputMap.get(Operation.PASCAL_TRIANGLE));
    }

    enum Operation {
        EVEN_NUMBERS,
        ODD_NUMBERS,
        DIVISIBLE_BY_3_NUMBERS,
        DIVISIBLE_BY_9_NUMBERS,
        DIVISIBLE_BY_5_NUMBERS,
        DIVISIBLE_BY_10_NUMBERS,
        GCD,
        LCM,
        PRIME_NUMBERS,
        HAPPY_NUMBERS,
        FIBONACCI_NUMBERS,
        PALINDROME_NUMBERS,
        DECIMAL_PERIOD,
        PASCAL_TRIANGLE;
    }

    private List<Integer> listInput() {
        int n;
        while (true) {
            try {
                System.out.print("Введите размерность массива n -> ");
                n = consoleInputScanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Неверный ввод! Попробуйте снова.");
                String inputCleaner = consoleInputScanner.nextLine();
            }
        }
        List<Integer> list = new ArrayList<>();
        while (true) {
            try {
                System.out.println("Введите массив длины " + n + ":");
                for (int i = 0; i < n; i++) {
                    System.out.print("array[" + i + "] -> ");
                    list.add(consoleInputScanner.nextInt());
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Неверный ввод! Попробуйте снова.");
                String inputCleaner = consoleInputScanner.nextLine();
            }
        }
        return list;
    }

    public String evenNumbers(Handler evenNumbersHandler) {
        StringBuilder evenNumbers = new StringBuilder("Четные числа: ");
        List<Integer> evenNumbersList = (List<Integer>) evenNumbersHandler.handle(numbers);
        if (evenNumbersList.size() != 0) {
            evenNumbers.append(evenNumbersList.toString());
        } else evenNumbers.append("-");
        return evenNumbers.toString();
    }

    public String oddNumbers(Handler oddNumbersHandler) {
        StringBuilder oddNumbers = new StringBuilder("Нечетные числа: ");
        List<Integer> oddNumbersList = (List<Integer>) oddNumbersHandler.handle(numbers);
        if (oddNumbersList.size() != 0) {
            oddNumbers.append(oddNumbersList.toString());
        } else oddNumbers.append("-");
        return oddNumbers.toString();
    }

    public String divisibleByThreeNumbers(Handler divisibleByThreeNumbersHandler) {
        StringBuilder divisibleByThreeNumbers = new StringBuilder("Числа, которые делятся на 3: ");
        List<Integer> divisibleByThreeNumbersList = (List<Integer>) divisibleByThreeNumbersHandler.handle(numbers);
        if (divisibleByThreeNumbersList.size() != 0) {
            divisibleByThreeNumbers.append(divisibleByThreeNumbersList.toString());
        } else divisibleByThreeNumbers.append("-");
        return divisibleByThreeNumbers.toString();
    }

    public String divisibleByNineNumbers(Handler divisibleByNineNumbersHandler) {
        StringBuilder divisibleByNineNumbers = new StringBuilder("Числа, которые делятся на 9: ");
        List<Integer> divisibleByNineNumbersList = (List<Integer>) divisibleByNineNumbersHandler.handle(numbers);
        if (divisibleByNineNumbersList.size() != 0) {
            divisibleByNineNumbers.append(divisibleByNineNumbersList.toString());
        } else divisibleByNineNumbers.append("-");
        return divisibleByNineNumbers.toString();
    }

    public String divisibleByFiveNumbers(Handler divisibleByFiveNumbersHandler) {
        StringBuilder divisibleByFiveNumbers = new StringBuilder("Числа, которые делятся на 5: ");
        List<Integer> divisibleByFiveNumbersList = (List<Integer>) divisibleByFiveNumbersHandler.handle(numbers);
        if (divisibleByFiveNumbersList.size() != 0) {
            divisibleByFiveNumbers.append(divisibleByFiveNumbersList.toString());
        } else divisibleByFiveNumbers.append("-");
        return divisibleByFiveNumbers.toString();
    }

    public String divisibleByTenNumbers(Handler divisibleByFiveNumbersHandler) {
        StringBuilder divisibleByTenNumbers = new StringBuilder("Числа, которые делятся на 10: ");
        List<Integer> divisibleByTenNumbersList = (List<Integer>) divisibleByFiveNumbersHandler.handle(numbers);
        if (divisibleByTenNumbersList.size() != 0) {
            divisibleByTenNumbers.append(divisibleByTenNumbersList.toString());
        } else divisibleByTenNumbers.append("-");
        return divisibleByTenNumbers.toString();
    }

    public String gcd(Handler gcdHandler) {
        Integer gcd = (Integer) gcdHandler.handle(numbers);
        if (gcd != -1) {
            return "Наибольший общий делитель: " + gcd;
        } else {
            return "Массив содержит менее двух положительных чисел, посчитать НОД не удалось!";
        }
    }

    public String lcm(Handler lcmHandler) {
        Integer lcm = (Integer) lcmHandler.handle(numbers);
        if (lcm != -1) {
            return "Наименьшее общее кратное: " + lcm;
        } else {
            return "Массив содержит менее двух положительных чисел, посчитать НОК не удалось!";
        }
    }

    public String primeNumbers(Handler primeNumbersHandler) {
        StringBuilder primeNumbers = new StringBuilder("Простые числа: ");
        List<Integer> primeNumbersList = (List<Integer>) primeNumbersHandler.handle(numbers);
        if (primeNumbersList.size() != 0) {
            primeNumbers.append(primeNumbersList.toString());
        } else primeNumbers.append("-");
        return primeNumbers.toString();
    }

    public String happyNumbers(Handler happyNumbersHandler) {
        StringBuilder happyNumbers = new StringBuilder("Счастливые числа: ");
        List<Integer> happyNumbersList = (List<Integer>) happyNumbersHandler.handle(numbers);
        if (happyNumbersList.size() != 0) {
            happyNumbers.append(happyNumbersList.toString());
        } else happyNumbers.append("-");
        return happyNumbers.toString();
    }

    public String fibonacciNumbers(Handler fibonacciNumbersHandler) {
        StringBuilder fibonacciNumbers = new StringBuilder("Числа Фибоначчи: ");
        List<Integer> fibonacciNumbersList = (List<Integer>) fibonacciNumbersHandler.handle(numbers);
        if (fibonacciNumbersList.size() != 0) {
            fibonacciNumbers.append(fibonacciNumbersList.toString());
        } else fibonacciNumbers.append("-");
        return fibonacciNumbers.toString();
    }

    public String palindromeNumbers(Handler palindromeNumbersHandler) {
        StringBuilder palindromeNumbers = new StringBuilder("Числа-палиндромы: ");
        List<Integer> palindromeNumbersList = (List<Integer>) palindromeNumbersHandler.handle(numbers);
        if (palindromeNumbersList.size() != 0) {
            palindromeNumbers.append(palindromeNumbersList.toString());
        } else palindromeNumbers.append("-");
        return palindromeNumbers.toString();
    }

    public String decimalPeriod(Handler decimalPeriodHandler) {
        Integer decimalPeriod = (Integer) decimalPeriodHandler.handle(numbers);
        if (decimalPeriod != -1) {
            return "Период десятичной дроби: " + decimalPeriod;
        } else {
            return "Период дроби посчитать не удалось! Массив не содержит двух положительных чисел, расположенных подряд!";
        }
    }

    public String pascalTriangle(Handler pascalTriangleHandler) {
        String pascalTriangle = (String) pascalTriangleHandler.handle(numbers);
        return "Треугольник паскаля:\n" + pascalTriangle;
    }
}