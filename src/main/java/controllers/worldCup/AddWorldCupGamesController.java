package controllers.worldCup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.apache.log4j.Logger;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.stereotype.Controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import start.Main;
import config.WorldCupLogDatabaseTestConfiguration;
import controllers.ControllersInterface;
import controllers.HomeScreenController;
import domains.Composed;
import domains.GameExt;
import domains.IsPartOf;
import domains.Participated;
import domains.Phase;
import domains.Player;
import domains.WorldCup;
import utils.WorldCupUtils;

@Controller
public class AddWorldCupGamesController  implements ControllersInterface {

	final static Logger logger = Logger.getLogger(AddWorldCupGamesController.class);

	private static final String ADD_SET_GAME = "/views/AddGameScreenSets.fxml";
	private static final String ADD_POINTS_GAME = "/views/AddGameScreen.fxml";
	private static final String HOMESCREEN = "/views/HomeScreen.fxml";

	private AddGameController addGameController;
	private AddGameSetsController addGameSetsController;

	private Stage primaryStage;
	private Stage popupStage;
	private WorldCup worldCup;
	private Phase last32;
	private Phase last16;
	private Phase quarterFinals;
	private Phase semiFinals;
	private Phase theFinal;
	private Phase thirdForth;

	private List<Player> registeredPlayers;
	
	private List<GameExt> gamesList;

	private List<GameExt> last32GamesList;
	private List<GameExt> last16GamesList;
	private List<GameExt> quarterFinalsGamesList;
	private List<GameExt> semiFinalsGamesList;
	private List<GameExt> finalGameList;
	private List<GameExt> thirdForthGameList;

	@FXML private TableView<GameExt> gamesTV;
	@FXML private TableColumn<GameExt, String> phaseColumn;
	@FXML private TableColumn<GameExt, String> gameDescriptionColumn;
	@FXML private TableColumn<GameExt, String> gameWinnerColumn;
	@FXML private Label last32Label;
	@FXML private Label last16Label;
	@FXML private Label quarterFinalsLabel;
	@FXML private Label semiFinalsLabel;
	@FXML private Label finalLabel;
	@FXML private Label thirdForthLabel;
	@FXML private ProgressBar progressBar;


	private int last32Count = 0;
	private int last16Count = 0;
	private int quarterFinalsCount = 0;
	private int semiFinalsCount = 0;
	private int thirdForthCount = 0;
	private int finalCount = 0;

	private SessionFactory sessionFactory;
	private Session session;


	@FXML
	public void initialize() {
		WorldCupLogDatabaseTestConfiguration config = new WorldCupLogDatabaseTestConfiguration();
		sessionFactory = config.getSessionFactory();
		progressBar.setVisible(false);
		preparePhases();
		phaseColumn.setCellValueFactory(new PropertyValueFactory<GameExt, String>("phaseDescription"));
		gameDescriptionColumn.setCellValueFactory(new PropertyValueFactory<GameExt, String>("gameDescription"));
		gameWinnerColumn.setCellValueFactory(new PropertyValueFactory<GameExt, String>("gameWinner"));
		gamesList = new ArrayList<GameExt>();
		gamesTV.setItems(FXCollections.observableArrayList(gamesList));
	}

	/**
	 * 
	 */
	private void preparePhases() {
		last32GamesList = new ArrayList<GameExt>();
		last16GamesList = new ArrayList<GameExt>();
		quarterFinalsGamesList = new ArrayList<GameExt>();
		semiFinalsGamesList = new ArrayList<GameExt>();
		finalGameList = new ArrayList<GameExt>();
		thirdForthGameList = new ArrayList<GameExt>();
	}

