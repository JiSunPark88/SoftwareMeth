package songLibrary;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AddMenuController {
	public static boolean isAdded = false;
	static Scene parentScene;
	static Stage parentStage;
    public static void setParents(Stage stage, Scene scene ){
    	 parentScene = scene;
         parentStage = stage;
         return;
    }
    @FXML
    private Button finishAddButton;
    @FXML
    private TextField nameField;
    @FXML
    private TextField artistField;
    @FXML
    private TextField albumField;
    @FXML
    private TextField yearField;
    
    public static Song addedSong;
    public void initialize() {
//		finishAddButton.setDisable(true);
		finishAddButton.disableProperty().bind(
			    Bindings.createBooleanBinding( () -> 
			    nameField.getText().trim().isEmpty(), nameField.textProperty()
			    )
			    .or(  Bindings.createBooleanBinding( () -> 
			    		artistField.getText().trim().isEmpty(), artistField.textProperty()
			         )
			      )
			);
	}
    
    @FXML
	private void cancel(ActionEvent event) throws IOException {
	   	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SongLibraryController.fxml")); 
	    Parent root = fxmlLoader.load();
	    parentStage.getScene().setRoot(root);
	    parentStage.setTitle("Song Library");
	}
    @FXML
	private void addEvent(ActionEvent event) throws IOException {
		Song song = new Song(nameField.getText(), artistField.getText(), albumField.getText(), yearField.getText());
		File file = new File("songs.json");
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
//		If file is empty, create new list and append song, else, load existing list and append new song to list.
	
		if (file.length() <= 3) {
			List<Song> list = new ArrayList<Song>();
			list.add(song);
			addedSong = song;
			isAdded = true;
		    writer.writeValue(file, list);
		}
        else {
        	List<Song> list = mapper.readValue(new FileReader(file), new TypeReference<List<Song>>(){});
        	for (int i = 0; i < list.size(); i++) {
        		if (list.get(i).getName().equals(nameField.getText()) && list.get(i).getArtist().equals(artistField.getText())) {
					Alert alert = new Alert(AlertType.ERROR, "Song with same name and artist already exists!", ButtonType.OK);
        			alert.showAndWait();
        			return;
				}
			}
        	addedSong = song;
        	isAdded = true;
        	list.add(song);
    	    writer.writeValue(file, list);
        }
		SongLibraryController.keepSelectedSong = song;
		SongLibraryController.selectedSong = addedSong;
		SongLibraryController.track = song.getName() + " by " + song.getArtist();
		
//		Return back to parentScene
		   	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SongLibraryController.fxml")); 
		    Parent root = fxmlLoader.load();
		    parentStage.getScene().setRoot(root);
		    parentStage.setTitle("Song Library");
	}
}
