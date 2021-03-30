package fr.unice.polytech.si4.isa.devops.teami.utils;

import fr.unice.polytech.si4.isa.devops.teami.entities.ceremony.Planning;
import fr.unice.polytech.si4.isa.devops.teami.entities.events.Event;
import fr.unice.polytech.si4.isa.devops.teami.entities.events.StudentEvent;
import fr.unice.polytech.si4.isa.devops.teami.entities.school.Speciality;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Stateless
public class PlanningManagerBean implements PlanningManager {

	@PersistenceContext
	EntityManager entityManager;

	private Planning planning;

	public PlanningManagerBean() {
	}

	private Event EventsAreCollapsing(Event event) {
		for (Event anEvent : planning.getEvents()) {
			if (anEvent.getStartTime().isAfter(event.getStartTime())
					&& anEvent.getStartTime().isBefore(event.getEndTime()))
				return event;
			if (anEvent.getEndTime().isAfter(event.getStartTime()) && anEvent.getEndTime().isBefore(event.getEndTime()))
				return event;
			if (event.getStartTime().isAfter(anEvent.getStartTime())
					&& event.getStartTime().isBefore(anEvent.getEndTime()))
				return event;
			if (event.getEndTime().isAfter(anEvent.getStartTime()) && event.getEndTime().isBefore(anEvent.getEndTime()))
				return event;
		}
		return null;
	}

	private String getPlanning() {
		return planning.toString();
	}

	public boolean addEvent(Event event) {
//		if (EventsAreCollapsing(event) != null)
//			return false;

		planning.addEvent(event);
		entityManager.merge(planning);
		return true;
	}

	public void deleteEvent(Event event) {
		planning.removeEvent(event);
		entityManager.merge(planning);
	}

	public void deleteEvent(LocalDateTime startTime, LocalDateTime endTime) {
		Event event = this.getEventFromTime(startTime, endTime);
		this.deleteEvent(event);
	}

	public boolean moveEvent(Event event, LocalDateTime startTime, LocalDateTime endTime) {
		for (Event anEvent : planning.getEvents()) {
			if (anEvent.getStartTime().isAfter(startTime) && anEvent.getStartTime().isBefore(endTime))
				return false;
			if (anEvent.getEndTime().isAfter(startTime) && anEvent.getEndTime().isBefore(endTime))
				return false;
			if (startTime.isAfter(anEvent.getStartTime()) && startTime.isBefore(anEvent.getEndTime()))
				return false;
			if (endTime.isAfter(anEvent.getStartTime()) && endTime.isBefore(anEvent.getEndTime()))
				return false;
		}
		event.setStartTime(startTime);
		event.setEndTime(endTime);
		return true;
	}

	public boolean moveEvent(LocalDateTime oldStartTime, LocalDateTime oldEndTime, LocalDateTime startTime,
			LocalDateTime endTime) {
		Event event = getEventFromTime(oldStartTime, oldEndTime);
		return moveEvent(event, startTime, endTime);
	}

	public Event getEventFromTime(LocalDateTime startTime, LocalDateTime endTime) {
		for (Event event : planning.getEvents()) {
			if (event.getStartTime().equals(startTime) && event.getEndTime().equals(endTime)) {
				return event;
			}
		}
		return null;
	}

	public Set<Event> getEvents() {
		return planning.getEvents();
	}

	public String displayPlanning() {
		StringBuilder stringBuilder = new StringBuilder();
		for (Event event : planning.getEvents()) {
			stringBuilder.append(event.toString());
			stringBuilder.append("\n");
		}
		return stringBuilder.toString();
	}

	@Override
	public void generateInitialPlanning(LocalDateTime startDate) {
		planning = new Planning();
		LocalDateTime curDate = LocalDateTime.from(startDate);
		List<Speciality> specialities = entityManager.createQuery("SELECT s FROM Speciality s").getResultList();
		for (Speciality speciality : specialities) {
			LocalDateTime endDate = curDate.plusMinutes(15);
			planning.addEvent(new StudentEvent(curDate, endDate, speciality));
			curDate = endDate;
		}
		entityManager.persist(planning);
	}
}
