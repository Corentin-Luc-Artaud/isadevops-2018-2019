package fr.unice.polytech.si4.isa.devops.teami.registers.BankMock;

import javax.ejb.Stateless;

@Stateless
public class BankService implements IBankService {

    public BankService() {
    }

    @Override
    public boolean payRequest(int Rib, double price){
        return true;
    }
}
