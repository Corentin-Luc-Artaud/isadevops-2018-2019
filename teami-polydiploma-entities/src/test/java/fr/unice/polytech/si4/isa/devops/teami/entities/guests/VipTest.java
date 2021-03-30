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
public class VipTest extends AbstractDeployment {

    @Test
    public void VIPPersistence() {
        Speciality speciality = new Speciality("vip speciality");
        entityManager.persist(speciality);
        Vip vip = new Vip("f", "l", "@", entityManager.find(Speciality.class, "vip speciality"));
        entityManager.persist(vip);

        assertEquals(vip, entityManager.find(Vip.class, 0));
    }

}
