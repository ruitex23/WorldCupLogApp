/**
 * 
 */
package controllers.worldCup;

import java.util.List;

import domains.Game;
import domains.GameExt;
import domains.Player;
import domains.WorldCup;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXTextField;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import utils.WorldCupUtils;

/**
 * @author ruitex23
 *
 */
@Controller
public class AddGameController {
	
	
	private String phaseDescription;
	private AddWorldCupGamesController manageGamesController;
//	private Stage stage;
	List<Player> availablePlayers;
	Game game;
	private Float averageP1;
	private Float averageP2;
	private Float gameAverage;
	private Boolean confirmedP1;
	private Boolean confirmedP2;
	private String player1Name;
	private String player2Name;
	
	@FXML private Button addGameBtn;

	//Game
	@FXML private Label gameAverageLabel;
	@FXML private Label winnerLabel;

	//Phase
	private final ToggleGroup phaseGroup = new ToggleGroup();
	@FXML private RadioButton last32RadioButton;
	@FXML private RadioButton last16RadioButton;
	@FXML private RadioButton quarterFinalsRadioButton;
	@FXML private RadioButton semiFinalsRadioButton;
	@FXML private RadioButton thirdForthRadioButton;
	@FXML private RadioButton theFinalRadioButton;

	//player1
	@FXML private ChoiceBox<Player> player1ChoiceBox;
	@FXML private JFXTextField player1PointsTextField;
	@FXML private JFXTextField player1ShootoutTextField;
	@FXML private JFXTextField player1InningsTextField;
	@FXML private JFXTextField player1HighRunTextField;
	@FXML private Label player1AverageLabel;
	@FXML private Label player1TotalPoints;

	//player2
	@FXML private ChoiceBox<Player> player2ChoiceBox;
	@FXML private JFXTextField player2PointsTextField;
	@FXML private JFXTextField player2ShootoutTextField;
	@FXML private JFXTextField player2InningsTextField;
	@FXML private JFXTextField player2HighRunTextField;
	@FXML private Label player2AverageLabel;
	@FXML private Label player2TotalPoints;

	public void setAvailablePlayers(List<Player> list) {
		this.availablePlayers = list;
	}
	
//	public void setStage(Stage stage) {
//		this.stage = stage;
//	}
	
	public void setGamesManager(AddWorldCupGamesController controller) {
		this.manageGamesController = controller;
	}

