/**
 * 
 */
package main.java.controllers.worldCup;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import main.java.domains.Game;
import main.java.domains.GameExt;
import main.java.domains.Player;
import main.java.domains.WorldCup;
import main.java.utils.WorldCupUtils;

/**
 * @author ruitex23
 *
 */
@Controller
public class AddGameSetsController {

	//Game
	@FXML private Label gameAverageLabel;
	@FXML private Label winnerLabel;
	@FXML private Button addGameBtn;
	@FXML private JFXToggleButton automaticToggle;

	//Phase
	private final ToggleGroup phaseGroup = new ToggleGroup();
	@FXML private RadioButton last32RadioButton;
	@FXML private RadioButton last16RadioButton;
	@FXML private RadioButton quarterFinalsRadioButton;
	@FXML private RadioButton semiFinalsRadioButton;
	@FXML private RadioButton thirdForthRadioButton;
	@FXML private RadioButton theFinalRadioButton;

	//Player1
	@FXML private ChoiceBox<Player> player1ChoiceBox;
	@FXML private ChoiceBox<String> player1Set1ChoiceBox;
	@FXML private JFXTextField player1Set1InningsTextField;
	@FXML private ChoiceBox<String> player1Set2ChoiceBox;
	@FXML private JFXTextField player1Set2InningsTextField;
	@FXML private ChoiceBox<String> player1Set3ChoiceBox;
	@FXML private JFXTextField player1Set3InningsTextField;
	@FXML private ChoiceBox<String> player1Set4ChoiceBox;
	@FXML private JFXTextField player1Set4InningsTextField;
	@FXML private ChoiceBox<String> player1Set5ChoiceBox;
	@FXML private JFXTextField player1Set5InningsTextField;
	@FXML private ChoiceBox<String> player1Set6ChoiceBox;
	@FXML private JFXTextField player1Set6InningsTextField;
	@FXML private ChoiceBox<String> player1Set7ChoiceBox;
	@FXML private JFXTextField player1Set7InningsTextField;
	@FXML private Label player1AverageLabel;
	@FXML private JFXTextField player1HighRunTextField;
	@FXML private JFXTextField player1TotalPointsTextField;
	@FXML private JFXTextField player1TotalInningsTextField;
	@FXML private JFXTextField player1SetsWon;
	@FXML private Button confirmP1Btn;

	//player2
	@FXML private ChoiceBox<Player> player2ChoiceBox;
	@FXML private ChoiceBox<String> player2Set1ChoiceBox;
	@FXML private JFXTextField player2Set1InningsTextField;
	@FXML private ChoiceBox<String> player2Set2ChoiceBox;
	@FXML private JFXTextField player2Set2InningsTextField;
	@FXML private ChoiceBox<String> player2Set3ChoiceBox;
	@FXML private JFXTextField player2Set3InningsTextField;
	@FXML private ChoiceBox<String> player2Set4ChoiceBox;
	@FXML private JFXTextField player2Set4InningsTextField;
	@FXML private ChoiceBox<String> player2Set5ChoiceBox;
	@FXML private JFXTextField player2Set5InningsTextField;
	@FXML private ChoiceBox<String> player2Set6ChoiceBox;
	@FXML private JFXTextField player2Set6InningsTextField;
	@FXML private ChoiceBox<String> player2Set7ChoiceBox;
	@FXML private JFXTextField player2Set7InningsTextField;
	@FXML private Label player2AverageLabel;
	@FXML private JFXTextField player2HighRunTextField;
	@FXML private JFXTextField player2TotalPointsTextField;
	@FXML private JFXTextField player2TotalInningsTextField;
	@FXML private JFXTextField player2SetsWon;
	@FXML private Button confirmP2Btn;

	private List<Player> availablePlayers;
	private ObservableList<String> pointsList = FXCollections.observableArrayList("-", "0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15");
	private ObservableList<String> pointsMinusList = FXCollections.observableArrayList("0","1","2","3","4","5","6","7","8","9","10","11","12","13","14");
	private String phaseDescription;
	private Game game;
	private Float averageP1;
	private Float averageP2;
	private Float gameAverage;
	private Boolean confirmedP1;
	private Boolean confirmedP2;
	private AddWorldCupGamesController previousController;
	
	private Integer totalSetsPlayed = 0;
	
	public void setAvailablePlayers(List<Player> list) {
		this.availablePlayers = list;
	}

	public void setPreviousController(AddWorldCupGamesController controller) {
		this.previousController = controller;
	}

	private void setAutomaticMode() {
		player1Set1ChoiceBox.setDisable(true);
		player1Set2ChoiceBox.setDisable(true);
		player1Set3ChoiceBox.setDisable(true);
		player1Set4ChoiceBox.setDisable(true);
		player1Set5ChoiceBox.setDisable(true);
		player1Set6ChoiceBox.setDisable(true);
		player1Set7ChoiceBox.setDisable(true);
		player1TotalPointsTextField.setDisable(true);
		player1TotalInningsTextField.setDisable(true);
		confirmP1Btn.setDisable(true);

		player2Set1ChoiceBox.setDisable(true);
		player2Set2ChoiceBox.setDisable(true);
		player2Set3ChoiceBox.setDisable(true);
		player2Set4ChoiceBox.setDisable(true);
		player2Set5ChoiceBox.setDisable(true);
		player2Set6ChoiceBox.setDisable(true);
		player2Set7ChoiceBox.setDisable(true);
		player2TotalPointsTextField.setDisable(true);
		player2TotalInningsTextField.setDisable(true);
		confirmP2Btn.setDisable(true);
	}

	private void setManualMode() {
		player1Set1ChoiceBox.setDisable(false);
		player1Set2ChoiceBox.setDisable(false);
		player1Set3ChoiceBox.setDisable(false);
		player1Set4ChoiceBox.setDisable(false);
		player1Set5ChoiceBox.setDisable(false);
		player1Set6ChoiceBox.setDisable(false);
		player1Set7ChoiceBox.setDisable(false);
		player1TotalPointsTextField.setDisable(false);
		player1TotalInningsTextField.setDisable(false);

		player2Set1ChoiceBox.setDisable(false);
		player2Set2ChoiceBox.setDisable(false);
		player2Set3ChoiceBox.setDisable(false);
		player2Set4ChoiceBox.setDisable(false);
		player2Set5ChoiceBox.setDisable(false);
		player2Set6ChoiceBox.setDisable(false);
		player2Set7ChoiceBox.setDisable(false);
		player2TotalPointsTextField.setDisable(false);
		player2TotalInningsTextField.setDisable(false);
	}

	public void setupScreen(WorldCup worldCup) {
		game = new Game();
		addGameBtn.setDisable(true);
		setupToggle();
		setupAutomaticFeatureListeners();
		phaseDescription = WorldCupUtils.LAST_32_PHASE;
		confirmedP1 = confirmedP2 = false;
		WorldCupUtils.sortPlayers(availablePlayers);
		this.player1ChoiceBox.setItems(FXCollections.observableArrayList(availablePlayers));
		this.player2ChoiceBox.setItems(FXCollections.observableArrayList(availablePlayers));
		this.player1ChoiceBox.getSelectionModel().selectFirst();
		this.player2ChoiceBox.getSelectionModel().selectFirst();
		last32RadioButton.setToggleGroup(phaseGroup);
		last32RadioButton.setSelected(true);
		last16RadioButton.setToggleGroup(phaseGroup);
		quarterFinalsRadioButton.setToggleGroup(phaseGroup);
		semiFinalsRadioButton.setToggleGroup(phaseGroup);
		theFinalRadioButton.setToggleGroup(phaseGroup);
		thirdForthRadioButton.setToggleGroup(phaseGroup);
		addRadioButtonListeners();
		setTextFieldsOnlyNumeric();
		setPointsChoiceBoxes();
	}

	/**
	 * 
	 */
	private void setupToggle() {
		automaticToggle.setSelected(true);
		automaticToggle.selectedProperty().addListener((p, o, n) -> {
			if(automaticToggle.isSelected()) {
				setAutomaticMode();
			} else {
				setManualMode();
			}
		});
		setAutomaticMode();
	}

