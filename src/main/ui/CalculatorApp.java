package ui;

import model.Calculator;
import model.Log;
import model.Logs;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

// Calculator application scans the inputs and do the calculation
// This class uses codes from the JsonSerializationDemo-master
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
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
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/logs.json";

    // REQUIRES: Keyboard input must be one of plus, minus, multiply, divide, power or quit
    // EFFECTS: read the input of operation and values, print the result
    public CalculatorApp() {
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        load();
        while (true) {
            temp = getOperationInput();
            if (Objects.equals(temp, "quit")) {
                break;
            } else {
                operation = temp;
            }
            operand1 = getFirstValueInput();
            operand2 = getSecondValueInput();

            result = doCalculation(operation, operand1, operand2);
            log = new Log(operand1 + " " + operation + " " + operand2 + " = " + result);
            logs.add(log);

            clear();
        }
        System.out.println(logs);
        searchLog(logs);

        save();
    }

    // EFFECTS: assign the keyboard input to operation
    public String getOperationInput() {
        System.out.println("Select the operation from plus, minus, multiply, divide and power or quit: ");
        keyboardInput = new Scanner(System.in);
        String currentOperation = keyboardInput.nextLine();
        System.out.println(currentOperation);
        return currentOperation;
    }

    // EFFECTS: assign the keyboard input to first operand
    public int getFirstValueInput() {
        System.out.println("please enter the first operand: ");
        valueInput1 = new Scanner(System.in);
        return valueInput1.nextInt();
    }

    // EFFECTS: assign the keyboard input to second operand
    public int getSecondValueInput() {
        System.out.println("please enter the second operand: ");
        valueInput2 = new Scanner(System.in);
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
                "Any operation you want to search? Type index according to calculation sequence or 0 to leave");

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

    // EFFECTS: save the logs to json file
    public void save() {
        System.out.println("You can type save to save logs of calculation or any key to skip");
        keyboardInput = new Scanner(System.in);
        String temp = keyboardInput.nextLine();
        if (temp.equals("save")) {
            saveLogs();
            System.out.println("Logs of calculation saved");
        }
    }

    // EFFECTS: load the data from json file
    public void load() {
        System.out.println("You can type load to load logs of calculation or any key to skip");
        keyboardInput = new Scanner(System.in);
        String temp = keyboardInput.nextLine();
        if (temp.equals("load")) {
            loadLogs();
            System.out.println(logs);
        }
    }

    public void clear() {
        System.out.println("You can clear the logs by typing clear");
        keyboardInput = new Scanner(System.in);
        String clearCommand = keyboardInput.nextLine();
        if (clearCommand.equals("clear")) {
            logs = new Logs();
        }
    }

    // EFFECTS: saves the logs to file
    private void saveLogs() {
        try {
            jsonWriter.open();
            jsonWriter.write(logs);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads logs from file
    private void loadLogs() {
        try {
            logs = jsonReader.read();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
