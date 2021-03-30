package fr.unice.polytech.si4.isa.devops.teami.commands;

import fr.unice.polytech.si4.isa.devops.teami.api.PublicInterludeApi;
import fr.unice.polytech.si4.isa.devops.teami.framework.Command;

import java.util.List;

public class PostulateOffer extends Command<PublicInterludeApi> {

    private int indexOffer;
    private String clubName;

    @Override
    public String identifier() {
        return "postulate";
    }

    @Override
    public void execute() throws Exception {
        this.shell.publicInterludeApi.publicInterludeSOAP.postulateOffer(indexOffer, clubName);
    }

    @Override
    public String describe() {
        return "postulate <indexOffer> <clubName>";
    }

    @Override
    public void load(List<String> args) {
        indexOffer = Integer.parseInt(args.get(0));
        clubName = args.get(1);
    }

}