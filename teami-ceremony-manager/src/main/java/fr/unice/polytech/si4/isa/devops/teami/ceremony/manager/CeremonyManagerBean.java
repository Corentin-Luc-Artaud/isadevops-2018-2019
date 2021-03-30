package fr.unice.polytech.si4.isa.devops.teami.ceremony.manager;

import fr.unice.polytech.si4.isa.devops.teami.api.GuestManager;
import fr.unice.polytech.si4.isa.devops.teami.entities.ceremony.Ceremony;
import fr.unice.polytech.si4.isa.devops.teami.notification.StudentNotification;
import fr.unice.polytech.si4.isa.devops.teami.utils.PlanningManager;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;


@Stateless
public class CeremonyManagerBean implements CeremonyManager {

    @EJB
    GuestManager guestManager;
    @EJB
    StudentNotification studentNotification;
    @EJB
    PlanningManager planningManager;

    @PersistenceContext
    private EntityManager entityManager;

    private Ceremony ceremony = null;

    @Override
    public void setDate(LocalDateTime date) {
        if (null == ceremony) return;
        else if (ceremony.getMinimalDate().isBefore(date)) {
            if (ceremony.getMinimalDate() != null) {
                this.ceremony.setDate(date);
                entityManager.merge(this.ceremony);
            } else
                System.out.println("Impossible to set the ceremony to this date : printRequest date is null");
        } else
            System.out.println("Impossible to set the ceremony to this date : printRequest date is after");
    }

    @Override
    public void setPlace(String place) {
        if (null == ceremony) return;
        this.ceremony.setPlace(place);
        entityManager.merge(this.ceremony);
    }

    @Override
    public Ceremony getCurrentCeremony() {
        return this.ceremony;
    }

    @Override
    public void createNewRdd(int year) {
        this.ceremony = entityManager.find(Ceremony.class, year);
        if (this.ceremony == null) {
            this.ceremony = new Ceremony(year);
            this.ceremony.setMinimalDate(guestManager.loadStudentsFromHyperplanning(year));
        }
        entityManager.persist(this.ceremony);
    }

    @Override
    public void generateInitialPlanning() {
        planningManager.generateInitialPlanning(ceremony.getDate());
    }

    @Override
    public void notifyGuests() {
        studentNotification.notifyEverybody();
    }

    @Override
    public void addVip(String firstName, String lastName, String email, String speciality) {
        guestManager.addVip(firstName, lastName, email, speciality);
    }

    @Override
    public void removeVip(String email) {
        guestManager.removeVip(email);
    }

    @Override
    public String getAllVipToString() {
        return guestManager.getAllVipToString();
    }
}