package fr.unice.polytech.si4.isa.devops.teami.entities.school;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class BillKey implements Serializable {

    private String orderBill;
    private Timestamp date;

    public BillKey() {}

    BillKey(String orderBill, Timestamp date) {
        this.orderBill = orderBill;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BillKey billKey = (BillKey) o;
        return orderBill.equals(billKey.orderBill) &&
                date.equals(billKey.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderBill, date);
    }

    @Override
    public String toString() {
        return "BillKey{" +
                "orderBill='" + orderBill + '\'' +
                ", date=" + date +
                '}';
    }
}