	private void setPointsChoiceBoxes() {
		//player1
		this.player1Set1ChoiceBox.setItems(pointsList);
		this.player1Set1ChoiceBox.getSelectionModel().selectFirst();
		this.player1Set2ChoiceBox.setItems(pointsList);
		this.player1Set2ChoiceBox.getSelectionModel().selectFirst();
		this.player1Set3ChoiceBox.setItems(pointsList);
		this.player1Set3ChoiceBox.getSelectionModel().selectFirst();
		this.player1Set4ChoiceBox.setItems(pointsList);
		this.player1Set4ChoiceBox.getSelectionModel().selectFirst();
		this.player1Set5ChoiceBox.setItems(pointsList);
		this.player1Set5ChoiceBox.getSelectionModel().selectFirst();
		this.player1Set6ChoiceBox.setItems(pointsList);
		this.player1Set6ChoiceBox.getSelectionModel().selectFirst();
		this.player1Set7ChoiceBox.setItems(pointsList);
		this.player1Set7ChoiceBox.getSelectionModel().selectFirst();
		//player2
		this.player2Set1ChoiceBox.setItems(pointsList);
		this.player2Set1ChoiceBox.getSelectionModel().selectFirst();
		this.player2Set2ChoiceBox.setItems(pointsList);
		this.player2Set2ChoiceBox.getSelectionModel().selectFirst();
		this.player2Set3ChoiceBox.setItems(pointsList);
		this.player2Set3ChoiceBox.getSelectionModel().selectFirst();
		this.player2Set4ChoiceBox.setItems(pointsList);
		this.player2Set4ChoiceBox.getSelectionModel().selectFirst();
		this.player2Set5ChoiceBox.setItems(pointsList);
		this.player2Set5ChoiceBox.getSelectionModel().selectFirst();
		this.player2Set6ChoiceBox.setItems(pointsList);
		this.player2Set6ChoiceBox.getSelectionModel().selectFirst();
		this.player2Set7ChoiceBox.setItems(pointsList);
		this.player2Set7ChoiceBox.getSelectionModel().selectFirst();
	}

	@FXML private void confirmP1(ActionEvent event) {
		game.setPlayerOneName(this.player1ChoiceBox.getValue().toString());
		game.setPlayerOnePoints(Integer.valueOf(player1TotalPointsTextField.getText()));
		game.setPlayerOneInnings(Integer.valueOf(player1TotalInningsTextField.getText()));
		averageP1 = game.getPlayerOnePoints().floatValue() / game.getPlayerOneInnings();
		game.setPlayerOneAverage(averageP1);
		player1AverageLabel.setText(String.format("%.3f", averageP1));
		confirmedP1 = true;
		disablePlayer1();
		if(confirmedP1 && confirmedP2) {
			updateGameStats();
			addGameBtn.setDisable(false);
		}
	}

	@FXML private void confirmPlayer2(ActionEvent event) {
		game.setPlayerTwoName(this.player2ChoiceBox.getValue().toString());
		game.setPlayerTwoPoints(Integer.valueOf(player2TotalPointsTextField.getText()));
		game.setPlayerTwoInnings(Integer.valueOf(player2TotalInningsTextField.getText()));
		averageP2 = game.getPlayerTwoPoints().floatValue() / game.getPlayerTwoInnings();
		game.setPlayerTwoAverage(averageP2);
		player2AverageLabel.setText(String.format("%.3f", averageP2));
		confirmedP2 = true;
		disablePlayer2();
		if(confirmedP1 && confirmedP2) {
			updateGameStats();
			updateGameWinner();
			addGameBtn.setDisable(false);
		}
	}
	
	@FXML 
	private void clearPlayerOne(ActionEvent event) {
		this.player1ChoiceBox.setItems(FXCollections.observableArrayList(availablePlayers));
		this.player1ChoiceBox.getSelectionModel().selectFirst();
		player1SetsWon.setText("");
		player1AverageLabel.setText("0.00");
		player1HighRunTextField.setText("");
		player1TotalPointsTextField.setText("");
		player1TotalInningsTextField.setText("");
		averageP1 = Float.valueOf(0);
		winnerLabel.setText("");
		gameAverageLabel.setText("");
		gameAverage = Float.valueOf(0);
		enablePlayer1();
		addGameBtn.setDisable(true);
	}

	@FXML
	private void clearPlayerTwo(ActionEvent event) {
		this.player2ChoiceBox.setItems(FXCollections.observableArrayList(availablePlayers));
		this.player2ChoiceBox.getSelectionModel().selectFirst();
		player2SetsWon.setText("");
		player2AverageLabel.setText("0.00");
		player2HighRunTextField.setText("");
		player2TotalInningsTextField.setText("");
		player2TotalPointsTextField.setText("");
		averageP2 = Float.valueOf(0);
		winnerLabel.setText("");
		gameAverageLabel.setText("");
		gameAverage = Float.valueOf(0);
		enablePlayer2();
		addGameBtn.setDisable(true);
	}
	
	
	private void enablePlayer1() {
		player1ChoiceBox.setDisable(false);
		player1Set1ChoiceBox.setDisable(false);
		player1Set2ChoiceBox.setDisable(false);
		player1Set3ChoiceBox.setDisable(false);
		player1Set4ChoiceBox.setDisable(false);
		player1Set5ChoiceBox.setDisable(false);
		player1Set6ChoiceBox.setDisable(false);
		player1Set7ChoiceBox.setDisable(false);
		player1HighRunTextField.setDisable(false);
		player1TotalInningsTextField.setDisable(false);
		player1TotalPointsTextField.setDisable(false);
	}
	
	private void disablePlayer1() {
		player1ChoiceBox.setDisable(true);
		player1Set1ChoiceBox.setDisable(true);
		player1Set2ChoiceBox.setDisable(true);
		player1Set3ChoiceBox.setDisable(true);
		player1Set4ChoiceBox.setDisable(true);
		player1Set5ChoiceBox.setDisable(true);
		player1Set6ChoiceBox.setDisable(true);
		player1Set7ChoiceBox.setDisable(true);
		player1HighRunTextField.setDisable(true);
		player1TotalInningsTextField.setDisable(true);
		player1TotalPointsTextField.setDisable(true);
	}
	
	
	private void enablePlayer2() {
		player2ChoiceBox.setDisable(false);
		player2Set1ChoiceBox.setDisable(false);
		player2Set2ChoiceBox.setDisable(false);
		player2Set3ChoiceBox.setDisable(false);
		player2Set4ChoiceBox.setDisable(false);
		player2Set5ChoiceBox.setDisable(false);
		player2Set6ChoiceBox.setDisable(false);
		player2Set7ChoiceBox.setDisable(false);
		player2HighRunTextField.setDisable(false);
		player2TotalInningsTextField.setDisable(false);
		player2TotalPointsTextField.setDisable(false);
	}
	
	private void disablePlayer2() {
		player2ChoiceBox.setDisable(true);
		player2Set1ChoiceBox.setDisable(true);
		player2Set2ChoiceBox.setDisable(true);
		player2Set3ChoiceBox.setDisable(true);
		player2Set4ChoiceBox.setDisable(true);
		player2Set5ChoiceBox.setDisable(true);
		player2Set6ChoiceBox.setDisable(true);
		player2Set7ChoiceBox.setDisable(true);
		player2HighRunTextField.setDisable(true);
		player2TotalInningsTextField.setDisable(true);
		player2TotalPointsTextField.setDisable(true);
	}
	
	private void updateGameStats() {
		gameAverage = (averageP1 + averageP2) / 2;
		gameAverageLabel.setText(String.format("%.3f", gameAverage));
	}
	
	private void updateGameWinner() {
		Integer p1Pts = Integer.valueOf(player1SetsWon.getText());
		Integer p2Pts = Integer.valueOf(player2SetsWon.getText());
		//P1 vence 3-0
		if(p1Pts.compareTo(3) == 0 && p2Pts.compareTo(0) == 0 || p1Pts.compareTo(3) == 0 && p2Pts.compareTo(1) == 0 ||
				p1Pts.compareTo(3) == 0 && p2Pts.compareTo(2) == 0 || p1Pts.compareTo(4) == 0 && p2Pts.compareTo(0) == 0 ||
				p1Pts.compareTo(4) == 0 && p2Pts.compareTo(1) == 0 || p1Pts.compareTo(4) == 0 && p2Pts.compareTo(2) == 0 ||
				p1Pts.compareTo(4) == 0 && p2Pts.compareTo(3) == 0) {
			winnerLabel.setText(player1ChoiceBox.getSelectionModel().getSelectedItem().toString());
		} else if(p1Pts.compareTo(0) == 0 && p2Pts.compareTo(3) == 0 || p1Pts.compareTo(1) == 0 && p2Pts.compareTo(3) == 0 ||
				p1Pts.compareTo(2) == 0 && p2Pts.compareTo(3) == 0 || p1Pts.compareTo(0) == 0 && p2Pts.compareTo(4) == 0 ||
				p1Pts.compareTo(1) == 0 && p2Pts.compareTo(4) == 0 || p1Pts.compareTo(2) == 0 && p2Pts.compareTo(4) == 0 ||
				p1Pts.compareTo(3) == 0 && p2Pts.compareTo(4) == 0) {
			winnerLabel.setText(player2ChoiceBox.getSelectionModel().getSelectedItem().toString());
		} 
		game.setWinner(winnerLabel.getText());
	}
	
