/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moneycalculator;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
/**
 *
 * @author Dhinesh Mangtani Madani
 */
public class MoneyCalculator {
    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        MoneyCalculator moneyCalculator = new MoneyCalculator();
        moneyCalculator.execute();
    }
    private double amount;
    private double exchangeRate;
    private String currencyFrom;
    private String currencyTo;
    private void execute() throws Exception{
        input();
        proces();
        output();
    }
    private void input() throws Exception{
        System.out.println("Introduzca una cantidad");
        Scanner scanner = new Scanner(System.in);
        amount = Double.parseDouble(scanner.next());
        System.out.println("Introduzca una divisa de origen");
        currencyFrom = scanner.next();
        System.out.println("Introduzca una divisa de destino");
        currencyTo = scanner.next();
    }
    private void proces() throws Exception{
        exchangeRate = getExchangeRate("USD","EUR");
    }
    private void output() throws Exception{
        System.out.println(amount+" "+currencyFrom+"equivalen a "+amount*exchangeRate+" "+currencyTo);
    }
    private static double getExchangeRate(String from, String to) throws Exception{
        URL url = new URL("http://api.fixer.io/latest?base=" +from+"&symbols="+to);
        HttpURLConnection connection =(HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        InputStreamReader input = new InputStreamReader(connection.getInputStream());
        try(BufferedReader reader = new BufferedReader(input)){
            String line =reader.readLine();
            line = line.substring(line.indexOf(to)+5,line.indexOf("}"));
            return Double.parseDouble(line);
        }
    }
    
}
