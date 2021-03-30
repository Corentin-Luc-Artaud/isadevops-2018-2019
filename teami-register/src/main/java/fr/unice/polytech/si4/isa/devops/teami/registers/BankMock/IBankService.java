package fr.unice.polytech.si4.isa.devops.teami.registers.BankMock;

import javax.ejb.Local;

@Local
public interface IBankService {

    boolean payRequest(int rib, double price);
}