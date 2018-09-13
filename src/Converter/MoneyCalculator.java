package Converter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class MoneyCalculator {

    double amount;
    double exchangeRate;
    String currencyFrom;
    String currencyTo;

    public static void main(String[] args) throws IOException {
        MoneyCalculator moneyCalculator = new MoneyCalculator();
        moneyCalculator.control();
    }

    private void control() throws IOException{
        input();
        process();
        output();
    }

    private void input(){
        System.out.println("Welcome once again to MoneyCalculator\n");
        System.out.println("Introduce the amount of money you would like to exchange: ");
        Scanner scanner = new Scanner(System.in);
        amount = scanner.nextDouble();

        System.out.println("Choose your actual currency: ");
        currencyFrom = scanner.next();

        System.out.println("Choose your desired currency: ");
        currencyTo = scanner.next();
    }

    private void process() throws IOException{
        exchangeRate = getExchangeRate(currencyFrom, currencyTo);
    }

    private void output(){
        System.out.println(amount + " "+currencyFrom+" are equivalent to "+ amount*exchangeRate +"EUR");
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
