/**
 * 
 */
package controllers.players;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import config.WorldCupLogDatabaseTestConfiguration;
import controllers.ControllersInterface;
import domains.Player;
import domains.WorldCup;
import org.apache.commons.lang.StringUtils;
import org.neo4j.ogm.cypher.query.SortOrder;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.jfoenix.controls.JFXListView;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import start.Main;
import utils.WorldCupUtils;


/**
 * @author ruitex23
 *
 */
@ContextConfiguration(classes = WorldCupLogDatabaseTestConfiguration.class)
@Transactional
public class ConsultPlayersController implements ControllersInterface {

	@FXML private ImageView profilePictureIV;
	@FXML private JFXListView<Label> playersListView;
	@FXML private ListView<WorldCup> playersWorldCupsListView;
	@FXML private Label lastnameLabel;
	@FXML private Label firstnameLabel;
	@FXML private Label ageLabel;
	@FXML private Label confederationLabel;
	@FXML private Label countryLabel;
	@FXML private Label worldCupWinsLbl;
	@FXML private Label worldCupFinalsLbl;
	@FXML private Label worldCupAvgLbl;
	@FXML private ImageView flagIV;
	private List<Player> addedPlayers;
	public Stage primaryStage;
	private SessionFactory sessionFactory;
	final FileChooser fileChooser = new FileChooser();
	private Stage popupStage;
	private static final String ADD_PLAYER_SCREEN = "/views/AddEditPlayerScreen.fxml";
	private Session session;
	private static final String DEFAULT_PICTURE = "/default.png";
	private static final String DEFAULT_PICTURE_LIST = "/playerDefault.png";

	public void setStage(Stage stage) {
		this.primaryStage = stage;
	}

	@FXML
	public void initialize() {
		WorldCupLogDatabaseTestConfiguration config = new WorldCupLogDatabaseTestConfiguration();
		sessionFactory = config.getSessionFactory();
		setupScreen();
	}


	public void setupScreen() {
		try {
			profilePictureIV.setFitHeight(125);
			profilePictureIV.setFitWidth(125);
			session = sessionFactory.openSession();
			addedPlayers = new ArrayList<Player>();
			if(session == null) {
				System.out.println("Estou nulo. Cant do shit.");
			} else {
				System.out.println("Não estou nulo. Doing something.");
				addedPlayers = (List<Player>) session.loadAll(Player.class, new SortOrder().add(SortOrder.Direction.DESC,"lastName"));
				if(addedPlayers != null && addedPlayers.size() > 0) {
					System.out.println("123");
					WorldCupUtils.sortPlayers(addedPlayers);
					updatePlayersList();
					setupListViewListeners();
					playersListView.getSelectionModel().selectFirst();
				} else {
					Alert alert = new Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
					alert.setTitle("Feed me Bert!");
					alert.setHeaderText("Empty DB");
					alert.setContentText("Looks like you haven't created any Player yet. Shall we start?");
					alert.showAndWait();
				}
			}
		} catch (Exception e) {
			Alert alert = new Alert(javafx.scene.control.Alert.AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Please send this to Ghostbusters.");
			alert.setContentText(e.getStackTrace().toString());
			alert.showAndWait();
		}
	}

	private void setupListViewListeners() {
		playersListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Label>() {

			@Override
			public void changed(ObservableValue<? extends Label> observable, Label oldValue, Label newValue) {
				if(playersListView.getSelectionModel().getSelectedIndex() >= 0) {
					Player player = addedPlayers.get(playersListView.getSelectionModel().getSelectedIndex());
					lastnameLabel.setText(player.getLastName());
					if(StringUtils.isNotBlank(player.getFirstName())) {
						firstnameLabel.setText(player.getFirstName());
					}
					if(StringUtils.isNotBlank(player.getConfederation())) {
						confederationLabel.setText(player.getConfederation());
					}
					if(StringUtils.isBlank(player.getProfilePicturePath())) {
						profilePictureIV.setImage(new Image(getClass().getResource(DEFAULT_PICTURE).toString()));
					} else {
						profilePictureIV.setImage(new Image(player.getProfilePicturePath()));
					}
					if(StringUtils.isNotBlank(player.getCountry())) {
						countryLabel.setText(player.getCountry());
						flagIV.setImage(new Image(getClass().getResource("/flags-normal/" +
								WorldCupUtils.getCountryCodeByDescription(player.getCountry()).toLowerCase() +
								".png").toString()));
						flagIV.setFitHeight(37);
						flagIV.setFitWidth(54);
					}
					if(player.getAge() != null) {
						ageLabel.setText("Age: " + player.getAge().toString());
					}
					updatePlayersInfoFromDB(player);
				}
			}
		});
	}
	
	private void updatePlayersInfoFromDB(Player player) {
		//list of WC participated
		List<WorldCup> playerWorldCups = getPlayerWorldCups(player.getFirstName());
		System.out.println("AQUI BOY: " + playerWorldCups.size());
		if(playerWorldCups.size() > 0) {
			playersWorldCupsListView.setItems(FXCollections.observableArrayList(getPlayerWorldCups(player.getFirstName())));
		} else {
			playersWorldCupsListView.setItems(null);
		}
		
		//count WC won
		Long wins = getPlayerWorldCupsVictories(player.getLastName() + ", " + player.getFirstName());
		if(wins == null) {
			//TODO remove Erro
			worldCupWinsLbl.setText("NULO");
		} else {
			worldCupWinsLbl.setText(String.valueOf(wins));
		}
	}
	
