package fr.unice.polytech.si4.isa.devops.teami.entities.guests;

import fr.unice.polytech.si4.isa.devops.teami.entities.school.Speciality;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Student extends Person implements Serializable {

    @NotEmpty
    private int graduatingYear;

    @NotEmpty
    @ManyToOne(cascade = CascadeType.MERGE)
    private Speciality speciality;

    private int studentId;

    private double price;

    public Student(String firstName, String lastName, String email, int graduatingYear, Speciality speciality, int studentId) {
        super(firstName, lastName, email);
        this.graduatingYear = graduatingYear;
        this.speciality = speciality;
        this.studentId = studentId;
    }

    public Student() {
        super();
    }

    public int getGraduatingYear() {
        return graduatingYear;
    }

    public void setGraduatingYear(int graduatingYear) {
        this.graduatingYear = graduatingYear;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return getGraduatingYear() == student.getGraduatingYear() &&
                getStudentId() == student.getStudentId() &&
                Double.compare(student.getPrice(), getPrice()) == 0 &&
                getSpeciality().equals(student.getSpeciality());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getGraduatingYear(), getSpeciality(), getStudentId(), getPrice());
    }

    @Override
    public String toString() {
        return "Student{" +
                "graduatingYear=" + graduatingYear +
                ", speciality=" + speciality +
                ", studentId=" + studentId +
                ", price=" + price +
                '}';
    }
}