package fr.unice.polytech.si4.isa.devops.teami.commands;

import fr.unice.polytech.si4.isa.devops.teami.api.StudentRegisterApi;
import fr.unice.polytech.si4.isa.devops.teami.framework.Command;

import java.util.List;

public class GetPrice extends Command<StudentRegisterApi> {

    int studentId;

    @Override
    public String identifier() {
        return "getprice";
    }

    @Override
    public void execute() {
        System.out.println(this.shell.system.studentSOAP.getPrice(studentId));
    }

    @Override
    public String describe() {
        return "getprice <studentId>";
    }

    @Override
    public void load(List<String> args) {
        studentId = Integer.parseInt(args.get(0));
    }
}
