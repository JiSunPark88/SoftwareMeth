package view;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

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

import model.CompareSong;
import model.SongList;



public class ListController {
	
	ListView<SongList> ListView;
	
	Label displayName, displayArtist, displayAlbum, displayYear; //variables to display string
	TextField Name, Artist, Album, Year; //variables used when songs are added, deleted, edited
	
	private ObservableList<SongList> list = FXCollections.observableArrayList();
	
	String fileName = "src/songfile.txt";

	
	//display song
	public void displaySong() {
		if(list.isEmpty()) {
			displayName.setText("");
			displayArtist.setText("");
			displayAlbum.setText("");
			displayYear.setText("");
		}
		
		SongList song = ListView.getSelectionModel().getSelectedItem();  
		
		if(song!=null) {
			displayName.setText(song.getName());
			displayArtist.setText(song.getArtist());
			displayAlbum.setText(song.getAlbum());
			displayYear.setText(song.getYear());
		}
	}// end displaySong

	

}
