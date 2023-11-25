import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CurrencyConverter {
    private static Map<String, Double> exchangeRates;

    public static void main(String[] args) {
        exchangeRates = new HashMap<>();
        exchangeRates.put("EUR", 0.011);
        exchangeRates.put("GBP", 0.0094);
        exchangeRates.put("USD", 0.014);
        exchangeRates.put("AUD", 0.019);
        exchangeRates.put("CAD", 0.018);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Currency Converter");
        System.out.println("Enter '1' to convert from INR to another currency.");
        System.out.println("Enter '2' to convert from another currency to INR.");
        int choice = scanner.nextInt();

        if (choice == 1) {
            convertINRtoOtherCurrency(scanner);
        } else if (choice == 2) {
            convertOtherCurrencytoINR(scanner);
        } else {
            System.out.println("Invalid choice!");
        }
    }

    private static void convertINRtoOtherCurrency(Scanner scanner) {
        System.out.println("Enter the amount in Indian Rupees (INR): ");
        double amountInINR = scanner.nextDouble();

        System.out.println("Enter the currency code to convert to specified currency:");
        System.out.println("Available currency codes: EUR, GBP, USD, AUD, CAD");
        String currencyCode = scanner.next().toUpperCase();

        if (exchangeRates.containsKey(currencyCode)) {
            double convertedAmount = amountInINR * exchangeRates.get(currencyCode);
            System.out.println("Amount in INR: " + amountInINR + " INR");
            displayConvertedAmount(currencyCode, convertedAmount);
        } else {
            System.out.println("Invalid currency code entered!");
        }
    }

    private static void convertOtherCurrencytoINR(Scanner scanner) {
        System.out.println("Enter the amount:");
        double amountInCurrency = scanner.nextDouble();

        System.out.println("Enter the currency code to convert the amount to Indian rupees:");
        System.out.println("Available currency codes: EUR, GBP, USD, AUD, CAD");
        String currencyCode = scanner.next().toUpperCase();

        if (exchangeRates.containsKey(currencyCode)) {
            double convertedAmount = amountInCurrency / exchangeRates.get(currencyCode);
            System.out.println("Amount in " + currencyCode + ": " + amountInCurrency + " " + currencyCode);
            displayConvertedAmount("INR", convertedAmount);
        } else {
            System.out.println("Invalid currency code entered!");
        }
    }

    private static void displayConvertedAmount(String targetCurrency, double convertedAmount) {
        if ("EUR".equals(targetCurrency)) {
            targetCurrency = "Euros";
        } else if ("GBP".equals(targetCurrency)) {
            targetCurrency = "Pounds";
        } else if ("USD".equals(targetCurrency)) {
            targetCurrency = "US Dollars";
        } else if ("AUD".equals(targetCurrency)) {
            targetCurrency = "Australian Dollars";
        } else if ("CAD".equals(targetCurrency)) {
            targetCurrency = "Canadian Dollars";
        }
        System.out.println("Amount in " + targetCurrency + ": " + convertedAmount + " " + targetCurrency);
    }
}
