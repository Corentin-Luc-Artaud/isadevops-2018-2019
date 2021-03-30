package fr.unice.polytech.si4.isa.devops.teami.commands;

import fr.unice.polytech.si4.isa.devops.teami.api.StudentRegisterApi;
import fr.unice.polytech.si4.isa.devops.teami.framework.Command;

import java.util.List;

public class PayForAttendant extends Command<StudentRegisterApi> {

    private int studentId;
    private int rib;

    @Override
    public String identifier() {
        return "payforattendant";
    }

    @Override
    public void execute() {
        if (this.shell.system.studentSOAP.payForAttendant(studentId, rib)) {
            System.out.println("Payment successful");
        } else {
            System.out.println("Payment couldn't be processed");
        }
    }

    @Override
    public String describe() {
        return "payforattendant <studentId> <rib>";
    }

    @Override
    public void load(List<String> args) {
        studentId = Integer.parseInt(args.get(0));
        rib = Integer.parseInt(args.get(1));
    }
}
