package com.code.challenge;

public class Transaction {

    private static final String EMPTY_TRANSACTION = "";
    private String receiverCountry;
    private String receiverAccountRef;
    private String senderAccountRef;
    private String sendersPaymentReference;
    private String valueDate;
    private String currency;
    private String amount;
    private String messageIdentifier;

    public Transaction() {
        this.receiverCountry = EMPTY_TRANSACTION;
        this.receiverAccountRef = EMPTY_TRANSACTION;
        this.senderAccountRef = EMPTY_TRANSACTION;
        this.sendersPaymentReference = EMPTY_TRANSACTION;
        this.valueDate = EMPTY_TRANSACTION;
        this.currency = EMPTY_TRANSACTION;
        this.amount = EMPTY_TRANSACTION;
        this.messageIdentifier = EMPTY_TRANSACTION;
    }

    public Transaction(String receiverCountry, String receiverAccountRef, String senderAccountRef, String sendersPaymentReference, String valueDate, String currency, String amount, String messageIdentifier) {
        this.receiverCountry = receiverCountry;
        this.receiverAccountRef = receiverAccountRef;
        this.senderAccountRef = senderAccountRef;
        this.sendersPaymentReference = sendersPaymentReference;
        this.valueDate = valueDate;
        this.currency = currency;
        this.amount = amount;
        this.messageIdentifier = messageIdentifier;
    }

    public String getReceiverCountry() {
        return receiverCountry;
    }

    public String getReceiverAccountRef() {
        return receiverAccountRef;
    }

    public String getSenderAccountRef() {
        return senderAccountRef;
    }

    public String getSendersPaymentReference() {
        return sendersPaymentReference;
    }

    public String getValueDate() {
        return valueDate;
    }

    public String getCurrency() {
        return currency;
    }

    public String getAmount() {
        return amount;
    }

    public String getMessageIdentifier() {
        return messageIdentifier;
    }
}
