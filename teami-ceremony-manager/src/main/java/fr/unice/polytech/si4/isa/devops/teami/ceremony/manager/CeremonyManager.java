package fr.unice.polytech.si4.isa.devops.teami.ceremony.manager;

import fr.unice.polytech.si4.isa.devops.teami.entities.ceremony.Ceremony;

import javax.ejb.Local;
import javax.jws.WebMethod;
import java.time.LocalDateTime;

@Local
public interface CeremonyManager {

    void createNewRdd(int year);

    void setDate(LocalDateTime date);

    void setPlace(String place);

    Ceremony getCurrentCeremony();

    void notifyGuests();

    void generateInitialPlanning();

    void addVip(String firstName, String lastName, String email, String speciality);

    void removeVip(String email);

    String getAllVipToString();
}