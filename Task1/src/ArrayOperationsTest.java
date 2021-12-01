import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArrayOperationsTest {
    private ArrayOperations arrayOperations;

    @Before
    public void setUp() throws Exception {
        arrayOperations = new ArrayOperations(new int[]{4, 22, -3, 44, 13, 0, 75, 121});
    }

    @Test
    public void getEvenNumbers() {
        assertEquals("Четные числа: 4, 22, 44, 0", arrayOperations.getEvenNumbers());
    }

    @Test
    public void getOddNumbers() {
        assertEquals("Нечетные числа: -3, 13, 75, 121", arrayOperations.getOddNumbers());
    }

    @Test
    public void getDivisibleByThreeNumbers() {
        assertEquals("Числа, которые делятся на 3: -3, 0, 75", arrayOperations.getDivisibleByXNumbers(3));
    }

    @Test
    public void getDivisibleByNineNumbers() {
        assertEquals("Числа, которые делятся на 9: 0", arrayOperations.getDivisibleByXNumbers(9));
    }

    @Test
    public void getDivisibleByFiveNumbers() {
        assertEquals("Числа, которые делятся на 5: 0, 75", arrayOperations.getDivisibleByXNumbers(5));
    }

    @Test
    public void getDivisibleByTenNumbers() {
        assertEquals("Числа, которые делятся на 10: 0", arrayOperations.getDivisibleByXNumbers(10));
    }

    @Test
    public void getGcdOfNumbers() {
        assertEquals("НОД чисел 4 и 22 равен 2", arrayOperations.getGcdOfNumbers());
    }

    @Test
    public void getLcmOfNumbers() {
        assertEquals("НОК чисел 4 и 22 равен 44", arrayOperations.getLcmOfNumbers());
    }

    @Test
    public void getPrimeNumbers() {
        assertEquals("Простые числа: 13", arrayOperations.getPrimeNumbers());
    }

    @Test
    public void getHappyNumbers() {
        assertEquals("Cчастливые числа: 44, 13", arrayOperations.getHappyNumbers());
    }

    @Test
    public void getFibonacciNumbers() {
        assertEquals("Числа Фибоначчи: 13", arrayOperations.getFibonacciNumbers());
    }

    @Test
    public void getPalindromeNumbers() {
        assertEquals("Числа-палиндромы: 4, 22, 44, 0, 121", arrayOperations.getPalindromeNumbers());
    }

    @Test
    public void getDecimalPeriod() {
        assertEquals("Период десятичной дроби p = m/n для m = 4 и n = 22: 0.(18)", arrayOperations.getDecimalPeriod());
    }

    @Test
    public void getPascalTriangle() {
        assertEquals("               1 \n            1    1 \n         1    2    1 \n      1    3    3    1 \n", arrayOperations.getPascalTriangle());
    }
}