package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Main class create a CalculatorAPP
public class GraphicalCalculator extends JFrame implements ActionListener {
    private JTextField field;

    public GraphicalCalculator() {
//        setButtons();
//        pack();
    }


//    private void setButton(String command) {
//        JButton button = new JButton(command);
//        button.setActionCommand(command);
//        button.addActionListener(this);
//        button.setPreferredSize(new Dimension(120, 40));
//        JLabel label = new JLabel();
//        add(button);
//        add(label);
//    }
//
//    private void setButtons() {
//        setButton("7");
//        setButton("8");
//        setButton("9");
//        setButton("4");
//        setButton("5");
//        setButton("6");
//        setButton("1");
//        setButton("2");
//        setButton("3");
//        setButton("delete");
//        setButton("0");
//        setButton("confirm");
//        setButton("+");
//        setButton("-");
//        setButton("*");
//        setButton("/");
//        setButton("^");
//        setButton("clear");
//    }

    public static void main(String[] args) {
        new GraphicalCalculator();
        new CalculatorDisplay();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String text = field.getText() + e.getActionCommand();
        field.setText(text);
        Font font = new Font("SansSerif", Font.BOLD, 50);
        field.setFont(font);

        if (e.getActionCommand().equals("+")
                || e.getActionCommand().equals("-")
                || e.getActionCommand().equals("*")
                || e.getActionCommand().equals("/")
                || e.getActionCommand().equals("^")) {

        }
    }
}
