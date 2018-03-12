package com.code.challenge;

import org.junit.Test;

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
