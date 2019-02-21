package Model;

import java.time.LocalTime;

/*
 * This is the conference model class 
 */
public class Conference {

	private Track track;
	private LocalTime time;
	
	public Conference(LocalTime time, Track track) {
		super();
		this.track = track;
		this.time = time;
	}
	
	/**
	 * @return the track
	 */
	public Track getTrack() {
		return track;
	}
	/**
	 * @param track the track to set
	 */
	public void setTrack(Track track) {
		this.track = track;
	}
	/**
	 * @return the time
	 */
	public LocalTime getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(LocalTime time) {
		this.time = time;
	}
	
}
