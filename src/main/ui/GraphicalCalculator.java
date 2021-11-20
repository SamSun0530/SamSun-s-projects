package ui;

import model.Calculator;
import model.Log;
import model.Logs;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;


// Main class create a Graphical CalculatorAPP
public class GraphicalCalculator extends JFrame implements ActionListener {
    private static final String OPERATOR_STATE = "i";
    private JTextField field;
    private String operator = null;
    private String operand1;
    private String operand2;
    private int result;
    private String resultText;
    private String text;
    private int positionOfOperator;
    private Calculator calculator;
    private String tempOperator = OPERATOR_STATE;
    private Log log;
    private Logs logs = new Logs();
    private boolean isLogModel = false;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/logs.json";

    public static void main(String[] args) {
        new GraphicalCalculator();
    }

    public GraphicalCalculator() {
        setTitle("2 Integer inputs calculator");
        setSize();
        setScreen();
        setButtons();
        setBackground();
        pack();
    }

    // EFFECTS: set background
    public void setBackground() {
        getBackgroundPicture(this);
    }

    // EFFECTS: set background using picture
    public void getBackgroundPicture(JFrame contentPane) {
        ((JPanel)contentPane.getContentPane()).setOpaque(false);
        ImageIcon background = new ImageIcon("src/img/picture.png");

        JLabel bglabel = new JLabel(background);
        bglabel.setIcon(background);

        this.getLayeredPane().add(bglabel,new Integer(Integer.MIN_VALUE));
        bglabel.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
    }

    // EFFECTS: set the background of calculator
    private void setSize() {
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
        setButton("saveLog");
        setButton("loadLog");
        setButton("clearLog");
    }

    // EFFECTS: achieve functions including calculation, display on screen, search, save, load, clear logs using button
    @Override
    public void actionPerformed(ActionEvent e) {
        jsonReader = new JsonReader(JSON_STORE);
        if (e.getActionCommand().equals("loadLog")) {
            loadLogs();
        } else if (e.getActionCommand().equals("clearLog")) {
            logs = new Logs();
        } else if (e.getActionCommand().equals("saveLog")) {
            isLogModel = true;
            jsonWriter = new JsonWriter(JSON_STORE);
            saveLogs();
        } else if (!isLogModel) {
            calculation(e);
            screenDisplay(e);
        } else {
            searchLog(e);
        }
    }

    // EFFECTS: display the button input and calculation result to screen
    public void screenDisplay(ActionEvent e) {
        if (tempOperator.equals("=")) {
            text = "";
            displayToScreen(text);
            tempOperator = OPERATOR_STATE;
        }
        if (e.getActionCommand().equals("delete")) {
            text = field.getText().substring(0, field.getText().length() - 1);
            displayToScreen(text);
        } else if (!e.getActionCommand().equals("clearLog")
                && !e.getActionCommand().equals("saveLog")
                && !e.getActionCommand().equals("=")) {
            text = field.getText() + e.getActionCommand();
            displayToScreen(text);
        } else if (e.getActionCommand().equals("=")) {
            text = field.getText() + e.getActionCommand() + resultText;
            displayToScreen(text);
            log = new Log(text);
            logs.add(log);
            tempOperator = "=";
        }
    }

    // EFFECTS: calculate the result
    private void calculation(ActionEvent e) {
        if (e.getActionCommand().equals("+")
                || e.getActionCommand().equals("-")
                || e.getActionCommand().equals("*")
                || e.getActionCommand().equals("/")
                || e.getActionCommand().equals("^")) {
            operator = e.getActionCommand();
        }
        if (e.getActionCommand().equals("=") && operator != null) {
            positionOfOperator = text.indexOf(operator);
            operand1 = text.substring(0, positionOfOperator);
            operand2 = text.substring(positionOfOperator + 1);
            result = doCalculation(operator, Integer.parseInt(operand1), Integer.parseInt(operand2));
            resultText = String.valueOf(result);
        }
    }

    // EFFECTS: search log by the index number
    public void searchLog(ActionEvent e) {
        if (tempOperator.equals("=")) {
            text = "";
            displayToScreen(text);
            tempOperator = OPERATOR_STATE;
        }
        if (e.getActionCommand().equals("delete")) {
            text = field.getText().substring(0, field.getText().length() - 1);
            displayToScreen(text);
        } else if (!e.getActionCommand().equals("=")) {
            text = field.getText() + e.getActionCommand();
            displayToScreen(text);
        } else {
            getLogByIndex(Integer.parseInt(text));
            tempOperator = "=";
        }
    }

    // EFFECTS: display log with given index
    public void getLogByIndex(int indexNumber) {
        if (1 <= indexNumber && indexNumber <= logs.size()) {
            displayToScreen(logs.get(indexNumber - 1).getLog());
        }
    }

    // EFFECTS: set the format of text and display on screen
    public void displayToScreen(String text) {
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
            Logs localLogs = jsonReader.read();
            for (int i = 0; i < localLogs.size(); i++) {
                logs.add(localLogs.get(i));
            }
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
