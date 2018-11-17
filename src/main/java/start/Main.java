package start;

import java.io.IOException;
import java.util.Stack;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import config.WorldCupLogDatabaseTestConfiguration;
import controllers.HomeScreenController;
import utils.WorldCupUtils;


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
			System.out.println(getClass().getResource("/views/HomeScreen.fxml"));
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/HomeScreen.fxml"));
			Parent root = (Parent) loader.load();
			HomeScreenController controller = loader.getController();
			controller.setStage(primaryStage);
			screens.push(root);
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
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
