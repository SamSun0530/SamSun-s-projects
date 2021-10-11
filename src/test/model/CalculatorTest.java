package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    private Calculator cal;

    @BeforeEach
    public void setup() {
        cal = new Calculator();
    }

    @Test
    public void plusZeroZeroTest() {
        assertEquals(0, cal.plus(0, 0));
    }

    @Test
    public void plusPositivePositiveTest() {
        assertEquals(12, cal.plus(7, 5));
    }

    @Test
    public void plusNegativeNegativeTest() {
        assertEquals(-8, cal.plus(-5, -3));
    }

    @Test
    public void plusPositiveNegativeTest() {
        assertEquals(2, cal.plus(5, -3));
    }

    @Test
    public void minusZeroZeroTest() {
        assertEquals(0, cal.plus(0, 0));
    }

    @Test
    public void minusPositivePositiveTest() {
        assertEquals(-3, cal.minus(2, 5));
    }

    @Test
    public void minusPositiveNegativeTest() {
        assertEquals(12, cal.minus(5, -7));
    }

    @Test
    public void minusNegativePositiveTest() {
        assertEquals(-10, cal.minus(-2, 8));
    }

    @Test
    public void minusNegativeNegativeTest() {
        assertEquals(-3, cal.minus(-6, -3));
    }

    @Test
    public void productZeroZeroTest() {
        assertEquals(0, cal.product(0, 0));
    }

    @Test
    public void productPositivePositiveTest() {
        assertEquals(63, cal.product(7, 9));
    }

    @Test
    public void productPositiveNegativeTest() {
        assertEquals(-32, cal.product(4, -8));
    }

    @Test
    public void productNegativeNegativeTest() {
        assertEquals(15, cal.product(-3, -5));
    }

    @Test
    public void divideZeroPositiveTest() {
        assertEquals(0, cal.divide(0, 5));
    }

    @Test
    public void divideZeroNegativeTest() {
        assertEquals(0, cal.divide(0, -5));
    }

    @Test
    public void dividePositvePositiveTest() {
        assertEquals(5, cal.divide(10, 2));
    }

    @Test
    public void divideNegativePositiveTest() {
        assertEquals(-2, cal.divide(-6, 3));
    }

    @Test
    public void divideNegativeNegativeTest() {
        assertEquals(6, cal.divide(-18, -3));
    }

    @Test
    public void powerZeroZeroTest() {
        assertEquals(0, cal.power(0, 0));
    }

    @Test
    public void powerPositiveZeroTest() {
        assertEquals(1, cal.power(5, 0));
    }

    @Test
    public void powerNegativeZeroTest() {
        assertEquals(1, cal.power(-7, 0));
    }

    @Test
    public void powerPositivePositveTest() {
        assertEquals(27, cal.power(3, 3));
    }

    @Test
    public void powerNegativeOddTest() {
        assertEquals(-64, cal.power(-4, 3));
    }

    @Test
    public void powerNegativeEvenTest() {
        assertEquals(25, cal.power(-5, 2));
    }
}