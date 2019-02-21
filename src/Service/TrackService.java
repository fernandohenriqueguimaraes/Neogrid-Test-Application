package Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Constants.TimeConstants;
import Model.Conference;
import Model.Track;

public class TrackService {

	/*
	 * This class is responsible for reading the input.txt 
	 * 
	 * @param String filePath
	 * @return BufferedReader
	 * @throws FileNotFoundException
	 */	
	public BufferedReader getInputData(String filePath) throws FileNotFoundException {
		
		File file = new File(filePath);
		FileReader fr = new FileReader(file);
		return new BufferedReader(fr);		
	}
	
	/*
	 * This class is responsible for build the track objects and identify its minutes 
	 * 
	 * @param BufferedReader
	 * @ArrayList<Track>
	 * @throws NumberFormatException, IOException
	 */	
	public ArrayList<Track> buildTracks(BufferedReader br) throws NumberFormatException, IOException {
		String line;
		Matcher matcher;
		Pattern pattern = Pattern.compile("\\d+");
		ArrayList<Track> tracks = new ArrayList<Track>();
		
		while((line = br.readLine()) != null){
				
			Track track = new Track(line, 5);
				
			matcher = pattern.matcher(line);
			if (matcher.find()) {
				track = new Track(line, Integer.parseInt(matcher.group()));
			}
				
			tracks.add(track);
		}
		
		return tracks;
	}
	
	/*
	 * This class is responsible for organize the conference event 
	 * 
	 * @param ArrayList<Track> tracks
	 * @return ArrayList<Conference> conferenceTrackList
	 */	
	public ArrayList<Conference> buildConference(ArrayList<Track> tracks) {
		
		ArrayList<Conference> conferenceTrackList = new ArrayList<Conference>();
		
		conferenceTrackList = buildMorningConference(tracks);
		conferenceTrackList.add(new Conference((LocalTime.of(TimeConstants.LUNCH_HOUR, 0, 0)), ( new Track("Lunch", 60))));
		conferenceTrackList = buildAfternoonConference(conferenceTrackList, tracks);
		
		return conferenceTrackList;
	}

	/*
	 * This class is responsible for organize the conference event during the morning
	 * 
	 * @param ArrayList<Track> tracks
	 * @return ArrayList<Conference> conferenceTrackList
	 */	
	private ArrayList<Conference> buildMorningConference(ArrayList<Track> tracks) {
		
		Random rand = new Random(); 
		ArrayList<Conference> conferenceTrackList = new ArrayList<Conference>();
		ArrayList<Track> morningTracks = new ArrayList<Track>();
		int minutes;
		
		do {
			minutes = 0;
			morningTracks.removeAll(morningTracks);
			
			for (int i = 0; i < 3; i++) {
				
				int randomIndex = rand.nextInt(tracks.size());
				
				if (morningTracks.contains(tracks.get(randomIndex))) {
					randomIndex = rand.nextInt(tracks.size());
				}
				
				morningTracks.add(tracks.get(randomIndex));
				minutes += tracks.get(randomIndex).getMinutes();
			}
			
		} while(minutes != TimeConstants.MAXIMUM_MORNING_MINUTES);
		
		LocalTime time = LocalTime.of(TimeConstants.START_MORNING_TRACK_HOUR, 0, 0);
		
		for(Track morningTrack : morningTracks ) {
			conferenceTrackList.add(new Conference(time, morningTrack));
			time = time.plusMinutes(morningTrack.getMinutes());
		}
		
		return conferenceTrackList;
	} 
	
	/*
	 * This class is responsible for organize the conference event during the afternoon
	 * 
	 * @param ArrayList<Conference> conferenceTrackList, ArrayList<Track> tracks
	 * @return ArrayList<Conference> conferenceTrackList
	 */	
	private ArrayList<Conference> buildAfternoonConference(ArrayList<Conference> conferenceTrackList,
			ArrayList<Track> tracks) {
		
		ArrayList<Track> afternoonTracks = new ArrayList<Track>();
		Random rand = new Random(); 
		
		// Remove all morning tracks
		for(Conference conferenceTrack : conferenceTrackList ) {
			tracks.remove(conferenceTrack.getTrack());
		}
		
		int minutes = 0;
		
		do {
			
			int randomIndex = rand.nextInt(tracks.size());
			if ((minutes + tracks.get(randomIndex).getMinutes()) < TimeConstants.MAXIMUM_AFTERNOON_MINUTES) { 
				afternoonTracks.add(tracks.get(randomIndex));
				minutes += tracks.get(randomIndex).getMinutes();
			}
			
			tracks.remove(tracks.get(randomIndex));
			
		} while (!tracks.isEmpty());
		
		LocalTime time = LocalTime.of(TimeConstants.START_AFTERNOON_TRACK_HOUR, 0, 0);
		
		for(Track afternoonTrack : afternoonTracks ) {
			conferenceTrackList.add(new Conference(time, afternoonTrack));
			time = time.plusMinutes(afternoonTrack.getMinutes());
		}
		
		conferenceTrackList.add(new Conference(time, new Track("Networking Event", 0)));
		
		return conferenceTrackList;
	}

	/*
	 * This class is responsible for showing the conference event
	 * 
	 * @param ArrayList<Conference> conferenceTracks
	 */	
	public void showConferenceEvent(ArrayList<Conference> conferenceTracks) {
		
		DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh:mm a");
		
		for (Conference conferenceTrack: conferenceTracks) {
			System.out.print(conferenceTrack.getTime().format(timeFormat) + " " + conferenceTrack.getTrack().getDescription() + "\n");
		}
	}

}
