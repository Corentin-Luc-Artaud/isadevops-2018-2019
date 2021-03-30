package fr.unice.polytech.si4.isa.devops.teami.entities.invitations;

import fr.unice.polytech.si4.isa.devops.teami.entities.guests.Student;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class StudentInvitation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotEmpty
    private boolean accepted = false;

    @NotEmpty
    @ManyToOne
    private Student student;

    public StudentInvitation() {}

    public StudentInvitation(Student student) {
        this.student = student;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentInvitation that = (StudentInvitation) o;
        return getId() == that.getId() &&
                isAccepted() == that.isAccepted() &&
                getStudent().equals(that.getStudent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), isAccepted(), getStudent());
    }

    @Override
    public String toString() {
        return "StudentInvitation{" +
                "id=" + id +
                ", accepted=" + accepted +
                ", student=" + student +
                '}';
    }
}
