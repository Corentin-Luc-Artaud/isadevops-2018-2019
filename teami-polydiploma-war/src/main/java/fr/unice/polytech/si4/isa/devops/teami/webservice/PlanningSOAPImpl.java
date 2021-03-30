package fr.unice.polytech.si4.isa.devops.teami.webservice;

import fr.unice.polytech.si4.isa.devops.teami.entities.events.StudentEvent;
import fr.unice.polytech.si4.isa.devops.teami.entities.school.Speciality;
import fr.unice.polytech.si4.isa.devops.teami.utils.PlanningManager;
import fr.unice.polytech.si4.isa.devops.teami.utils.PlanningManagerBean;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebService(targetNamespace = "www.polydiploma.fr")
@Stateless(name = "planning")
public class PlanningSOAPImpl implements PlanningSOAP {

    @EJB
    private PlanningManager planningManager;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy:HH:mm");

    @Override
    public boolean addStudentEvent(String startTimeString, String endTimeString, String specialtyString) {

        LocalDateTime startTime = LocalDateTime.parse(startTimeString, formatter);
        LocalDateTime endTime = LocalDateTime.parse(endTimeString, formatter);

        StudentEvent studentEvent = new StudentEvent(startTime, endTime, new Speciality(specialtyString));

        return planningManager.addEvent(studentEvent);
    }

    @Override
    public void deleteEvent(String startTimeString, String endTimeString) {
        LocalDateTime startTime = LocalDateTime.parse(startTimeString, formatter);
        LocalDateTime endTime = LocalDateTime.parse(endTimeString, formatter);

        planningManager.deleteEvent(startTime, endTime);
    }

    @Override
    public boolean moveEvent(String oldStartTimeString, String oldEndTimeString, String startTimeString, String endTimeString) {
        LocalDateTime oldStartTime = LocalDateTime.parse(oldStartTimeString, formatter);
        LocalDateTime oldEndTime = LocalDateTime.parse(oldEndTimeString, formatter);

        LocalDateTime startTime = LocalDateTime.parse(startTimeString, formatter);
        LocalDateTime endTime = LocalDateTime.parse(endTimeString, formatter);

        return planningManager.moveEvent(oldStartTime, oldEndTime, startTime, endTime);
    }

    @Override
    public String displayPlanning() {
        return planningManager.displayPlanning();
    }

}