	@FXML
	private void saveWCtoDB() {
		session = sessionFactory.openSession();
		progressBar.setVisible(true);
		progressBar.setProgress(0.25);
		//Players
		for(Player player: registeredPlayers) {
			session.save(new Participated(player, worldCup));
		}

		progressBar.setProgress(0.35);

		session.save(worldCup);
		
		//Last32
		if(!last32GamesList.isEmpty() && worldCup.getHasLast32()) {
			last32 = new Phase();
			last32.setPhaseDescription(WorldCupUtils.LAST_32_PHASE);
			session.save(last32);
			for(GameExt game: last32GamesList) {
				session.save(game);
				session.save(new IsPartOf(game, last32));
			}
			session.save(last32);
			session.save(new Composed(last32, worldCup));
		}

		progressBar.setProgress(0.40);
		//Last16
		if(!last16GamesList.isEmpty() && worldCup.getHasLast16()) {
			last16 = new Phase();
			last16.setPhaseDescription(WorldCupUtils.LAST_16_PHASE);
			session.save(last16);
			for(GameExt game: last16GamesList) {
				session.save(game);
				session.save(new IsPartOf(game, last16));
			}
			session.save(new Composed(last16, worldCup));
			session.save(last16);
		}

		progressBar.setProgress(0.50);
		//QuarterFinals
		if(!quarterFinalsGamesList.isEmpty()) {
			quarterFinals = new Phase();
			quarterFinals.setPhaseDescription(WorldCupUtils.QUARTER_FINALS_PHASE);
			session.save(quarterFinals);
			for(GameExt game: quarterFinalsGamesList) {
				session.save(game);
				session.save(new IsPartOf(game, quarterFinals));
			}
			session.save(new Composed(quarterFinals, worldCup));
			session.save(quarterFinals);
		}

		progressBar.setProgress(0.65);
		//SemiFinals
		if(!semiFinalsGamesList.isEmpty()) {
			semiFinals = new Phase();
			semiFinals.setPhaseDescription(WorldCupUtils.SEMI_FINALS_PHASE);
			session.save(semiFinals);
			for(GameExt game: semiFinalsGamesList) {
				session.save(game);
				session.save(new IsPartOf(game, semiFinals));
			}
			session.save(new Composed(semiFinals, worldCup));
			session.save(semiFinals);
		}

		//Final
		for(GameExt game: finalGameList) {
			worldCup.setWinner(game.getWinner());
			session.save(worldCup);
			theFinal = new Phase();
			theFinal.setPhaseDescription(WorldCupUtils.FINAL_PHASE);
			session.save(theFinal);
			session.save(game);
			session.save(new IsPartOf(game, theFinal));
			session.save(new Composed(theFinal, worldCup));
		}

		progressBar.setProgress(0.90);
		//Third and Forth, if there is
		if(thirdForthGameList.size() > 0 && worldCup.getHasThirdForth()) {
			thirdForth = new Phase();
			thirdForth.setPhaseDescription(WorldCupUtils.THIRD_FORTH_PHASE);
			session.save(thirdForth);
			for(GameExt game: thirdForthGameList) {
				session.save(game);
				session.save(new IsPartOf(game, thirdForth));
			}
			session.save(new Composed(thirdForth, worldCup));
			session.save(thirdForth);
		}
		goToHomescreen();
	}

	private void goToHomescreen(){
		FXMLLoader loader;
		Parent root;
		try {
			loader = new FXMLLoader(getClass().getResource(HOMESCREEN));
			root = (Parent) loader.load();
			HomeScreenController controller = loader.getController();
			controller.setStage(primaryStage);
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			Main.screens.clear();
			primaryStage.show();
		} catch (IOException e) {
			logger.error(e);
		}
	}

