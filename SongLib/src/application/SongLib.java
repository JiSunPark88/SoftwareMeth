package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import view.ListController;

public class SongLib extends Application {
	public void start (Stage primaryStage) throws Exception {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/songLib.fxml"));
			GridPane root = loader.load();
			
			ListController listController = loader.getController();
			listController.start(primaryStage); 
			
			Scene scene = new Scene(root, root.getPrefWidth(), root.getPrefHeight());
			primaryStage.setTitle("Song Library by jp1476 and mms402");
			primaryStage.setScene(scene);
			primaryStage.setResizable(true);
			primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
