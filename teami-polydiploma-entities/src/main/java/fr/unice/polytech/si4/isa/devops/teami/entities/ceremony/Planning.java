package fr.unice.polytech.si4.isa.devops.teami.entities.ceremony;

import fr.unice.polytech.si4.isa.devops.teami.entities.events.Event;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Planning implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "planning")
    private Set<Event> events;

    public Planning() {
        this.events = new HashSet<>();
    }

    public Set<Event> getEvents() {
        return Collections.unmodifiableSet(events);
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public void removeEvent(Event event) {
        events.remove(event);
    }

    public int getId() {
        return id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planning planning = (Planning) o;
        return getId() == planning.getId() &&
                Objects.equals(getEvents(), planning.getEvents());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEvents());
    }

    @Override
    public String toString() {
        return "Planning{" +
                "id=" + id +
                ", events=" + events +
                '}';
    }
}