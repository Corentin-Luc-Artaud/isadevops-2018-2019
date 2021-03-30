package fr.unice.polytech.si4.isa.devops.teami.commands;

import fr.unice.polytech.si4.isa.devops.teami.api.StudentRegisterApi;
import fr.unice.polytech.si4.isa.devops.teami.framework.Command;

public class SeeRegisteredStudents extends Command<StudentRegisterApi> {

    @Override
    public String identifier() {
        return "seeRegisteredStudents";
    }

    @Override
    public void execute() {
        String result = shell.system.studentSOAP.seeRegisteredStudents();
        System.out.println(result);
    }

    @Override
    public String describe() {
        return "Student list";
    }
}
