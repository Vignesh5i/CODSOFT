import java.util.Scanner;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CurrencyConverter {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("========== CURRENCY CONVERTER ==========");

            System.out.print("Enter Base Currency (e.g., USD, INR, EUR): ");
            String baseCurrency = sc.next().toUpperCase();
            
            System.out.print("Enter Target Currency (e.g., INR, USD, GBP): ");
            String targetCurrency = sc.next().toUpperCase();

            String urlString = "https://open.er-api.com/v6/latest/" + baseCurrency;
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                System.out.print("Enter the amount to convert: ");
                double amount = sc.nextDouble();

                String jsonResponse = response.toString();
                
                String searchKey = "\"" + targetCurrency + "\":";
                int index = jsonResponse.indexOf(searchKey);
                
                if (index != -1) {
                    int start = index + searchKey.length();
                    int end = jsonResponse.indexOf(",", start);
                    if (end == -1) end = jsonResponse.indexOf("}", start);
                    
                    double exchangeRate = Double.parseDouble(jsonResponse.substring(start, end));
                    double convertedAmount = amount * exchangeRate;

                    System.out.println("\n---------------------------------------");
                    System.out.println("Base Currency: " + baseCurrency);
                    System.out.println("Target Currency: " + targetCurrency);
                    System.out.println("Exchange Rate: " + exchangeRate);
                    System.out.println("Converted Amount: " + String.format("%.2f", convertedAmount) + " " + targetCurrency);
                    System.out.println("---------------------------------------");
                } else {
                    System.out.println("Error: Target currency not found in API data.");
                }
            } else {
                System.out.println("Error: API Connection Failed (Check your internet).");
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}