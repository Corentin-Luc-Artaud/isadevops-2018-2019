package fr.unice.polytech.si4.isa.devops.teami.entities.school;

import fr.unice.polytech.si4.isa.devops.teami.entities.events.EventOffer;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Club implements Serializable {

    @Id
    @NotEmpty
    private String name;

    @NotEmpty
    private int presentMemberNumber;

    @ManyToOne(cascade = CascadeType.MERGE)
    private EventOffer eventOffer;

    public Club() {
    }

    public Club(String name, int presentMemberNumber) {
        this.name = name;
        this.presentMemberNumber = presentMemberNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPresentMemberNumber() {
        return presentMemberNumber;
    }

    public void setPresentMemberNumber(int presentMemberNumber) {
        this.presentMemberNumber = presentMemberNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Club club = (Club) o;
        return getPresentMemberNumber() == club.getPresentMemberNumber() &&
                getName().equals(club.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPresentMemberNumber());
    }

    @Override
    public String toString() {
        return "Club{" +
                "name='" + name + '\'' +
                ", presentMemberNumber=" + presentMemberNumber +
                '}';
    }
}
