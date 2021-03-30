package fr.unice.polytech.si4.isa.devops.teami.api;

import fr.unice.polytech.si4.isa.devops.teami.entities.guests.Student;
import fr.unice.polytech.si4.isa.devops.teami.entities.guests.Vip;

import javax.ejb.Local;
import java.time.LocalDateTime;
import java.util.List;

@Local
public interface GuestManager {


    /**
     * Load students from hyperplanning and ask the deadline for print diplomas
     * @param year
     * @return the delivered date for diplomas
     */
    LocalDateTime loadStudentsFromHyperplanning(int year);

    List<Student> getAllStudents();

    String getAllStudentsToString();

    List<Student> getStudentsBySpeciality(String speciality);

    String getStudentsBySpecialityToString(String speciality);

    void removeStudent(int studentId);

    void addStudent(String firstName, String lastName, String email, int graduatingYear, String speciality, int studentId);

    void addVip(String firstName, String lastName, String email, String speciality);

    void removeVip(String email);

    List<Vip> getAllVip();

    String getAllVipToString();

}
