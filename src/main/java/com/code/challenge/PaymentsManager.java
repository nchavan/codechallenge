package com.code.challenge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public class PaymentsManager implements Payments {

    private List<Payment> incoming;
    private List<Payment> outgoing;
    private Map<String, Integer> consolidatedPayment;

    public PaymentsManager(){
        incoming = new ArrayList<>();
        outgoing = new ArrayList<>();
        consolidatedPayment = new HashMap <>();
    }

    @Override
    public List<Payment> getIncoming() {
        return incoming;
    }

    @Override
    public List<Payment> getOutgoing() {
        return outgoing;
    }

    @Override
    public void addIncoming(Payment incoming) {
        this.incoming.add(incoming);
    }

    @Override
    public void addOutgoing(Payment outgoing) {
        this.outgoing.add(outgoing);
    }

    @Override
    public Map<String, Integer> getConsolidatedPayments() {
        calculateConsolidatedPayment();
        return consolidatedPayment;
    }

    private void calculateConsolidatedPayment() {
        Map <String, List <Payment>> incomingAccountPayments = incoming.stream().collect(groupingBy(Payment::getReceiverAccountRef));
        calculateTotalAmount(incomingAccountPayments, true);
        Map <String, List <Payment>> outgoingAccountPayments = outgoing.stream().collect(groupingBy(Payment::getSenderAccountRef));
        calculateTotalAmount(outgoingAccountPayments, false);
    }

    private void calculateTotalAmount(Map <String, List <Payment>> account, boolean incomingAddOrOutgoingSubstract) {
        for(Map.Entry<String,List<Payment>> e : account.entrySet()){
            for ( Payment payment : e.getValue()) {
                Integer currentAmount = currentAmount(e.getKey());
                int totalAmount = totalAmount(incomingAddOrOutgoingSubstract, payment, currentAmount);
                consolidatedPayment.put(e.getKey(), totalAmount);
            }
        }
    }

    private Integer currentAmount(String accountNumber) {
        Integer currentAmount = consolidatedPayment.get(accountNumber);
        return currentAmount == null ? Integer.valueOf(0) : currentAmount;
    }

    private int totalAmount(boolean incomingAddOrOutgoingSubstract, Payment payment, Integer currentAmount) {
        if(incomingAddOrOutgoingSubstract) {
            return currentAmount + Integer.parseInt(payment.getAmount());
        } else {
            return currentAmount - Integer.parseInt(payment.getAmount());
        }
    }

}
