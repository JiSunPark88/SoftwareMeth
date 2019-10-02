package songLibrary;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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

public class EditMenuController {
	public static boolean isEdited = false;
	static Scene parentScene;
	static Stage parentStage;
    public static void setParents(Stage stage, Scene scene ){
    	 parentScene = scene;
         parentStage = stage;
         return;
    }
    @FXML
    private Button saveButton;
    @FXML
    private TextField nameField;
    @FXML
    private TextField artistField;
    @FXML
    private TextField albumField;
    @FXML
    private TextField yearField;
    
    private String initialNameField;
    private String initialArtistField;
    
    public static Song editedSong;
    public void initialize() {
//		finishAddButton.setDisable(true);
    	saveButton.disableProperty().bind(
			    Bindings.createBooleanBinding( () -> 
			    nameField.getText().trim().isEmpty(), nameField.textProperty()
			    )
			    .or(  Bindings.createBooleanBinding( () -> 
			    		artistField.getText().trim().isEmpty(), artistField.textProperty()
			         )
			      )
			);
    	Song song = SongLibraryController.selectedSong;
    	nameField.setText(song.getName());
    	artistField.setText(song.getArtist());
    	albumField.setText(song.getAlbum());
    	yearField.setText(song.getYear());
    	SongLibraryController.keepSelectedSong = song;
    	initialNameField = nameField.getText();
    	initialArtistField = artistField.getText();
	}
    
    @FXML
	private void cancel(ActionEvent event) throws IOException {
	   	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SongLibraryController.fxml")); 
	    Parent root = fxmlLoader.load();
	    parentStage.getScene().setRoot(root);
	    parentStage.setTitle("Song Library");
	}
    @FXML
	private void editEvent(ActionEvent event) throws IOException {
		Song song = new Song(nameField.getText(), artistField.getText(), albumField.getText(), yearField.getText());
		File file = new File("songs.json");
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
//		If file is empty, create new list and append song, else, load existing list and append new song to list.
		if (file.length() <= 3) {
			return;
		}
        else {
        	List<Song> list = mapper.readValue(new FileReader(file), new TypeReference<List<Song>>(){});
        	for (int i =0; i<list.size(); i++){
        		if (SongLibraryController.selectedSong.getName().equals(list.get(i).getName()) && SongLibraryController.selectedSong.getArtist().equals(list.get(i).getArtist()) ) {
        			for (int j = 0; j <list.size(); j++) {
        				if (initialNameField==nameField.getText() && initialArtistField==artistField.getText()) {
							if (list.get(j).getName().equals(nameField.getText()) && list.get(j).getArtist().equals(artistField.getText())) {
								Alert alert = new Alert(AlertType.ERROR, "Song with same name and artist already exists!", ButtonType.OK);
			        			alert.showAndWait();
			        			return;
							}
        				}
					}
        			list.remove(i);
				}
			}
        	editedSong = song;
        	isEdited = true;
        	list.add(song);
    	    writer.writeValue(file, list);
        }
		SongLibraryController.selectedSong = editedSong;
		SongLibraryController.track = song.getName() + " by " + song.getArtist();
		
//		Return back to parentScene
		   	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SongLibraryController.fxml")); 
		    Parent root = fxmlLoader.load();
		    parentStage.getScene().setRoot(root);
		    parentStage.setTitle("Song Library");
	}
}
