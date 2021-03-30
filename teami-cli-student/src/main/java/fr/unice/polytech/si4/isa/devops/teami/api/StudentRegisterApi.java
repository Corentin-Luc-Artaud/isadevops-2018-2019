package fr.unice.polytech.si4.isa.devops.teami.api;

import fr.polydiploma.StudentSOAP;
import fr.polydiploma.StudentSOAPImplService;

import javax.xml.ws.BindingProvider;
import java.net.URL;

public class StudentRegisterApi {
    public StudentSOAP studentSOAP;

    public StudentRegisterApi(String host, String port) {
        this.initStudentRegister(host, port);
    }

    private void initStudentRegister(String host, String port) {
        URL wsdlLocation = StudentRegisterApi.class.getResource("/register-student.wsdl");
        StudentSOAPImplService factory = new StudentSOAPImplService(wsdlLocation);
        this.studentSOAP = factory.getStudentSOAPImplPort();
        String address = "http://" + host + ":" + port + "/teami-polydiploma/webservices/register-student";
        ((BindingProvider) studentSOAP).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
    }
}