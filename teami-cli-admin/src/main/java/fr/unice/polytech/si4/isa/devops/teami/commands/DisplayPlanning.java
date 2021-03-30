package fr.unice.polytech.si4.isa.devops.teami.commands;

import fr.unice.polytech.si4.isa.devops.teami.api.PlanningApi;
import fr.unice.polytech.si4.isa.devops.teami.framework.Command;

public class DisplayPlanning extends Command<PlanningApi> {

    @Override
    public String identifier() {
        return "planning";
    }

    @Override
    public void execute() throws Exception {
        System.out.println(this.shell.planningApi.planning.displayPlanning());
    }

    @Override
    public String describe() {
        return "Display all the planning events";
    }

}