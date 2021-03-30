package fr.unice.polytech.si4.isa.devops.teami.entities.guests;

import fr.unice.polytech.si4.isa.devops.teami.entities.AbstractDeployment;
import fr.unice.polytech.si4.isa.devops.teami.entities.school.Speciality;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
@Transactional(TransactionMode.ROLLBACK)
public class StudentTest extends AbstractDeployment {

    @Test
    public void StudentPersistence() {
        Speciality speciality = new Speciality("SI");
        entityManager.persist(speciality);
        Student student = new Student("f", "s", "@",
                2018, entityManager.find(Speciality.class, "SI"), 0);
        entityManager.persist(student);

        assertEquals(student, entityManager.find(Student.class, 0));
    }
}
