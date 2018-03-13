package com.code.challenge;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class App 
{
    public static void main( String[] args ) throws IOException, JSONException {
        User manager = new Manager();
        JSONHandler jsonHandler = new JSONHandler();

        List<Payment> incomingPayment = jsonHandler.calculatePayments(jsonHandler.readJSONFile("src/main/resources/payments-in.json"));
        List <Payment> outgoingPayment = jsonHandler.calculatePayments(jsonHandler.readJSONFile("src/main/resources/payments-out.json"));

        System.out.println("------------------------ Incoming Payments ------------------------");
        incomingPayment.stream().forEach(System.out::println);
        System.out.println("------------------------ Outcoming Payments ------------------------");
        outgoingPayment.stream().forEach(System.out::println);

        incomingPayment.stream().forEach(payment -> manager.addIncomingPayment(payment));
        outgoingPayment.stream().forEach(payment -> manager.addOutgoingPayment(payment));

        Map<String, Integer> consolidatedPayments = manager.getPayments().getConsolidatedPayments();
        System.out.println("------------------------ Account Total ------------------------");
        consolidatedPayments.forEach((k,v)->System.out.println("Account : " + k + " total amount : " + v));

    }
}
