package com.example.androidproject.model;

import java.io.Serializable;

public class RequestModel implements Serializable {
    private String senderEmail;
    private String receiverEmail;
    private String states;
    private String senderID;
    private String DocId;
    public RequestModel(){}

    public RequestModel(String senderEmail, String receiverEmail, String states, String senderID, String docId) {
        this.senderEmail = senderEmail;
        this.receiverEmail = receiverEmail;
        this.states = states;
        this.senderID = senderID;
        DocId = docId;
    }

    public String getDocId() {
        return DocId;
    }

    public void setDocId(String docId) {
        DocId = docId;
    }

    public String getSenderID() {
        return senderID;
    }

    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }
}

