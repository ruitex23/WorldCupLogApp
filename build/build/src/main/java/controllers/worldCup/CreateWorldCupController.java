/**
 * 
 */
package main.java.controllers.worldCup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.jfoenix.controls.JFXTextField;

import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import main.java.Main;
import main.java.config.WorldCupLogDatabaseTestConfiguration;
import main.java.controllers.ControllersInterface;
import main.java.controllers.players.ConsultPlayersController;
import main.java.domains.Player;
import main.java.domains.WorldCup;
import main.java.utils.WorldCupUtils;

/**
 * @author ruitex23
 *
 */
@Transactional
@Controller
@ContextConfiguration(classes = WorldCupLogDatabaseTestConfiguration.class)
public class CreateWorldCupController implements ControllersInterface {

	final static Logger logger = Logger.getLogger(ConsultPlayersController.class);

	public Stage primaryStage;
	Scene scene;

	private List<Player> registeredPlayers;
	private List<Player> allAvailablePlayers;


	//General Information 
	@FXML private JFXTextField cityTextField;
	@FXML private JFXTextField countryTextField;
	@FXML private JFXTextField confederationTextField;
	@FXML private JFXTextField organizerTextField;
	@FXML private ChoiceBox<Integer> yearChoiceBox;

	//WorldCup Settings
	private final ToggleGroup group = new ToggleGroup();
	@FXML private RadioButton setsRadioButton;
	@FXML private RadioButton fortyPointsRadioButton;
	@FXML private CheckBox last32Checkbox;
	@FXML private CheckBox last16Checkbox;
	@FXML private CheckBox quarterFinalsCheckbox;
	@FXML private CheckBox semiFinalsCheckbox;
	@FXML private CheckBox finalCheckbox;
	@FXML private CheckBox thirdAndForthCheckbox;

	//Players Participation List
	@FXML private ListView<Player> allPlayers;
	@FXML private ListView<Player> selectedPlayers;
	@FXML private Label totalRegisteredPlayers;

	//Buttons
	@FXML private Button nextBtn;
	@FXML private Button saveQuitBtn;
	@FXML private Button clearAllBtn;
	@FXML private Button homeBtn;

	private SessionFactory sessionFactory;
	private Session session;

	public void setStage(Stage stage) {
		this.primaryStage = stage;
	}

	@FXML
	public void initialize() {
		WorldCupLogDatabaseTestConfiguration config = new WorldCupLogDatabaseTestConfiguration();
		sessionFactory = config.getSessionFactory();
		setupCreateWorldCup();
	}

	private void setupCreateWorldCup() {
		session = sessionFactory.openSession();
		allAvailablePlayers = new ArrayList<Player>();
		registeredPlayers = new ArrayList<Player>();
		System.out.println("Initialize CreateWorldCupController");
		if(session == null) {
			System.out.println("Estou nulo. Cant do shit.");
		} else {
			System.out.println("Não estou nulo. Doing dshit.");
			allAvailablePlayers = (List<Player>) session.loadAll(Player.class);
			WorldCupUtils.sortPlayers(allAvailablePlayers);
		}
		this.allPlayers.setItems(FXCollections.observableArrayList(allAvailablePlayers));
		this.selectedPlayers.setItems(FXCollections.observableArrayList(registeredPlayers));
		this.yearChoiceBox.setItems(FXCollections.observableArrayList(1986, 1987, 1988, 
				1989, 1990, 1991, 1992, 1993, 1994, 1995, 1997, 1998, 1999, 
				2000, 2001, 2002, 2003, 2004, 2005, 2006, 2007, 2008, 2009, 
				2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018));
		this.yearChoiceBox.getSelectionModel().selectLast();
		setsRadioButton.setToggleGroup(group);
		fortyPointsRadioButton.setToggleGroup(group);
		fortyPointsRadioButton.setSelected(true);
		quarterFinalsCheckbox.setSelected(true);
		semiFinalsCheckbox.setSelected(true);
		finalCheckbox.setSelected(true);
		BooleanBinding booleanBind = cityTextField.textProperty().isEmpty()
				.or(countryTextField.textProperty().isEmpty())
				.or(confederationTextField.textProperty().isEmpty())
				.or(organizerTextField.textProperty().isEmpty());
		nextBtn.disableProperty().bind(booleanBind);
		totalRegisteredPlayers.setText("0");
	}

