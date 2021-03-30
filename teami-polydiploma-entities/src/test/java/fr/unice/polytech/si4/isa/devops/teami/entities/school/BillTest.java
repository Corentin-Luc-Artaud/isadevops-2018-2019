package fr.unice.polytech.si4.isa.devops.teami.entities.school;

import fr.unice.polytech.si4.isa.devops.teami.entities.AbstractDeployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
@Transactional(TransactionMode.ROLLBACK)
public class BillTest extends AbstractDeployment {

    @Test
    public void BillPersistence() {
        Timestamp date = Timestamp.valueOf(LocalDateTime.now());
        Bill bill = new Bill(20.0, date, "o");
        entityManager.persist(bill);

        assertEquals(bill, entityManager.find(Bill.class, new BillKey("o", date)));
    }
}
