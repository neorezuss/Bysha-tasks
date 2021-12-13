package application.tests;

import application.handlers.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ApplicationTestNormalData {
    private List<Integer> numbers;

    @Before
    public void setUp() throws Exception {
        numbers = new ArrayList<>();
        numbers.add(4);
        numbers.add(22);
        numbers.add(-3);
        numbers.add(44);
        numbers.add(13);
        numbers.add(0);
        numbers.add(75);
        numbers.add(121);
    }

    @Test
    public void evenNumbersHandler() {
        String evenNumbers = new EvenNumbersHandler().handle(numbers).toString();
        assertEquals("[4, 22, 44, 0]", evenNumbers);
    }

    @Test
    public void oddNumbersHandler() {
        String oddNumbers = new OddNumbersHandler().handle(numbers).toString();
        assertEquals("[-3, 13, 75, 121]", oddNumbers);
    }

    @Test
    public void divisibleByThreeNumbersHandler() {
        String divisibleByThreeNumbers = new DivisibleByThreeNumbersHandler().handle(numbers).toString();
        assertEquals("[-3, 0, 75]", divisibleByThreeNumbers);
    }

    @Test
    public void divisibleByNineNumbersHandler() {
        String divisibleByNineNumbers = new DivisibleByNineNumbersHandler().handle(numbers).toString();
        assertEquals("[0]", divisibleByNineNumbers);
    }

    @Test
    public void divisibleByFiveNumbersHandler() {
        String divisibleByFiveNumbers = new DivisibleByFiveNumbersHandler().handle(numbers).toString();
        assertEquals("[0, 75]", divisibleByFiveNumbers);
    }

    @Test
    public void divisibleByTenNumbersHandler() {
        String divisibleByTenNumbers = new DivisibleByTenNumbersHandler().handle(numbers).toString();
        assertEquals("[0]", divisibleByTenNumbers);
    }

    @Test
    public void gcdHandler() {
        String gcd = new GCDHandler().handle(numbers).toString();
        assertEquals("2", gcd);
    }

    @Test
    public void lcmHandler() {
        String lcm = new LCMHandler().handle(numbers).toString();
        assertEquals("44", lcm);
    }

    @Test
    public void primeNumbersHandler() {
        String primeNumbers = new PrimeNumbersHandler().handle(numbers).toString();
        assertEquals("[13]", primeNumbers);
    }

    @Test
    public void happyNumbersHandler() {
        String happyNumbers = new HappyNumbersHandler().handle(numbers).toString();
        assertEquals("[44, 13]", happyNumbers);
    }

    @Test
    public void fibonacciNumbersHandler() {
        String fibonacciNumbers = new FibonacciNumbersHandler().handle(numbers).toString();
        assertEquals("[13]", fibonacciNumbers);
    }

    @Test
    public void palindromeNumbersHandler() {
        String palindromeNumbers = new PalindromeNumbersHandler().handle(numbers).toString();
        assertEquals("[4, 22, 44, 0, 121]", palindromeNumbers);
    }

    @Test
    public void decimalPeriodHandler() {
        String decimalPeriod = new DecimalPeriodHandler().handle(numbers).toString();
        assertEquals("18", decimalPeriod);
    }

    @Test
    public void pascalTriangleHandler() {
        String pascalTriangle = new PascalTriangleHandler().handle(numbers).toString();
        assertEquals("Треугольник паскаля:\n               1 \n            1    1 \n         1    2    1 \n      1    3    3    1 \n", pascalTriangle);
    }
}