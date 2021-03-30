package fr.unice.polytech.si4.isa.devops.teami.entities.guests;

import fr.unice.polytech.si4.isa.devops.teami.entities.school.Speciality;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Vip extends Person implements Serializable {

    @ManyToOne(cascade = CascadeType.MERGE)
    private Speciality speciality;

    public Vip(String firstName, String lastName, String email, Speciality speciality) {
        super(firstName, lastName, email);
        this.speciality = speciality;
    }

    public Vip() {
        super();
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Vip vip = (Vip) o;
        return Objects.equals(getSpeciality(), vip.getSpeciality());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getSpeciality());
    }

    @Override
    public String toString() {
        return super.toString() + "Vip{" +
                "speciality=" + speciality +
                '}';
    }
}