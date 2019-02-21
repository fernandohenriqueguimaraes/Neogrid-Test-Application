package ServiceTest;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Constants.TimeConstants;
import Model.Conference;
import Model.Track;
import Service.TrackService;

public class TestTrackService {

	@Test
    public void testTrackService() {
		
		TrackService service = new TrackService();
		
		BufferedReader lines;
		
		try {
			
			lines = service.getInputData("input/input.txt");
			ArrayList<Track> tracks = service.buildTracks(lines);
			ArrayList<Conference> conferenceTracks = service.buildConference(tracks);
			
			// Validating morning tracks
			assertTrue(conferenceTracks.get(0).getTime().compareTo(LocalTime.of(TimeConstants.START_MORNING_TRACK_HOUR, 0, 0)) == 0);
			assertTrue(conferenceTracks.get(0).getTime().isBefore(LocalTime.of(TimeConstants.LUNCH_HOUR, 0, 0)));
			
			assertTrue(conferenceTracks.get(1).getTime().isAfter(LocalTime.of(TimeConstants.START_MORNING_TRACK_HOUR, 0, 0)));
			assertTrue(conferenceTracks.get(1).getTime().isBefore(LocalTime.of(TimeConstants.LUNCH_HOUR, 0, 0)));
			
			assertTrue(conferenceTracks.get(2).getTime().isAfter(LocalTime.of(TimeConstants.START_MORNING_TRACK_HOUR, 0, 0)));
			assertTrue(conferenceTracks.get(2).getTime().isBefore(LocalTime.of(TimeConstants.LUNCH_HOUR, 0, 0)));
			
			// Validating lunch track
			assertTrue(conferenceTracks.get(3).getTime().compareTo(LocalTime.of(TimeConstants.LUNCH_HOUR, 0, 0)) == 0);
			
			// Validating afternoon tracks
			assertTrue(conferenceTracks.get(4).getTime().compareTo(LocalTime.of(TimeConstants.START_AFTERNOON_TRACK_HOUR, 0, 0)) == 0);
			assertTrue(conferenceTracks.get(4).getTime().isBefore(LocalTime.of(TimeConstants.MAXIMUM_END_AFTERNOON_TRACK_HOUR, 0, 0)));
			
			for (int index = 5; index < (conferenceTracks.size()-1); index++) {
				assertTrue(conferenceTracks.get(index).getTime().isAfter(LocalTime.of(TimeConstants.START_AFTERNOON_TRACK_HOUR, 0, 0)));
				assertTrue(conferenceTracks.get(index).getTime().isBefore(LocalTime.of(TimeConstants.MAXIMUM_END_AFTERNOON_TRACK_HOUR, 0, 0)));
			}
			
			// Validating network event track
			assertTrue(conferenceTracks.get((conferenceTracks.size()-1)).getTime().isAfter(LocalTime.of(TimeConstants.MINIMUM_END_AFTERNOON_TRACK_HOUR, 0, 0)));
			assertTrue(conferenceTracks.get((conferenceTracks.size()-1)).getTime().isBefore(LocalTime.of(TimeConstants.MAXIMUM_END_AFTERNOON_TRACK_HOUR, 0, 0)));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
    }

}
