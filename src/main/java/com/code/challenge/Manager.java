package com.code.challenge;

public class Manager implements User {

    Payments payments;

    public Manager(){
        payments = new PaymentsManager();
    }

    @Override
    public Payments getPayments() {
        return payments;
    }

    @Override
    public void addIncomingPayment(Payment incomingPayment) {
        payments.addIncoming(incomingPayment);
    }

    @Override
    public void addOutgoingPayment(Payment outgoingPayment) {
        payments.addOutgoing(outgoingPayment);
    }
}
