package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorDisplay extends JFrame implements ActionListener {
    private JTextField field;

    public CalculatorDisplay() {
        super("2 Integer inputs calculator");
        setBackground();
        setScreen();
        setButtons();
        pack();
    }

    private void setBackground() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(500, 750));
        setVisible(true);
        setResizable(false);
    }

    private void setScreen() {
        field = new JTextField(40);
        field.setPreferredSize(new Dimension(480, 100));
        field.setEditable(false);
        add(field);
    }

    private void setButton(String command) {
        JButton button = new JButton(command);
        button.setActionCommand(command);
        button.addActionListener(this);
        button.setPreferredSize(new Dimension(120, 40));
        JLabel label = new JLabel();
        add(button);
        add(label);
    }

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
        setButton("confirm");
        setButton("+");
        setButton("-");
        setButton("*");
        setButton("/");
        setButton("^");
        setButton("clear");
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
            System.out.println("");
        }
    }
}
