package test;

import lombok.Data;

@Data
public class Params {

    private int sum;
    private int months;
    private double percent;

    public Params(int sum, int months, double percent) {
        this.sum = sum;
        this.months = months;
        this.percent = percent;
    }
}
