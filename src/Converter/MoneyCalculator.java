package Converter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class MoneyCalculator {
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome once again to MoneyCalculator\n");
        System.out.println("Introduce the amount of money you would like to exchange (USD to EUR): ");
        Scanner scanner = new Scanner(System.in);
        double dollars = scanner.nextDouble();
        double exchangeRate = getExchangeRate("USD", "EUR");
        System.out.println(dollars + "USD are equivalent to "+ dollars*exchangeRate +"EUR");
    }

    private static double getExchangeRate(String from, String to) throws IOException {
        URL url = new URL("http://free.currencyconverterapi.com/api/v5/convert?q=" + from +"_" +to+ "&compact=y");
        URLConnection connection = url.openConnection();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))){
            String line = reader.readLine();
            String line1 = line.substring(line.indexOf(to)+12, line.indexOf("}"));
            return Double.parseDouble(line1);
        }
    }
}
