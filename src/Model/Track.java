package Model;

import java.time.LocalTime;

/*
 * This is the track model class 
 */
public class Track {

	private String description;
	private int minutes;
	
	public Track(String description, int minutes) {
		super();
		setDescription(description);
		setMinutes(minutes);
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the minutes
	 */
	public int getMinutes() {
		return minutes;
	}
	/**
	 * @param minutes the minutes to set
	 */
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

}
