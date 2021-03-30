package fr.unice.polytech.si4.isa.devops.teami.commands;

import fr.unice.polytech.si4.isa.devops.teami.api.CeremonyManagerApi;
import fr.unice.polytech.si4.isa.devops.teami.framework.Command;

import java.util.List;

public class CreateNewRdd extends Command<CeremonyManagerApi> {

    protected int year;

    @Override
    public void execute() {
        shell.ceremonyManagerApi.ceremony.createNewRdd(year);
        System.out.println("create RDD for year " + year);
    }

    @Override
    public String identifier() {
        return "new_rdd";
    }

    @Override
    public String describe() {
        return "Create a Rdd for the given year new_rdd <year>";
    }

    @Override
    public void load(List<String> args) {
        year = Integer.parseInt(args.get(0));
    }


}