package com.code.challenge;

import org.json.JSONException;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class PaymentInteractionsTest {

    @Test
    public void testEmptyPaymentInteractions() {
        Payments payments = new Manager().getPayments();

        assertNoIncomingAndOutgoingPaymentTransactions(payments);
    }

    @Test
    public void testValidPaymentInteractionsWithOneIncomingPaymentAndNoOutgoingPayment() {
        User manager = new Manager();
        Payment incomingPayment = new Payment("UK", "000001",
                "571986", "THEIRSENDREF001", "31122016", "GBP", "975000", "");

        manager.addIncomingPayment(incomingPayment);
        Payments payments = manager.getPayments();

        assertOneValidIncomingPaymentTransaction(payments, incomingPayment, 1, 0);
    }

    @Test
    public void testValidPaymentInteractionsWithTwoIncomingAndOutgoingPayment() {
        User manager = new Manager();
        Payment incomingPayment1 = new Payment("UK", "000001",
                "571986", "THEIRSENDREF001", "31122016", "GBP", "975000", "");
        Payment incomingPayment2 = new Payment("UK", "000001",
                "4564568", "THEIRSENDREF002", "01012017", "GBP", "40000", "Alise1");
        Payment outgoingPayment1 = new Payment("xxxxxx", "148652",
                "000001", "SENDREF001", "31122016", "GBP", "575000", "");
        Payment outgoingPayment2 = new Payment("UK", "976859",
                "000001", "SENDREF002", "31122016", "GBP", "235000", "");

        manager.addIncomingPayment(incomingPayment1);
        manager.addIncomingPayment(incomingPayment2);
        manager.addOutgoingPayment(outgoingPayment1);
        manager.addOutgoingPayment(outgoingPayment2);
        Payments payments = manager.getPayments();

        assertValidMultiplePaymentTransaction(payments, incomingPayment1, outgoingPayment1);
    }

    @Test
    public void testReadPaymentsFromFileAndCalculateMultipleIncomingAndOutgoingPayments() throws IOException, JSONException {

        User manager = new Manager();
        JSONHandler jsonHandler = new JSONHandler();

        List <Payment> incomingPayment = jsonHandler.calculatePayments(jsonHandler.readJSONFile("src/main/resources/payments-in.json"));
        List <Payment> outgoingPayment = jsonHandler.calculatePayments(jsonHandler.readJSONFile("src/main/resources/payments-out.json"));

        incomingPayment.stream().forEach(payment -> manager.addIncomingPayment(payment));
        outgoingPayment.stream().forEach(payment -> manager.addOutgoingPayment(payment));

        assertIncomingAndOutgoingPaymentTransactions(manager.getPayments(), 4, 5);
    }

    @Test
    public void testAllPaymentsAreConsolidatedByAccount() throws IOException, JSONException {

        User manager = new Manager();
        JSONHandler jsonHandler = new JSONHandler();

        List <Payment> incomingPayment = jsonHandler.calculatePayments(jsonHandler.readJSONFile("src/main/resources/payments-in.json"));
        List <Payment> outgoingPayment = jsonHandler.calculatePayments(jsonHandler.readJSONFile("src/main/resources/payments-out.json"));
        incomingPayment.stream().forEach(payment -> manager.addIncomingPayment(payment));
        outgoingPayment.stream().forEach(payment -> manager.addOutgoingPayment(payment));
        Map <String, Integer> consolidatedPayments = manager.getPayments().getConsolidatedPayments();

        assertThat(consolidatedPayments.get("000001"), is(495000));
        assertThat(consolidatedPayments.get("000002"), is(-606000));
    }

    private void assertValidMultiplePaymentTransaction(Payments payments, Payment incomingPayment1, Payment outgoingPayment1) {
        assertNotNull(payments);
        assertThat(payments.getIncoming().size(),is(2));
        assertThat(payments.getOutgoing().size(),is(2));
        assertThat(payments.getOutgoing().get(0).getMessageIdentifier(),is(incomingPayment1.getMessageIdentifier()));
        assertThat(payments.getOutgoing().get(0).getAmount(),is(outgoingPayment1.getAmount()));
        assertThat(payments.getOutgoing().get(0).getCurrency(),is(outgoingPayment1.getCurrency()));
        assertThat(payments.getOutgoing().get(0).getReceiverAccountRef(),is(outgoingPayment1.getReceiverAccountRef()));
        assertThat(payments.getOutgoing().get(0).getSenderAccountRef(),is(outgoingPayment1.getSenderAccountRef()));
        assertThat(payments.getOutgoing().get(0).getValueDate(),is(outgoingPayment1.getValueDate()));
        assertThat(payments.getOutgoing().get(0).getReceiverCountry(),is(outgoingPayment1.getReceiverCountry()));
        assertThat(payments.getOutgoing().get(0).getSendersPaymentReference(),is(outgoingPayment1.getSendersPaymentReference()));
    }

    private void assertNoIncomingAndOutgoingPaymentTransactions(Payments payments) {
        assertNotNull(payments);
        assertThat(payments.getIncoming().size(),is(0));
        assertThat(payments.getOutgoing().size(),is(0));
    }

    private void assertIncomingAndOutgoingPaymentTransactions(Payments payments, int incomingSize, int outgoingSize) {
        assertNotNull(payments);
        assertThat(payments.getIncoming().size(),is(incomingSize));
        assertThat(payments.getOutgoing().size(),is(outgoingSize));
    }

    private void assertOneValidIncomingPaymentTransaction(Payments payments, Payment incomingPayment, int incomingSize, int outgoingSize) {
        assertNotNull(payments);
        assertThat(payments.getIncoming().size(),is(incomingSize));
        assertThat(payments.getOutgoing().size(),is(outgoingSize));
        assertThat(payments.getIncoming().get(0).getMessageIdentifier(),is(incomingPayment.getMessageIdentifier()));
        assertThat(payments.getIncoming().get(0).getAmount(),is(incomingPayment.getAmount()));
        assertThat(payments.getIncoming().get(0).getCurrency(),is(incomingPayment.getCurrency()));
    }

}
