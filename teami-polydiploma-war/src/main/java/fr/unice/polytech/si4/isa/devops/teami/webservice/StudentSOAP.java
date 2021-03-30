package fr.unice.polytech.si4.isa.devops.teami.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(targetNamespace = "www.polydiploma.fr")
public interface StudentSOAP {

    @WebMethod
    @WebResult(name = "registerStudentResult")
    void registerStudent(@WebParam(name = "id") int id);

    @WebMethod
    @WebResult(name = "seeRegisteredStudentResult")
    String seeRegisteredStudents();

    @WebMethod
    @WebResult(name = "seeAllStudentsResult")
    String seeAllStudents();

    boolean isRegistered(int studentId);

    int getNumberOfRegisteredGuest();

    boolean addAttendant(int studentId, String firstName, String lastName);

    boolean payForAttendant(int studentId, int rib);

    double getPrice(int studentId);
}
