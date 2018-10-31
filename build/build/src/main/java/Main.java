package main.java;
	
import java.io.IOException;
import java.util.Stack;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.config.WorldCupLogDatabaseTestConfiguration;
import main.java.controllers.HomeScreenController;
import main.java.utils.WorldCupUtils;


@ContextConfiguration(classes = WorldCupLogDatabaseTestConfiguration.class)
@ActiveProfiles(profiles = "test")
public class Main extends Application {
	
	private Stage primaryStage;
	
	public static Stack<Parent> screens;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setResizable(false);
		screens = new Stack<Parent>();
		WorldCupUtils.initializeCountries();
		getHomeScreen();
	}
	
	public void getHomeScreen() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/resources/views/HomeScreen.fxml"));
			Parent root = (Parent) loader.load();
			HomeScreenController controller = loader.getController();
			controller.setStage(primaryStage);
			screens.push(root);
			Scene scene = new Scene(root);
			scene.getStylesheets().add(Main.class.getResource("/main/resources/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
