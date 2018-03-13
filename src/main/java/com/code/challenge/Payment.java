package com.code.challenge;

public class Payment {

    private String receiverCountry;
    private String receiverAccountRef;
    private String senderAccountRef;
    private String sendersPaymentReference;
    private String valueDate;
    private String currency;
    private String amount;
    private String messageIdentifier;

    public Payment(String receiverCountry, String receiverAccountRef, String senderAccountRef, String sendersPaymentReference, String valueDate, String currency, String amount, String messageIdentifier) {
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

    @Override
    public String toString() {
        return "Payment{" +
                "receiverCountry='" + receiverCountry + '\'' +
                ", receiverAccountRef='" + receiverAccountRef + '\'' +
                ", senderAccountRef='" + senderAccountRef + '\'' +
                ", sendersPaymentReference='" + sendersPaymentReference + '\'' +
                ", valueDate='" + valueDate + '\'' +
                ", currency='" + currency + '\'' +
                ", amount='" + amount + '\'' +
                ", messageIdentifier='" + messageIdentifier + '\'' +
                '}';
    }
}
