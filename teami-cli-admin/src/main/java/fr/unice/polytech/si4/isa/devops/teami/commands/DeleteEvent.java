package fr.unice.polytech.si4.isa.devops.teami.commands;

import fr.unice.polytech.si4.isa.devops.teami.api.PlanningApi;
import fr.unice.polytech.si4.isa.devops.teami.framework.Command;

import java.util.List;

public class DeleteEvent extends Command<PlanningApi> {

    private String startTimeString;
    private String endTimeString;

    @Override
    public String identifier() {
        return "deletevent";
    }

    @Override
    public void execute() throws Exception {
        this.shell.planningApi.planning.deleteEvent(startTimeString, endTimeString);
    }

    @Override
    public String describe() {
        return "Delete an event (StudentEvent, ClubEvent, VipEvent). Example: deletevent <startTime: dd-MM-yyyy:HH:mm> <endTime: dd-MM-yyyy:HH:mm>";
    }

    @Override
    public void load(List<String> args) {
        startTimeString = args.get(0);
        endTimeString = args.get(1);
    }

}