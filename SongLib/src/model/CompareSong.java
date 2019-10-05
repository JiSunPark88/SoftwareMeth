//SongLib Project CS213 Project 1
//Ji Sun Park and Sakar Mohamed M

package model;

import java.util.Comparator;

public class CompareSong implements Comparator<SongList>{

	@Override
	public int compare(SongList first, SongList second) {
		
		if(first.getName().toLowerCase().compareTo(second.getName().toLowerCase()) == 0) {
			
			return first.getArtist().toLowerCase().compareTo(second.getArtist().toLowerCase());
		}
		
		return first.getName().toLowerCase().compareTo(second.getName().toLowerCase());
	}

}
