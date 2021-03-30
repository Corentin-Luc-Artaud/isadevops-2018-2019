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
public class AttendantTest extends AbstractDeployment {

    @Test
    public void AttendantPersistence() {
        Speciality speciality = new Speciality("BAT");
        entityManager.persist(speciality);
        Student student = new Student("f2", "s2", "@2", 2018,
                entityManager.find(Speciality.class, "BAT"), 0);
        entityManager.persist(student);
        Attendant attendant = new Attendant("f", "n",
                entityManager.find(Student.class, 0));
        entityManager.persist(attendant);

        assertEquals(attendant, entityManager.find(Attendant.class, 0));
    }
}
