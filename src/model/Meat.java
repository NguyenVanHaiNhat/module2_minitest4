package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Meat extends Material implements Discount, Serializable {
    private final double weight;

    public Meat(double weight) {
        this.weight = weight;
    }

    public Meat(String id, String name, LocalDate manufacturingDate, int cost, double weight) {
        super(id, name, manufacturingDate, cost);
        this.weight = weight;
    }

    @Override
    public double getAmount() {
        return getCost() * this.weight;
    }

    @Override
    public LocalDate getExpiryDate() {
        return this.getManufacturingDate().plusDays(7);
    }

    @Override
    public double getRealMoney() {
        return 0;
    }

    @Override
    public String toString() {
        return "Meat{" +
                "id=" + getId() +
                ", name=" + getName() +
                ", manufacturingDate" + getManufacturingDate() +
                ", cost=" + getCost() +
                ", weight=" + weight +
                '}';
    }
}
