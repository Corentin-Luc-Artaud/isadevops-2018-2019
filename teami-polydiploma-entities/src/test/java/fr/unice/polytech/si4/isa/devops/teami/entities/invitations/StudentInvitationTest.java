package fr.unice.polytech.si4.isa.devops.teami.entities.invitations;

import fr.unice.polytech.si4.isa.devops.teami.entities.AbstractDeployment;
import fr.unice.polytech.si4.isa.devops.teami.entities.guests.Student;
import fr.unice.polytech.si4.isa.devops.teami.entities.school.Speciality;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
@Transactional(TransactionMode.ROLLBACK)
public class StudentInvitationTest extends AbstractDeployment {

    @Test
    public void StudentInvitationPersistence() {
        Speciality speciality = new Speciality("vipinvitation speciality");
        entityManager.persist(speciality);
        Student student = new Student("f", "l", "@",2018,
                entityManager.find(Speciality.class, "vipinvitation speciality"), 0);
        entityManager.persist(student);
        StudentInvitation studentInvitation = new StudentInvitation(entityManager.find(Student.class, 0));
        entityManager.persist(studentInvitation);

        assertEquals(studentInvitation, entityManager.find(StudentInvitation.class, 0));
    }
}
