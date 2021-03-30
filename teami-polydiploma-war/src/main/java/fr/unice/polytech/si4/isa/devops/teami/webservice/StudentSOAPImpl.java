package fr.unice.polytech.si4.isa.devops.teami.webservice;

import fr.unice.polytech.si4.isa.devops.teami.api.GuestManager;
import fr.unice.polytech.si4.isa.devops.teami.registers.StudentRegister;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

@WebService(targetNamespace = "www.polydiploma.fr")
@Stateless(name = "register-student")
public class StudentSOAPImpl implements StudentSOAP {

    @EJB
    private StudentRegister studentRegister;

    @EJB
    private GuestManager guestManager;

    @Override
    public void registerStudent(int id) {
        studentRegister.registerStudent(id);
    }

    @Override
    public String seeRegisteredStudents() {
        return studentRegister.registeredStudentsToString();
    }

    @Override
    public String seeAllStudents() {
        return guestManager.getAllStudentsToString();
    }

    @Override
    public boolean isRegistered(int studentId) {
        return studentRegister.isRegistered(studentId);
    }

    @Override
    public int getNumberOfRegisteredGuest() {
        return studentRegister.getNumberOfRegisteredGuest();
    }

    @Override
    public boolean addAttendant(int studentId, String firstName, String lastName) {
        return studentRegister.addAttendant(studentId, firstName, lastName);
    }

    @Override
    public boolean payForAttendant(int studentId, int rib) {
        return studentRegister.payForAttendant(studentId, rib);
    }

    @Override
    public double getPrice(int studentId) {
        return studentRegister.getPrice(studentId);
    }

}
