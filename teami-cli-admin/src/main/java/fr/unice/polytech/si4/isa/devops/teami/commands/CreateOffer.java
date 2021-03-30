package fr.unice.polytech.si4.isa.devops.teami.commands;

import fr.unice.polytech.si4.isa.devops.teami.api.AdminInterludeApi;
import fr.unice.polytech.si4.isa.devops.teami.framework.Command;

import java.util.List;

public class CreateOffer extends Command<AdminInterludeApi> {

    private String startTimeString;
    private int duration;

    @Override
    public String identifier() {
        return "createoffer";
    }

    @Override
    public void execute() throws Exception {
        this.shell.adminInterludeApi.adminInterludeSOAP.createOffer(startTimeString, duration);
    }

    @Override
    public String describe() {
        return "Add an event offer. args: createoffer <String startTimeString> <int duration>";
    }

    @Override
    public void load(List<String> args) {
        startTimeString = args.get(0);
        duration = Integer.parseInt(args.get(1));
    }

}