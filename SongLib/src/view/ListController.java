//SongLib Project CS213 Project 1
//Ji Sun Park and Sakar Mohamed M

package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import model.SongList;
import model.CompareSong;;

public class ListController {
	
	
	@FXML
	ListView<SongList> SongList;
	
	@FXML
	Button pressButton;
	
	@FXML
	TextField FName, FArtist, FAlbum, FYear;
	
	@FXML
	Label displayName, displayArtist, displayAlbum, displayYear;
	
	private ObservableList<SongList> list = FXCollections.observableArrayList();
		
	String file = "src/SongLib.txt";
	
	
	public void start(Stage primaryStage) {
		
		String string;
		SongList song;
		
		try {
			
			FileReader fileRead = new FileReader(file);
			
			BufferedReader bfr = new BufferedReader(fileRead);
			
			while((string = bfr.readLine()) != null) {
				
				
				String[] songString = string.split(",");
				

				switch (songString.length)
				{
				case 2:
					song = new SongList(
							songString[0], songString[1]);
					list.add(song);
					break;
				case 3:
					song = new SongList(
							songString[0], songString[1], songString[2]);
					list.add(song);
					break;
				case 4:
					song = new SongList(
							songString[0], songString[1], songString[2], songString[3]);
					list.add(song);
					break;
				default:
					System.out.println("Error Found");
}
			}
			
			bfr.close();
			
		} catch(FileNotFoundException notFound) {
			
			System.out.println("No such file exists  " + file);
			
		} catch(IOException io) {
			
			System.out.println("Error Found  " + file);
			
		}
		
		
		SongList.setItems(list);
				
		SongList.getSelectionModel().selectedItemProperty().addListener((value, oldValue, newValue) -> displaySong());
		
		if(!list.isEmpty()) { 
			
			SongList.getSelectionModel().select(0);
		}
		
		primaryStage.setOnCloseRequest(event -> {
			
			writeToFile();
		});

	}
	
	
	public void addList(ActionEvent event) {
		
		String name = FName.getText().trim();
		String artist = FArtist.getText().trim();
		String album = FAlbum.getText().trim();
		String year =  FYear.getText().trim();
				
		SongList temp = new SongList(name,artist,album,year);  
		
		if(!errorCheck(temp,true)) {
			
			return;
			
		}
			
		list.add(temp); 
		
		int i = 0;
		
		for(SongList song: list) {
			
			if(song.getName().equals(temp.getName()) && song.getArtist().equals(temp.getArtist())) {
				
				SongList.getSelectionModel().select(i);
				
				break;
			}
			
			i++;
		}
		
		
		FXCollections.sort(list, new CompareSong());
		
		
		
		
		FName.clear();
		FArtist.clear();
		FAlbum.clear();
		FYear.clear();
				
			
	}
	
	public void editList(ActionEvent event) {
		
		
		String name = FName.getText().trim();
		String artist = FArtist.getText().trim();
		String album = FAlbum.getText().trim();
		String year =  FYear.getText().trim();
		
		
				
				
		int index = SongList.getSelectionModel().getSelectedIndex();
		
		int i = 0;
		for(SongList song: list) {
			
			
			
			if(song.getName().equals(name) && song.getArtist().equals(artist) 
					&& index != i) { 
				
				Alert error = new Alert(AlertType.ERROR);
				error.setContentText("This song already exists");
				error.show();
				
				return;
			}
			i++;
			
		}
		
		SongList editSong = SongList.getSelectionModel().getSelectedItem();
		
		if(name.isEmpty()) {
			
			name = editSong.getName();
		}
		
		if(artist.isEmpty()) {
			
			artist = editSong.getArtist();
		}
		
		if(album.isEmpty()) {
			
			album = editSong.getAlbum();
		}
		
		if(year.isEmpty()) {
			
			year = editSong.getYear();
		}


		SongList editedSong = new SongList(name,artist,album,year);
		
			
		if(!errorCheck(editedSong,false)) {
			
			return;
			
		}

		
		
		list.set(index, editedSong);
		
		FXCollections.sort(list, new CompareSong());
		
		
		i = 0;
		for(SongList song: list) {
			
			
			
			if(song.getName().equals(name) && song.getArtist().equals(artist)) {
				
				break;
			}
			i++;
			
		}
		
		SongList.getSelectionModel().select(i);
		
				
		FName.clear();
		FArtist.clear();
		FAlbum.clear();
		FYear.clear();	
		
	
	}
	
