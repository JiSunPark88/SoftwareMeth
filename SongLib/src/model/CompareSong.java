package model;

import java.util.Comparator;

public class CompareSong implements Comparator <SongList> {
	
	public int compare(SongList first, SongList second) {
		
		if(first.getName().toLowerCase().compareTo(second.getName().toLowerCase()) == 0) {
			
			return first.getArtist().toLowerCase().compareTo(second.getArtist().toLowerCase());
		}
		
		return first.getName().toLowerCase().compareTo(second.getName().toLowerCase());
	}

}
