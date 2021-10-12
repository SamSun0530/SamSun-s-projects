package model;

// 5 math operation include plus, minus, multiply, divide and power
public class Calculator {

    public Calculator() {

    }

    //EFFECTS: add 2 values together
    public int plus(int operand1, int operand2) {
        return operand1 + operand2;
    }

    //EFFECTS: first value subtract second value
    public int minus(int operand1, int operand2) {
        return operand1 - operand2;
    }

    //EFFECTS: multiply 2 values together
    public int multiply(int operand1, int operand2) {
        return operand1 * operand2;
    }

    //REQUIRES: second operand cannot be 0, first operand can only be exact divided by second operand
    //EFFECTS: first value divides second value
    public int divide(int operand1, int operand2) {
        return operand1 / operand2;
    }

    //REQUIRES: exponential cannot be negative
    //EFFECTS: Power of value
    public int power(int base, int exponential) {
        int result = 1;

        if (base == 0) {
            result = 0;
        } else if (exponential == 0) {
            result = 1;
        } else {
            for (int i0 = exponential; i0 > 0; i0--) {
                result = result * base;
            }
        }
        return result;
    }
}
