package fr.unice.polytech.si4.isa.devops.teami.commands;

import fr.unice.polytech.si4.isa.devops.teami.api.CeremonyManagerApi;
import fr.unice.polytech.si4.isa.devops.teami.framework.Command;

import java.util.List;

public class AddVip extends Command<CeremonyManagerApi> {

    private String firstName;
    private String lastName;
    private String email;
    private String speciality;

    @Override
    public void execute() {
        shell.ceremonyManagerApi.ceremony.addVip(firstName, lastName, email, speciality);
    }

    @Override
    public String identifier() {
        return "addvip";
    }

    @Override
    public String describe() {
        return "addvip <String firstName> <String lastName> <String email> <String specialty>";
    }

    @Override
    public void load(List<String> args) {
        firstName = args.get(0);
        lastName = args.get(1);
        email = args.get(2);
        speciality = args.get(3);
    }


}