	@FXML
	private void addPlayerToRegistered() {
		if(allPlayers.getSelectionModel().getSelectedItem() != null) {
			registeredPlayers.add(allPlayers.getSelectionModel().getSelectedItem());
			allAvailablePlayers.remove(allPlayers.getSelectionModel().getSelectedItem());
			WorldCupUtils.sortPlayers(registeredPlayers);
			WorldCupUtils.sortPlayers(allAvailablePlayers);
			this.allPlayers.setItems(FXCollections.observableArrayList(allAvailablePlayers));
			this.selectedPlayers.setItems(FXCollections.observableArrayList(registeredPlayers));
			totalRegisteredPlayers.setText(String.valueOf(selectedPlayers.getItems().size()));
		}
	}

	@FXML
	private void removePlayerFromRegistered() {
		if(selectedPlayers.getSelectionModel().getSelectedItem() != null) {
			registeredPlayers.remove(selectedPlayers.getSelectionModel().getSelectedItem());
			allAvailablePlayers.add(selectedPlayers.getSelectionModel().getSelectedItem());
			WorldCupUtils.sortPlayers(registeredPlayers);
			WorldCupUtils.sortPlayers(allAvailablePlayers);
			this.allPlayers.setItems(FXCollections.observableArrayList(allAvailablePlayers));
			this.selectedPlayers.setItems(FXCollections.observableArrayList(registeredPlayers));
			totalRegisteredPlayers.setText(String.valueOf(selectedPlayers.getItems().size()));
		}
	}


	@FXML
	private void goToHomeScreen(ActionEvent event){
		Parent root = Main.screens.pop();
		Scene scene = root.getScene();
		primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.setScene(scene); 
		primaryStage.show();
	}

	@FXML
	private void goToAddGamesScreen(ActionEvent event){
		WorldCup worldCup = createWorldCup();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/resources/views/AddWorldCupGamesScreen.fxml"));
			Parent root = (Parent) loader.load();
			AddWorldCupGamesController controller = loader.getController();
			controller.setWorldCup(worldCup);
			controller.setStage(primaryStage);
			controller.setRegisteredPlayers(registeredPlayers);
			
			Main.screens.push(this.primaryStage.getScene().getRoot());
			System.out.println("just added CreateWorldCupScreen to the queue");

			Scene scene = new Scene(root);
			primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			primaryStage.setScene(scene); 
			primaryStage.show();
		} catch (IOException e) {
			logger.error(e);
		}
	}



	private WorldCup createWorldCup() {

		//lets have some fun
		//worldCup
		WorldCup worldCup = new WorldCup();
		worldCup.setCity(cityTextField.getText());
		worldCup.setConfederation(confederationTextField.getText());
		worldCup.setCountry(countryTextField.getText());
		worldCup.setOrganizer(organizerTextField.getText());
		worldCup.setYear(yearChoiceBox.getValue());

		worldCup.setIsSet(setsRadioButton.isSelected());

		//creating phases according picked in screen
		//I guess quarter-finals they all have so its just 
		//really a matter of last32 or last16
		
		//last32
		worldCup.setHasLast32(last32Checkbox.isSelected());
		
		//last16
		worldCup.setHasLast16(last16Checkbox.isSelected());
		
		//QF
		worldCup.setHasQF(quarterFinalsCheckbox.isSelected());
		
		//3rd and 4th
		worldCup.setHasThirdForth(thirdAndForthCheckbox.isSelected());

		return worldCup;
	}

	@FXML
	public void clearAll() {
		this.cityTextField.setText("");
		this.countryTextField.setText("");
		this.confederationTextField.setText("");
		this.organizerTextField.setText("");
		this.yearChoiceBox.getSelectionModel().selectLast();
		this.setsRadioButton.setSelected(false);
		this.fortyPointsRadioButton.setSelected(false);
		this.last32Checkbox.setSelected(false);
		this.last16Checkbox.setSelected(false);
		this.quarterFinalsCheckbox.setSelected(false);
		this.semiFinalsCheckbox.setSelected(false);
		this.finalCheckbox.setSelected(false);
		this.thirdAndForthCheckbox.setSelected(false);
		//		this.selectedPlayers.setItems(null);
	}

	public String toString() {
		return "CreateWorldCupController";
	}

}
