package application.tests;

import application.handlers.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ApplicationTestInvalidData {
    private List<Integer> numbers;

    @Before
    public void setUp() throws Exception {
        numbers = new ArrayList<>();
        numbers.add(-7);
        numbers.add(-11);
    }

    @Test
    public void evenNumbersHandler() {
        String evenNumbers = new EvenNumbersHandler().handle(numbers).toString();
        assertEquals("[]", evenNumbers);
    }

    @Test
    public void oddNumbersHandler() {
        String oddNumbers = new OddNumbersHandler().handle(numbers).toString();
        assertEquals("[-7, -11]", oddNumbers);
    }

    @Test
    public void divisibleByThreeNumbersHandler() {
        String divisibleByThreeNumbers = new DivisibleByThreeNumbersHandler().handle(numbers).toString();
        assertEquals("[]", divisibleByThreeNumbers);
    }

    @Test
    public void divisibleByNineNumbersHandler() {
        String divisibleByNineNumbers = new DivisibleByNineNumbersHandler().handle(numbers).toString();
        assertEquals("[]", divisibleByNineNumbers);
    }

    @Test
    public void divisibleByFiveNumbersHandler() {
        String divisibleByFiveNumbers = new DivisibleByFiveNumbersHandler().handle(numbers).toString();
        assertEquals("[]", divisibleByFiveNumbers);
    }

    @Test
    public void divisibleByTenNumbersHandler() {
        String divisibleByTenNumbers = new DivisibleByTenNumbersHandler().handle(numbers).toString();
        assertEquals("[]", divisibleByTenNumbers);
    }

    @Test
    public void gcdHandler() {
        String gcd = new GCDHandler().handle(numbers).toString();
        assertEquals("-1", gcd);
    }

    @Test
    public void lcmHandler() {
        String lcm = new LCMHandler().handle(numbers).toString();
        assertEquals("-1", lcm);
    }

    @Test
    public void primeNumbersHandler() {
        String primeNumbers = new PrimeNumbersHandler().handle(numbers).toString();
        assertEquals("[]", primeNumbers);
    }

    @Test
    public void happyNumbersHandler() {
        String happyNumbers = new HappyNumbersHandler().handle(numbers).toString();
        assertEquals("[]", happyNumbers);
    }

    @Test
    public void fibonacciNumbersHandler() {
        String fibonacciNumbers = new FibonacciNumbersHandler().handle(numbers).toString();
        assertEquals("[]", fibonacciNumbers);
    }

    @Test
    public void palindromeNumbersHandler() {
        String palindromeNumbers = new PalindromeNumbersHandler().handle(numbers).toString();
        assertEquals("[]", palindromeNumbers);
    }

    @Test
    public void decimalPeriodHandler() {
        String decimalPeriod = new DecimalPeriodHandler().handle(numbers).toString();
        assertEquals("-1", decimalPeriod);
    }

    @Test
    public void pascalTriangleHandler() {
        String pascalTriangle = new PascalTriangleHandler().handle(numbers).toString();
        assertEquals("Не удалось построить треугольник Паскаля! В массиве нет положительных чисел!", pascalTriangle);
    }
}