	/**
	 * gets WC participations for the selected player
	 * @param playerName
	 * @return
	 */
	private List<WorldCup> getPlayerWorldCups(String playerName) {
		String query = "MATCH (player:Player)-[p:PARTICIPATED]->(wc:WorldCup) where player.firstName = '" + playerName + "' RETURN  wc";
		List<WorldCup> wc = (List<WorldCup>) session.query(WorldCup.class, query, Collections.emptyMap());
		if(wc == null) {
			return new ArrayList<WorldCup>();
		} else { 
			return wc;
		}
	}
	
	/**
	 * gets WC victories for the selected player
	 * @param playerName
	 * @return
	 */
	private Long getPlayerWorldCupsVictories(String playerName) {
		String query = "MATCH (wc:WorldCup) where wc.winner = '" + playerName + "' RETURN  count(wc) as COUNT";
		Long count = (Long) session.query(query, Collections.emptyMap()).iterator().next().get("COUNT");
		if(count == null) {
			return Long.valueOf(0);
		} else { 
			return count;
		}
	}

	/**
	 * @throws FileNotFoundException
	 */
	private void updatePlayersList() {
		System.out.println("Update Player List");
		playersListView.getItems().clear();
		for(Player p: addedPlayers) {
			Label lbl = new Label(p.toString());
			if(StringUtils.isBlank(p.getProfilePicturePath())) {
				lbl.setGraphic(new ImageView(new Image(DEFAULT_PICTURE_LIST)));
			} else {
				ImageView tmp = new ImageView(new Image(p.getProfilePicturePath()));
				tmp.setFitHeight(40);
				tmp.setFitWidth(40);
				lbl.setGraphic(tmp);
			}
			playersListView.getItems().add(lbl);
		}
		//		playersListView.getSelectionModel().selectFirst();
	}

	@FXML
	private void addEditPlayer(ActionEvent event){
		FXMLLoader loader;
		Parent root;
		try {
			popupStage = new Stage();
			loader = new FXMLLoader(getClass().getResource(ADD_PLAYER_SCREEN));
			root = (Parent) loader.load();
			AddEditPlayerController addPlayerController = loader.getController();
			addPlayerController.setPreviousController(this);
			popupStage.setScene(new Scene(root));
			addPlayerController.setStage(popupStage);
			addPlayerController.setupScreen();
			popupStage.initModality(Modality.WINDOW_MODAL);
			popupStage.initOwner(
					((Node)event.getSource()).getScene().getWindow() );
			popupStage.show();
		} catch (IOException e) {
			Alert alert = new Alert(javafx.scene.control.Alert.AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Please send this to Ghostbusters.");
			alert.setContentText(e.getStackTrace().toString());
			alert.showAndWait();
		}
	}

	@FXML
	private void editPlayer(ActionEvent event){
		FXMLLoader loader;
		Parent root;
		try {
			popupStage = new Stage();
			loader = new FXMLLoader(getClass().getResource(ADD_PLAYER_SCREEN));
			root = (Parent) loader.load();
			AddEditPlayerController addPlayerController = loader.getController();
			addPlayerController.setPreviousController(this);
			popupStage.setScene(new Scene(root));
			addPlayerController.setStage(popupStage);
			addPlayerController.setupScreen();
			addPlayerController.setupEditScreen(addedPlayers.get(playersListView.getSelectionModel().getSelectedIndex()));
			popupStage.initModality(Modality.WINDOW_MODAL);
			popupStage.initOwner(
					((Node)event.getSource()).getScene().getWindow() );
			popupStage.show();
		} catch (IOException e) {
			Alert alert = new Alert(javafx.scene.control.Alert.AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Please send this to Ghostbusters.");
			alert.setContentText(e.getStackTrace().toString());
			alert.showAndWait();
		}
	}

	@FXML
	private void removePlayer(ActionEvent event) {
		if(playersListView.getSelectionModel().getSelectedItem() != null) {
			session.delete(addedPlayers.get(playersListView.getSelectionModel().getSelectedIndex()));
			addedPlayers.remove(playersListView.getSelectionModel().getSelectedIndex());
			WorldCupUtils.sortPlayers(addedPlayers);
			updatePlayersList();
			playersListView.getSelectionModel().selectFirst();
		}
	}

	public void backFromAddPlayer(Player player) {
		if(isPlayerAlreadyInserted(player)) {
			Alert alert = new Alert(javafx.scene.control.Alert.AlertType.ERROR);
			alert.setTitle("Houston we have a problem!");
			alert.setHeaderText("Repeated Player!");
			alert.setContentText("Looks like we already have that player in our database.");
			alert.showAndWait();
		} else {
			System.out.println("4");
			session.save(player);
			addedPlayers.add(player);
			WorldCupUtils.sortPlayers(addedPlayers);
			updatePlayersList();
			popupStage.close();
		}
	}

	public void backFromEditPlayer(Player player) {
		session.save(player);
		addedPlayers.remove(playersListView.getSelectionModel().getSelectedIndex());
		addedPlayers.add(player);
		WorldCupUtils.sortPlayers(addedPlayers);
		updatePlayersList();
		playersListView.getSelectionModel().selectFirst();
		popupStage.close();

	}

	private Boolean isPlayerAlreadyInserted(Player player) {
		Boolean isRepeated = false;
		for(Player p :addedPlayers) {
			if(p.compareTo(player) == 0) {
				isRepeated = true;
			}
		}
		return isRepeated;
	}

	@FXML
	private void goToHomeScreen(ActionEvent event){
		Parent root = Main.screens.pop();
		Scene scene = root.getScene();
		primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.setScene(scene); 
		primaryStage.show();
	}
}
