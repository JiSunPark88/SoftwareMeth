//SongLib Project CS213 Project 1
//Ji Sun Park and Sakar Mohamed M

package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.ListController;

public class SongLib extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(
				getClass().getResource("/view/SongLib.fxml")); 
		
		AnchorPane root = (AnchorPane)loader.load();
		
		ListController controller = loader.getController();
		controller.start(primaryStage);
		
		Scene scene = new Scene(root);
	    primaryStage.setTitle("Song Library - jp1476 and mms402 ");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		
		launch(args);
	}

}
