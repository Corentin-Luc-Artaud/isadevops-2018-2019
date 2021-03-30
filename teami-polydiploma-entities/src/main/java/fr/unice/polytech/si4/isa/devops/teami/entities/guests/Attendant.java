package fr.unice.polytech.si4.isa.devops.teami.entities.guests;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Attendant extends Person implements Serializable {

    @NotEmpty
    @ManyToOne(cascade = CascadeType.MERGE)
    private Student student;

    public Attendant() {
        super();
    }

    public Attendant(String firstName, String lastName, Student student) {
        super(firstName, lastName, null);
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Attendant attendant = (Attendant) o;
        return getStudent().equals(attendant.getStudent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getStudent());
    }

    @Override
    public String toString() {
        return super.toString() + "Attendant{" +
                "student=" + student +
                '}';
    }
}
