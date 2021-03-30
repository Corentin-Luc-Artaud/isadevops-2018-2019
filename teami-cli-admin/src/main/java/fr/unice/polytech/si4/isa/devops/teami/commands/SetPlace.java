package fr.unice.polytech.si4.isa.devops.teami.commands;

import fr.unice.polytech.si4.isa.devops.teami.api.CeremonyManagerApi;
import fr.unice.polytech.si4.isa.devops.teami.framework.Command;

import java.util.List;

public class SetPlace extends Command<CeremonyManagerApi> {

    private String place;

    @Override
    public String identifier() {
        return "set_place";
    }

    @Override
    public void execute() throws Exception {
        shell.ceremonyManagerApi.ceremony.setPlace(place);
    }

    @Override
    public String describe() {
        return "set the place of the ceremony";
    }

    @Override
    public void load(List<String> args) {
        place = args.get(0);
    }

}