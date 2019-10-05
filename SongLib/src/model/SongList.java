//SongLib Project CS213 Project 1
//Ji Sun Park and Sakar Mohamed M

package model;

public class SongList {
	
	private String name, artist, album, year;
	
	public SongList(String name, String artist) { 
		
		this(name, artist, "", "");
	}
	
	public SongList(String name, String artist,  String album) { 
		
		this(name, artist, album, "");
	}
	
	public SongList(String name, String artist, String album, String year) { 
		
		this.name = name;
		this.artist = artist;
		this.album = album;
		this.year = year;
	}
	
	public String getName() {  
		
		return this.name;
	}
	
	public String getArtist() {
		
		return this.artist;
	}
	
	public String getAlbum() {  
		
		return this.album;
	}
	
	public String getYear() {  
		
		return this.year;
	}
	
	public void setTitle(String name) {  
		
		this.name = name;
	}
	
	public void setArtist(String artist) {  
		
		this.artist = artist;
	}
	
	public void setAlbum(String album) {  
		
		this.album = album;
	}
	
	public void setYear(String year) {  
		
		this.year = year;
	}
	
	
	public String toString() {
		
		return name + " - " + artist;
	}

}
