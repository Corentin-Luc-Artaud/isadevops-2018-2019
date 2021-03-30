package fr.unice.polytech.si4.isa.devops.teami.entities.school;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Speciality implements Serializable {

    @Id
    @NotEmpty
    private String name;

    public Speciality(String name) {
        this.name = name;
    }

    public Speciality() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Speciality that = (Speciality) o;
        return getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        return "Speciality{" +
                "name='" + name + '\'' +
                '}';
    }
}
