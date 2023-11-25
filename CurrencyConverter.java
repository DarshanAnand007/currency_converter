import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConverterUI extends JFrame {
    private Map<String, Double> exchangeRates;

    private JTextField amountField;
    private JComboBox<String> conversionTypeComboBox;
    private JComboBox<String> currencyComboBox;
    private JTextArea resultArea;

    public CurrencyConverterUI() {
        exchangeRates = new HashMap<>();
        exchangeRates.put("EUR", 0.011);
        exchangeRates.put("GBP", 0.0094);
        exchangeRates.put("USD", 0.014);
        exchangeRates.put("AUD", 0.019);
        exchangeRates.put("CAD", 0.018);
        exchangeRates.put("JPY", 1.52);  // Japanese Yen
        exchangeRates.put("CNY", 0.086); // Chinese Yuan
        exchangeRates.put("BRL", 0.0025); // Brazilian Real
        exchangeRates.put("INR", 1.0);   // Indian Rupee
        exchangeRates.put("ZAR", 0.22);  // South African Rand
        exchangeRates.put("MXN", 0.072); // Mexican Peso
        exchangeRates.put("RUB", 1.15);  // Russian Ruble
        exchangeRates.put("CHF", 0.013); // Swiss Franc
        exchangeRates.put("SEK", 0.11);  // Swedish Krona

        setTitle("Currency Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        addComponents();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addComponents() {
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Enter Amount:"));
        amountField = new JTextField(10);
        inputPanel.add(amountField);

        inputPanel.add(new JLabel("Select Conversion Type:"));
        conversionTypeComboBox = new JComboBox<>(new String[]{"INR to Another Currency", "Another Currency to INR"});
        inputPanel.add(conversionTypeComboBox);

        inputPanel.add(new JLabel("Select Currency:"));
        currencyComboBox = new JComboBox<>(exchangeRates.keySet().toArray(new String[0]));
        inputPanel.add(currencyComboBox);

        JButton convertButton = new JButton("Convert");
        convertButton.addActionListener(e -> convert());
        inputPanel.add(convertButton);

        add(inputPanel);

        resultArea = new JTextArea(10, 20);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        add(scrollPane);
    }

    private void convert() {
        String conversionType = (String) conversionTypeComboBox.getSelectedItem();
        double amount = Double.parseDouble(amountField.getText());
        String currency = (String) currencyComboBox.getSelectedItem();

        if (exchangeRates.containsKey(currency)) {
            double convertedAmount;

            if (conversionType.equals("INR to Another Currency")) {
                convertedAmount = amount * exchangeRates.get(currency);
                resultArea.setText("Result: " + amount + " INR is approximately " + convertedAmount + " " + currency);
            } else if (conversionType.equals("Another Currency to INR")) {
                convertedAmount = amount / exchangeRates.get(currency);
                resultArea.setText("Result: " + amount + " " + currency + " is approximately " + convertedAmount + " INR");
            }
        } else {
            resultArea.setText("Invalid currency selected!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CurrencyConverterUI();
        });
    }
}
