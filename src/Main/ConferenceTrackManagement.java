package Main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import Model.Conference;
import Model.Track;
import Service.TrackService;

/*
 * This is the main class, responsible for running the code 
 */
public class ConferenceTrackManagement {

	public static void main(String[] args) {
		
		TrackService service = new TrackService();
		
		try {
			
			BufferedReader lines = service.getInputData("input/input.txt");
			ArrayList<Track> tracks = service.buildTracks(lines);
			ArrayList<Conference> conferenceTracks =service.buildConference(tracks);
			service.showConferenceEvent(conferenceTracks);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}
}
