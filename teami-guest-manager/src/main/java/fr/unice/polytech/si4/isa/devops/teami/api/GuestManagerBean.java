package fr.unice.polytech.si4.isa.devops.teami.api;

import fr.unice.polytech.si4.isa.devops.teami.api.PrintingServiceMock.IPrintingService;
import fr.unice.polytech.si4.isa.devops.teami.api.utils.IStudentAPI;
import fr.unice.polytech.si4.isa.devops.teami.entities.guests.Student;
import fr.unice.polytech.si4.isa.devops.teami.entities.guests.Vip;
import fr.unice.polytech.si4.isa.devops.teami.entities.school.Speciality;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Stateless
public class GuestManagerBean implements GuestManager {

    @PersistenceContext
    private EntityManager entityManager;

    @EJB
    IStudentAPI studentAPI;

    @EJB
    IPrintingService printingService;

    @Override
    public List<Student> getAllStudents() {
        return entityManager.createQuery("SELECT s FROM Student s").getResultList();
    }

    private Set<Speciality> getAllSpecialities () {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Speciality> cq = cb.createQuery(Speciality.class);
        Root<Speciality> rootEntry = cq.from(Speciality.class);
        CriteriaQuery<Speciality> all = cq.select(rootEntry);
        TypedQuery<Speciality> allQuery = entityManager.createQuery(all);
        return new HashSet<>(allQuery.getResultList());
    }

    @Override
    public LocalDateTime loadStudentsFromHyperplanning(int year) {
        List<Student> students = new ArrayList<>();
        try {
            students = studentAPI.getGraduatedStudent(year);
            Set<Speciality> specialities = studentAPI.getSpecialities();
            Set<Speciality> known_specialities = getAllSpecialities();
            specialities.removeAll(known_specialities);
            specialities.forEach(s -> {System.out.println(s);entityManager.persist(s);});
            students.forEach(s -> entityManager.persist(s));
        } catch (Exception e) {
            System.err.println("Error when trying to load students from web service :\n  " + e);
        }
        return printingService.printRequest(students);
    }

    @Override
    public List<Student> getStudentsBySpeciality(String speciality) {
        List<Student> students = entityManager.createQuery("SELECT s FROM Student s").getResultList();
        return students.stream()
                .filter(student -> student.getSpeciality().getName().equals(speciality))
                .collect(Collectors.toList());
    }

    @Override
    public void removeStudent(int id) {
        entityManager.createQuery("DELETE FROM Student s WHERE s.id = "+ id );
    }

    @Override
    public void addStudent(String firstName, String lastName, String email, int graduatingYear, String speciality, int studentId) {
        //TODO add speciality when not exist
        Speciality speciality1 = entityManager.find(Speciality.class, speciality);
        entityManager.persist(new Student(firstName, lastName, email, graduatingYear, speciality1, 0));
    }

    @Override
    public void addVip(String firstName, String lastName, String email, String speciality){
        Speciality speciality1 = entityManager.find(Speciality.class, speciality);
        entityManager.persist(new Vip(firstName, lastName, email, speciality1));
    }

    @Override
    public void removeVip(String email){
        //TODO Ca marche pas vraiment
        entityManager.createQuery("DELETE FROM Student s WHERE s.email = '"+ email +"'");
    }

    @Override
    public List<Vip> getAllVip(){
        return entityManager.createQuery("SELECT s FROM Vip s").getResultList();
    }

    @Override
    public String getAllVipToString(){
        return "VIP : \n"+listToString(getAllVip());
    }

    @Override
    public String getStudentsBySpecialityToString(String speciality){
        return "Student : \n" + listToString(getStudentsBySpeciality(speciality));
    }

    @Override
    public String getAllStudentsToString() {
        return "Student : \n" + getAllStudentsToString();
    }

    private String listToString(List personList){
        StringBuilder stringBuilder = new StringBuilder();

        if (personList.isEmpty()) {
            stringBuilder.append("No person registered");
        }else{
            for (Object person : personList) {
                stringBuilder.append(person.toString()).append("\n");
            }
        }
        return stringBuilder.toString();
    }
}
