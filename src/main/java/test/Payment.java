package test;

import lombok.Data;

@Data
public class Payment {
    int num;
    String date;
    String mainPayment;
    String percentPayment;
    String mainLeft;
    String allPayment;

    public Payment(int num, String date, String mainPayment, String percentPayment, String mainLeft, String allPayment) {
        this.num = num;
        this.date = date;
        this.mainPayment = mainPayment;
        this.percentPayment = percentPayment;
        this.mainLeft = mainLeft;
        this.allPayment = allPayment;
    }
}
