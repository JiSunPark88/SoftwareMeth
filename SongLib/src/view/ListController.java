package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

import model.SongList;
import model.CompareSong;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;



public class ListController {

	ListView<SongList> listView;  

	Text name, artist, album, year;

	   private ObservableList<SongList> list;
	   
	   public void start(Stage main) {                

		   list = FXCollections.observableArrayList(readFile("src/songs.txt")); 
		      listView.setItems(list); 
		      
		      listView.setCellFactory(new Callback<ListView<SongList>, ListCell<SongList>>(){
		    	  
		            public ListCell<SongList> call(ListView<SongList> p) {
		                 
		                ListCell<SongList> listCell = new ListCell<SongList>(){
		 
		                    protected void updateItem(SongList song, boolean bool) {
		                        super.updateItem(song, bool);
		                        if (song != null) {
		                            setText(song.getName());
		                        }
		                        else if (song == null)
		                        {
		                        	setText(null);
		                        }
		                    }
		 
		                };
		                 
		                return listCell;
		            }
		        }
		      );
		      
		      if (!list.isEmpty())
		    	  listView.getSelectionModel().select(0);

		      displaySong();

		      listView.getSelectionModel().selectedItemProperty().addListener(
		    		  (value, oldValue, newValue) -> 
		    		  displaySong());
		      
		      main.setOnCloseRequest(event -> {
		    	  PrintWriter printWrite;
		    	  	try {
		    	  			File file = new File ("src/songs.txt");
		    	  			file.createNewFile();
		    	  			printWrite = new PrintWriter(file);
							for(SongList song: list)
					    	  {
								printWrite.println(song.getName());
								printWrite.println(song.getArtist());
								printWrite.println(song.getAlbum());
								printWrite.println(song.getYear());
					    		  
					    	  }
							printWrite.close(); 
				} catch (Exception exception) {
					exception.printStackTrace();
				}    	 
		      });

		   }//end start
	   
	   //show song
		  private void displaySong() {
			   if (listView.getSelectionModel().getSelectedIndex() < 0)
				   return;
			   			   
			   SongList song = listView.getSelectionModel().getSelectedItem();
			   
			   name.setText(song.getName());
			   artist.setText(song.getArtist());
			   album.setText(song.getAlbum());
			   year.setText(song.getYear());
		   }//end showSong
	   
		  //key press
		  private void keyPress(KeyEvent e) {
			   if(e.getCode() == KeyCode.UP || e.getCode() == KeyCode.DOWN)
				   displaySong();
		   }//end keyPress
		  
		  //read file
		  private ArrayList<SongList> readFile(String filePathName)
		   {
			   ArrayList <SongList> songs = new ArrayList<SongList>();
			   BufferedReader br;
			   Path filePath = Paths.get(filePathName);
			   try {

					if (!new File(filePathName).exists())
					{
					   return songs;
					}
				   br = Files.newBufferedReader(filePath);
				   String line = br.readLine();
					
				   while (line != null) { 
			              
					   String title = line;
			               
					   line = br.readLine();
					   String artist = line;
			               
					   line = br.readLine();
					   String album = line;
			               
					   line = br.readLine();
					   String year = line;
			               
					   SongList temp = new SongList(title, artist, album, year);
					   songs.add(temp);
			               
					   line = br.readLine(); 
				   }
				  
					
			   } catch (IOException e) {
				   e.printStackTrace();
			   }
			      
			   Collections.sort(songs, new CompareSong());
			   return songs;
		   }
	
		  
		  //show an error
		  //use when the song is added, deleted, or edited 
		   private void error(String error) {
			   Alert errorAlert = 
					   new Alert(AlertType.INFORMATION);
			   errorAlert.setTitle("Error");
			   errorAlert.setHeaderText("Error");
			   String content = error;
			   errorAlert.setContentText(content);
			   errorAlert.showAndWait();
		   }//end error
		   

		   //check field
		   //alert that title and artist cannot be empty
		   //alert that year must be number and cannot exceed 4 digits
		   //use when the song is added and edited
		   private String checkFields(String name, String artist, String album, String year) {
			   if (name.trim().isEmpty())
				   return "Name must be filled in.";
			   else if (artist.trim().isEmpty())
				   return "Artist must be filled in.";
			   else if (!existSong(name, artist))
				   return "This title and artist already existed in the library.";
			   
			   else if (!year.trim().isEmpty()) {
				   if (!year.trim().matches("[0-9]+"))
					   return "Use only number for Year";
				   else if (year.trim().length() != 4)
					   return "Year should not exceed 4 digits";
			   }
			   
			   return null;
		   }//end checkFields
		   
		   //check if the song is existing already
		   //false if it already exists 
		   private boolean existSong(String name, String artist) {
			   for (SongList song : list) {
				   				   
				   if (song.getName().toLowerCase().equals(name.trim().toLowerCase()) &&
						   song.getArtist().toLowerCase().equals(artist.trim().toLowerCase()))
				   {
					   return false;
				   }
					   
			   }
			   return true;
		   }//end existSong
}
