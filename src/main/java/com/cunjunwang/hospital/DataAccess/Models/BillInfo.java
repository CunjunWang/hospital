package com.cunjunwang.hospital.DataAccess.Models;

/**
 * Created by CunjunWang on 16/10/17.
 */
public class BillInfo {

    private int b_number;
    private double amount;

    // default constructor
    public BillInfo(){}

    // minimal constructor
    public BillInfo(int b_number){
        this.b_number = b_number;
    }

    // full constructor
    public BillInfo(int b_number, double amount){
        this.b_number = b_number;
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public int getB_number() {
        return b_number;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setB_number(int b_number) {
        this.b_number = b_number;
    }
}
