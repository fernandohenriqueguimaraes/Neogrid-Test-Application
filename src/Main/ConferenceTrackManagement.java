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
		DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh:mm a");
		
		try {
		
			BufferedReader lines = service.getInputData("input/input.txt");
			ArrayList<Track> tracks = service.buildTracks(lines);
			ArrayList<Conference> conferenceTracks =service.buildConference(tracks);
			
			for (Conference conferenceTrack: conferenceTracks) {
				System.out.print(conferenceTrack.getTime().format(timeFormat) + " " + conferenceTrack.getTrack().getDescription() + "\n");
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}