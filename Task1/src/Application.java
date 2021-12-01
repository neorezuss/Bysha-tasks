import java.util.InputMismatchException;
import java.util.Scanner;

public class Application {
    private int[] numbers;
    private Scanner consoleInputScanner;

    public Application() {
        consoleInputScanner = new Scanner(System.in);
    }

    public void start() {
        numbers = this.arrayInput();
        this.printArray(numbers);
        ArrayOperations arrayOperations = new ArrayOperations(numbers);
        System.out.println("1. Четные и нечетные числа");
        System.out.println(arrayOperations.getEvenNumbers());
        System.out.println(arrayOperations.getOddNumbers());
        System.out.println("2. Числа, которые делятся на 3 или на 9");
        System.out.println(arrayOperations.getDivisibleByXNumbers(3));
        System.out.println(arrayOperations.getDivisibleByXNumbers(9));
        System.out.println("3. Числа, которые делятся на 5 или на 10");
        System.out.println(arrayOperations.getDivisibleByXNumbers(5));
        System.out.println(arrayOperations.getDivisibleByXNumbers(10));
        System.out.println("4. Наибольший общий делитель и наименьшее общее кратное этих чисел");
        System.out.println(arrayOperations.getGcdOfNumbers());
        System.out.println(arrayOperations.getLcmOfNumbers());
        System.out.println("5. Простые числа");
        System.out.println(arrayOperations.getPrimeNumbers());
        System.out.println("6. “Счастливые” числа");
        System.out.println(arrayOperations.getHappyNumbers());
        System.out.println("7. Числа Фибоначчи");
        System.out.println(arrayOperations.getFibonacciNumbers());
        System.out.println("8. Числа-палиндромы");
        System.out.println(arrayOperations.getPalindromeNumbers());
        System.out.println("9. Период десятичной дроби p = m/n");
        System.out.println(arrayOperations.getDecimalPeriod());
        System.out.println("10. Треугольник Паскаля");
        System.out.println(arrayOperations.getPascalTriangle());
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

    private void printArray(int[] numbers) {
        StringBuilder arrayToString = new StringBuilder("[");
        for (int i = 0; i < numbers.length; i++) {
            if (i != numbers.length - 1) {
                arrayToString.append(numbers[i] + ", ");
            } else {
                arrayToString.append(numbers[i] + "]");
            }
        }
        System.out.println("Текущий массив " + arrayToString);
    }
}