	@FXML
	public void addGame() {
		GameExt gameExt = toGameExt();
		previousController.backFromAddGame(gameExt);
	}

	/**
	 * @return
	 */
	private GameExt toGameExt() {
		GameExt gameExt = new GameExt(phaseDescription, game.toString(), game.getWinner());
		totalSetsPlayed = Integer.sum(Integer.valueOf(player1SetsWon.getText()), Integer.valueOf(player2SetsWon.getText()));
		gameExt.setFinalResult(game.getFinalResult());
		gameExt.setGameAverage(game.getGameAverage());
		gameExt.setIsSet(game.getIsSet());
		gameExt.setLocation(game.getLocation());
		gameExt.setWinner(game.getWinner());
		
		switch (totalSetsPlayed) {
			
		case 3: //case 3-0
			gameExt.setPlayerOneSetOne(Integer.valueOf(player1Set1ChoiceBox.getSelectionModel().getSelectedItem()));
			gameExt.setPlayerOneSetTwo(Integer.valueOf(player1Set2ChoiceBox.getSelectionModel().getSelectedItem()));
			gameExt.setPlayerOneSetThree(Integer.valueOf(player1Set3ChoiceBox.getSelectionModel().getSelectedItem()));
			
			gameExt.setPlayerTwoSetOne(Integer.valueOf(player2Set1ChoiceBox.getSelectionModel().getSelectedItem()));
			gameExt.setPlayerTwoSetTwo(Integer.valueOf(player2Set1ChoiceBox.getSelectionModel().getSelectedItem()));
			gameExt.setPlayerTwoSetThree(Integer.valueOf(player2Set1ChoiceBox.getSelectionModel().getSelectedItem()));
			break;
			
		case 4: 	//case 3-1
			gameExt.setPlayerOneSetOne(Integer.valueOf(player1Set1ChoiceBox.getSelectionModel().getSelectedItem()));
			gameExt.setPlayerOneSetTwo(Integer.valueOf(player1Set2ChoiceBox.getSelectionModel().getSelectedItem()));
			gameExt.setPlayerOneSetThree(Integer.valueOf(player1Set3ChoiceBox.getSelectionModel().getSelectedItem()));
			gameExt.setPlayerOneSetFour(Integer.valueOf(player1Set4ChoiceBox.getSelectionModel().getSelectedItem()));
			
			gameExt.setPlayerTwoSetOne(Integer.valueOf(player2Set1ChoiceBox.getSelectionModel().getSelectedItem()));
			gameExt.setPlayerTwoSetTwo(Integer.valueOf(player2Set1ChoiceBox.getSelectionModel().getSelectedItem()));
			gameExt.setPlayerTwoSetThree(Integer.valueOf(player2Set1ChoiceBox.getSelectionModel().getSelectedItem()));
			gameExt.setPlayerTwoSetFour(Integer.valueOf(player2Set1ChoiceBox.getSelectionModel().getSelectedItem()));
			
			break;
		case 5: 	//case 3-2
			gameExt.setPlayerOneSetOne(Integer.valueOf(player1Set1ChoiceBox.getSelectionModel().getSelectedItem()));
			gameExt.setPlayerOneSetTwo(Integer.valueOf(player1Set2ChoiceBox.getSelectionModel().getSelectedItem()));
			gameExt.setPlayerOneSetThree(Integer.valueOf(player1Set3ChoiceBox.getSelectionModel().getSelectedItem()));
			gameExt.setPlayerOneSetFour(Integer.valueOf(player1Set4ChoiceBox.getSelectionModel().getSelectedItem()));
			gameExt.setPlayerOneSetFive(Integer.valueOf(player1Set5ChoiceBox.getSelectionModel().getSelectedItem()));
	
			
			gameExt.setPlayerTwoSetOne(Integer.valueOf(player2Set1ChoiceBox.getSelectionModel().getSelectedItem()));
			gameExt.setPlayerTwoSetTwo(Integer.valueOf(player2Set1ChoiceBox.getSelectionModel().getSelectedItem()));
			gameExt.setPlayerTwoSetThree(Integer.valueOf(player2Set1ChoiceBox.getSelectionModel().getSelectedItem()));
			gameExt.setPlayerTwoSetFour(Integer.valueOf(player2Set1ChoiceBox.getSelectionModel().getSelectedItem()));
			gameExt.setPlayerTwoSetFive(Integer.valueOf(player2Set1ChoiceBox.getSelectionModel().getSelectedItem()));
			
			break;
		case 6: 	//case 4-2
			gameExt.setPlayerOneSetOne(Integer.valueOf(player1Set1ChoiceBox.getSelectionModel().getSelectedItem()));
			gameExt.setPlayerOneSetTwo(Integer.valueOf(player1Set2ChoiceBox.getSelectionModel().getSelectedItem()));
			gameExt.setPlayerOneSetThree(Integer.valueOf(player1Set3ChoiceBox.getSelectionModel().getSelectedItem()));
			gameExt.setPlayerOneSetFour(Integer.valueOf(player1Set4ChoiceBox.getSelectionModel().getSelectedItem()));
			gameExt.setPlayerOneSetFive(Integer.valueOf(player1Set5ChoiceBox.getSelectionModel().getSelectedItem()));
			gameExt.setPlayerOneSetSix(Integer.valueOf(player1Set6ChoiceBox.getSelectionModel().getSelectedItem()));
			
			gameExt.setPlayerTwoSetOne(Integer.valueOf(player2Set1ChoiceBox.getSelectionModel().getSelectedItem()));
			gameExt.setPlayerTwoSetTwo(Integer.valueOf(player2Set1ChoiceBox.getSelectionModel().getSelectedItem()));
			gameExt.setPlayerTwoSetThree(Integer.valueOf(player2Set1ChoiceBox.getSelectionModel().getSelectedItem()));
			gameExt.setPlayerTwoSetFour(Integer.valueOf(player2Set1ChoiceBox.getSelectionModel().getSelectedItem()));
			gameExt.setPlayerTwoSetFive(Integer.valueOf(player2Set1ChoiceBox.getSelectionModel().getSelectedItem()));
			gameExt.setPlayerTwoSetSix(Integer.valueOf(player2Set1ChoiceBox.getSelectionModel().getSelectedItem()));
			break;
		case 7: 	//case 4-3
			gameExt.setPlayerOneSetOne(Integer.valueOf(player1Set1ChoiceBox.getSelectionModel().getSelectedItem()));
			gameExt.setPlayerOneSetTwo(Integer.valueOf(player1Set2ChoiceBox.getSelectionModel().getSelectedItem()));
			gameExt.setPlayerOneSetThree(Integer.valueOf(player1Set3ChoiceBox.getSelectionModel().getSelectedItem()));
			gameExt.setPlayerOneSetFour(Integer.valueOf(player1Set4ChoiceBox.getSelectionModel().getSelectedItem()));
			gameExt.setPlayerOneSetFive(Integer.valueOf(player1Set5ChoiceBox.getSelectionModel().getSelectedItem()));
			gameExt.setPlayerOneSetSix(Integer.valueOf(player1Set6ChoiceBox.getSelectionModel().getSelectedItem()));
			gameExt.setPlayerOneSetSeven(Integer.valueOf(player1Set7ChoiceBox.getSelectionModel().getSelectedItem()));
			
			gameExt.setPlayerTwoSetOne(Integer.valueOf(player2Set1ChoiceBox.getSelectionModel().getSelectedItem()));
			gameExt.setPlayerTwoSetTwo(Integer.valueOf(player2Set1ChoiceBox.getSelectionModel().getSelectedItem()));
			gameExt.setPlayerTwoSetThree(Integer.valueOf(player2Set1ChoiceBox.getSelectionModel().getSelectedItem()));
			gameExt.setPlayerTwoSetFour(Integer.valueOf(player2Set1ChoiceBox.getSelectionModel().getSelectedItem()));
			gameExt.setPlayerTwoSetFive(Integer.valueOf(player2Set1ChoiceBox.getSelectionModel().getSelectedItem()));
			gameExt.setPlayerTwoSetSix(Integer.valueOf(player2Set1ChoiceBox.getSelectionModel().getSelectedItem()));
			gameExt.setPlayerTwoSetSeven(Integer.valueOf(player2Set1ChoiceBox.getSelectionModel().getSelectedItem()));
			
			break;
		}
		//player 1
		gameExt.setPlayerOneAverage(game.getPlayerOneAverage());
		gameExt.setPlayerOneHighrun(game.getPlayerOneHighrun());
		gameExt.setPlayerOneInnings(game.getPlayerOneInnings());
		gameExt.setPlayerOneName(this.player1ChoiceBox.getValue().toString());
		gameExt.setPlayerOnePoints(game.getPlayerOnePoints());
		gameExt.setPlayerOneSetsWon(Integer.valueOf(player1SetsWon.getText()));
		//player 2
		gameExt.setPlayerTwoAverage(game.getPlayerTwoAverage());
		gameExt.setPlayerTwoHighrun(game.getPlayerTwoHighrun());
		gameExt.setPlayerTwoInnings(game.getPlayerTwoInnings());
		gameExt.setPlayerTwoName(this.player2ChoiceBox.getValue().toString());
		gameExt.setPlayerTwoPoints(Integer.valueOf(player2Set1ChoiceBox.getSelectionModel().getSelectedItem()));
		gameExt.setPlayerTwoSetsWon(Integer.valueOf(player2SetsWon.getText()));
		
		gameExt.setPhaseDescription(phaseDescription);
		return gameExt;
	}
	