	public void deleteList(ActionEvent event) {
		

		if(list.size() == 0) {
			
			return;
		}
		
		SongList deleteName = SongList.getSelectionModel().getSelectedItem();  
		
		int i = 0;
		for(SongList song: list) {  
			
			if(deleteName.getName().equals(song.getName()) && deleteName.getArtist().equals(song.getArtist())) {  
				
				break;
				
			}
			i++;
		}
		
		list.remove(i);
		
		if(list.size() == i) {
			
			SongList.getSelectionModel().select(--i);  
			
		} else {
			
			SongList.getSelectionModel().select(i);  
			
		}
			
		
	}
	
	public void clearList(ActionEvent event) {
		
		FName.clear();
		FArtist.clear();
		FAlbum.clear();
		FYear.clear();
		
	}
	
	public boolean errorCheck(SongList song, boolean tf) {
		
		if(song.getName().trim().isEmpty() || song.getArtist().trim().isEmpty()) {
			
			Alert errorFound = new Alert(AlertType.ERROR);
			errorFound.setContentText("You must enter Name and Artist");
			errorFound.show();
			
			return false;
			
		}
		
		
		if(tf) {
			for(SongList song2: list) {
									
				if(song2.getName().equals(song.getName()) && song2.getArtist().equals(song.getArtist())) { 
						
						
					Alert errorFound = new Alert(AlertType.ERROR);
					errorFound.setContentText("This song already exists");
					errorFound.show();
				
					return false;
				}			
			}
		
		}
		
		
		
		
		String year = song.getYear();
		
		if(year.length() == 0) {
			
			return true;
		}
		
		
		if(year.length() != 4) {
			Alert errorFound = new Alert(AlertType.ERROR);
			errorFound.setContentText("Year must be 4 digits number (YYYY)");
			errorFound.show();
			
			return false;
		}
		
		for(int i = 0; i < 4; i++) {
			
			if(!Character.isDigit(year.charAt(i))) {
				Alert errorFound = new Alert(AlertType.ERROR);
				errorFound.setContentText("Year must be 4 digits number (YYYY");
				errorFound.show();
				
				return false;
			}
			
		}
		
		return true;
		
	}
	
	
	public void writeToFile() {
		
		try {
			
			FileWriter fileWriter = new FileWriter(file);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			
			for(SongList song: list) {
				
				
				
				 if(song.getAlbum().trim().isEmpty() && song.getYear().trim().isEmpty()) {
					 					
					 printWriter.print(song.getName()+ "\t"+ song.getArtist()+ "\n");
			
				 } else if(song.getAlbum().trim().isEmpty()) {
					 
					 printWriter.print(song.getName() + "\t" + song.getArtist() + "\t" + song.getYear() + "\n");
					
				} else if(song.getYear().trim().isEmpty()) {
								
					printWriter.print(song.getName() + "\t" + song.getArtist() + "\t" + song.getAlbum() + "\n");
					
				} else {
					
					printWriter.printf("%s \t %s \t %s \t %s,\n",song.getName(),song.getArtist(),song.getAlbum(),song.getYear());
				}
				
				
				
			}
			
			printWriter.close();
			
		} catch(FileNotFoundException notFound) {
			
			System.out.println("No such file exists  " + file);
			
		} catch(IOException io) {
			
			System.out.println("Error Found  " + file);
			
		}
	}
	
	public void displaySong() { 
		
		if(list.isEmpty()) {
		}
		
		
		SongList song = SongList.getSelectionModel().getSelectedItem();  
		
		if(song != null) {
			
			displayName.setText(song.getName());
			displayArtist.setText(song.getArtist());
			displayAlbum.setText(song.getAlbum());
			displayYear.setText(song.getYear());
		}
	}
	
}
