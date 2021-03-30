package fr.unice.polytech.si4.isa.devops.teami.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(targetNamespace = "www.polydiploma.fr")
public interface PlanningSOAP {


    @WebMethod
    @WebResult(name = "addStudentEventResult")
    boolean addStudentEvent(@WebParam(name = "startTimeString") String startTimeString,
                            @WebParam(name = "endTimeString") String endTimeString,
                            @WebParam(name = "specialtyString") String specialtyString);

    @WebMethod
    void deleteEvent(@WebParam(name = "startTimeString") String startTimeString,
                     @WebParam(name = "endTimeString") String endTimeString);

    @WebMethod
    @WebResult(name = "moveEventResult")
    boolean moveEvent(@WebParam(name = "oldStartTimeString") String oldStartTimeString,
                      @WebParam(name = "oldEndTimeString") String oldEndTimeString,
                      @WebParam(name = "startTimeString") String startTimeString,
                      @WebParam(name = "endTimeString") String endTimeString);


    @WebMethod
    @WebResult(name="getEventsResult")
    String displayPlanning();
}