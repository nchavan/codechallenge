package com.code.challenge;

import java.util.ArrayList;
import java.util.List;

public class Payments {

    List<Transaction> incoming;
    List<Transaction> outgoing;

    public Payments(){
        incoming = new ArrayList<Transaction>();
        outgoing = new ArrayList<Transaction>();
    }

    public List<Transaction> getIncoming() {
        return incoming;
    }

    public List<Transaction> getOutgoing() {
        return outgoing;
    }

    public void addIncoming(Transaction incoming) {
        this.incoming.add(incoming);
    }

    public void addOutgoing(Transaction outgoing) {
        this.outgoing.add(outgoing);
    }
}
