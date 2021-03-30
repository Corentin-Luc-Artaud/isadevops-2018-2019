package fr.unice.polytech.si4.isa.devops.teami.webservice;

import fr.unice.polytech.si4.isa.devops.teami.entities.events.EventOffer;
import fr.unice.polytech.si4.isa.devops.teami.entities.school.Club;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.List;
import java.util.Set;

@WebService(targetNamespace = "www.polydiploma.fr")
public interface AdminInterludeSOAP {

	@WebMethod
	void createOffer(@WebParam(name = "startTimeString") String startTimeString, @WebParam(name = "duration") int duration);

	@WebMethod
	@WebResult(name = "offers")
	String getOffers();

	@WebMethod
	@WebResult(name = "clubs")
	String getCandidatesListForOffer(@WebParam(name = "index") int index);

	@WebMethod
	void chooseCandidateForOffer(@WebParam(name = "index") int index, @WebParam(name = "clubName") String clubName);

}
