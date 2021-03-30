package fr.unice.polytech.si4.isa.devops.teami.api;

import fr.polydiploma.AdminInterludeSOAP;
import fr.polydiploma.AdminInterludeSOAPImplService;

import javax.xml.ws.BindingProvider;
import java.net.URL;

public class AdminInterludeApi {
    public AdminInterludeSOAP adminInterludeSOAP;

    public AdminInterludeApi(String host, String port) {
        this.initAdminInterlude(host, port);
    }

    private void initAdminInterlude(String host, String port) {
        URL wsdlLocation = AdminInterludeApi.class.getResource("/admin-interlude.wsdl");
        AdminInterludeSOAPImplService factory = new AdminInterludeSOAPImplService(wsdlLocation);
        this.adminInterludeSOAP = factory.getAdminInterludeSOAPImplPort();
        String address = "http://" + host + ":" + port + "/teami-polydiploma/webservices/admin-interlude";
        ((BindingProvider) adminInterludeSOAP).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
    }
}
