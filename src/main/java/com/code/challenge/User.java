package com.code.challenge;

public interface User {
    Payments getPayments();

    void addIncomingPayment(Transaction incomingTransaction);
}
