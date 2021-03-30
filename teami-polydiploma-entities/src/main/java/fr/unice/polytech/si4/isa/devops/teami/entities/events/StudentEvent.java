package fr.unice.polytech.si4.isa.devops.teami.entities.events;

import fr.unice.polytech.si4.isa.devops.teami.entities.school.Speciality;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class StudentEvent extends Event implements Serializable {

    @NotEmpty
    @ManyToOne(cascade = CascadeType.MERGE)
    private Speciality speciality;

    public StudentEvent(LocalDateTime startTime, LocalDateTime endTime, Speciality speciality) {
        super(startTime, endTime);
        this.speciality = speciality;
    }

    public StudentEvent() {
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
        StudentEvent that = (StudentEvent) o;
        return getSpeciality().equals(that.getSpeciality());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getSpeciality());
    }

    @Override
    public String toString() {
        return super.toString() + "StudentEvent{" +
                "speciality=" + speciality +
                '}';
    }
}
