package fr.unice.polytech.si4.isa.devops.teami.commands;

import fr.unice.polytech.si4.isa.devops.teami.api.StudentRegisterApi;
import fr.unice.polytech.si4.isa.devops.teami.framework.Command;

public class SeeAllStudents extends Command<StudentRegisterApi> {

    @Override
    public String identifier() {
        return "seeStudents";
    }

    @Override
    public void execute() {
        String result = shell.system.studentSOAP.seeAllStudents();
        System.out.println(result);
    }

    @Override
    public String describe() {
        return "Student list";
    }
}
