package fr.unice.polytech.si4.isa.devops.teami.commands;

import fr.unice.polytech.si4.isa.devops.teami.api.StudentRegisterApi;
import fr.unice.polytech.si4.isa.devops.teami.framework.Command;

import java.util.List;

public class AddAttendant  extends Command<StudentRegisterApi> {

    int studentId;
    String firstName;
    String lastName;

    @Override
    public String identifier() {
        return "addattendant";
    }

    @Override
    public void execute() {
        if(this.shell.system.studentSOAP.addAttendant(studentId, firstName, lastName)){
            System.out.println("Attendant added");
        }else{
            System.out.println("Couldn't add attendant");
        }
    }

    @Override
    public String describe() {
        return "addattendant <int studentId> <String firstName> <String lastName>";
    }

    @Override
    public void load(List<String> args) {
        studentId = Integer.parseInt(args.get(0));
        firstName = args.get(1);
        lastName = args.get(2);
    }
}
