package hust.hedspi.project.group6.history.screen;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Home extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/hust/hedspi/project/group6/history/screen/Home.fxml"));
		loader.setController(new HomeController());
		Parent root = loader.load();
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
	
	public static void mainHome(String args[]) {
		launch(args);
	}

}
