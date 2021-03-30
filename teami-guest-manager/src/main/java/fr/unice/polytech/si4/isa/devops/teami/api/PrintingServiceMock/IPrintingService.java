package fr.unice.polytech.si4.isa.devops.teami.api.PrintingServiceMock;

import fr.unice.polytech.si4.isa.devops.teami.entities.guests.Student;

import javax.ejb.Local;
import java.time.LocalDateTime;
import java.util.List;

@Local
public interface IPrintingService {

    LocalDateTime printRequest(List<Student> students);
}