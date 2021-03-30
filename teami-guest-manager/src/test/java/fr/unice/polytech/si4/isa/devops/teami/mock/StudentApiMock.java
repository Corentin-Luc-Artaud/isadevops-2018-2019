package fr.unice.polytech.si4.isa.devops.teami.mock;

import fr.unice.polytech.si4.isa.devops.teami.api.utils.IStudentAPI;
import fr.unice.polytech.si4.isa.devops.teami.entities.guests.Student;
import fr.unice.polytech.si4.isa.devops.teami.entities.school.Speciality;

import javax.ejb.Stateless;
import java.util.*;

@Stateless
public class StudentApiMock implements IStudentAPI {

    private List<Speciality> specialities;
    private List<Student> students;

    public StudentApiMock() {
        specialities = Arrays
                .asList(new Speciality("SI"), new Speciality("ELEC"), new Speciality("MAM"));
        students = new ArrayList<>();
        for (int i = 0; i < 100; ++i) {
            String firstName = UUID.randomUUID().toString();
            String lastName = UUID.randomUUID().toString();
            students.add(new Student(firstName, lastName, firstName + "." + lastName + "@etu.unice.fr", 0,
                    specialities.get(i % specialities.size()), i));
        }
    }

    @Override
    public List<Student> getGraduatedStudent(int year) {
        students.forEach(s -> s.setGraduatingYear(year));
        return students;
    }

    @Override
    public Set<Speciality> getSpecialities() {
        return new HashSet<>(specialities);
    }

}