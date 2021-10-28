package ui;

import model.Calculator;
import model.Log;
import model.Logs;
import java.util.Objects;
import java.util.Scanner;

// Calculator application scans the inputs and do the calculation
public class CalculatorApp {
    private int operand1;
    private int operand2;
    private String temp;
    private String operation;
    private Scanner valueInput1;
    private Scanner valueInput2;
    private Scanner keyboardInput;
    private Calculator calculator;
    private int result;
    private Log log;
    private Logs logs = new Logs();
    private Scanner searchIndex;
    private int indexNumber;

    // REQUIRES: Keyboard input must be one of plus, minus, multiply, divide, power or quit
    // EFFECTS: read the input of operation and values, print the result
    public CalculatorApp() {
        while (true) {
            System.out.println("Select the operation from plus, minus, multiply, divide and power or quit: ");
            keyboardInput = new Scanner(System.in);
            temp = getOperationInput();
            if (Objects.equals(temp, "quit")) {
                break;
            } else {
                operation = temp;
            }

            System.out.println("please enter the first operand: ");
            valueInput1 = new Scanner(System.in);
            operand1 = getFirstValueInput();

            System.out.println("please enter the second operand: ");
            valueInput2 = new Scanner(System.in);
            operand2 = getSecondValueInput();

            result = doCalculation(operation, operand1, operand2);
            log = new Log(operand1 + " " + operation + " " + operand2 + " = " + result);
            logs.add(log);
        }
        System.out.println(logs);

        searchLog(logs);
    }

    // EFFECTS: assign the keyboard input to operation
    public String getOperationInput() {
        String currentOperation = keyboardInput.nextLine();
        System.out.println(currentOperation);
        return currentOperation;
    }

    // EFFECTS: assign the keyboard input to first operand
    public int getFirstValueInput() {
        return valueInput1.nextInt();
    }

    // EFFECTS: assign the keyboard input to second operand
    public int getSecondValueInput() {
        return valueInput2.nextInt();
    }

    // EFFECTS: use operands and operation to do the calculation and print result
    public int doCalculation(String operation, int operand1, int operand2) {
        int result = 0;
        calculator = new Calculator();

        if (Objects.equals(operation, "plus")) {
            result = calculator.plus(operand1, operand2);
        } else if (Objects.equals(operation, "minus")) {
            result = calculator.minus(operand1, operand2);
        } else if (Objects.equals(operation, "multiply")) {
            result = calculator.multiply(operand1, operand2);
        } else if (Objects.equals(operation, "divide")) {
            result = calculator.divide(operand1, operand2);
        } else if (Objects.equals(operation, "power")) {
            result = calculator.power(operand1, operand2);
        }

        System.out.println(result);
        return result;
    }

    // REQUIRES: input cannot be negative number
    // EFFECTS: search the log history according to the index input
    public void searchLog(Logs logs) {
        System.out.println(
                "Any operation you want to search? Type index according to calculation sequence or -1 to leave");

        while (true) {
            searchIndex = new Scanner(System.in);
            indexNumber = searchIndex.nextInt();

            if (indexNumber == 0) {
                break;
            } else if (1 <= indexNumber && indexNumber <= logs.size()) {
                System.out.println(logs.get(indexNumber - 1));
            } else {
                System.out.println("The index is invalid, please type the index again");
            }
        }
    }
}
