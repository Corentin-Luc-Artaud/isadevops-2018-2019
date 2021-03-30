package fr.unice.polytech.si4.isa.devops.teami.api.PrintingServiceMock;


import fr.unice.polytech.si4.isa.devops.teami.entities.guests.Student;

import javax.ejb.Stateless;
import java.time.LocalDateTime;
import java.util.List;

@Stateless
public class PrintingService implements IPrintingService {

    public PrintingService() {
    }

    @Override
    public LocalDateTime printRequest(List<Student> students){
        LocalDateTime deliveringDate = LocalDateTime.now();
        deliveringDate = deliveringDate.plusHours(students.size());
        return deliveringDate;
    }

}
