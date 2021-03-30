package fr.unice.polytech.si4.isa.devops.teami.commands;

import fr.unice.polytech.si4.isa.devops.teami.api.PlanningApi;
import fr.unice.polytech.si4.isa.devops.teami.framework.Command;

import java.util.List;

public class MoveEvent extends Command<PlanningApi> {

    private String oldStartTimeString;
    private String oldEndTimeString;
    private String startTimeString;
    private String endTimeString;

    @Override
    public String identifier() {
        return "moveEvent";
    }

    @Override
    public void execute() throws Exception {
        if(!this.shell.planningApi.planning.moveEvent(oldStartTimeString, oldEndTimeString, startTimeString, endTimeString)){
            System.out.println("The event couldn't be moved, it's conflicting with an other event");
        }
    }

    @Override
    public String describe() {
        return "Move an event (StudentEvent, ClubEvent, VipEvent. Example: moveEvent <oldStartTime: dd-MM-yyyy:HH:mm> <oldEndTime: dd-MM-yyyy:HH:mm> <newStartTime: dd-MM-yyyy:HH:mm> <newEndTime: dd-MM-yyyy:HH:mm>";
    }

    @Override
    public void load(List<String> args) {
        oldStartTimeString = args.get(0);
        oldEndTimeString = args.get(1);
        startTimeString = args.get(2);
        endTimeString = args.get(3);
    }

}