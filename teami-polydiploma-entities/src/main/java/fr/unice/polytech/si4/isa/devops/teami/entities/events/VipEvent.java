package fr.unice.polytech.si4.isa.devops.teami.entities.events;

import fr.unice.polytech.si4.isa.devops.teami.entities.guests.Vip;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class VipEvent extends Event implements Serializable {

    @NotEmpty
    @ManyToOne(cascade = CascadeType.MERGE)
    private Vip vip;

    public VipEvent() {}

    public VipEvent(LocalDateTime startTime, LocalDateTime endTime, Vip vip) {
        super(startTime, endTime);
        this.vip = vip;
    }

    public Vip getVip() {
        return vip;
    }

    public void setVip(Vip vip) {
        this.vip = vip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        VipEvent vipEvent = (VipEvent) o;
        return getVip().equals(vipEvent.getVip());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getVip());
    }

    @Override
    public String toString() {
        return super.toString() + "VipEvent{" +
                "vip=" + vip +
                '}';
    }
}
