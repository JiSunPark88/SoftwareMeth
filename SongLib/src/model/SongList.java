package model;

public class SongList {
	
private String name, artist, album, year;
	
	public SongList (String name, String artist, String album, String year) {
		this.name=name;
		this.artist=artist;
		this.album=album;
		this.year=year;
	}
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name=name;
	}
	
	public String getArtist() {
		return this.artist;
	}
	public void setArtist(String artist) {
		this.artist=artist;
	}
	
	public String getAlbum() {
		return this.album;
	}
	public void setAlbum(String album) {
		this.album=album;
	}
	
	public String getYear() {
		return this.year;
	}
	public void setYear(String year) {
		this.year=year;
	}
	
	public String displaySong () {
		return this.name + "by" + this.artist;
	}
	public String detailedSong () {
		return this.name + "\n" + this.artist + "\n" + this.album + "\n" + this.year + "\n";
	}

}
