package application.tests;

import application.ArrayOperations;
import application.handlers.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HandlersTestNormalData {
    private ArrayOperations arrayOperations;

    @Before
    public void setUp() throws Exception {
        arrayOperations = new ArrayOperations(new int[]{4, 22, -3, 44, 13, 0, 75, 121});
    }

    @Test
    public void evenNumbersHandler() {
        EvenNumbersHandler evenNumbersHandler = new EvenNumbersHandler();
        assertEquals("Четные числа: [4, 22, 44, 0]", evenNumbersHandler.handle(arrayOperations));
    }

    @Test
    public void oddNumbersHandler() {
        OddNumbersHandler oddNumbersHandler = new OddNumbersHandler();
        assertEquals("Нечетные числа: [-3, 13, 75, 121]", oddNumbersHandler.handle(arrayOperations));
    }

    @Test
    public void divisibleByThreeNumbersHandler() {
        DivisibleByThreeNumbersHandler divisibleByThreeNumbersHandler = new DivisibleByThreeNumbersHandler();
        assertEquals("Числа, которые делятся на 3: [-3, 0, 75]", divisibleByThreeNumbersHandler.handle(arrayOperations));
    }

    @Test
    public void divisibleByNineNumbersHandler() {
        DivisibleByNineNumbersHandler divisibleByNineNumbersHandler = new DivisibleByNineNumbersHandler();
        assertEquals("Числа, которые делятся на 9: [0]", divisibleByNineNumbersHandler.handle(arrayOperations));
    }

    @Test
    public void divisibleByFiveNumbersHandler() {
        DivisibleByFiveNumbersHandler divisibleByFiveNumbersHandler = new DivisibleByFiveNumbersHandler();
        assertEquals("Числа, которые делятся на 5: [0, 75]", divisibleByFiveNumbersHandler.handle(arrayOperations));
    }

    @Test
    public void divisibleByTenNumbersHandler() {
        DivisibleByTenNumbersHandler divisibleByTenNumbersHandler = new DivisibleByTenNumbersHandler();
        assertEquals("Числа, которые делятся на 10: [0]", divisibleByTenNumbersHandler.handle(arrayOperations));
    }

    @Test
    public void gcdHandler() {
        GCDHandler gcdHandler = new GCDHandler();
        assertEquals("Наибольший общий делитель: 2", gcdHandler.handle(arrayOperations));
    }

    @Test
    public void lcmHandler() {
        LCMHandler lcmHandler = new LCMHandler();
        assertEquals("Наименьшее общее кратное: 44", lcmHandler.handle(arrayOperations));
    }

    @Test
    public void primeNumbersHandler() {
        PrimeNumbersHandler primeNumbersHandler = new PrimeNumbersHandler();
        assertEquals("Простые числа: [13]", primeNumbersHandler.handle(arrayOperations));
    }

    @Test
    public void happyNumbersHandler() {
        HappyNumbersHandler happyNumbersHandler = new HappyNumbersHandler();
        assertEquals("Счастливые числа: [44, 13]", happyNumbersHandler.handle(arrayOperations));
    }

    @Test
    public void fibonacciNumbersHandler() {
        FibonacciNumbersHandler fibonacciNumbersHandler = new FibonacciNumbersHandler();
        assertEquals("Числа Фибоначчи: [13]", fibonacciNumbersHandler.handle(arrayOperations));
    }

    @Test
    public void palindromeNumbersHandler() {
        PalindromeNumbersHandler palindromeNumbersHandler = new PalindromeNumbersHandler();
        assertEquals("Числа-палиндромы: [4, 22, 44, 0, 121]", palindromeNumbersHandler.handle(arrayOperations));
    }

    @Test
    public void decimalPeriodHandler() {
        DecimalPeriodHandler decimalPeriodHandler = new DecimalPeriodHandler();
        assertEquals("Период десятичной дроби: 18", decimalPeriodHandler.handle(arrayOperations));
    }

    @Test
    public void pascalTriangleHandler() {
        PascalTriangleHandler pascalTriangleHandler = new PascalTriangleHandler();
        assertEquals("Треугольник паскаля:\n               1 \n            1    1 \n         1    2    1 \n      1    3    3    1 \n", pascalTriangleHandler.handle(arrayOperations));
    }
}