	public void setupScreen(WorldCup worldCup) {
		addGameBtn.setDisable(true);
		confirmedP1 = confirmedP2 = false;
		game = new Game();
		WorldCupUtils.sortPlayers(availablePlayers);
		this.player1ChoiceBox.setItems(FXCollections.observableArrayList(availablePlayers));
		this.player2ChoiceBox.setItems(FXCollections.observableArrayList(availablePlayers));
		this.player1ChoiceBox.getSelectionModel().selectFirst();
		this.player2ChoiceBox.getSelectionModel().selectFirst();
		last32RadioButton.setToggleGroup(phaseGroup);
		last16RadioButton.setToggleGroup(phaseGroup);
		quarterFinalsRadioButton.setToggleGroup(phaseGroup);
		semiFinalsRadioButton.setToggleGroup(phaseGroup);
		theFinalRadioButton.setToggleGroup(phaseGroup);
		thirdForthRadioButton.setToggleGroup(phaseGroup);
		addRadioButtonListeners();
		setTextFieldsOnlyNumeric();
		BooleanBinding booleanBind = player1ChoiceBox.getSelectionModel().selectedIndexProperty().lessThan(0)
									.or(player2ChoiceBox.getSelectionModel().selectedIndexProperty().lessThan(0))
									.or(player1PointsTextField.textProperty().isEmpty())
									.or(player2PointsTextField.textProperty().isEmpty())
									.or(player1InningsTextField.textProperty().isEmpty())
									.or(player2InningsTextField.textProperty().isEmpty())
									.or(phaseGroup.selectedToggleProperty().isNull());
		addGameBtn.disableProperty().bind(booleanBind);
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
	private void setTextFieldsOnlyNumeric() {

		player1PointsTextField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					player1PointsTextField.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
		player1InningsTextField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					player1InningsTextField.setText(newValue.replaceAll("[^\\d]", ""));
				}
				player2InningsTextField.setText(newValue);
			}
		});
		player1ShootoutTextField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					player1ShootoutTextField.setText(newValue.replaceAll("[^\\d]", ""));
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
		player2PointsTextField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					player2PointsTextField.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
		player2InningsTextField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					player2InningsTextField.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
		player2ShootoutTextField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					player2ShootoutTextField.setText(newValue.replaceAll("[^\\d]", ""));
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
	}

	@FXML
	public void addGame() {
		GameExt gameExt = toGameExt();
		manageGamesController.backFromAddGame(gameExt);
	}

	/**
	 * @return
	 */
	private GameExt toGameExt() {
		GameExt gameExt = new GameExt(phaseDescription, game.toString(), game.getWinner());
		gameExt.setFinalResult(game.getFinalResult());
		gameExt.setGameAverage(game.getGameAverage());
		gameExt.setIsSet(game.getIsSet());
		gameExt.setLocation(game.getLocation());
		gameExt.setWinner(game.getWinner());
		//player 1
		gameExt.setPlayerOneAverage(game.getPlayerOneAverage());
		gameExt.setPlayerOneHighrun(game.getPlayerOneHighrun());
		gameExt.setPlayerOneInnings(game.getPlayerOneInnings());
		gameExt.setPlayerOneName(game.getPlayerOneName());
		gameExt.setPlayerOnePenalties(game.getPlayerOnePenalties());
		gameExt.setPlayerOnePoints(game.getPlayerOnePoints());
		gameExt.setPlayerOneSetFive(game.getPlayerOneSetFive());
		gameExt.setPlayerOneSetFiveInnings(game.getPlayerOneSetFiveInnings());
		gameExt.setPlayerOneSetFour(game.getPlayerOneSetFour());
		gameExt.setPlayerOneSetFourInnings(game.getPlayerOneSetFourInnings());
		gameExt.setPlayerOneSetOne(game.getPlayerOneSetOne());
		gameExt.setPlayerOneSetOneInnings(game.getPlayerOneSetOneInnings());
		gameExt.setPlayerOneSetSeven(game.getPlayerOneSetSeven());
		gameExt.setPlayerOneSetSevenInnings(game.getPlayerOneSetSevenInnings());
		gameExt.setPlayerOneSetSix(game.getPlayerOneSetSix());
		gameExt.setPlayerOneSetSixInnings(game.getPlayerOneSetSixInnings());
		gameExt.setPlayerOneSetsWon(game.getPlayerOneSetsWon());
		gameExt.setPlayerOneSetThree(game.getPlayerOneSetThree());
		gameExt.setPlayerOneSetThreeInnings(game.getPlayerOneSetThreeInnings());
		gameExt.setPlayerOneSetTwo(game.getPlayerOneSetTwo());
		gameExt.setPlayerOneSetTwoInnings(game.getPlayerOneSetTwoInnings());
		//player 2
		gameExt.setPlayerTwoAverage(game.getPlayerTwoAverage());
		gameExt.setPlayerTwoHighrun(game.getPlayerTwoHighrun());
		gameExt.setPlayerTwoInnings(game.getPlayerTwoInnings());
		gameExt.setPlayerTwoName(game.getPlayerTwoName());
		gameExt.setPlayerTwoPenalties(game.getPlayerTwoPenalties());
		gameExt.setPlayerTwoPoints(game.getPlayerTwoPoints());
		gameExt.setPlayerTwoSetFive(game.getPlayerTwoSetFive());
		gameExt.setPlayerTwoSetFiveInnings(game.getPlayerTwoSetFiveInnings());
		gameExt.setPlayerTwoSetFour(game.getPlayerTwoSetFour());
		gameExt.setPlayerTwoSetFourInnings(game.getPlayerTwoSetFourInnings());
		gameExt.setPlayerTwoSetOne(game.getPlayerTwoSetOne());
		gameExt.setPlayerTwoSetOneInnings(game.getPlayerTwoSetOneInnings());
		gameExt.setPlayerTwoSetSeven(game.getPlayerTwoSetSeven());
		gameExt.setPlayerTwoSetSevenInnings(game.getPlayerTwoSetSevenInnings());
		gameExt.setPlayerTwoSetSix(game.getPlayerTwoSetSix());
		gameExt.setPlayerTwoSetSixInnings(game.getPlayerTwoSetSixInnings());
		gameExt.setPlayerTwoSetsWon(game.getPlayerTwoSetsWon());
		gameExt.setPlayerTwoSetThree(game.getPlayerTwoSetThree());
		gameExt.setPlayerTwoSetThreeInnings(game.getPlayerTwoSetThreeInnings());
		gameExt.setPlayerTwoSetTwo(game.getPlayerTwoSetTwo());
		gameExt.setPlayerTwoSetTwoInnings(game.getPlayerTwoSetTwoInnings());
		
		gameExt.setPhaseDescription(phaseDescription);
		return gameExt;
	}
	
	private void updateGameWinner() {
		gameAverage = (averageP1 + averageP2) / 2;
		winnerLabel.setText(game.getTotalPointsPlayer1() > game.getTotalPointsPlayer2() ? 
				player1Name : player2Name);
		game.setWinner(winnerLabel.getText());
		gameAverageLabel.setText(String.format("%.3f", gameAverage));
	}

	@FXML private void confirmPlayer1(ActionEvent event) {
		player1Name = player1ChoiceBox.getValue().toString();
		game.setPlayerOneName(player1Name);
		game.setPlayerOnePoints(Integer.valueOf(player1PointsTextField.getText()));
		game.setPlayerOneInnings(Integer.valueOf(player1InningsTextField.getText()));
		if(StringUtils.isNotBlank(player1ShootoutTextField.getText())) {
			game.setPlayerOnePenalties(Integer.valueOf(player1ShootoutTextField.getText()));
		}
		if(StringUtils.isNotBlank(player1HighRunTextField.getText())) {
			game.setPlayerOneHighrun(Integer.valueOf(player1HighRunTextField.getText()));
		}
		averageP1 = game.getPlayerOnePoints().floatValue() / game.getPlayerOneInnings().floatValue();
		game.setPlayerOneAverage(averageP1);
		player1AverageLabel.setText(String.format("%.3f", averageP1));
		player1TotalPoints.setText(game.getTotalPointsPlayer1().toString());
		confirmedP1 = true;
		disablePlayer1();
		if(confirmedP1 && confirmedP2) {
			updateGameWinner();
//			addGameBtn.setDisable(false);
		}
	}

	@FXML private void confirmPlayer2(ActionEvent event) {
		player2Name = player2ChoiceBox.getValue().toString();
		game.setPlayerTwoName(player2Name);
		game.setPlayerTwoPoints(Integer.valueOf(player2PointsTextField.getText()));
		game.setPlayerTwoInnings(Integer.valueOf(player2InningsTextField.getText()));
		if(StringUtils.isNotBlank(player2ShootoutTextField.getText())) {
			game.setPlayerTwoPenalties(Integer.valueOf(player2ShootoutTextField.getText()));
		}
		if(StringUtils.isNotBlank(player2HighRunTextField.getText())) {
			game.setPlayerTwoHighrun(Integer.valueOf(player2HighRunTextField.getText()));
		}
		averageP2 = game.getPlayerTwoPoints().floatValue() / game.getPlayerTwoInnings().floatValue();
		game.setPlayerTwoAverage(averageP2);
		player2AverageLabel.setText(String.format("%.3f", averageP2));
		player2TotalPoints.setText(game.getTotalPointsPlayer2().toString());
		confirmedP2 = true;
		disablePlayer2();
		if(confirmedP1 && confirmedP2) {
			updateGameWinner();
//			addGameBtn.setDisable(false);
		}
	}

	@FXML 
	private void clearPlayerOne(ActionEvent event) {
		this.player1ChoiceBox.setItems(FXCollections.observableArrayList(availablePlayers));
		this.player1ChoiceBox.getSelectionModel().selectFirst();
		player1AverageLabel.setText("0.00");
		player1HighRunTextField.setText("");
		player1InningsTextField.setText("");
		player1PointsTextField.setText("");
		player1ShootoutTextField.setText("");
		player1TotalPoints.setText("");
		averageP1 = Float.valueOf(0);
		winnerLabel.setText("");
		gameAverageLabel.setText("");
		gameAverage = Float.valueOf(0);
		enablePlayer1();
//		addGameBtn.setDisable(true);
		confirmedP1 = false;
	}

	@FXML
	private void clearPlayerTwo(ActionEvent event) {
		this.player2ChoiceBox.setItems(FXCollections.observableArrayList(availablePlayers));
		this.player2ChoiceBox.getSelectionModel().selectFirst();
		player2AverageLabel.setText("0.00");
		player2HighRunTextField.setText("");
		player2InningsTextField.setText("");
		player2PointsTextField.setText("");
		player2ShootoutTextField.setText("");
		player2TotalPoints.setText("");
		averageP2 = Float.valueOf(0);
		winnerLabel.setText("");
		gameAverageLabel.setText("");
		gameAverage = Float.valueOf(0);
		enablePlayer2();
//		addGameBtn.setDisable(true);
		confirmedP2 = false;
	}
	
	
	private void enablePlayer1() {
		player1ChoiceBox.setDisable(false);
		player1HighRunTextField.setDisable(false);
		player1InningsTextField.setDisable(false);
		player1PointsTextField.setDisable(false);
		player1ShootoutTextField.setDisable(false);
	}
	
	private void disablePlayer1() {
		player1ChoiceBox.setDisable(true);
		player1HighRunTextField.setDisable(true);
		player1InningsTextField.setDisable(true);
		player1PointsTextField.setDisable(true);
		player1ShootoutTextField.setDisable(true);
	}
	
	
	private void enablePlayer2() {
		player2ChoiceBox.setDisable(false);
		player2HighRunTextField.setDisable(false);
		player2InningsTextField.setDisable(false);
		player2PointsTextField.setDisable(false);
		player2ShootoutTextField.setDisable(false);
	}
	
	private void disablePlayer2() {
		player2ChoiceBox.setDisable(true);
		player2HighRunTextField.setDisable(true);
		player2InningsTextField.setDisable(true);
		player2PointsTextField.setDisable(true);
		player2ShootoutTextField.setDisable(true);
	}

	@FXML private void updatePlayer2Innings(ActionEvent event) {
		if(StringUtils.isBlank(player2InningsTextField.getText()) || 
				player2InningsTextField.getText().compareToIgnoreCase(player1InningsTextField.getText()) != 0) {
			player2InningsTextField.setText(player1InningsTextField.getText());
		}
	}
	
	@FXML private void updatePlayer1Innings(ActionEvent event) {
		if(StringUtils.isBlank(player1InningsTextField.getText()) || 
				player1InningsTextField.getText().compareToIgnoreCase(player2InningsTextField.getText()) != 0) {
			player1InningsTextField.setText(player2InningsTextField.getText());
		}
	}
}
