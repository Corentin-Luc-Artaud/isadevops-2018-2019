package fr.unice.polytech.si4.isa.devops.teami.entities.invitations;

import fr.unice.polytech.si4.isa.devops.teami.entities.guests.Vip;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class VIPInvitation implements Serializable {

    @Id
    @NotEmpty
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    private boolean missing = false;

    @NotEmpty
    @ManyToOne
    private Vip vip;

    public VIPInvitation() {
    }

    public VIPInvitation(Vip vip) {
        this.vip = vip;
    }

    public boolean isMissing() {
        return missing;
    }

    public void setMissing(boolean missing) {
        this.missing = missing;
    }

    public Vip getVip() {
        return vip;
    }

    public void setVip(Vip vip) {
        this.vip = vip;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VIPInvitation that = (VIPInvitation) o;
        return getId() == that.getId() &&
                isMissing() == that.isMissing() &&
                getVip().equals(that.getVip());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), isMissing(), getVip());
    }

    @Override
    public String toString() {
        return "VIPInvitation{" +
                "id=" + id +
                ", missing=" + missing +
                ", vip=" + vip +
                '}';
    }
}
