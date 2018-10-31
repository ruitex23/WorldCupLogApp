/**
 * 
 */
package main.java.model;

import java.util.List;

import main.java.domains.Phase;
import main.java.domains.Player;
import main.java.domains.WorldCup;

/**
 * @author ruitex23
 *
 */
public class CreateWorldCupModel {
	
	private WorldCup worldCup;
	private Phase last32;
	private Phase last16;
	private Phase quarterFinals;
	private Phase semiFinals;
	private Phase theFinal;
	private Phase thirdForth;
	private Boolean isSet;
	private List<Player> regsPlayers;
	
	
	/**
	 * @return the worldCup
	 */
	public WorldCup getWorldCup() {
		return worldCup;
	}
	/**
	 * @param worldCup the worldCup to set
	 */
	public void setWorldCup(WorldCup worldCup) {
		this.worldCup = worldCup;
	}
	/**
	 * @return the last32
	 */
	public Phase getLast32() {
		return last32;
	}
	/**
	 * @param last32 the last32 to set
	 */
	public void setLast32(Phase last32) {
		this.last32 = last32;
	}
	/**
	 * @return the last16
	 */
	public Phase getLast16() {
		return last16;
	}
	/**
	 * @param last16 the last16 to set
	 */
	public void setLast16(Phase last16) {
		this.last16 = last16;
	}
	/**
	 * @return the quarterFinals
	 */
	public Phase getQuarterFinals() {
		return quarterFinals;
	}
	/**
	 * @param quarterFinals the quarterFinals to set
	 */
	public void setQuarterFinals(Phase quarterFinals) {
		this.quarterFinals = quarterFinals;
	}
	/**
	 * @return the semiFinals
	 */
	public Phase getSemiFinals() {
		return semiFinals;
	}
	/**
	 * @param semiFinals the semiFinals to set
	 */
	public void setSemiFinals(Phase semiFinals) {
		this.semiFinals = semiFinals;
	}
	/**
	 * @return the theFinal
	 */
	public Phase getTheFinal() {
		return theFinal;
	}
	/**
	 * @param theFinal the theFinal to set
	 */
	public void setTheFinal(Phase theFinal) {
		this.theFinal = theFinal;
	}
	/**
	 * @return the thirdForth
	 */
	public Phase getThirdForth() {
		return thirdForth;
	}
	/**
	 * @param thirdForth the thirdForth to set
	 */
	public void setThirdForth(Phase thirdForth) {
		this.thirdForth = thirdForth;
	}
	/**
	 * @return the isSet
	 */
	public Boolean getIsSet() {
		return isSet;
	}
	/**
	 * @param isSet the isSet to set
	 */
	public void setIsSet(Boolean isSet) {
		this.isSet = isSet;
	}
	/**
	 * @return the regsPlayers
	 */
	public List<Player> getRegsPlayers() {
		return regsPlayers;
	}
	/**
	 * @param regsPlayers the regsPlayers to set
	 */
	public void setRegsPlayers(List<Player> regsPlayers) {
		this.regsPlayers = regsPlayers;
	}
}
