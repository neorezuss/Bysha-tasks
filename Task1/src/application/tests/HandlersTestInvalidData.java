package application.tests;

import application.ArrayOperations;
import application.handlers.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HandlersTestInvalidData {
    private ArrayOperations arrayOperations;

    @Before
    public void setUp() throws Exception {
        arrayOperations = new ArrayOperations(new int[]{-7, -11});
    }

    @Test
    public void evenNumbersHandler() {
        EvenNumbersHandler evenNumbersHandler = new EvenNumbersHandler();
        assertEquals("Четные числа: -", evenNumbersHandler.handle(arrayOperations));
    }

    @Test
    public void oddNumbersHandler() {
        OddNumbersHandler oddNumbersHandler = new OddNumbersHandler();
        assertEquals("Нечетные числа: [-7, -11]", oddNumbersHandler.handle(arrayOperations));
    }

    @Test
    public void divisibleByThreeNumbersHandler() {
        DivisibleByThreeNumbersHandler divisibleByThreeNumbersHandler = new DivisibleByThreeNumbersHandler();
        assertEquals("Числа, которые делятся на 3: -", divisibleByThreeNumbersHandler.handle(arrayOperations));
    }

    @Test
    public void divisibleByNineNumbersHandler() {
        DivisibleByNineNumbersHandler divisibleByNineNumbersHandler = new DivisibleByNineNumbersHandler();
        assertEquals("Числа, которые делятся на 9: -", divisibleByNineNumbersHandler.handle(arrayOperations));
    }

    @Test
    public void divisibleByFiveNumbersHandler() {
        DivisibleByFiveNumbersHandler divisibleByFiveNumbersHandler = new DivisibleByFiveNumbersHandler();
        assertEquals("Числа, которые делятся на 5: -", divisibleByFiveNumbersHandler.handle(arrayOperations));
    }

    @Test
    public void divisibleByTenNumbersHandler() {
        DivisibleByTenNumbersHandler divisibleByTenNumbersHandler = new DivisibleByTenNumbersHandler();
        assertEquals("Числа, которые делятся на 10: -", divisibleByTenNumbersHandler.handle(arrayOperations));
    }

    @Test
    public void gcdHandler() {
        GCDHandler gcdHandler = new GCDHandler();
        assertEquals("Массив содержит менее двух положительных чисел, посчитать НОД не удалось!", gcdHandler.handle(arrayOperations));
    }

    @Test
    public void lcmHandler() {
        LCMHandler lcmHandler = new LCMHandler();
        assertEquals("Массив содержит менее двух положительных чисел, посчитать НОК не удалось!", lcmHandler.handle(arrayOperations));
    }

    @Test
    public void primeNumbersHandler() {
        PrimeNumbersHandler primeNumbersHandler = new PrimeNumbersHandler();
        assertEquals("Простые числа: -", primeNumbersHandler.handle(arrayOperations));
    }

    @Test
    public void happyNumbersHandler() {
        HappyNumbersHandler happyNumbersHandler = new HappyNumbersHandler();
        assertEquals("Счастливые числа: -", happyNumbersHandler.handle(arrayOperations));
    }

    @Test
    public void fibonacciNumbersHandler() {
        FibonacciNumbersHandler fibonacciNumbersHandler = new FibonacciNumbersHandler();
        assertEquals("Числа Фибоначчи: -", fibonacciNumbersHandler.handle(arrayOperations));
    }

    @Test
    public void palindromeNumbersHandler() {
        PalindromeNumbersHandler palindromeNumbersHandler = new PalindromeNumbersHandler();
        assertEquals("Числа-палиндромы: -", palindromeNumbersHandler.handle(arrayOperations));
    }

    @Test
    public void decimalPeriodHandler() {
        DecimalPeriodHandler decimalPeriodHandler = new DecimalPeriodHandler();
        assertEquals("Период дроби посчитать не удалось! Массив не содержит двух положительных чисел, расположенных подряд!", decimalPeriodHandler.handle(arrayOperations));
    }

    @Test
    public void pascalTriangleHandler() {
        PascalTriangleHandler pascalTriangleHandler = new PascalTriangleHandler();
        assertEquals("Треугольник паскаля:\nНе удалось построить треугольник Паскаля! В массиве нет положительных чисел!", pascalTriangleHandler.handle(arrayOperations));
    }
}