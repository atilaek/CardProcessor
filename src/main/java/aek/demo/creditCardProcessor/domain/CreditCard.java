package aek.demo.creditCardProcessor.domain;

/**
 * CreditCard DAO represents a single credit card.
 *
 * @author Atila Ekimci
 */
public class CreditCard {

    private String name;
    private String number;
    private double balance;
    private double limit;

    public CreditCard(final String name, final String number, final double limit) {
        this.name = name;
        this.number = number;
        this.balance = 0.00;
        this.limit = limit;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(final String number) {
        this.number = number;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(final double balance) {
        this.balance = balance;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

}
