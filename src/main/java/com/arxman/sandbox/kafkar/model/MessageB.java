package com.arxman.sandbox.kafkar.model;

public class MessageB {
    private String infoB;
    private int number;

    public MessageB() {
    }

    public MessageB(String info, int number) {
        this.infoB = info;
        this.number = number;
    }

    public String getInfoB() {
        return infoB;
    }

    public void setInfoB(String infoB) {
        this.infoB = infoB;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return String.format("MessageB {infoB='%s', number='%s'}", infoB, String.valueOf(number));
    }
}
