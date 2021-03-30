package fr.unice.polytech.si4.isa.devops.teami.utils;

import fr.unice.polytech.si4.isa.devops.teami.entities.events.Event;

import javax.ejb.Local;
import java.time.LocalDateTime;
import java.util.Set;

@Local
public interface PlanningManager {

	boolean addEvent(Event event);

	void deleteEvent(Event event);

	void deleteEvent(LocalDateTime startTime, LocalDateTime endTime);

	boolean moveEvent(Event event, LocalDateTime startTime, LocalDateTime endTime);

	boolean moveEvent(LocalDateTime oldStartTime, LocalDateTime oldEndTime, LocalDateTime startTime, LocalDateTime endTime);

	Event getEventFromTime(LocalDateTime startTime, LocalDateTime endTime);

	void generateInitialPlanning(LocalDateTime startDate);

	Set<Event> getEvents();

	String displayPlanning();
}
