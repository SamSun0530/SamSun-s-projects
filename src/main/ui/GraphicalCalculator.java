package ui;

import model.Calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;


// Main class create a CalculatorAPP
public class GraphicalCalculator extends JFrame implements ActionListener {
    private JTextField field;
    private String operator = null;
    private String operand1;
    private String operand2;
    private int result;
    private String text;
    private String resultText;
    private int positionOfOperator;
    private Calculator calculator;

    public static void main(String[] args) {
        new GraphicalCalculator();
    }

    public GraphicalCalculator() {
        setTitle("2 Integer inputs calculator");
        setBackground();
        setScreen();
        setButtons();
        pack();
    }

    // EFFECTS: set the background of calculator
    private void setBackground() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(500, 750));
        setVisible(true);
        setResizable(false);
    }

    // EFFECTS: set the screen of calculator
    private void setScreen() {
        field = new JTextField(40);
        field.setPreferredSize(new Dimension(480, 100));
        field.setEditable(false);
        add(field);
    }

    // EFFECTS: set the individual button
    private void setButton(String command) {
        JButton button = new JButton(command);
        button.setActionCommand(command);
        button.addActionListener(this);
        button.setPreferredSize(new Dimension(120, 40));
        JLabel label = new JLabel();
        add(button);
        add(label);
    }

    // EFFECTS: set all the buttons
    private void setButtons() {
        setButton("7");
        setButton("8");
        setButton("9");
        setButton("4");
        setButton("5");
        setButton("6");
        setButton("1");
        setButton("2");
        setButton("3");
        setButton("delete");
        setButton("0");
        setButton("=");
        setButton("+");
        setButton("-");
        setButton("*");
        setButton("/");
        setButton("^");
        setButton("clear");
        setButton("logs");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        calculation(e);
        displayOnScreen(e);
    }

    public void displayOnScreen(ActionEvent e) {
        if (e.getActionCommand().equals("delete")) {
            text = field.getText().substring(0, field.getText().length() - 1);
            displayToScreen(text);
        } else if (!e.getActionCommand().equals("clear")
                && !e.getActionCommand().equals("logs")
                && !e.getActionCommand().equals("=")) {
            text = field.getText() + e.getActionCommand();
            displayToScreen(text);
        } else if (e.getActionCommand().equals("=")) {
            text = field.getText() + e.getActionCommand() + resultText;
            displayToScreen(text);
            // I want to reset the text
        }
    }

    private void calculation(ActionEvent e) {
        if (e.getActionCommand().equals("+")
                || e.getActionCommand().equals("-")
                || e.getActionCommand().equals("*")
                || e.getActionCommand().equals("/")
                || e.getActionCommand().equals("^")) {
            operator = e.getActionCommand();
        }
        if (e.getActionCommand().equals("=") && operator != null) {
            findOperands();
        }
    }

    private void findOperands() {
        positionOfOperator = text.indexOf(operator);
        operand1 = text.substring(0, positionOfOperator);
        operand2 = text.substring(positionOfOperator + 1);
        result = doCalculation(operator, Integer.parseInt(operand1), Integer.parseInt(operand2));
        resultText = String.valueOf(result);
    }

    private void displayToScreen(String text) {
        field.setText(text);
        Font font = new Font("SansSerif", Font.BOLD, 50);
        field.setFont(font);
    }

    // EFFECTS: use operands and operation to do the calculation and print result
    public int doCalculation(String operation, int operand1, int operand2) {
        int result = 0;
        calculator = new Calculator();

        if (Objects.equals(operation, "+")) {
            result = calculator.plus(operand1, operand2);
        } else if (Objects.equals(operation, "-")) {
            result = calculator.minus(operand1, operand2);
        } else if (Objects.equals(operation, "*")) {
            result = calculator.multiply(operand1, operand2);
        } else if (Objects.equals(operation, "/")) {
            result = calculator.divide(operand1, operand2);
        } else if (Objects.equals(operation, "^")) {
            result = calculator.power(operand1, operand2);
        }

        return result;
    }
}
