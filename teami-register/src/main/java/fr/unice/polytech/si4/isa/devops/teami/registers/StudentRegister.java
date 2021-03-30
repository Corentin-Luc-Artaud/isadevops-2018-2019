package fr.unice.polytech.si4.isa.devops.teami.registers;

import javax.ejb.Local;

@Local
public interface StudentRegister {

    boolean registerStudent(int studentId);

    boolean isRegistered(int studentId);

    String registeredStudentsToString();

    int getNumberOfRegisteredGuest();

    boolean addAttendant(int studentId, String firstName, String lastName);

    boolean vipIsMissing(String firstName, String lastName);

    boolean payForAttendant(int studentId, int rib);

    double getPrice(int studentId);
}
