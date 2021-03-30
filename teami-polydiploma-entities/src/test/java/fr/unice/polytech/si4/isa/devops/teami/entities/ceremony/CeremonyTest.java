package fr.unice.polytech.si4.isa.devops.teami.entities.ceremony;

import fr.unice.polytech.si4.isa.devops.teami.entities.AbstractDeployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
@Transactional(TransactionMode.ROLLBACK)
public class CeremonyTest extends AbstractDeployment {

    @Test
    public void CeremonyPersistence() {
        Ceremony ceremony = new Ceremony(2018);
        LocalDateTime date = LocalDateTime.of(2018, 2, 1, 12, 30, 0);
        ceremony.setDate(date);
        ceremony.setPlace("Biot");
        entityManager.persist(ceremony);
        Ceremony result = entityManager.find(Ceremony.class, 2018);
        assertEquals(ceremony, result);
        assertEquals("Biot", result.getPlace());
        assertEquals(date , result.getDate());
    }
}
