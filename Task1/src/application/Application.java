package application;

import application.handlers.*;

import java.util.*;

public class Application {
    private int[] numbers;
    private Scanner consoleInputScanner;
    private ArrayOperations arrayOperations;
    private Map<Operation, Handler> handlers;

    {
        consoleInputScanner = new Scanner(System.in);
        numbers = arrayInput();
        System.out.println(Arrays.toString(numbers));
        arrayOperations = new ArrayOperations(numbers);
        handlers = new EnumMap<>(Operation.class);
        handlers.put(Operation.EVEN_NUMBERS, new EvenNumbersHandler());
        handlers.put(Operation.ODD_NUMBERS, new OddNumbersHandler());
        handlers.put(Operation.DIVISIBLE_BY_3_NUMBERS, new DivisibleByThreeNumbersHandler());
        handlers.put(Operation.DIVISIBLE_BY_9_NUMBERS, new DivisibleByNineNumbersHandler());
        handlers.put(Operation.DIVISIBLE_BY_5_NUMBERS, new DivisibleByFiveNumbersHandler());
        handlers.put(Operation.DIVISIBLE_BY_10_NUMBERS, new DivisibleByTenNumbersHandler());
        handlers.put(Operation.GCD, new GCDHandler());
        handlers.put(Operation.LCM, new LCMHandler());
        handlers.put(Operation.PRIME_NUMBERS, new PrimeNumbersHandler());
        handlers.put(Operation.HAPPY_NUMBERS, new HappyNumbersHandler());
        handlers.put(Operation.FIBONACCI_NUMBERS, new FibonacciNumbersHandler());
        handlers.put(Operation.PALINDROME_NUMBERS, new PalindromeNumbersHandler());
        handlers.put(Operation.DECIMAL_PERIOD, new DecimalPeriodHandler());
        handlers.put(Operation.PASCAL_TRIANGLE, new PascalTriangleHandler());
    }

    public void start() {
        System.out.println(handlers.get(Operation.EVEN_NUMBERS).handle(arrayOperations));
        System.out.println(handlers.get(Operation.ODD_NUMBERS).handle(arrayOperations));
        System.out.println(handlers.get(Operation.DIVISIBLE_BY_3_NUMBERS).handle(arrayOperations));
        System.out.println(handlers.get(Operation.DIVISIBLE_BY_9_NUMBERS).handle(arrayOperations));
        System.out.println(handlers.get(Operation.DIVISIBLE_BY_5_NUMBERS).handle(arrayOperations));
        System.out.println(handlers.get(Operation.DIVISIBLE_BY_10_NUMBERS).handle(arrayOperations));
        System.out.println(handlers.get(Operation.GCD).handle(arrayOperations));
        System.out.println(handlers.get(Operation.LCM).handle(arrayOperations));
        System.out.println(handlers.get(Operation.PRIME_NUMBERS).handle(arrayOperations));
        System.out.println(handlers.get(Operation.HAPPY_NUMBERS).handle(arrayOperations));
        System.out.println(handlers.get(Operation.FIBONACCI_NUMBERS).handle(arrayOperations));
        System.out.println(handlers.get(Operation.PALINDROME_NUMBERS).handle(arrayOperations));
        System.out.println(handlers.get(Operation.DECIMAL_PERIOD).handle(arrayOperations));
        System.out.println(handlers.get(Operation.PASCAL_TRIANGLE).handle(arrayOperations));
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

    private int[] arrayInput() {
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
        int[] array = new int[n];
        while (true) {
            try {
                System.out.println("Введите массив длины " + n + ":");
                for (int i = 0; i < n; i++) {
                    System.out.print("array[" + i + "] -> ");
                    array[i] = consoleInputScanner.nextInt();
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Неверный ввод! Попробуйте снова.");
                String inputCleaner = consoleInputScanner.nextLine();
            }
        }
        return array;
    }
}