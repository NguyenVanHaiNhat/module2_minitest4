package model;

import java.io.Serializable;
import java.time.LocalDate;

public class CrispyFlour extends Material implements Discount, Serializable {

    private final int quantity;

    public CrispyFlour(int quantity) {
        this.quantity = quantity;
    }

    public CrispyFlour(String id, String name, LocalDate manufacturingDate, int cost, int quantity) {
        super(id, name, manufacturingDate, cost);
        this.quantity = quantity;
    }

    @Override
    public double getAmount() {
        return this.quantity * getCost();
    }

    @Override
    public LocalDate getExpiryDate() {
        return this.getManufacturingDate().plusYears(1);
    }

    @Override
    public double getRealMoney() {
        return 0;
    }

    @Override
    public String toString() {
        return "CrispyFlour{" +
                "id=" + getId() +
                ", name=" + getName() +
                ", manufacturingDate" + getManufacturingDate() +
                ", cost=" + getCost() +
                ", quantity=" + quantity +
                '}';
    }
}
