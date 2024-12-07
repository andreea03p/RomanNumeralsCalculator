package operations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI extends JFrame
{
    private JTextField displayField;
    private StringBuilder currentInput = new StringBuilder();
    private String firstOperand = "";
    private String operator = "";

    public CalculatorGUI()
    {
        // Setup the frame
        setTitle("Roman Numeral Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 600);
        setLayout(new BorderLayout());

        // Create the display field
        displayField = new JTextField();
        displayField.setFont(new Font("Arial", Font.BOLD, 24));
        displayField.setEditable(false);
        add(displayField, BorderLayout.NORTH);

        // Create button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4));

        // Roman numeral buttons
        String[] romanButtons = {"I", "V", "X", "L", "C", "D", "M"};
        for (String roman : romanButtons)
        {
            JButton button = new JButton(roman);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.addActionListener(e -> {
                currentInput.append(roman);
                displayField.setText(currentInput.toString());
            });
            buttonPanel.add(button);
        }

        // Operation buttons
        String[] operations = {"+", "-", "*", "/", "="};
        for (String operation : operations)
        {
            JButton button = new JButton(operation);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.addActionListener(new OperationListener());
            buttonPanel.add(button);
        }

        // Clear button
        JButton clearButton = new JButton("Clear");
        clearButton.setFont(new Font("Arial", Font.BOLD, 20));
        clearButton.addActionListener(e -> {
            currentInput.setLength(0);
            firstOperand = "";
            operator = "";
            displayField.setText("");
        });
        buttonPanel.add(clearButton);

        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private class OperationListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String command = e.getActionCommand();

            if (command.equals("="))
            {
                String secondOperand = currentInput.toString();
                if (!isValidRomanNumeral(firstOperand))
                {
                    displayField.setText("Error: Invalid input: " + firstOperand);
                    clearInputFields();
                    return;
                }

                if (!isValidRomanNumeral(secondOperand))
                {
                    displayField.setText("Error: Invalid input: " + secondOperand);
                    clearInputFields();
                    return;
                }

                try {
                    String result = performCalculation(firstOperand, secondOperand, operator);
                    displayField.setText(firstOperand + " " + operator + " " + secondOperand + " = " + result);
                    clearInputFields();
                } catch (Exception ex) {
                    displayField.setText("Error: " + ex.getMessage());
                }
            }
            else
            {
                if (!firstOperand.isEmpty() && !operator.isEmpty())
                {
                    return;
                }
                firstOperand = currentInput.toString();
                operator = command;
                currentInput.setLength(0);
                displayField.setText(firstOperand + " " + operator);
            }
        }
    }

    public boolean isValidRomanNumeral(String roman)
    {
        return roman.matches("^(M{0,3})(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$");
    }

    private String performCalculation(String first, String second, String operator)
    {
        switch (operator)
        {
            case "+":
                return new Addition().calculate(first, second);
            case "-":
                return new Subtraction().calculate(first, second);
            case "*":
                return new Multiplication().calculate(first, second);
            case "/":
                return new Division().calculate(first, second);
            default:
                throw new IllegalArgumentException("Unknown operation");
        }
    }

    // Clear inputs for rewriting the operation
    private void clearInputFields()
    {
        currentInput.setLength(0);
        firstOperand = "";
        operator = "";
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(CalculatorGUI::new);
    }
}
