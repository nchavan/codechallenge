package com.code.challenge;

public class Manager implements User {

    Payments payments;

    public Manager(){
        payments = new Payments();
    }

    @Override
    public Payments getPayments() {
        return payments;
    }

    @Override
    public void addIncomingPayment(Payment incomingPayment) {
        payments.addIncoming(incomingPayment);
    }
}
