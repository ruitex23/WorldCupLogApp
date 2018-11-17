/**
 * 
 */
package domains;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * @author ruitex23
 *
 */
@NodeEntity
public class Game {
	
	@GraphId
    private Long id;
	private Phase phase;
	private Boolean isSet;
	private String playerOneName;
	private String playerTwoName;
	private Integer playerOnePoints;
	private Integer playerTwoPoints;
	private Integer playerOneInnings;
	private Integer playerTwoInnings;
	private Integer playerOneHighrun;
	private Integer playerTwoHighrun;
	private Float playerOneAverage;
	private Float playerTwoAverage;
	private Float gameAverage;
	private Integer playerOneSetOne;
	private Integer playerOneSetOneInnings;
	private Integer playerOneSetTwo;
	private Integer playerOneSetTwoInnings;
	private Integer playerOneSetThree;
	private Integer playerOneSetThreeInnings;
	private Integer playerOneSetFour;
	private Integer playerOneSetFourInnings;
	private Integer playerOneSetFive;
	private Integer playerOneSetFiveInnings;
	private Integer playerOneSetSix;
	private Integer playerOneSetSixInnings;
	private Integer playerOneSetSeven;
	private Integer playerOneSetSevenInnings;
	private Integer playerTwoSetOne;
	private Integer playerTwoSetOneInnings;
	private Integer playerTwoSetTwo;
	private Integer playerTwoSetTwoInnings;
	private Integer playerTwoSetThree;
	private Integer playerTwoSetThreeInnings;
	private Integer playerTwoSetFour;
	private Integer playerTwoSetFourInnings;
	private Integer playerTwoSetFive;
	private Integer playerTwoSetFiveInnings;
	private Integer playerTwoSetSix;
	private Integer playerTwoSetSixInnings;
	private Integer playerTwoSetSeven;
	private Integer playerTwoSetSevenInnings;
	private Integer playerOneSetsWon;
	private Integer playerTwoSetsWon;
	private Integer playerOnePenalties;
	private Integer playerTwoPenalties;
	private String winner;
	private String finalResult;
	private String resultPenalties;
	private String location;
	private String phaseDescription;
	
	
	
