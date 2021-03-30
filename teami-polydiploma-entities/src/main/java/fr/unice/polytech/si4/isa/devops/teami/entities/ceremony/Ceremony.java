package fr.unice.polytech.si4.isa.devops.teami.entities.ceremony;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Ceremony implements Serializable {

    @Id
    @NotEmpty
    private int graduatedYear;

    private LocalDateTime date;

    private LocalDateTime minimalDate;

    private String place;

    public Ceremony(){
    }

    public Ceremony(int graduatedYear) {
        this.graduatedYear = graduatedYear;
    }

    public int getGraduatedYear() {
        return graduatedYear;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public LocalDateTime getMinimalDate() {
        return minimalDate;
    }

    public void setMinimalDate(LocalDateTime minimalDate) {
        this.minimalDate = minimalDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ceremony ceremony = (Ceremony) o;
        return getGraduatedYear() == ceremony.getGraduatedYear() &&
                Objects.equals(getDate(), ceremony.getDate()) &&
                Objects.equals(getMinimalDate(), ceremony.getMinimalDate()) &&
                Objects.equals(getPlace(), ceremony.getPlace());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGraduatedYear(), getDate(), getMinimalDate(), getPlace());
    }

    @Override
    public String toString() {
        return "Ceremony{" +
                "graduatedYear=" + graduatedYear +
                ", date=" + date +
                ", minimalDate=" + minimalDate +
                ", place='" + place + '\'' +
                '}';
    }
}
