package com.arxman.sandbox.kafkar.model;

public class MessageA {
    private String infoA;
    private int number;

    public MessageA() {
    }

    public MessageA(String infoA, int number) {
        this.infoA = infoA;
        this.number = number;
    }

    public String getInfoA() {
        return infoA;
    }

    public void setInfoA(String infoA) {
        this.infoA = infoA;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return String.format("MessageA {infoA='%s', number='%s'}", infoA, String.valueOf(number));
    }
}