	public String getPhaseDescription() {
		return phaseDescription;
	}
	public void setPhaseDescription(String phaseDescription) {
		this.phaseDescription = phaseDescription;
	}
	public Integer getPlayerOneSetOneInnings() {
		return playerOneSetOneInnings;
	}
	public void setPlayerOneSetOneInnings(Integer playerOneSetOneInnings) {
		this.playerOneSetOneInnings = playerOneSetOneInnings;
	}
	public Integer getPlayerOneSetTwoInnings() {
		return playerOneSetTwoInnings;
	}
	public void setPlayerOneSetTwoInnings(Integer playerOneSetTwoInnings) {
		this.playerOneSetTwoInnings = playerOneSetTwoInnings;
	}
	public Integer getPlayerOneSetThreeInnings() {
		return playerOneSetThreeInnings;
	}
	public void setPlayerOneSetThreeInnings(Integer playerOneSetThreeInnings) {
		this.playerOneSetThreeInnings = playerOneSetThreeInnings;
	}
	public Integer getPlayerOneSetFourInnings() {
		return playerOneSetFourInnings;
	}
	public void setPlayerOneSetFourInnings(Integer playerOneSetFourInnings) {
		this.playerOneSetFourInnings = playerOneSetFourInnings;
	}
	public Integer getPlayerOneSetFiveInnings() {
		return playerOneSetFiveInnings;
	}
	public void setPlayerOneSetFiveInnings(Integer playerOneSetFiveInnings) {
		this.playerOneSetFiveInnings = playerOneSetFiveInnings;
	}
	public Integer getPlayerTwoSetOneInnings() {
		return playerTwoSetOneInnings;
	}
	public void setPlayerTwoSetOneInnings(Integer playerTwoSetOneInnings) {
		this.playerTwoSetOneInnings = playerTwoSetOneInnings;
	}
	public Integer getPlayerTwoSetTwoInnings() {
		return playerTwoSetTwoInnings;
	}
	public void setPlayerTwoSetTwoInnings(Integer playerTwoSetTwoInnings) {
		this.playerTwoSetTwoInnings = playerTwoSetTwoInnings;
	}
	public Integer getPlayerTwoSetThreeInnings() {
		return playerTwoSetThreeInnings;
	}
	public void setPlayerTwoSetThreeInnings(Integer playerTwoSetThreeInnings) {
		this.playerTwoSetThreeInnings = playerTwoSetThreeInnings;
	}
	public Integer getPlayerTwoSetFourInnings() {
		return playerTwoSetFourInnings;
	}
	public void setPlayerTwoSetFourInnings(Integer playerTwoSetFourInnings) {
		this.playerTwoSetFourInnings = playerTwoSetFourInnings;
	}
	public Integer getPlayerTwoSetFiveInnings() {
		return playerTwoSetFiveInnings;
	}
	public void setPlayerTwoSetFiveInnings(Integer playerTwoSetFiveInnings) {
		this.playerTwoSetFiveInnings = playerTwoSetFiveInnings;
	}
	public Integer getPlayerOnePenalties() {
		if (this.playerOnePenalties == null) {
			return 0;
		} else {
			return playerOnePenalties;
		}
	}
	public void setPlayerOnePenalties(Integer playerOnePenalties) {
		this.playerOnePenalties = playerOnePenalties;
	}
	public Integer getPlayerTwoPenalties() {
		if (this.playerTwoPenalties == null) {
			return 0;
		} else {
			return playerTwoPenalties;
		}
	}
	public void setPlayerTwoPenalties(Integer playerTwoPenalties) {
		this.playerTwoPenalties = playerTwoPenalties;
	}
	public Integer getPlayerOneSetsWon() {
		return playerOneSetsWon;
	}
	public void setPlayerOneSetsWon(Integer playerOneSetsWon) {
		this.playerOneSetsWon = playerOneSetsWon;
	}
	public Integer getPlayerTwoSetsWon() {
		return playerTwoSetsWon;
	}
	public void setPlayerTwoSetsWon(Integer playerTwoSetsWon) {
		this.playerTwoSetsWon = playerTwoSetsWon;
	}
	public Boolean getIsSet() {
		return isSet;
	}
	public void setIsSet(Boolean isSet) {
		this.isSet = isSet;
	}
	public Integer getPlayerOneSetSix() {
		return playerOneSetSix;
	}
	public void setPlayerOneSetSix(Integer playerOneSetSix) {
		this.playerOneSetSix = playerOneSetSix;
	}
	public Integer getPlayerOneSetSixInnings() {
		return playerOneSetSixInnings;
	}
	public void setPlayerOneSetSixInnings(Integer playerOneSetSixInnings) {
		this.playerOneSetSixInnings = playerOneSetSixInnings;
	}
	public Integer getPlayerOneSetSeven() {
		return playerOneSetSeven;
	}
	public void setPlayerOneSetSeven(Integer playerOneSetSeven) {
		this.playerOneSetSeven = playerOneSetSeven;
	}
	public Integer getPlayerOneSetSevenInnings() {
		return playerOneSetSevenInnings;
	}
	public void setPlayerOneSetSevenInnings(Integer playerOneSetSevenInnings) {
		this.playerOneSetSevenInnings = playerOneSetSevenInnings;
	}
	public Integer getPlayerTwoSetSix() {
		return playerTwoSetSix;
	}
	public void setPlayerTwoSetSix(Integer playerTwoSetSix) {
		this.playerTwoSetSix = playerTwoSetSix;
	}
	public Integer getPlayerTwoSetSixInnings() {
		return playerTwoSetSixInnings;
	}
	public void setPlayerTwoSetSixInnings(Integer playerTwoSetSixInnings) {
		this.playerTwoSetSixInnings = playerTwoSetSixInnings;
	}
	public Integer getPlayerTwoSetSeven() {
		return playerTwoSetSeven;
	}
	public void setPlayerTwoSetSeven(Integer playerTwoSetSeven) {
		this.playerTwoSetSeven = playerTwoSetSeven;
	}
	public Integer getPlayerTwoSetSevenInnings() {
		return playerTwoSetSevenInnings;
	}
	public void setPlayerTwoSetSevenInnings(Integer playerTwoSetSevenInnings) {
		this.playerTwoSetSevenInnings = playerTwoSetSevenInnings;
	}
	/**
	 * @return the playerOneName
	 */
	public String getPlayerOneName() {
		return playerOneName;
	}
	/**
	 * @param playerOneName the playerOneName to set
	 */
	public void setPlayerOneName(String playerOneName) {
		this.playerOneName = playerOneName;
	}
	/**
	 * @return the playerTwoName
	 */
	public String getPlayerTwoName() {
		return playerTwoName;
	}
	/**
	 * @return the phase
	 */
	public Phase getPhase() {
		return phase;
	}
	/**
	 * @param phase the phase to set
	 */
	public void setPhase(Phase phase) {
		this.phase = phase;
	}
	/**
	 * @param playerTwoName the playerTwoName to set
	 */
	public void setPlayerTwoName(String playerTwoName) {
		this.playerTwoName = playerTwoName;
	}
	/**
	 * @return the playerOneHighrun
	 */
	public Integer getPlayerOneHighrun() {
		return playerOneHighrun;
	}
	/**
	 * @param playerOneHighrun the playerOneHighrun to set
	 */
	public void setPlayerOneHighrun(Integer playerOneHighrun) {
		this.playerOneHighrun = playerOneHighrun;
	}
	/**
	 * @return the playerTwoHighrun
	 */
	public Integer getPlayerTwoHighrun() {
		return playerTwoHighrun;
	}
	/**
	 * @param playerTwoHighrun the playerTwoHighrun to set
	 */
	public void setPlayerTwoHighrun(Integer playerTwoHighrun) {
		this.playerTwoHighrun = playerTwoHighrun;
	}
	/**
	 * @return the winner
	 */
	public String getWinner() {
		return winner;
	}
	/**
	 * @param winner the winner to set
	 */
	public void setWinner(String winner) {
		this.winner = winner;
	}
	/**
	 * @return the resultPenalties
	 */
	public String getResultPenalties() {
		return resultPenalties;
	}
	/**
	 * @param resultPenalties the resultPenalties to set
	 */
	public void setResultPenalties(String resultPenalties) {
		this.resultPenalties = resultPenalties;
	}
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	public Integer getPlayerOnePoints() {
		return playerOnePoints;
	}
	public void setPlayerOnePoints(Integer playerOnePoints) {
		this.playerOnePoints = playerOnePoints;
	}
	public Integer getPlayerTwoPoints() {
		return playerTwoPoints;
	}
	public void setPlayerTwoPoints(Integer playerTwoPoints) {
		this.playerTwoPoints = playerTwoPoints;
	}
	public Integer getPlayerOneInnings() {
		return playerOneInnings;
	}
	public void setPlayerOneInnings(Integer playerOneInnings) {
		this.playerOneInnings = playerOneInnings;
	}
	public Integer getPlayerTwoInnings() {
		return playerTwoInnings;
	}
	public void setPlayerTwoInnings(Integer playerTwoInnings) {
		this.playerTwoInnings = playerTwoInnings;
	}
	public Integer getPlayerOneSetOne() {
		return playerOneSetOne;
	}
	public void setPlayerOneSetOne(Integer playerOneSetOne) {
		this.playerOneSetOne = playerOneSetOne;
	}
	public Integer getPlayerOneSetTwo() {
		return playerOneSetTwo;
	}
	public void setPlayerOneSetTwo(Integer playerOneSetTwo) {
		this.playerOneSetTwo = playerOneSetTwo;
	}
	public Integer getPlayerOneSetThree() {
		return playerOneSetThree;
	}
	public void setPlayerOneSetThree(Integer playerOneSetThree) {
		this.playerOneSetThree = playerOneSetThree;
	}
	public Integer getPlayerOneSetFour() {
		return playerOneSetFour;
	}
	public void setPlayerOneSetFour(Integer playerOneSetFour) {
		this.playerOneSetFour = playerOneSetFour;
	}
	public Integer getPlayerOneSetFive() {
		return playerOneSetFive;
	}
	public void setPlayerOneSetFive(Integer playerOneSetFive) {
		this.playerOneSetFive = playerOneSetFive;
	}
	public Integer getPlayerTwoSetOne() {
		return playerTwoSetOne;
	}
	public void setPlayerTwoSetOne(Integer playerTwoSetOne) {
		this.playerTwoSetOne = playerTwoSetOne;
	}
	public Integer getPlayerTwoSetTwo() {
		return playerTwoSetTwo;
	}
	public void setPlayerTwoSetTwo(Integer playerTwoSetTwo) {
		this.playerTwoSetTwo = playerTwoSetTwo;
	}
	public Integer getPlayerTwoSetThree() {
		return playerTwoSetThree;
	}
	public void setPlayerTwoSetThree(Integer playerTwoSetThree) {
		this.playerTwoSetThree = playerTwoSetThree;
	}
	public Integer getPlayerTwoSetFour() {
		return playerTwoSetFour;
	}
	public void setPlayerTwoSetFour(Integer playerTwoSetFour) {
		this.playerTwoSetFour = playerTwoSetFour;
	}
	public Integer getPlayerTwoSetFive() {
		return playerTwoSetFive;
	}
	public void setPlayerTwoSetFive(Integer playerTwoSetFive) {
		this.playerTwoSetFive = playerTwoSetFive;
	}
	public String getFinalResult() {
		return finalResult;
	}
	public void setFinalResult(String finalResult) {
		this.finalResult = finalResult;
	}
	
