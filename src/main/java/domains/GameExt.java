/**
 * 
 */
package domains;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author ruitex23
 *
 */
public class GameExt extends Game {
	
	private final SimpleStringProperty phaseDescription;
	private final SimpleStringProperty gameDescription;
	private final SimpleStringProperty gameWinner;
	
	public GameExt(String phaseDescription, String gameDescription, String gameWinner) {
		this.phaseDescription = new SimpleStringProperty(phaseDescription);
		this.gameDescription = new SimpleStringProperty(gameDescription);
		this.gameWinner = new SimpleStringProperty(gameWinner);
	}

	public String getPhaseDescription() {
		return phaseDescription.get();
	}
	
	public void setPhaseDescription(String phaseDescription) {
	    this.phaseDescription.set(phaseDescription);
	}

	public StringProperty phaseDescriptionProperty() {
	    return this.phaseDescription;
	}

	public String getGameDescription() {
		return gameDescription.get();
	}
	
	public void setGameDescription(String gameDescription) {
		this.gameDescription.set(gameDescription);
	}
	
	public StringProperty gameDescriptionProperty() {
		return this.gameDescription;
	}
	
	public void setGameWinner(String gameWinner) {
		this.gameWinner.set(gameWinner);
	}
	
	public StringProperty gameWinnerVal() {
		return this.gameWinner;
	}

	public String getGameWinner() {
		return gameWinner.get();
	}
	
	
}
