package fr.unice.polytech.si4.isa.devops.teami.notification;

import javax.ejb.Local;

@Local
public interface StudentNotification {

    /**
     * Send an email invitation for all students
     * @return number of invitation sent
     */
    int notifyStudents();

    /**
     * Send an email invitation for all VIP (professors and CEO)
     * @return number of invitation sent
     */
    int notifyVips();

    /**
     * Send an email invitation for everybody (students, professors and CEO)
     * @return number of invitation sent
     */
    int notifyEverybody();
}
