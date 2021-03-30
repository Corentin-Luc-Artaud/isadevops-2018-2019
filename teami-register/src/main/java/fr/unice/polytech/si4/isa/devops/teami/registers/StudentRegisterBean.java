package fr.unice.polytech.si4.isa.devops.teami.registers;

import fr.unice.polytech.si4.isa.devops.teami.entities.guests.Attendant;
import fr.unice.polytech.si4.isa.devops.teami.entities.guests.Student;
import fr.unice.polytech.si4.isa.devops.teami.entities.invitations.StudentInvitation;
import fr.unice.polytech.si4.isa.devops.teami.entities.invitations.VIPInvitation;
import fr.unice.polytech.si4.isa.devops.teami.registers.BankMock.IBankService;
import gherkin.lexer.Vi;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class StudentRegisterBean implements StudentRegister {

    @PersistenceContext
    private EntityManager entityManager;

    @EJB
    private IBankService iBankService;

    @Override
    public boolean registerStudent(int studentId) {
        List<StudentInvitation> studentInvitations = entityManager
                .createQuery("SELECT s FROM StudentInvitation s")
                .getResultList();
        for (StudentInvitation studentInvitation : studentInvitations) {

            if (studentInvitation.getStudent().getStudentId() == studentId) {
                if(studentInvitation.isAccepted()) return false;
                studentInvitation.setAccepted(true);
                entityManager.merge(studentInvitation);
                return true;
            }
        }
        return false;
    }

    public boolean isRegistered(int studentId) {
        List<StudentInvitation> studentInvitations = entityManager
                .createQuery("SELECT s FROM StudentInvitation s")
                .getResultList();
        System.out.println(studentInvitations);
        studentInvitations = studentInvitations.stream().filter(studentInvitation -> studentInvitation.getStudent().getStudentId() == studentId).collect(Collectors.toList());
        if (studentInvitations.isEmpty())
            return false;
        else
            return studentInvitations.get(0).isAccepted();
    }

    @Override
    public String registeredStudentsToString() {
        List<StudentInvitation> studentInvitations = entityManager
                .createQuery("SELECT s FROM StudentInvitation s WHERE s.accepted = TRUE")
                .getResultList();
        studentInvitations = studentInvitations.stream().filter(StudentInvitation::isAccepted).collect(Collectors.toList());
        StringBuilder stringBuilder = new StringBuilder();
        if (studentInvitations.isEmpty()) {
            stringBuilder.append("No student registred");
        }else{
            for (StudentInvitation studentInvitation : studentInvitations) {
                stringBuilder.append(studentInvitation.toString()).append("\n");
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public int getNumberOfRegisteredGuest() {
        List<StudentInvitation> studentInvitations = entityManager
                .createQuery("SELECT s FROM StudentInvitation s WHERE s.accepted = TRUE")
                .getResultList();

        List<VIPInvitation> vipInvitations = entityManager
                .createQuery("SELECT z FROM VIPInvitation z WHERE z.missing = FALSE")
                .getResultList();


        List<Attendant> attendants = entityManager
                .createQuery("SELECT a FROM Attendant a")
                .getResultList();

        return studentInvitations.size() + vipInvitations.size() + attendants.size();
    }

    @Override
    public boolean addAttendant(int studentId, String firstName, String lastName) {
        if(!isRegistered(studentId)) return false;
        Student student = (Student) entityManager
                .createQuery("SELECT s FROM Student s WHERE s.studentId =" + studentId)
                .getResultList().get(0);
        if(student == null) return false;

        List<Attendant> attendants = entityManager
                .createQuery("SELECT s FROM Attendant s WHERE s.student.studentId =" + studentId)
                .getResultList();

        if(attendants.size()>=2) {
            student.setPrice(student.getPrice()+8);
            entityManager.merge(student);
        }
        Attendant attendant = new Attendant(firstName, lastName, student);
        entityManager.persist(attendant);
        return true;
    }

    @Override
    public double getPrice(int studentId) {
        Student student = (Student) entityManager
                .createQuery("SELECT s FROM Student s WHERE s.studentId =" + studentId)
                .getResultList().get(0);
        return student.getPrice();
    }

    @Override
    public boolean payForAttendant(int studentId, int rib) {
        double price = getPrice(studentId);

        if(price == 0)
            return false;
        else {
            Student student = (Student) entityManager.createQuery("SELECT s FROM Student s WHERE s.studentId =" + studentId).getResultList().get(0);
            if(iBankService.payRequest(rib, price)) {
                student.setPrice(0);
                entityManager.merge(student);
                return true;
            }else
                return false;
        }
    }

    @Override
    public boolean vipIsMissing(String firstName, String lastName) {
        List<VIPInvitation> vipInvitations = entityManager
                .createQuery("SELECT s FROM VIPInvitation s")
                .getResultList();
        for (VIPInvitation vipInvitation : vipInvitations) {
            if (vipInvitation.getVip().getLastName() == lastName) {
                if(vipInvitation.isMissing()) return false;
                vipInvitation.setMissing(true);
                entityManager.merge(vipInvitation);
                return true;
            }
        }
        return false;
    }
}