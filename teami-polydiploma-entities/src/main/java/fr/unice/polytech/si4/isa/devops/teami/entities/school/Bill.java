package fr.unice.polytech.si4.isa.devops.teami.entities.school;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@IdClass(BillKey.class)
public class Bill implements Serializable {

    @Id
    @NotEmpty
    private String orderBill;

    @Id
    @NotEmpty
    private Timestamp date;

    @NotEmpty
    private double price;

    public Bill() {
    }

    public Bill(double price, Timestamp date, String order) {
        this.price = price;
        this.date = date;
        this.orderBill = order;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getOrderBill() {
        return orderBill;
    }

    public void setOrderBill(String orderBill) {
        this.orderBill = orderBill;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bill bill = (Bill) o;
        return Double.compare(bill.getPrice(), getPrice()) == 0 &&
                getOrderBill().equals(bill.getOrderBill()) &&
                getDate().equals(bill.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderBill(), getDate(), getPrice());
    }

    @Override
    public String toString() {
        return "Bill{" +
                "orderBill='" + orderBill + '\'' +
                ", date=" + date +
                ", price=" + price +
                '}';
    }
}
