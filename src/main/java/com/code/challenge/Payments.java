package com.code.challenge;

import java.util.List;
import java.util.Map;

public interface Payments {

    List<Payment> getIncoming();

    List<Payment> getOutgoing();

    void addIncoming(Payment incoming);

    void addOutgoing(Payment outgoing);

    Map<String, Integer> getConsolidatedPayments();

}
