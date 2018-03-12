package com.code.challenge;

import java.util.ArrayList;
import java.util.List;

public class Payments {

    List<Payment> incoming;
    List<Payment> outgoing;

    public Payments(){
        incoming = new ArrayList<Payment>();
        outgoing = new ArrayList<Payment>();
    }

    public List<Payment> getIncoming() {
        return incoming;
    }

    public List<Payment> getOutgoing() {
        return outgoing;
    }

    public void addIncoming(Payment incoming) {
        this.incoming.add(incoming);
    }

    public void addOutgoing(Payment outgoing) {
        this.outgoing.add(outgoing);
    }
}
