/**
 * 
 */
package main.java.controllers;

import java.io.IOException;

import org.springframework.stereotype.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import main.java.Main;
import main.java.controllers.players.ConsultPlayersController;
import main.java.controllers.worldCup.CreateWorldCupController;

/**
 * @author ruitex23
 *
 */
@Controller
public class HomeScreenController  implements ControllersInterface {


	@FXML private Button players;
	@FXML private Button worldCups;

	private Stage primaryStage;

	public void setStage(Stage stage) {
		this.primaryStage = stage;
	}

	@FXML
	public void initialize() {
	}

	@FXML
	private void goToCreateWorldCupScreen(ActionEvent event){
		try {
			if(Main.screens.size() == 0) {
				Main.screens.push(this.primaryStage.getScene().getRoot());
				System.out.println("Add HomeScreen to the queue because it's empty: " + Main.screens.size());
			} else {
				System.out.println("The queue is not empty. Should see this message only once in the beggining of the app");
			}
			System.out.println("Entrei no goToCreateWorldCupScreen");
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/resources/views/CreateWorldCupScreen.fxml"));
			Parent root = (Parent) loader.load();
			CreateWorldCupController controller = loader.getController();
			controller.primaryStage = this.primaryStage;
			System.out.println("HomeScreenController -> CreateWorldCupController");
			Scene scene = new Scene(root);
			primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			primaryStage.setScene(scene); 
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void goToPlayersScreen(ActionEvent event){
		try {
			if(Main.screens.size() == 0) {
				Main.screens.push(this.primaryStage.getScene().getRoot());
				System.out.println("Add HomeScreen to the queue because it's empty: " + Main.screens.size());
			} else {
				System.out.println("The queue is not empty. Should see this message only once in the beggining of the app");
			}
			System.out.println("Entrei no goToPlayersScreen");
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/resources/views/ConsultPlayersScreen.fxml"));
			Parent root = (Parent) loader.load();
			ConsultPlayersController controller = loader.getController();
			controller.primaryStage = this.primaryStage;
			System.out.println("HomeScreenController -> ConsultPlayers");
			Scene scene = new Scene(root);
			primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			primaryStage.setScene(scene); 
			primaryStage.show();
		} catch (IOException l) {
			l.printStackTrace();
		}
	}


	//	@FXML
	//	private void goToCreateWorldCupScreen(ActionEvent event){
	//		try {
	//			if(Main.screens.size() == 0) {
	//				Main.screens.push(this.primaryStage.getScene().getRoot());
	//				System.out.println("Add HomeScreen to the queue because it's empty: " + Main.screens.size());
	//			} else {
	//				System.out.println("The queue is not empty. Should see this message only once in the beggining of the app");
	//			}
	//			System.out.println("Entrei no goToCreateWorldCupScreen");
	//			Parent root = FXMLLoader.load(Main.class.getResource("views/WorldCup/CreateWorldCupScreen.fxml"));
	//			System.out.println("HomeScreenController -> CreateWorldCupController");
	//			Scene scene = new Scene(root);
	//			scene.getStylesheets().add(getClass().getResource("../application.css").toExternalForm());
	//			primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	//			primaryStage.setScene(scene); 
	//			primaryStage.show();
	//		} catch (IOException e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}
	//	}

	//	@FXML
	//	private void goToCreateWorldCupScreen(ActionEvent event){
	//		try {
	//			FXMLLoader loader = new FXMLLoader(Main.class.getResource("views/WorldCup/CreateWorldCupScreen.fxml"));
	//			anchorPane = (AnchorPane) loader.load();
	//			CreateWorldCupController controller = loader.getController();
	//			controller.setStage(primaryStage);
	////			controller.setPreviousScreen(this);
	//			
	//			Scene scene = new Scene(anchorPane);
	//			scene.getStylesheets().add(getClass().getResource("../application.css").toExternalForm());
	//			primaryStage.setScene(scene);
	//			primaryStage.show();
	//		} catch (IOException e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}
	//	}

	public String toString() {
		return "HomeScreenController";
	}

	//    @FXML
	//    private void goToScreen3(ActionEvent event){
	//       myController.setScreen(Main.screen3ID);
	//    }

}
