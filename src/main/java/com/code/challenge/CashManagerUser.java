package com.code.challenge;

public class CashManagerUser implements User {

    Payments payments;

    public CashManagerUser(){
        payments = new Payments();
    }

    @Override
    public Payments getPayments() {
        return payments;
    }

    public void addIncomingPayment(Transaction incomingTransaction) {
        payments.addIncoming(incomingTransaction);
    }
}
