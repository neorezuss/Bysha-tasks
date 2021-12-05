package application.tests;

import application.ArrayOperations;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayOperationsTestInvalidData {
    private ArrayOperations arrayOperations;

    @Before
    public void setUp() throws Exception {
        arrayOperations = new ArrayOperations(new int[]{-7, -11});
    }

    @Test
    public void getEvenNumbers() {
        assertArrayEquals(new int[]{}, arrayOperations.getEvenNumbers());
    }

    @Test
    public void getOddNumbers() {
        assertArrayEquals(new int[]{-7, -11}, arrayOperations.getOddNumbers());
    }

    @Test
    public void getDivisibleThreeXNumbers() {
        assertArrayEquals(new int[]{}, arrayOperations.getDivisibleByXNumbers(3));
    }

    @Test
    public void getDivisibleByNineNumbers() {
        assertArrayEquals(new int[]{}, arrayOperations.getDivisibleByXNumbers(9));
    }

    @Test
    public void getDivisibleByFiveNumbers() {
        assertArrayEquals(new int[]{}, arrayOperations.getDivisibleByXNumbers(5));
    }

    @Test
    public void getDivisibleByTenNumbers() {
        assertArrayEquals(new int[]{}, arrayOperations.getDivisibleByXNumbers(10));
    }

    @Test
    public void getGcdOfNumbers() {
        assertEquals(-1, arrayOperations.getGcdOfNumbers());
    }

    @Test
    public void getLcmOfNumbers() {
        assertEquals(-1, arrayOperations.getLcmOfNumbers());
    }

    @Test
    public void getPrimeNumbers() {
        assertArrayEquals(new int[]{}, arrayOperations.getPrimeNumbers());
    }

    @Test
    public void getHappyNumbers() {
        assertArrayEquals(new int[]{}, arrayOperations.getHappyNumbers());
    }

    @Test
    public void getFibonacciNumbers() {
        assertArrayEquals(new int[]{}, arrayOperations.getFibonacciNumbers());
    }

    @Test
    public void getPalindromeNumbers() {
        assertArrayEquals(new int[]{}, arrayOperations.getPalindromeNumbers());
    }

    @Test
    public void getDecimalPeriod() {
        assertEquals(-1, arrayOperations.getDecimalPeriod());
    }

    @Test
    public void getPascalTriangle() {
        assertEquals("Не удалось построить треугольник Паскаля! В массиве нет положительных чисел!", arrayOperations.getPascalTriangle());
    }
}