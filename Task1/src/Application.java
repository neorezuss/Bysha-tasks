import java.util.Arrays;
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

        Operation operation = Operation.EVEN_NUMBERS;
        operation.print(numbers);
        operation = Operation.ODD_NUMBERS;
        operation.print(numbers);
        operation = Operation.DIVISIBLE_BY_3_NUMBERS;
        operation.print(numbers);
        operation = Operation.DIVISIBLE_BY_9_NUMBERS;
        operation.print(numbers);
        operation = Operation.DIVISIBLE_BY_5_NUMBERS;
        operation.print(numbers);
        operation = Operation.DIVISIBLE_BY_10_NUMBERS;
        operation.print(numbers);
        operation = Operation.GCD;
        operation.print(numbers);
        operation = Operation.LCM;
        operation.print(numbers);
        operation = Operation.PRIME_NUMBERS;
        operation.print(numbers);
        operation = Operation.HAPPY_NUMBERS;
        operation.print(numbers);
        operation = Operation.FIBONACCI_NUMBERS;
        operation.print(numbers);
        operation = Operation.PALINDROME_NUMBERS;
        operation.print(numbers);
        operation = Operation.DECIMAL_PERIOD;
        operation.print(numbers);
        operation = Operation.PASCAL_TRIANGLE;
        operation.print(numbers);
    }

    enum Operation {
        EVEN_NUMBERS {
            @Override
            public void print(int[] numbers) {
                ArrayOperations arrayOperations = new ArrayOperations(numbers);
                System.out.print("Четные числа: ");
                if (arrayOperations.getEvenNumbers().length != 0) {
                    printArray(arrayOperations.getEvenNumbers());
                } else System.out.println("-");
            }
        },
        ODD_NUMBERS {
            @Override
            public void print(int[] numbers) {
                ArrayOperations arrayOperations = new ArrayOperations(numbers);
                System.out.print("Нечетные числа: ");
                if (arrayOperations.getOddNumbers().length != 0) {
                    printArray(arrayOperations.getOddNumbers());
                } else System.out.println("-");
            }
        },
        DIVISIBLE_BY_3_NUMBERS {
            @Override
            public void print(int[] numbers) {
                ArrayOperations arrayOperations = new ArrayOperations(numbers);
                System.out.print("Числа, которые делятся на 3: ");
                if (arrayOperations.getDivisibleByXNumbers(3).length != 0) {
                    printArray(arrayOperations.getDivisibleByXNumbers(3));
                } else System.out.println("-");
            }
        },
        DIVISIBLE_BY_9_NUMBERS {
            @Override
            public void print(int[] numbers) {
                ArrayOperations arrayOperations = new ArrayOperations(numbers);
                System.out.print("Числа, которые делятся на 9: ");
                if (arrayOperations.getDivisibleByXNumbers(9).length != 0) {
                    printArray(arrayOperations.getDivisibleByXNumbers(9));
                } else System.out.println("-");
            }
        },
        DIVISIBLE_BY_5_NUMBERS {
            @Override
            public void print(int[] numbers) {
                ArrayOperations arrayOperations = new ArrayOperations(numbers);
                System.out.print("Числа, которые делятся на 5: ");
                if (arrayOperations.getDivisibleByXNumbers(5).length != 0) {
                    printArray(arrayOperations.getDivisibleByXNumbers(5));
                } else System.out.println("-");
            }
        },
        DIVISIBLE_BY_10_NUMBERS {
            @Override
            public void print(int[] numbers) {
                ArrayOperations arrayOperations = new ArrayOperations(numbers);
                System.out.print("Числа, которые делятся на 10: ");
                if (arrayOperations.getDivisibleByXNumbers(10).length != 0) {
                    printArray(arrayOperations.getDivisibleByXNumbers(10));
                } else System.out.println("-");
            }
        },
        GCD {
            @Override
            public void print(int[] numbers) {
                ArrayOperations arrayOperations = new ArrayOperations(numbers);
                if (arrayOperations.getGcdOfNumbers() != -1) {
                    System.out.println("Наибольший общий делитель: " + arrayOperations.getGcdOfNumbers());
                } else System.out.println("Массив содержит менее двух положительных чисел, посчитать НОД не удалось!");
            }
        },
        LCM {
            @Override
            public void print(int[] numbers) {
                ArrayOperations arrayOperations = new ArrayOperations(numbers);
                if (arrayOperations.getLcmOfNumbers() != -1) {
                    System.out.println("Наименьшее общее кратное: " + arrayOperations.getLcmOfNumbers());
                } else System.out.println("Массив содержит менее двух положительных чисел, посчитать НОК не удалось!");
            }
        },
        PRIME_NUMBERS {
            @Override
            public void print(int[] numbers) {
                ArrayOperations arrayOperations = new ArrayOperations(numbers);
                System.out.print("Простые числа: ");
                if (arrayOperations.getPrimeNumbers().length != 0) {
                    printArray(arrayOperations.getPrimeNumbers());
                } else System.out.println("-");
            }
        },
        HAPPY_NUMBERS {
            @Override
            public void print(int[] numbers) {
                ArrayOperations arrayOperations = new ArrayOperations(numbers);
                System.out.print("Счастливые числа: ");
                if (arrayOperations.getHappyNumbers().length != 0) {
                    printArray(arrayOperations.getHappyNumbers());
                } else System.out.println("-");
            }
        },
        FIBONACCI_NUMBERS {
            @Override
            public void print(int[] numbers) {
                ArrayOperations arrayOperations = new ArrayOperations(numbers);
                System.out.print("Числа Фибоначчи: ");
                if (arrayOperations.getFibonacciNumbers().length != 0) {
                    printArray(arrayOperations.getFibonacciNumbers());
                } else System.out.println("-");
            }
        },
        PALINDROME_NUMBERS {
            @Override
            public void print(int[] numbers) {
                ArrayOperations arrayOperations = new ArrayOperations(numbers);
                System.out.print("Числа-палиндромы: ");
                if (arrayOperations.getPalindromeNumbers().length != 0) {
                    printArray(arrayOperations.getPalindromeNumbers());
                } else System.out.println("-");
            }
        },
        DECIMAL_PERIOD {
            @Override
            public void print(int[] numbers) {
                ArrayOperations arrayOperations = new ArrayOperations(numbers);
                if (arrayOperations.getDecimalPeriod() != -1) {
                    System.out.println("Период десятичной дроби: " + arrayOperations.getDecimalPeriod());
                } else
                    System.out.println("Период дроби посчитать не удалось! Массив не содержит двух положительных чисел, расположенных подряд!");
            }
        },
        PASCAL_TRIANGLE {
            @Override
            public void print(int[] numbers) {
                ArrayOperations arrayOperations = new ArrayOperations(numbers);
                System.out.println("Треугольник паскаля:");
                System.out.println(arrayOperations.getPascalTriangle());
            }
        };

        public abstract void print(int[] numbers);
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

    public static void printArray(int[] numbers) {
        System.out.println(Arrays.toString(numbers));
    }
}
