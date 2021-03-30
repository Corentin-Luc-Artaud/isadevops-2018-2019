package fr.unice.polytech.si4.isa.devops.teami.webservice;

import fr.unice.polytech.si4.isa.devops.teami.ceremony.manager.CeremonyManager;
import fr.unice.polytech.si4.isa.devops.teami.entities.ceremony.Ceremony;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import java.time.LocalDateTime;

@WebService(targetNamespace = "www.polydiploma.fr")
@Stateless(name = "ceremony-manager")
public class CeremonyManagerServiceImpl implements CeremonyManagerService {

    @EJB
    private CeremonyManager manager;

    @Override
    public void createNewRdd(int year) {
        manager.createNewRdd(year);
    }

    @Override
    public Ceremony getCurrentCeremony() {
        return manager.getCurrentCeremony();
    }

    @Override
    public void setDate(String date) {
        //dd-MM-yyy HH:mm
        LocalDateTime dateObj = LocalDateTime.parse(date); //, formatter);
        manager.setDate(dateObj);
    }

    @Override
    public void setPlace(String place) {
        manager.setPlace(place);
    }

    @Override
    public void notifyGuests() {
        manager.notifyGuests();
    }

    @Override
    public void generateInitialPlanning(){
        manager.generateInitialPlanning();
    }

    @Override
    public void addVip(String firstName, String lastName, String email, String speciality) {
        manager.addVip(firstName, lastName, email, speciality);
    }

    @Override
    public void removeVip(String email) {
        manager.removeVip(email);
    }

    @Override
    public String getAllVipToString() {
        return manager.getAllVipToString();
    }

    @Override
    public String ping() {
        return "pong";
    }

}