	public Integer getTotalPointsPlayer1() {
		return this.playerOnePoints + this.getPlayerOnePenalties();
	}
	
	public Integer getTotalPointsPlayer2() {
		return this.playerTwoPoints + this.getPlayerTwoPenalties();
	}
	public Float getPlayerOneAverage() {
		return playerOneAverage;
	}
	public void setPlayerOneAverage(Float playerOneAverage) {
		this.playerOneAverage = playerOneAverage;
	}
	public Float getPlayerTwoAverage() {
		return playerTwoAverage;
	}
	public void setPlayerTwoAverage(Float playerTwoAverage) {
		this.playerTwoAverage = playerTwoAverage;
	}
	public Float getGameAverage() {
		return gameAverage;
	}
	public void setGameAverage(Float gameAverage) {
		this.gameAverage = gameAverage;
	}
	
	public void clearPlayerOne() {
		this.playerOneAverage = null;
		this.playerOneHighrun = null;
		this.playerOneInnings = null;
		this.playerOneName = null;
		this.playerOnePenalties = null;
		this.playerOnePoints = null;
		this.playerOneSetFive = null;
		this.playerOneSetFiveInnings = null;
		this.playerOneSetFour = null;
		this.playerOneSetFourInnings = null;
		this.playerOneSetOne = null;
		this.playerOneSetOneInnings = null;
		this.playerOneSetsWon = null;
		this.playerOneSetThree = null;
		this.playerOneSetThreeInnings = null;
		this.playerOneSetTwo = null;
		this.playerOneSetTwoInnings = null;
		this.playerOneSetSeven = null;
		this.playerOneSetSevenInnings = null;
		this.playerOneSetSix = null;
		this.playerOneSetSixInnings = null;
	}
	
	public void clearPlayerTwo() {
		this.playerTwoAverage = null;
		this.playerTwoHighrun = null;
		this.playerTwoInnings = null;
		this.playerTwoName = null;
		this.playerTwoPenalties = null;
		this.playerTwoPoints = null;
		this.playerTwoSetFive = null;
		this.playerTwoSetFiveInnings = null;
		this.playerTwoSetFour = null;
		this.playerTwoSetFourInnings = null;
		this.playerTwoSetOne = null;
		this.playerTwoSetOneInnings = null;
		this.playerTwoSetsWon = null;
		this.playerTwoSetThree = null;
		this.playerTwoSetThreeInnings = null;
		this.playerTwoSetTwo = null;
		this.playerTwoSetTwoInnings = null;
		this.playerTwoSetSeven = null;
		this.playerTwoSetSevenInnings = null;
		this.playerTwoSetSix = null;
		this.playerTwoSetSixInnings = null;
	}
	
	public String toString() {
		return this.playerOneName + " vs " + this.playerTwoName;
	}
	
}
