package fr.unice.polytech.si4.isa.devops.teami.notification;

import fr.unice.polytech.si4.isa.devops.teami.entities.guests.Student;
import fr.unice.polytech.si4.isa.devops.teami.entities.guests.Vip;
import fr.unice.polytech.si4.isa.devops.teami.entities.invitations.StudentInvitation;
import fr.unice.polytech.si4.isa.devops.teami.entities.invitations.VIPInvitation;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class StudentNotificationBean implements StudentNotification {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public int notifyStudents() {
        List<Student> students = entityManager.createQuery(
                "SELECT s FROM Student s").getResultList();

        students.forEach(student -> {
            entityManager.persist(new StudentInvitation(student));
            System.out.println("Invitation Student envoyé à : " + student.getEmail());
        });

        return students.size();
    }

    @Override
    public int notifyVips() {
        List<Vip> vips = entityManager.createQuery(
                "SELECT v FROM Vip v").getResultList();

        vips.forEach(vip -> {
            entityManager.persist(new VIPInvitation(vip));
            System.out.println("Invitation VIP envoyé à : " + vip.getEmail());
        });

        return vips.size();
    }

    @Override
    public int notifyEverybody(){
        return notifyVips() + notifyStudents();
    }

}