	/**
	 * 
	 */
	private void setTextFieldsOnlyNumeric() {
		player1SetsWon.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					player1SetsWon.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
		player2SetsWon.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					player2SetsWon.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
		player1HighRunTextField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					player1HighRunTextField.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
		player2HighRunTextField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					player2HighRunTextField.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
		player1TotalPointsTextField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					player1TotalPointsTextField.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
		player2TotalPointsTextField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					player2TotalPointsTextField.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
		player1TotalInningsTextField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					player1TotalInningsTextField.setText(newValue.replaceAll("[^\\d]", ""));
				}
				if(StringUtils.isNotBlank(player1TotalPointsTextField.getText()) && automaticToggle.isSelected()) {
					confirmP1Btn.setDisable(false);
				}
			}
		});
		player2TotalInningsTextField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					player2TotalInningsTextField.setText(newValue.replaceAll("[^\\d]", ""));
				}
				if(StringUtils.isNotBlank(player2TotalPointsTextField.getText()) && automaticToggle.isSelected()) {
					confirmP2Btn.setDisable(false);
				}
			}
		});
	}

	/**
	 * 
	 */
	private void addRadioButtonListeners() {
		last32RadioButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
				if (isNowSelected) { 
					phaseDescription = WorldCupUtils.LAST_32_PHASE;
					System.out.println("Entrei!!");
				} else {
					phaseDescription = "";
				}
			}
		});
		last16RadioButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
				if (isNowSelected) { 
					phaseDescription = WorldCupUtils.LAST_16_PHASE;
					System.out.println("Entrei!!");
				} else {
					phaseDescription = "";
				}
			}
		});
		quarterFinalsRadioButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
				if (isNowSelected) { 
					phaseDescription = WorldCupUtils.QUARTER_FINALS_PHASE;
					System.out.println("Entrei!!");
				} else {
					phaseDescription = "";
				}
			}
		});
		semiFinalsRadioButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
				if (isNowSelected) { 
					phaseDescription = WorldCupUtils.SEMI_FINALS_PHASE;
					System.out.println("Entrei!!");
				} else {
					phaseDescription = "";
				}
			}
		});
		thirdForthRadioButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
				if (isNowSelected) { 
					phaseDescription = WorldCupUtils.THIRD_FORTH_PHASE;
					System.out.println("Entrei!!");
				} else {
					phaseDescription = "";
				}
			}
		});
		theFinalRadioButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
				if (isNowSelected) { 
					phaseDescription = WorldCupUtils.FINAL_PHASE;
					System.out.println("Entrei!!");
				} else {
					phaseDescription = "";
				}
			}
		});
	}

	/**
	 * 
	 */
	private void setupAutomaticFeatureListeners() {
		//depois de inserir o numero de sets ganhos pelo jogador 2, 
		//o automatismo atribui os pontos e "desbloqueia" o número correcto de sets que foram disputados
		player2SetsWon.textProperty().addListener((observable, oldValue, newValue) -> {
			if(automaticToggle.isSelected()) {
				if(StringUtils.isNotBlank(player1SetsWon.getText()) && StringUtils.isNotBlank(player2SetsWon.getText())) {
					Integer p1Pts = Integer.valueOf(player1SetsWon.getText());
					Integer p2Pts = Integer.valueOf(player2SetsWon.getText());
					//P1 vence 3-0
					if(p1Pts.compareTo(3) == 0 && p2Pts.compareTo(0) == 0) {
						player1Set1ChoiceBox.getSelectionModel().select(16);      
						player1Set2ChoiceBox.getSelectionModel().select(16);
						player1Set3ChoiceBox.getSelectionModel().select(16);
						player2Set1ChoiceBox.setDisable(false);
						player2Set2ChoiceBox.setDisable(false);
						player2Set3ChoiceBox.setDisable(false);
						player2Set1ChoiceBox.setItems(pointsMinusList);
						player2Set1ChoiceBox.getSelectionModel().select(1);
						player2Set2ChoiceBox.setItems(pointsMinusList);
						player2Set2ChoiceBox.getSelectionModel().select(1);
						player2Set3ChoiceBox.setItems(pointsMinusList);
						player2Set3ChoiceBox.getSelectionModel().select(1);
						player1TotalInningsTextField.setDisable(false);
						player2TotalInningsTextField.setDisable(false);
						player1TotalPointsTextField.setText("45");
						player2TotalPointsTextField.setDisable(false);
						totalSetsPlayed = 3;
					}

					//P2 vence 3-0
					else if(p1Pts.compareTo(0) == 0 && p2Pts.compareTo(3) == 0) {
						player2Set1ChoiceBox.getSelectionModel().select(16);
						player2Set2ChoiceBox.getSelectionModel().select(16);
						player2Set3ChoiceBox.getSelectionModel().select(16);
						player1Set1ChoiceBox.setDisable(false);
						player1Set2ChoiceBox.setDisable(false);
						player1Set3ChoiceBox.setDisable(false);
						player1Set1ChoiceBox.setItems(pointsMinusList);
						player1Set1ChoiceBox.getSelectionModel().select(1);
						player1Set2ChoiceBox.setItems(pointsMinusList);
						player1Set2ChoiceBox.getSelectionModel().select(1);
						player1Set3ChoiceBox.setItems(pointsMinusList);
						player1Set3ChoiceBox.getSelectionModel().select(1);
						player2TotalInningsTextField.setDisable(false);
						player1TotalInningsTextField.setDisable(false);
						player1TotalInningsTextField.setText("");
						player2TotalPointsTextField.setText("45");
						player1TotalPointsTextField.setDisable(false);
						totalSetsPlayed = 3;
					} 
					
					//P1 vence 3-1 ou P2 vence 3-1
					else if((p1Pts.compareTo(3) == 0 && p2Pts.compareTo(1) == 0) || 
							(p1Pts.compareTo(1) == 0 && p2Pts.compareTo(3) == 0)) {
						player1Set1ChoiceBox.setDisable(false);
						player1Set2ChoiceBox.setDisable(false);
						player1Set3ChoiceBox.setDisable(false);
						player1Set4ChoiceBox.setDisable(false);
						player2Set1ChoiceBox.setDisable(false);
						player2Set2ChoiceBox.setDisable(false);
						player2Set3ChoiceBox.setDisable(false);
						player2Set4ChoiceBox.setDisable(false);
						player1Set1ChoiceBox.getSelectionModel().select(1);
						player1Set2ChoiceBox.getSelectionModel().select(1);
						player1Set3ChoiceBox.getSelectionModel().select(1);
						player1Set4ChoiceBox.getSelectionModel().select(1);
						player2Set1ChoiceBox.getSelectionModel().select(1);
						player2Set2ChoiceBox.getSelectionModel().select(1);
						player2Set3ChoiceBox.getSelectionModel().select(1);
						player2Set4ChoiceBox.getSelectionModel().select(1);
						player1TotalPointsTextField.setDisable(false);
						player1TotalInningsTextField.setDisable(false);
						player2TotalInningsTextField.setDisable(false);
						player2TotalPointsTextField.setDisable(false);
						totalSetsPlayed = 4;
					} 
					
					//P1 vence 3-2 ou P2 vence 3-2
					else if((p1Pts.compareTo(3) == 0 && p2Pts.compareTo(2) == 0) || 
							(p1Pts.compareTo(2) == 0 && p2Pts.compareTo(3) == 0)) {
						player1Set1ChoiceBox.setDisable(false);
						player1Set2ChoiceBox.setDisable(false);
						player1Set3ChoiceBox.setDisable(false);
						player1Set4ChoiceBox.setDisable(false);
						player1Set5ChoiceBox.setDisable(false);
						player2Set1ChoiceBox.setDisable(false);
						player2Set2ChoiceBox.setDisable(false);
						player2Set3ChoiceBox.setDisable(false);
						player2Set4ChoiceBox.setDisable(false);
						player2Set5ChoiceBox.setDisable(false);
						player1Set1ChoiceBox.getSelectionModel().select(1);
						player1Set2ChoiceBox.getSelectionModel().select(1);
						player1Set3ChoiceBox.getSelectionModel().select(1);
						player1Set4ChoiceBox.getSelectionModel().select(1);
						player1Set5ChoiceBox.getSelectionModel().select(1);
						player2Set1ChoiceBox.getSelectionModel().select(1);
						player2Set2ChoiceBox.getSelectionModel().select(1);
						player2Set3ChoiceBox.getSelectionModel().select(1);
						player2Set4ChoiceBox.getSelectionModel().select(1);
						player2Set5ChoiceBox.getSelectionModel().select(1);
						player1TotalPointsTextField.setDisable(false);
						player1TotalInningsTextField.setDisable(false);
						player2TotalInningsTextField.setDisable(false);
						player2TotalPointsTextField.setDisable(false);
						totalSetsPlayed = 5;
					} 
				} else {
					setAutomaticMode();
					setPointsChoiceBoxes();
					player1TotalPointsTextField.setText("");
					player2TotalPointsTextField.setText("");
				}
			} 
		});

		//para o caso em que o jogador 1 vence 3-1 e o jogador 2 vence o primeiro set
		player1Set1ChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new
				ChangeListener<Number>() {
			public void changed(ObservableValue ov, Number value, Number new_value) {
				if(automaticToggle.isSelected()) {
					if(StringUtils.isNumeric(new_value.toString()) && new_value.intValue() > 0 
							&& StringUtils.isNumeric(player1Set1ChoiceBox.getItems().get(new_value.intValue()))) {
						Integer p1Pts = Integer.valueOf(player1Set1ChoiceBox.getItems().get(new_value.intValue()));
						Integer p1SetsResult = Integer.valueOf(player1SetsWon.getText());
						Integer p2SetsResult = Integer.valueOf(player2SetsWon.getText());
						//P1 vence 3-1
						if(p1SetsResult.compareTo(3) == 0 && p2SetsResult.compareTo(1) == 0) {
							//e P2 vence Set1
							System.out.println(p1Pts.compareTo(15));
							System.out.println(p1Pts);
							if(p1Pts.compareTo(15) < 0 && p1Pts.compareTo(0) != 0) {
//								player1Set1ChoiceBox.setDisable(true);
								player2Set1ChoiceBox.getSelectionModel().select(16);
//								player2Set1ChoiceBox.setDisable(true);
							}
						}
					} else {
						player1TotalPointsTextField.setText("");
					}
				}
			}
		});
		
		//P2 vence 3-0, logo pelo automatismo, este sera o ultimo set preenchido para o jogador 1, 
		//logo depois deste pode acertar as contas dos pontos do jogador 1
		player1Set3ChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new
				ChangeListener<Number>() {
			public void changed(ObservableValue ov, Number value, Number new_value) {
				if(automaticToggle.isSelected()) {
					if(StringUtils.isNumeric(new_value.toString()) && new_value.intValue() > 0 
							&& StringUtils.isNumeric(player1Set3ChoiceBox.getItems().get(new_value.intValue()))) {
						Integer p1SetsResult = Integer.valueOf(player1SetsWon.getText());
						Integer p2SetsResult = Integer.valueOf(player2SetsWon.getText());
						if(p1SetsResult.compareTo(0) == 0 && p2SetsResult.compareTo(3) == 0) {
							//verifica se os 3 sets estao com valores numericos
							boolean aux = StringUtils.isNumeric(player1Set1ChoiceBox.getSelectionModel().getSelectedItem()) && 
									StringUtils.isNumeric(player1Set2ChoiceBox.getSelectionModel().getSelectedItem());
							if(aux) {
								int totalPointsSet1 = Integer.valueOf(player1Set1ChoiceBox.getSelectionModel().getSelectedItem());
								int totalPointsSet2 = Integer.valueOf(player1Set2ChoiceBox.getSelectionModel().getSelectedItem());
								int totalPoints = totalPointsSet1 + totalPointsSet2 + Integer.valueOf(player1Set3ChoiceBox.getItems().get(new_value.intValue()));
								player1TotalPointsTextField.setText(String.valueOf(totalPoints));
							}
							
						}
					} else {
						player1TotalPointsTextField.setText("");
					}
				}
			}
		});
		
		player1Set4ChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new
				ChangeListener<Number>() {
			public void changed(ObservableValue ov, Number value, Number new_value) {
				if(automaticToggle.isSelected()) {
					if(StringUtils.isNumeric(new_value.toString()) && new_value.intValue() > 0 
							&& StringUtils.isNumeric(player1Set4ChoiceBox.getItems().get(new_value.intValue()))) {
						Integer p1SetsResult = Integer.valueOf(player1SetsWon.getText());
						Integer p2SetsResult = Integer.valueOf(player2SetsWon.getText());
						//P2 vence 3-1 ou 4-0, logo pelo automatismo, este sera o ultimo set preenchido para o jogador 1, 
						//logo depois deste pode acertar as contas dos pontos do jogador 1
						if(p1SetsResult.compareTo(1) == 0 && p2SetsResult.compareTo(3) == 0 || p1SetsResult.compareTo(0) == 0 && p2SetsResult.compareTo(4) == 0) {
							//verifica se os 4 sets estao com valores numericos
							boolean aux = StringUtils.isNumeric(player1Set1ChoiceBox.getSelectionModel().getSelectedItem()) && 
									StringUtils.isNumeric(player1Set2ChoiceBox.getSelectionModel().getSelectedItem()) &&
									StringUtils.isNumeric(player1Set3ChoiceBox.getSelectionModel().getSelectedItem());
							if(aux) {
								int totalPointsSet1 = Integer.valueOf(player1Set1ChoiceBox.getSelectionModel().getSelectedItem());
								int totalPointsSet2 = Integer.valueOf(player1Set2ChoiceBox.getSelectionModel().getSelectedItem());
								int totalPointsSet3 = Integer.valueOf(player1Set3ChoiceBox.getSelectionModel().getSelectedItem());
								int totalPoints = totalPointsSet1 + totalPointsSet2 + totalPointsSet3 + Integer.valueOf(player1Set4ChoiceBox.getItems().get(new_value.intValue()));
								player1TotalPointsTextField.setText(String.valueOf(totalPoints));
							}
						} 
					} else {
						player1TotalPointsTextField.setText("");
					}
				}
			}
		});
		
		player1Set5ChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new
				ChangeListener<Number>() {
			public void changed(ObservableValue ov, Number value, Number new_value) {
				if(automaticToggle.isSelected()) {
					if(StringUtils.isNumeric(new_value.toString()) && new_value.intValue() > 0 
							&& StringUtils.isNumeric(player1Set5ChoiceBox.getItems().get(new_value.intValue()))) {
						Integer p1SetsResult = Integer.valueOf(player1SetsWon.getText());
						Integer p2SetsResult = Integer.valueOf(player2SetsWon.getText());
						//P2 vence 3-2, logo pelo automatismo, este sera o ultimo set preenchido para o jogador 1, 
						//logo depois deste pode acertar as contas dos pontos do jogador 1
						if(p1SetsResult.compareTo(2) == 0 && p2SetsResult.compareTo(3) == 0 || p1SetsResult.compareTo(1) == 0 && p2SetsResult.compareTo(4) == 0) {
							//verifica se os 5 sets estao com valores numericos
							boolean aux = StringUtils.isNumeric(player1Set1ChoiceBox.getSelectionModel().getSelectedItem()) && 
									StringUtils.isNumeric(player1Set2ChoiceBox.getSelectionModel().getSelectedItem()) &&
									StringUtils.isNumeric(player1Set3ChoiceBox.getSelectionModel().getSelectedItem()) && 
									StringUtils.isNumeric(player1Set4ChoiceBox.getSelectionModel().getSelectedItem());
							if(aux) {
								int totalPointsSet1 = Integer.valueOf(player1Set1ChoiceBox.getSelectionModel().getSelectedItem());
								int totalPointsSet2 = Integer.valueOf(player1Set2ChoiceBox.getSelectionModel().getSelectedItem());
								int totalPointsSet3 = Integer.valueOf(player1Set3ChoiceBox.getSelectionModel().getSelectedItem());
								int totalPointsSet4 = Integer.valueOf(player1Set4ChoiceBox.getSelectionModel().getSelectedItem());
								int totalPoints = totalPointsSet1 + totalPointsSet2 + totalPointsSet3 + totalPointsSet4 + Integer.valueOf(player1Set5ChoiceBox.getItems().get(new_value.intValue()));
								player1TotalPointsTextField.setText(String.valueOf(totalPoints));
							}
						}
					} else {
						player1TotalPointsTextField.setText("");
					}
				}
			}
		});
		
		player1Set6ChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new
				ChangeListener<Number>() {
			public void changed(ObservableValue ov, Number value, Number new_value) {
				if(automaticToggle.isSelected()) {
					if(StringUtils.isNumeric(new_value.toString()) && new_value.intValue() > 0 
							&& StringUtils.isNumeric(player1Set6ChoiceBox.getItems().get(new_value.intValue()))) {
						Integer p1SetsResult = Integer.valueOf(player1SetsWon.getText());
						Integer p2SetsResult = Integer.valueOf(player2SetsWon.getText());
						//P2 vence 3-2, logo pelo automatismo, este sera o ultimo set preenchido para o jogador 1, 
						//logo depois deste pode acertar as contas dos pontos do jogador 1
						if(p1SetsResult.compareTo(2) == 0 && p2SetsResult.compareTo(4) == 0) {
							//verifica se os 5 sets estao com valores numericos
							boolean aux = StringUtils.isNumeric(player1Set1ChoiceBox.getSelectionModel().getSelectedItem()) && 
									StringUtils.isNumeric(player1Set2ChoiceBox.getSelectionModel().getSelectedItem()) &&
									StringUtils.isNumeric(player1Set3ChoiceBox.getSelectionModel().getSelectedItem()) && 
									StringUtils.isNumeric(player1Set4ChoiceBox.getSelectionModel().getSelectedItem()) && 
									StringUtils.isNumeric(player1Set5ChoiceBox.getSelectionModel().getSelectedItem());
							if(aux) {
								int totalPointsSet1 = Integer.valueOf(player1Set1ChoiceBox.getSelectionModel().getSelectedItem());
								int totalPointsSet2 = Integer.valueOf(player1Set2ChoiceBox.getSelectionModel().getSelectedItem());
								int totalPointsSet3 = Integer.valueOf(player1Set3ChoiceBox.getSelectionModel().getSelectedItem());
								int totalPointsSet4 = Integer.valueOf(player1Set4ChoiceBox.getSelectionModel().getSelectedItem());
								int totalPointsSet5 = Integer.valueOf(player1Set5ChoiceBox.getSelectionModel().getSelectedItem());
								int totalPoints = totalPointsSet1 + totalPointsSet2 + totalPointsSet3 + totalPointsSet4 + 
										totalPointsSet5 + Integer.valueOf(player1Set6ChoiceBox.getItems().get(new_value.intValue()));
								player1TotalPointsTextField.setText(String.valueOf(totalPoints));
							}
						}
					} else {
						player1TotalPointsTextField.setText("");
					}
				}
			}
		});
		
		player1Set7ChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new
				ChangeListener<Number>() {
			public void changed(ObservableValue ov, Number value, Number new_value) {
				if(automaticToggle.isSelected()) {
					if(StringUtils.isNumeric(new_value.toString()) && new_value.intValue() > 0 
							&& StringUtils.isNumeric(player1Set7ChoiceBox.getItems().get(new_value.intValue()))) {
						Integer p1SetsResult = Integer.valueOf(player1SetsWon.getText());
						Integer p2SetsResult = Integer.valueOf(player2SetsWon.getText());
						//P2 vence 3-2, logo pelo automatismo, este sera o ultimo set preenchido para o jogador 1, 
						//logo depois deste pode acertar as contas dos pontos do jogador 1
						if(p1SetsResult.compareTo(3) == 0 && p2SetsResult.compareTo(4) == 0) {
							//verifica se os 5 sets estao com valores numericos
							boolean aux = StringUtils.isNumeric(player1Set1ChoiceBox.getSelectionModel().getSelectedItem()) && 
									StringUtils.isNumeric(player1Set2ChoiceBox.getSelectionModel().getSelectedItem()) &&
									StringUtils.isNumeric(player1Set3ChoiceBox.getSelectionModel().getSelectedItem()) && 
									StringUtils.isNumeric(player1Set4ChoiceBox.getSelectionModel().getSelectedItem()) && 
									StringUtils.isNumeric(player1Set5ChoiceBox.getSelectionModel().getSelectedItem()) && 
									StringUtils.isNumeric(player1Set6ChoiceBox.getSelectionModel().getSelectedItem());
							if(aux) {
								int totalPointsSet1 = Integer.valueOf(player1Set1ChoiceBox.getSelectionModel().getSelectedItem());
								int totalPointsSet2 = Integer.valueOf(player1Set2ChoiceBox.getSelectionModel().getSelectedItem());
								int totalPointsSet3 = Integer.valueOf(player1Set3ChoiceBox.getSelectionModel().getSelectedItem());
								int totalPointsSet4 = Integer.valueOf(player1Set4ChoiceBox.getSelectionModel().getSelectedItem());
								int totalPointsSet5 = Integer.valueOf(player1Set5ChoiceBox.getSelectionModel().getSelectedItem());
								int totalPointsSet6 = Integer.valueOf(player1Set6ChoiceBox.getSelectionModel().getSelectedItem());
								int totalPoints = totalPointsSet1 + totalPointsSet2 + totalPointsSet3 + totalPointsSet4 + 
										totalPointsSet5 + totalPointsSet6 + Integer.valueOf(player1Set7ChoiceBox.getItems().get(new_value.intValue()));
								player1TotalPointsTextField.setText(String.valueOf(totalPoints));
							}
						}
					} else {
						player1TotalPointsTextField.setText("");
					}
				}
			}
		});
		
		player2Set1ChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new
				ChangeListener<Number>() {
			public void changed(ObservableValue ov, Number value, Number new_value) {
				if(automaticToggle.isSelected()) {
					//casos de 3-1
					if(StringUtils.isNumeric(player1Set1ChoiceBox.getSelectionModel().getSelectedItem()) 
							&& StringUtils.isNumeric(new_value.toString()) 
							&& new_value.intValue() > 0 
							&&	StringUtils.isNumeric(player2Set1ChoiceBox.getItems().get(new_value.intValue()))) {
						
//						Integer p1Pts = Integer.valueOf(player1Set1ChoiceBox.getSelectionModel().getSelectedIndex()-1);
						Integer p1Pts = Integer.valueOf(player1Set1ChoiceBox.getSelectionModel().getSelectedItem());
						Integer p2Pts = Integer.valueOf(player2Set1ChoiceBox.getItems().get(new_value.intValue()));
						Integer p1SetsResult = Integer.valueOf(player1SetsWon.getText());
						Integer p2SetsResult = Integer.valueOf(player2SetsWon.getText());

						//P1 vence 3-1
						if(p1SetsResult.compareTo(3) == 0 && p2SetsResult.compareTo(1) == 0) {
							//e P2 vence Set1
							if(p1Pts.compareTo(p2Pts) < 0) {
								player1Set2ChoiceBox.getSelectionModel().select(16);
								player1Set3ChoiceBox.getSelectionModel().select(16);
								player1Set4ChoiceBox.getSelectionModel().select(16);
								player2Set2ChoiceBox.setItems(pointsMinusList);
								player2Set2ChoiceBox.getSelectionModel().select(0);
								player2Set3ChoiceBox.setItems(pointsMinusList);
								player2Set3ChoiceBox.getSelectionModel().select(0);
								player2Set4ChoiceBox.setItems(pointsMinusList);
								player2Set4ChoiceBox.getSelectionModel().select(0);
								player1TotalPointsTextField.setText(String.valueOf(p1Pts + 45));
							} else {
								player1Set2ChoiceBox.getSelectionModel().select(1);
								player1Set3ChoiceBox.getSelectionModel().select(1);
								player1Set4ChoiceBox.getSelectionModel().select(1);
								player2Set2ChoiceBox.setItems(pointsList);
								player2Set2ChoiceBox.getSelectionModel().select(1);
								player2Set3ChoiceBox.setItems(pointsList);
								player2Set3ChoiceBox.getSelectionModel().select(1);
								player2Set4ChoiceBox.setItems(pointsList);
								player2Set4ChoiceBox.getSelectionModel().select(1);
								player1TotalPointsTextField.setText("");
							}
						} else if(p1SetsResult.compareTo(1) == 0 && p2SetsResult.compareTo(3) == 0) {
							//e P1 vence Set1
							if(p1Pts.compareTo(p2Pts) > 0) {
								player2Set2ChoiceBox.getSelectionModel().select(16);
								player2Set3ChoiceBox.getSelectionModel().select(16);
								player2Set4ChoiceBox.getSelectionModel().select(16);
								player1Set2ChoiceBox.setItems(pointsMinusList);
								player1Set2ChoiceBox.getSelectionModel().select(0);
								player1Set3ChoiceBox.setItems(pointsMinusList);
								player1Set3ChoiceBox.getSelectionModel().select(0);
								player1Set4ChoiceBox.setItems(pointsMinusList);
								player1Set4ChoiceBox.getSelectionModel().select(0);
								player2TotalPointsTextField.setText(String.valueOf(p2Pts + 45));
							} else {
								player2Set2ChoiceBox.getSelectionModel().select(1);
								player2Set3ChoiceBox.getSelectionModel().select(1);
								player2Set4ChoiceBox.getSelectionModel().select(1);
								player1Set2ChoiceBox.setItems(pointsList);
								player1Set2ChoiceBox.getSelectionModel().select(1);
								player1Set3ChoiceBox.setItems(pointsList);
								player1Set3ChoiceBox.getSelectionModel().select(1);
								player1Set4ChoiceBox.setItems(pointsList);
								player1Set4ChoiceBox.getSelectionModel().select(1);
								player2TotalPointsTextField.setText("");
							}
						}
					}
				}
			}
		});
		
		player2Set2ChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new
				ChangeListener<Number>() {
			public void changed(ObservableValue ov, Number value, Number new_value) {
				if(automaticToggle.isSelected()) {
					System.out.println("new value:" + new_value.intValue());
					if(StringUtils.isNumeric(player1Set2ChoiceBox.getSelectionModel().getSelectedItem()) 
							&& StringUtils.isNumeric(new_value.toString())
							&& new_value.intValue() > 0
							&& StringUtils.isNumeric(player2Set2ChoiceBox.getItems().get(new_value.intValue()))) {
						
						Integer p1Set1Pts = Integer.valueOf(player1Set1ChoiceBox.getSelectionModel().getSelectedItem());
						Integer p1Set2Pts = Integer.valueOf(player1Set2ChoiceBox.getSelectionModel().getSelectedItem());
						Integer p2Set1Pts = Integer.valueOf(player2Set1ChoiceBox.getSelectionModel().getSelectedItem());
						Integer p2Set2Pts = Integer.valueOf(player2Set2ChoiceBox.getItems().get(new_value.intValue()));
						Integer p1SetsResult = Integer.valueOf(player1SetsWon.getText());
						Integer p2SetsResult = Integer.valueOf(player2SetsWon.getText());

						//P1 vence 3-2
						if(p1SetsResult.compareTo(3) == 0 && p2SetsResult.compareTo(2) == 0) {
							//e P2 vence os 2 primeiros sets
							if((p1Set1Pts.compareTo(p2Set1Pts) < 0) && (p1Set2Pts.compareTo(p2Set2Pts) < 0)) {
								player1Set3ChoiceBox.getSelectionModel().select(16);
								player1Set4ChoiceBox.getSelectionModel().select(16);
								player1Set5ChoiceBox.getSelectionModel().select(16);
								player2Set3ChoiceBox.setItems(pointsMinusList);
								player2Set3ChoiceBox.getSelectionModel().select(0);
								player2Set4ChoiceBox.setItems(pointsMinusList);
								player2Set4ChoiceBox.getSelectionModel().select(0);
								player2Set5ChoiceBox.setItems(pointsMinusList);
								player2Set5ChoiceBox.getSelectionModel().select(0);
								player1TotalPointsTextField.setText(String.valueOf(p1Set1Pts + p1Set2Pts + 45));
								player1TotalPointsTextField.setDisable(true);
							} else {
								player1Set3ChoiceBox.getSelectionModel().select(1);
								player1Set4ChoiceBox.getSelectionModel().select(1);
								player1Set5ChoiceBox.getSelectionModel().select(1);
								player2Set3ChoiceBox.setItems(pointsList);
								player2Set3ChoiceBox.getSelectionModel().select(1);
								player2Set4ChoiceBox.setItems(pointsList);
								player2Set4ChoiceBox.getSelectionModel().select(1);
								player2Set5ChoiceBox.setItems(pointsList);
								player2Set5ChoiceBox.getSelectionModel().select(1);
								player1TotalPointsTextField.setText("");
							}
						} else if(p1SetsResult.compareTo(2) == 0 && p2SetsResult.compareTo(3) == 0) {
							//e P1 vence os 2 primeiros sets
							if((p1Set1Pts.compareTo(p2Set1Pts) > 0) && (p1Set2Pts.compareTo(p2Set2Pts) > 0)) {
								player2Set3ChoiceBox.getSelectionModel().select(16);
								player2Set4ChoiceBox.getSelectionModel().select(16);
								player2Set5ChoiceBox.getSelectionModel().select(16);
								player1Set3ChoiceBox.setItems(pointsMinusList);
								player1Set3ChoiceBox.getSelectionModel().select(0);
								player1Set4ChoiceBox.setItems(pointsMinusList);
								player1Set4ChoiceBox.getSelectionModel().select(0);
								player1Set5ChoiceBox.setItems(pointsMinusList);
								player1Set5ChoiceBox.getSelectionModel().select(0);
								player2TotalPointsTextField.setText(String.valueOf(p2Set1Pts + p2Set2Pts + 45));
							} else {
								player2Set3ChoiceBox.getSelectionModel().select(1);
								player2Set4ChoiceBox.getSelectionModel().select(1);
								player2Set5ChoiceBox.getSelectionModel().select(1);
								player1Set3ChoiceBox.setItems(pointsList);
								player1Set3ChoiceBox.getSelectionModel().select(1);
								player1Set4ChoiceBox.setItems(pointsList);
								player1Set4ChoiceBox.getSelectionModel().select(1);
								player1Set5ChoiceBox.setItems(pointsList);
								player1Set5ChoiceBox.getSelectionModel().select(1);
								player2TotalPointsTextField.setText("");
							}
						}
					}
				}
			}
		});
		
		player2Set3ChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new
				ChangeListener<Number>() {
			public void changed(ObservableValue ov, Number value, Number new_value) {
				if(automaticToggle.isSelected()) {
					if(StringUtils.isNumeric(new_value.toString()) 
							&& new_value.intValue() > 0 
							&&	StringUtils.isNumeric(player2Set3ChoiceBox.getItems().get(new_value.intValue()))) {
						Integer p1SetsResult = Integer.valueOf(player1SetsWon.getText());
						Integer p2SetsResult = Integer.valueOf(player2SetsWon.getText());
						//P1 vence 3-0, logo pelo automatismo, este sera o ultimo set preenchido para o jogador 2, 
						//logo depois deste pode acertar as contas dos pontos do jogador 2
						if(p1SetsResult.compareTo(3) == 0 && p2SetsResult.compareTo(0) == 0) {
							//verifica se os 3 sets estao com valores numericos
							boolean aux = StringUtils.isNumeric(player2Set1ChoiceBox.getSelectionModel().getSelectedItem()) && 
									StringUtils.isNumeric(player2Set2ChoiceBox.getSelectionModel().getSelectedItem());
							if(aux) {
								int totalPointsSet1 = Integer.valueOf(player2Set1ChoiceBox.getSelectionModel().getSelectedItem());
								int totalPointsSet2 = Integer.valueOf(player2Set2ChoiceBox.getSelectionModel().getSelectedItem());
								int totalPoints = totalPointsSet1 + totalPointsSet2 + Integer.valueOf(player2Set3ChoiceBox.getItems().get(new_value.intValue()));
								player2TotalPointsTextField.setText(String.valueOf(totalPoints));
							}
						}
					} else {
						player2TotalPointsTextField.setText("");
					}
				}
			}
		});
		
		player2Set4ChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new
				ChangeListener<Number>() {
			public void changed(ObservableValue ov, Number value, Number new_value) {
				if(automaticToggle.isSelected()) {
					if(StringUtils.isNumeric(new_value.toString()) 
							&& new_value.intValue() > 0 
							&&	StringUtils.isNumeric(player2Set4ChoiceBox.getItems().get(new_value.intValue()))) {
						Integer p1SetsResult = Integer.valueOf(player1SetsWon.getText());
						Integer p2SetsResult = Integer.valueOf(player2SetsWon.getText());
						//P1 vence 3-1 ou 4-0, logo pelo automatismo, este sera o ultimo set preenchido para o jogador 2, 
						//logo depois deste pode acertar as contas dos pontos do jogador 2
						if(p1SetsResult.compareTo(3) == 0 && p2SetsResult.compareTo(1) == 0 || p1SetsResult.compareTo(4) == 0 && p2SetsResult.compareTo(0) == 0) {
							//verifica se os 4 sets estao com valores numericos
							boolean aux = StringUtils.isNumeric(player2Set1ChoiceBox.getSelectionModel().getSelectedItem()) && 
									StringUtils.isNumeric(player2Set2ChoiceBox.getSelectionModel().getSelectedItem()) &&
									StringUtils.isNumeric(player2Set3ChoiceBox.getSelectionModel().getSelectedItem());
							if(aux) {
								int totalPointsSet1 = Integer.valueOf(player2Set1ChoiceBox.getSelectionModel().getSelectedItem());
								int totalPointsSet2 = Integer.valueOf(player2Set2ChoiceBox.getSelectionModel().getSelectedItem());
								int totalPointsSet3 = Integer.valueOf(player2Set3ChoiceBox.getSelectionModel().getSelectedItem());
								int totalPoints = totalPointsSet1 + totalPointsSet2 + totalPointsSet3 + Integer.valueOf(player2Set4ChoiceBox.getItems().get(new_value.intValue()));
								player2TotalPointsTextField.setText(String.valueOf(totalPoints));
							}
						} 
					} else {
						player2TotalPointsTextField.setText("");
					}
				}
			}
		});
		
		player2Set5ChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new
				ChangeListener<Number>() {
			public void changed(ObservableValue ov, Number value, Number new_value) {
				if(automaticToggle.isSelected()) {
					if(StringUtils.isNumeric(new_value.toString()) 
							&& new_value.intValue() > 0 
							&&	StringUtils.isNumeric(player2Set5ChoiceBox.getItems().get(new_value.intValue()))) {
						Integer p1SetsResult = Integer.valueOf(player1SetsWon.getText());
						Integer p2SetsResult = Integer.valueOf(player2SetsWon.getText());
						//P1 vence 3-2 ou 4-1, logo pelo automatismo, este sera o ultimo set preenchido para o jogador 2, 
						//logo depois deste pode acertar as contas dos pontos do jogador 2
						if(p1SetsResult.compareTo(3) == 0 && p2SetsResult.compareTo(2) == 0 || p1SetsResult.compareTo(4) == 0 && p2SetsResult.compareTo(1) == 0) {
							//verifica se os 5 sets estao com valores numericos
							boolean aux = StringUtils.isNumeric(player2Set1ChoiceBox.getSelectionModel().getSelectedItem()) && 
									StringUtils.isNumeric(player2Set2ChoiceBox.getSelectionModel().getSelectedItem()) &&
									StringUtils.isNumeric(player2Set3ChoiceBox.getSelectionModel().getSelectedItem()) && 
									StringUtils.isNumeric(player2Set4ChoiceBox.getSelectionModel().getSelectedItem());
							if(aux) {
								int totalPointsSet1 = Integer.valueOf(player2Set1ChoiceBox.getSelectionModel().getSelectedItem());
								int totalPointsSet2 = Integer.valueOf(player2Set2ChoiceBox.getSelectionModel().getSelectedItem());
								int totalPointsSet3 = Integer.valueOf(player2Set3ChoiceBox.getSelectionModel().getSelectedItem());
								int totalPointsSet4 = Integer.valueOf(player2Set4ChoiceBox.getSelectionModel().getSelectedItem());
								int totalPoints = totalPointsSet1 + totalPointsSet2 + totalPointsSet3 + totalPointsSet4 + Integer.valueOf(player2Set5ChoiceBox.getItems().get(new_value.intValue()));
								player2TotalPointsTextField.setText(String.valueOf(totalPoints));
							}
						}
					} else {
						player2TotalPointsTextField.setText("");
					}
				}
			}
		});
		
		player2Set6ChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new
				ChangeListener<Number>() {
			public void changed(ObservableValue ov, Number value, Number new_value) {
				if(automaticToggle.isSelected()) {
					if(StringUtils.isNumeric(new_value.toString()) 
							&& new_value.intValue() > 0 
							&&	StringUtils.isNumeric(player2Set6ChoiceBox.getSelectionModel().getSelectedItem())) {
						Integer p1SetsResult = Integer.valueOf(player1SetsWon.getText());
						Integer p2SetsResult = Integer.valueOf(player2SetsWon.getText());
						//P1 vence 4-2, logo pelo automatismo, este sera o ultimo set preenchido para o jogador 2, 
						//logo depois deste pode acertar as contas dos pontos do jogador 2
						if(p1SetsResult.compareTo(4) == 0 && p2SetsResult.compareTo(2) == 0) {
							//verifica se os 5 sets estao com valores numericos
							boolean aux = StringUtils.isNumeric(player2Set1ChoiceBox.getSelectionModel().getSelectedItem()) && 
									StringUtils.isNumeric(player2Set2ChoiceBox.getSelectionModel().getSelectedItem()) &&
									StringUtils.isNumeric(player2Set3ChoiceBox.getSelectionModel().getSelectedItem()) && 
									StringUtils.isNumeric(player2Set4ChoiceBox.getSelectionModel().getSelectedItem()) && 
									StringUtils.isNumeric(player2Set5ChoiceBox.getSelectionModel().getSelectedItem());
							if(aux) {
								int totalPointsSet1 = Integer.valueOf(player2Set1ChoiceBox.getSelectionModel().getSelectedItem());
								int totalPointsSet2 = Integer.valueOf(player2Set2ChoiceBox.getSelectionModel().getSelectedItem());
								int totalPointsSet3 = Integer.valueOf(player2Set3ChoiceBox.getSelectionModel().getSelectedItem());
								int totalPointsSet4 = Integer.valueOf(player2Set4ChoiceBox.getSelectionModel().getSelectedItem());
								int totalPointsSet5 = Integer.valueOf(player2Set5ChoiceBox.getSelectionModel().getSelectedItem());
								int totalPoints = totalPointsSet1 + totalPointsSet2 + totalPointsSet3 + totalPointsSet4 + 
										totalPointsSet5 + Integer.valueOf(player2Set6ChoiceBox.getItems().get(new_value.intValue()));
								player2TotalPointsTextField.setText(String.valueOf(totalPoints));
							}
						}
					} else {
						player2TotalPointsTextField.setText("");
					}
				}
			}
		});
		
		player2Set7ChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new
				ChangeListener<Number>() {
			public void changed(ObservableValue ov, Number value, Number new_value) {
				if(automaticToggle.isSelected()) {
					if(StringUtils.isNumeric(new_value.toString()) 
							&& new_value.intValue() > 0 
							&&	StringUtils.isNumeric(player2Set7ChoiceBox.getSelectionModel().getSelectedItem())) {
						Integer p1SetsResult = Integer.valueOf(player1SetsWon.getText());
						Integer p2SetsResult = Integer.valueOf(player2SetsWon.getText());
						//P1 vence 4-3, logo pelo automatismo, este sera o ultimo set preenchido para o jogador 2, 
						//logo depois deste pode acertar as contas dos pontos do jogador 2
						if(p1SetsResult.compareTo(4) == 0 && p2SetsResult.compareTo(3) == 0) {
							//verifica se os 5 sets estao com valores numericos
							boolean aux = StringUtils.isNumeric(player2Set1ChoiceBox.getSelectionModel().getSelectedItem()) && 
									StringUtils.isNumeric(player2Set2ChoiceBox.getSelectionModel().getSelectedItem()) &&
									StringUtils.isNumeric(player2Set3ChoiceBox.getSelectionModel().getSelectedItem()) && 
									StringUtils.isNumeric(player2Set4ChoiceBox.getSelectionModel().getSelectedItem()) && 
									StringUtils.isNumeric(player2Set5ChoiceBox.getSelectionModel().getSelectedItem()) && 
									StringUtils.isNumeric(player2Set6ChoiceBox.getSelectionModel().getSelectedItem());
							if(aux) {
								int totalPointsSet1 = Integer.valueOf(player2Set1ChoiceBox.getSelectionModel().getSelectedItem());
								int totalPointsSet2 = Integer.valueOf(player2Set2ChoiceBox.getSelectionModel().getSelectedItem());
								int totalPointsSet3 = Integer.valueOf(player2Set3ChoiceBox.getSelectionModel().getSelectedItem());
								int totalPointsSet4 = Integer.valueOf(player2Set4ChoiceBox.getSelectionModel().getSelectedItem());
								int totalPointsSet5 = Integer.valueOf(player2Set5ChoiceBox.getSelectionModel().getSelectedItem());
								int totalPointsSet6 = Integer.valueOf(player2Set6ChoiceBox.getSelectionModel().getSelectedItem());
								int totalPoints = totalPointsSet1 + totalPointsSet2 + totalPointsSet3 + totalPointsSet4 + 
										totalPointsSet5 + totalPointsSet6 + Integer.valueOf(player2Set3ChoiceBox.getItems().get(new_value.intValue()));
								player2TotalPointsTextField.setText(String.valueOf(totalPoints));
							}
						}
					} else {
						player2TotalPointsTextField.setText("");
					}
				}
			}
		});
	}
}
