import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayOperationsTestNormalData {
    private ArrayOperations arrayOperations;

    @Before
    public void setUp() throws Exception {
        arrayOperations = new ArrayOperations(new int[]{4, 22, -3, 44, 13, 0, 75, 121});
    }

    @Test
    public void getEvenNumbers() {
        assertArrayEquals(new int[]{4, 22, 44, 0}, arrayOperations.getEvenNumbers());
    }

    @Test
    public void getOddNumbers() {
        assertArrayEquals(new int[]{-3, 13, 75, 121}, arrayOperations.getOddNumbers());
    }

    @Test
    public void getDivisibleThreeXNumbers() {
        assertArrayEquals(new int[]{-3, 0, 75}, arrayOperations.getDivisibleByXNumbers(3));
    }

    @Test
    public void getDivisibleByNineNumbers() {
        assertArrayEquals(new int[]{0}, arrayOperations.getDivisibleByXNumbers(9));
    }

    @Test
    public void getDivisibleByFiveNumbers() {
        assertArrayEquals(new int[]{0, 75}, arrayOperations.getDivisibleByXNumbers(5));
    }

    @Test
    public void getDivisibleByTenNumbers() {
        assertArrayEquals(new int[]{0}, arrayOperations.getDivisibleByXNumbers(10));
    }

    @Test
    public void getGcdOfNumbers() {
        assertEquals(2, arrayOperations.getGcdOfNumbers());
    }

    @Test
    public void getLcmOfNumbers() {
        assertEquals(44, arrayOperations.getLcmOfNumbers());
    }

    @Test
    public void getPrimeNumbers() {
        assertArrayEquals(new int[]{13}, arrayOperations.getPrimeNumbers());
    }

    @Test
    public void getHappyNumbers() {
        assertArrayEquals(new int[]{44, 13}, arrayOperations.getHappyNumbers());
    }

    @Test
    public void getFibonacciNumbers() {
        assertArrayEquals(new int[]{13}, arrayOperations.getFibonacciNumbers());
    }

    @Test
    public void getPalindromeNumbers() {
        assertArrayEquals(new int[]{4, 22, 44, 0, 121}, arrayOperations.getPalindromeNumbers());
    }

    @Test
    public void getDecimalPeriod() {
        assertEquals(18, arrayOperations.getDecimalPeriod());
    }

    @Test
    public void getPascalTriangle() {
        assertEquals("               1 \n            1    1 \n         1    2    1 \n      1    3    3    1 \n", arrayOperations.getPascalTriangle());
    }
}