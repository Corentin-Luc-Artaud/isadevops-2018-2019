package fr.unice.polytech.si4.isa.devops.teami.commands;

import fr.unice.polytech.si4.isa.devops.teami.api.PlanningApi;
import fr.unice.polytech.si4.isa.devops.teami.framework.Command;

import java.util.List;

public class AddStudentEvent extends Command<PlanningApi> {

    private String startTimeString;
    private String endTimeString;
    private String specialtyString;

    @Override
    public String identifier() {
        return "addstudentevent";
    }

    @Override
    public void execute() throws Exception {
        if(!this.shell.planningApi.planning.addStudentEvent(startTimeString, endTimeString, specialtyString)){
            System.out.println("The event couldn't be added, it's conflicting with an other event");
        }
    }

    @Override
    public String describe() {
        return "Add a student event to the planning. Example: addstudentevent <startTime: dd-MM-yyyy:HH:mm> <endTime: dd-MM-yyyy:HH:mm> <specialty>";
    }

    @Override
    public void load(List<String> args) {
        startTimeString = args.get(0);
        endTimeString = args.get(1);
        specialtyString = args.get(2);
    }

}