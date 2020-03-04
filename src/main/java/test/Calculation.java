package test;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class Calculation {
    Params params;
    ArrayList<Payment> list = new ArrayList<Payment>();
    double annPayment;
    double p;

    public Calculation(Params params) {
        this.params = params;
        p = params.getPercent() / (100 * 12);
        annPayment = params.getSum() * (p / (1 - Math.pow(1 + p, -params.getMonths())));
    }

    private double round(double d, int precise) {
        precise = 10 ^ precise;
        d = d * precise;
        int i = (int) Math.round(d);
        return (double) i / precise;
    }

    private String format(double in) {
        return String.format("%.2f", in);
    }

    public ArrayList<Payment> execute() {
        Payment paymentRecord;
        LocalDate date = LocalDate.now();
        double pp = params.getSum() * p;
        double mp = annPayment - pp;
        double left = params.getSum() - mp;
        for (int i = 1; i <= params.getMonths(); i++) {
            paymentRecord = new Payment(i, date.getMonthValue() + "/" + date.getYear(), format(mp), format(pp), format(left), format(annPayment));
            list.add(paymentRecord);

            date = date.plus(Period.ofMonths(1));
            pp = left * p;
            mp = annPayment - pp;
            left = left - mp;
        }
        return list;
    }
}
