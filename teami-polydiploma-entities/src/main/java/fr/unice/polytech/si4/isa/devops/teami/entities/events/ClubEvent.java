package fr.unice.polytech.si4.isa.devops.teami.entities.events;

import fr.unice.polytech.si4.isa.devops.teami.entities.school.Club;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class ClubEvent extends Event implements Serializable {

    @NotEmpty
    @ManyToOne(cascade = CascadeType.MERGE)
    private Club club;

    public ClubEvent() {}

    public ClubEvent(LocalDateTime startTime, LocalDateTime endTime, Club club) {
        super(startTime, endTime);
        this.club = club;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ClubEvent clubEvent = (ClubEvent) o;
        return getClub().equals(clubEvent.getClub());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getClub());
    }

    @Override
    public String toString() {
        return super.toString() + "ClubEvent{" +
                "club=" + club +
                '}';
    }
}
