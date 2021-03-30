package fr.unice.polytech.si4.isa.devops.teami.api.utils;

import fr.unice.polytech.si4.isa.devops.teami.entities.guests.Student;
import fr.unice.polytech.si4.isa.devops.teami.entities.school.Speciality;

import javax.ejb.Local;
import java.util.List;
import java.util.Set;

@Local
public interface IStudentAPI {
    public List<Student> getGraduatedStudent(int year);
    public Set<Speciality> getSpecialities();
}