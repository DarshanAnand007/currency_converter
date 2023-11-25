import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CurrencyConverter {
    private static Map<String, CurrencyInfo> exchangeRates;

    public static void main(String[] args) {
        exchangeRates = new HashMap<>();
        exchangeRates.put("EUR", new CurrencyInfo(0.011, "€")); // Euro
        exchangeRates.put("GBP", new CurrencyInfo(0.0094, "£")); // British Pound
        exchangeRates.put("USD", new CurrencyInfo(0.014, "$")); // US Dollar
        exchangeRates.put("AUD", new CurrencyInfo(0.019, "A$")); // Australian Dollar
        exchangeRates.put("CAD", new CurrencyInfo(0.018, "CA$")); // Canadian Dollar
        exchangeRates.put("JPY", new CurrencyInfo(1.53, "¥")); // Japanese Yen
        exchangeRates.put("INR", new CurrencyInfo(1.0, "₹")); // Indian Rupee (base currency)

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Currency Converter");
        System.out.println("Enter '1' to convert from INR to another country's currency.");
        System.out.println("Enter '2' to convert from another country's currency to INR.");
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

        System.out.println("Enter the country name to convert to its currency:");
        System.out.println("Available countries: Germany, United Kingdom, United States, Australia, Canada, Japan");
        String countryName = scanner.next();

        CurrencyInfo currencyInfo = getCurrencyInfoByCountry(countryName);

        if (currencyInfo != null) {
            double convertedAmount = amountInINR * currencyInfo.getExchangeRate();
            System.out.println("Amount in INR: " + amountInINR + " INR");
            displayConvertedAmount(countryName, convertedAmount, currencyInfo.getCurrencySymbol());
        } else {
            System.out.println("Invalid country name entered!");
        }
    }

    private static void convertOtherCurrencytoINR(Scanner scanner) {
        System.out.println("Enter the amount:");
        double amountInCurrency = scanner.nextDouble();

        System.out.println("Enter the country name to convert its currency to Indian rupees:");
        System.out.println("Available countries: Germany, United Kingdom, United States, Australia, Canada, Japan");
        String countryName = scanner.next();

        CurrencyInfo currencyInfo = getCurrencyInfoByCountry(countryName);

        if (currencyInfo != null) {
            double convertedAmount = amountInCurrency / currencyInfo.getExchangeRate();
            System.out.println("Amount in " + countryName + "'s currency: " + amountInCurrency + " " + currencyInfo.getCurrencySymbol());
            displayConvertedAmount("INR", convertedAmount, "₹");
        } else {
            System.out.println("Invalid country name entered!");
        }
    }

    private static void displayConvertedAmount(String targetCurrency, double convertedAmount, String currencySymbol) {
        System.out.println("Amount in " + targetCurrency + ": " + currencySymbol + " " + convertedAmount + " " + targetCurrency);
    }

    private static CurrencyInfo getCurrencyInfoByCountry(String countryName) {
        switch (countryName.toLowerCase()) {
            case "germany":
                return exchangeRates.get("EUR");
            case "united kingdom":
                return exchangeRates.get("GBP");
            case "united states":
                return exchangeRates.get("USD");
            case "australia":
                return exchangeRates.get("AUD");
            case "canada":
                return exchangeRates.get("CAD");
            case "japan":
                return exchangeRates.get("JPY");
            default:
                return null;
        }
    }

    private static class CurrencyInfo {
        private double exchangeRate;
        private String currencySymbol;

        public CurrencyInfo(double exchangeRate, String currencySymbol) {
            this.exchangeRate = exchangeRate;
            this.currencySymbol = currencySymbol;
        }

        public double getExchangeRate() {
            return exchangeRate;
        }

        public String getCurrencySymbol() {
            return currencySymbol;
        }
    }
}
