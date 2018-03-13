package com.code.challenge;

public interface User {
    Payments getPayments();

    void addIncomingPayment(Payment incomingPayment);

    void addOutgoingPayment(Payment outgoingPayment);
}
