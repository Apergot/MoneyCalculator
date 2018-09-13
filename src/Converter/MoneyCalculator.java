package Converter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class MoneyCalculator {
    public static void main(String[] args) throws Exception {
        MoneyCalculator moneyCalculator = new MoneyCalculator();
        moneyCalculator.control();
    }

    private double amount;
    private double exchangeRate;

    private void control() throws IOException{
        input();
        process();
        output();
    }

    private void input(){
        System.out.println("Welcome once again to MoneyCalculator\n");
        System.out.println("Introduce the amount of money you would like to exchange (USD to EUR): ");
        Scanner scanner = new Scanner(System.in);
        amount = scanner.nextDouble();
    }

    private void process() throws IOException{
        exchangeRate = getExchangeRate("USD", "EUR");
    }

    private void output(){
        System.out.println(amount + "USD are equivalent to "+ amount*exchangeRate +"EUR");
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
