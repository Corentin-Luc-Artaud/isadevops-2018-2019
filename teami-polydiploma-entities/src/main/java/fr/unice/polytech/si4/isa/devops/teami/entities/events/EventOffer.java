package fr.unice.polytech.si4.isa.devops.teami.entities.events;

import fr.unice.polytech.si4.isa.devops.teami.entities.school.Club;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class EventOffer implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToMany(cascade = CascadeType.MERGE, mappedBy = "eventOffer")
	private Set<Club> candidates = new HashSet<>();

	@NotEmpty
	private LocalDateTime startTime;

	@NotEmpty
	private LocalDateTime endTime;

	public EventOffer() {}

	public EventOffer(LocalDateTime startTime, LocalDateTime endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * Constructeur EventOffer
	 * @param startTime localdatetime de début
	 * @param duration durée de l'event en minutes
	 */
	public EventOffer(LocalDateTime startTime, int duration){
		this.startTime = startTime;
		this.endTime = startTime.plusMinutes(duration);
	}

	public int getId() {
		return id;
	}

	public Set<Club> getCandidates(){
		return candidates;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void applyToOffer(Club club) {
		candidates.add(club);
	}

	public void refuseOffer(Club club) {
		candidates.remove(club);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		EventOffer that = (EventOffer) o;
		return getId() == that.getId() &&
				Objects.equals(getCandidates(), that.getCandidates()) &&
				getStartTime().equals(that.getStartTime()) &&
				getEndTime().equals(that.getEndTime());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getCandidates(), getStartTime(), getEndTime());
	}

	@Override
	public String toString() {
		return id+". EventOffer{" +
				"id=" + id +
				", candidates=" + candidates +
				", startTime=" + startTime +
				", endTime=" + endTime +
				'}';
	}
}
