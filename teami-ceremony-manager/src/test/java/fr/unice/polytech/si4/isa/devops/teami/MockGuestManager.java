package fr.unice.polytech.si4.isa.devops.teami;

import fr.unice.polytech.si4.isa.devops.teami.api.GuestManager;
import fr.unice.polytech.si4.isa.devops.teami.entities.guests.Student;
import fr.unice.polytech.si4.isa.devops.teami.entities.guests.Vip;

import javax.ejb.Stateless;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class MockGuestManager implements GuestManager {

    @Override
    public LocalDateTime loadStudentsFromHyperplanning(int year) {
        return LocalDateTime.now();
    }

    @Override
    public List<Student> getAllStudents() {
        return new ArrayList<>();
    }

    @Override
    public String getAllStudentsToString() {
        return "";
    }

    @Override
    public List<Student> getStudentsBySpeciality(String s) {
        return null;
    }

    @Override
    public String getStudentsBySpecialityToString(String speciality){return "";}

    @Override
    public void removeStudent(int i) {

    }

    @Override
    public void addStudent(String s, String s1, String s2, int i, String s3, int id) {

    }

    @Override
    public void addVip(String a, String b, String z, String r){

    }

    @Override
    public void removeVip(String t){

    }

    @Override
    public List<Vip> getAllVip(){
        return null;
    }

    @Override
    public String getAllVipToString(){
        return "";
    }
}
