package com.code.challenge;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class PaymentInteractionsTest {

    @Test
    public void testEmptyPaymentInteractions() {
        User cashManagerUser = new CashManagerUser();
        Payments payments = cashManagerUser.getPayments();
        assertNoIncomingOutgoindPaymentTransactions(payments, 0, 0);
    }

    private void assertNoIncomingOutgoindPaymentTransactions(Payments payments, int incomingSize, int outgoingSize) {
        assertNotNull(payments);
        assertThat(payments.getIncoming().size(),is(incomingSize));
        assertThat(payments.getOutgoing().size(),is(outgoingSize));
    }

    @Test
    public void testValidPaymentInteractionsWithOneIncomingPaymentAndNoOutgoingPayment() {
        User cashManagerUser = new CashManagerUser();
        Transaction incomingTransaction = new Transaction("UK", "000001", "571986", "THEIRSENDREF001", "31122016", "GBP", "975000", "");
        cashManagerUser.addIncomingPayment(incomingTransaction);
        Payments payments = cashManagerUser.getPayments();
        assertOneValidIncomingPaymentTransaction(payments, incomingTransaction, 1, 0);
    }

    private void assertOneValidIncomingPaymentTransaction(Payments payments, Transaction incomingTransaction, int incomingSize, int outgoingSize) {
        assertNotNull(payments);
        assertThat(payments.getIncoming().size(),is(incomingSize));
        assertThat(payments.getOutgoing().size(),is(outgoingSize));
        assertThat(payments.getIncoming().get(0).getMessageIdentifier(),is(incomingTransaction.getMessageIdentifier()));
        assertThat(payments.getIncoming().get(0).getAmount(),is(incomingTransaction.getAmount()));
        assertThat(payments.getIncoming().get(0).getCurrency(),is(incomingTransaction.getCurrency()));
    }


}