	@FXML
	private void goToPreviousScreen(ActionEvent event){
		Parent root = Main.screens.pop();
		Scene scene = root.getScene();
		primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	@FXML
	private void addGameScreen(ActionEvent event){
		FXMLLoader loader;
		Parent root;
		try {
			popupStage = new Stage();
			if(this.worldCup.getIsSet()) {
				loader = new FXMLLoader(getClass().getResource(ADD_SET_GAME));
				root = (Parent) loader.load();
				addGameSetsController = loader.getController();
				addGameSetsController.setPreviousController(this);
				addGameSetsController.setAvailablePlayers(registeredPlayers);
				addGameSetsController.setupScreen(worldCup);
				popupStage.setScene(new Scene(root));
//				addGameSetsController.setStage(popupStage);
				popupStage.initModality(Modality.WINDOW_MODAL);
				popupStage.initOwner(
						((Node)event.getSource()).getScene().getWindow() );
				popupStage.show();
			} else {
				loader = new FXMLLoader(getClass().getResource(ADD_POINTS_GAME));
				root = (Parent) loader.load();
				addGameController = loader.getController();
				addGameController.setGamesManager(this);
				addGameController.setAvailablePlayers(registeredPlayers);
				addGameController.setupScreen(worldCup);
				popupStage.setScene(new Scene(root));
//				addGameController.setStage(popupStage);
				popupStage.initModality(Modality.WINDOW_MODAL);
				popupStage.initOwner(
						((Node)event.getSource()).getScene().getWindow() );
				popupStage.show();
			}
		} catch (IOException e) {
			logger.error(e);
		}
	}

	@FXML
	private void removeGame(ActionEvent event){
		if(gamesTV.getSelectionModel().getSelectedItem() != null) {
			decrementPhaseLabel(gamesTV.getSelectionModel().getSelectedItem());
			gamesList.remove(gamesTV.getSelectionModel().getSelectedItem());
			gamesTV.setItems(FXCollections.observableArrayList(gamesList));
			//TODO ha que remover tambem da lista de jogos da respectiva Phase()
			
		}
	}

	public void backFromAddGame(GameExt game) {
		//are any of this players already in another game for same phase?
		if(isPlayersAlreadyInPhase(game)) {
			Alert alert = new Alert(javafx.scene.control.Alert.AlertType.ERROR);
			alert.setTitle("Houston we have a problem!");
			alert.setHeaderText("Repeated Player!");
			alert.setContentText("The same player cannot play two games in the same phase.");
			alert.showAndWait();
			//are all the games for this phase already setted?
		} else if(isPhaseAlreadyFull(game))	{
			Alert alert = new Alert(javafx.scene.control.Alert.AlertType.ERROR);
			alert.setTitle("Houston we have a problem!");
			alert.setHeaderText("Phase already setted up?");
			alert.setContentText("Looks like the phase you selected for this game has already all the games assigned.");
			alert.showAndWait();
		} else {
			popupStage.close();
			gamesList.add(game);
			gamesTV.setItems(FXCollections.observableArrayList(gamesList));
			incrementPhaseLabel(game);
		}
	}

	private Boolean isPhaseAlreadyFull(GameExt game) {
		Boolean isFull = false;
		if(game.getPhaseDescription().compareToIgnoreCase(WorldCupUtils.LAST_32_PHASE) == 0) {
			//last32 has 16 games
			isFull = last32GamesList.size() == 16;
		} else if(game.getPhaseDescription().compareToIgnoreCase(WorldCupUtils.LAST_16_PHASE) == 0) {
			//last 16 has 8 games
			isFull = last16GamesList.size() == 8;
		} else if(game.getPhaseDescription().compareToIgnoreCase(WorldCupUtils.QUARTER_FINALS_PHASE) == 0) {
			isFull = quarterFinalsGamesList.size() == 4;
		} else if(game.getPhaseDescription().compareToIgnoreCase(WorldCupUtils.SEMI_FINALS_PHASE) == 0) {
			isFull = semiFinalsGamesList.size() == 2;
		} else if(game.getPhaseDescription().compareToIgnoreCase(WorldCupUtils.FINAL_PHASE) == 0) {
			isFull = finalGameList.size() == 1;
		} else if(game.getPhaseDescription().compareToIgnoreCase(WorldCupUtils.THIRD_FORTH_PHASE) == 0) {
			isFull = thirdForthGameList.size() == 1;
		}
		return isFull;
	}

	private Boolean isPlayersAlreadyInPhase(GameExt game) {
		Boolean hasFound = false;
		//if he's trying to go on last32, check on last32
		if(game.getPhaseDescription().compareToIgnoreCase(WorldCupUtils.LAST_32_PHASE) == 0) {
			for(GameExt vo:last32GamesList) {
				if(game.getPlayerOneName().compareTo(vo.getPlayerOneName()) == 0 || game.getPlayerOneName().compareTo(vo.getPlayerTwoName()) == 0
						|| game.getPlayerTwoName().compareTo(vo.getPlayerOneName()) == 0 || game.getPlayerTwoName().compareTo(vo.getPlayerTwoName()) == 0) {
					hasFound = true;
				}
			}
		} else if(game.getPhaseDescription().compareToIgnoreCase(WorldCupUtils.LAST_16_PHASE) == 0) {
			for(GameExt vo:last16GamesList) {
				if(game.getPlayerOneName().compareTo(vo.getPlayerOneName()) == 0 || game.getPlayerOneName().compareTo(vo.getPlayerTwoName()) == 0
						|| game.getPlayerTwoName().compareTo(vo.getPlayerOneName()) == 0 || game.getPlayerTwoName().compareTo(vo.getPlayerTwoName()) == 0) {
					hasFound = true;
				}
			}
		} else if(game.getPhaseDescription().compareToIgnoreCase(WorldCupUtils.QUARTER_FINALS_PHASE) == 0) {
			for(GameExt vo:quarterFinalsGamesList) {
				if(game.getPlayerOneName().compareTo(vo.getPlayerOneName()) == 0 || game.getPlayerOneName().compareTo(vo.getPlayerTwoName()) == 0
						|| game.getPlayerTwoName().compareTo(vo.getPlayerOneName()) == 0 || game.getPlayerTwoName().compareTo(vo.getPlayerTwoName()) == 0) {
					hasFound = true;
				}
			}
		} else if(game.getPhaseDescription().compareToIgnoreCase(WorldCupUtils.SEMI_FINALS_PHASE) == 0) {
			for(GameExt vo:semiFinalsGamesList) {
				if(game.getPlayerOneName().compareTo(vo.getPlayerOneName()) == 0 || game.getPlayerOneName().compareTo(vo.getPlayerTwoName()) == 0
						|| game.getPlayerTwoName().compareTo(vo.getPlayerOneName()) == 0 || game.getPlayerTwoName().compareTo(vo.getPlayerTwoName()) == 0) {
					hasFound = true;
				}
			}
		} else if(game.getPhaseDescription().compareToIgnoreCase(WorldCupUtils.FINAL_PHASE) == 0) {
			for(GameExt vo:finalGameList) {
				if(game.getPlayerOneName().compareTo(vo.getPlayerOneName()) == 0 || game.getPlayerOneName().compareTo(vo.getPlayerTwoName()) == 0
						|| game.getPlayerTwoName().compareTo(vo.getPlayerOneName()) == 0 || game.getPlayerTwoName().compareTo(vo.getPlayerTwoName()) == 0) {
					hasFound = true;
				}
			}
		} else if(game.getPhaseDescription().compareToIgnoreCase(WorldCupUtils.THIRD_FORTH_PHASE) == 0) {
			for(GameExt vo:thirdForthGameList) {
				if(game.getPlayerOneName().compareTo(vo.getPlayerOneName()) == 0 || game.getPlayerOneName().compareTo(vo.getPlayerTwoName()) == 0
						|| game.getPlayerTwoName().compareTo(vo.getPlayerOneName()) == 0 || game.getPlayerTwoName().compareTo(vo.getPlayerTwoName()) == 0) {
					hasFound = true;
				}
			}
		}
		return hasFound;
	}

	private void incrementPhaseLabel(GameExt game) {
		if(game.getPhaseDescription().compareToIgnoreCase(WorldCupUtils.LAST_32_PHASE) == 0) {
			last32Count++;
			last32Label.setText(String.valueOf(last32Count));
			last32GamesList.add(game);
		} else if(game.getPhaseDescription().compareToIgnoreCase(WorldCupUtils.LAST_16_PHASE) == 0) {
			last16Count++;
			last16Label.setText(String.valueOf(last16Count));
			last16GamesList.add(game);
		} else if(game.getPhaseDescription().compareToIgnoreCase(WorldCupUtils.QUARTER_FINALS_PHASE) == 0) {
			quarterFinalsCount++;
			quarterFinalsLabel.setText(String.valueOf(quarterFinalsCount));
			quarterFinalsGamesList.add(game);
		} else if(game.getPhaseDescription().compareToIgnoreCase(WorldCupUtils.SEMI_FINALS_PHASE) == 0) {
			semiFinalsCount++;
			semiFinalsLabel.setText(String.valueOf(semiFinalsCount));
			semiFinalsGamesList.add(game);
		} else if(game.getPhaseDescription().compareToIgnoreCase(WorldCupUtils.THIRD_FORTH_PHASE) == 0) {
			thirdForthCount++;
			thirdForthLabel.setText(String.valueOf(thirdForthCount));
			thirdForthGameList.add(game);
		} else {
			finalCount++;
			finalLabel.setText(String.valueOf(finalCount));
			finalGameList.add(game);
		}
	}

	private void decrementPhaseLabel(GameExt game) {
		if(game.getPhaseDescription().compareToIgnoreCase(WorldCupUtils.LAST_32_PHASE) == 0) {
			if(last32Count > 0) {
				last32Count--;
				last32Label.setText(String.valueOf(last32Count));
				last32GamesList.remove(game);
			}
		} else if(game.getPhaseDescription().compareToIgnoreCase(WorldCupUtils.LAST_16_PHASE) == 0) {
			if(last16Count > 0) {
				last16Count--;
				last16Label.setText(String.valueOf(last16Count));
				last16GamesList.remove(game);
			}
		} else if(game.getPhaseDescription().compareToIgnoreCase(WorldCupUtils.QUARTER_FINALS_PHASE) == 0) {
			if(quarterFinalsCount > 0) {
				quarterFinalsCount--;
				quarterFinalsLabel.setText(String.valueOf(quarterFinalsCount));
				quarterFinalsGamesList.remove(game);
			}
		} else if(game.getPhaseDescription().compareToIgnoreCase(WorldCupUtils.SEMI_FINALS_PHASE) == 0) {
			if(semiFinalsCount > 0) {
				semiFinalsCount--;
				semiFinalsLabel.setText(String.valueOf(semiFinalsCount));
				semiFinalsGamesList.remove(game);
			}
		} else if(game.getPhaseDescription().compareToIgnoreCase(WorldCupUtils.THIRD_FORTH_PHASE) == 0) {
			if(thirdForthCount > 0) {
				thirdForthCount--;
				thirdForthLabel.setText(String.valueOf(thirdForthCount));
				thirdForthGameList.remove(game);
			}
		} else {
			if(finalCount > 0) {
				finalCount--;
				finalLabel.setText(String.valueOf(finalCount));
			}
		}
	}

	public void setStage(Stage stage) {
		this.primaryStage = stage;
	}

	public void setWorldCup(WorldCup worldCup) {
		this.worldCup = worldCup;
	}

	/**
	 * @return the registeredPlayers
	 */
	public List<Player> getRegisteredPlayers() {
		return registeredPlayers;
	}

	/**
	 * @param registeredPlayers the registeredPlayers to set
	 */
	public void setRegisteredPlayers(List<Player> registeredPlayers) {
		this.registeredPlayers = registeredPlayers;
	}

}
