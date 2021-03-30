package fr.unice.polytech.si4.isa.devops.teami.commands;

import fr.unice.polytech.si4.isa.devops.teami.api.StudentRegisterApi;
import fr.unice.polytech.si4.isa.devops.teami.framework.Command;

import java.util.List;

public class RegisterStudent extends Command<StudentRegisterApi> {

    private int studentId;

    @Override
    public String identifier() {
        return "register";
    }

    @Override
    public void execute() throws Exception {
        shell.system.studentSOAP.registerStudent(studentId);
    }

    @Override
    public String describe() {
        return "Used to register a student to the ceremony. register <studentId>";
    }

    @Override
    public void load(List<String> args) {
        studentId = Integer.parseInt(args.get(0));
    }

}
