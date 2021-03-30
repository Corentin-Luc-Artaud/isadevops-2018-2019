package fr.unice.polytech.si4.isa.devops.teami.commands;

import fr.unice.polytech.si4.isa.devops.teami.api.PublicInterludeApi;
import fr.unice.polytech.si4.isa.devops.teami.framework.Command;

import java.util.List;

public class RegisterClub extends Command<PublicInterludeApi> {

    private int nbMembers;
    private String clubName;

    @Override
    public String identifier() {
        return "registerclub";
    }

    @Override
    public void execute() throws Exception {
        this.shell.publicInterludeApi.publicInterludeSOAP.registerClub(clubName, nbMembers);
    }

    @Override
    public String describe() {
        return "registerclub <clubName> <nbMembers>";
    }

    @Override
    public void load(List<String> args) {
        clubName = args.get(0);
        nbMembers = Integer.parseInt(args.get(1));

    }

}