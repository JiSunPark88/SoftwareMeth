package model;

import java.util.Comparator;

public class CompareSong implements Comparator <SongList> {
	
	public int compare(SongList first, SongList second) {
		return first.getName().toLowerCase().compareTo(second.getName().toLowerCase());
	}
	
	public class IgnoreSensitive implements Comparator<Object> {
	    public int compare(Object o1, Object o2) {
	        String frist = (String) o1;
	        String second = (String) o2;
	        return frist.toLowerCase().compareTo(second.toLowerCase());
	    }

}
	
}
