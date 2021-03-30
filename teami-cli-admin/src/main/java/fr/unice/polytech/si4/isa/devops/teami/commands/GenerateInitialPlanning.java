package fr.unice.polytech.si4.isa.devops.teami.commands;

import fr.unice.polytech.si4.isa.devops.teami.api.CeremonyManagerApi;
import fr.unice.polytech.si4.isa.devops.teami.framework.Command;

public class GenerateInitialPlanning extends Command<CeremonyManagerApi> {

    @Override
    public String identifier() {
        return "generatePlanning";
    }

    @Override
    public void execute() throws Exception {
        shell.ceremonyManagerApi.ceremony.generateInitialPlanning();
    }

    @Override
    public String describe() {
        return "generateplanning";
    }


}
