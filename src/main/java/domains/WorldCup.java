/**
 * 
 */
package main.java.domains;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * @author ruitex23
 *
 */
@NodeEntity
public class WorldCup {
	
	@GraphId
    private Long id;
	
	private String city;
	private String country;
	private String confederation;
	private Integer year;
	private String winner;
	private String runnerUp;
	
	private Integer highestRun;
	private Double higherAverage;
	private String organizer;
	private Integer totalPoints;
	private Double worldCupAverage;
	
	private Boolean isSet;
	
	private Boolean hasLast32;
	private Boolean hasLast16;
	private Boolean hasQF;
	private Boolean hasThirdForth;
	
	
	public Boolean getHasLast32() {
		return hasLast32;
	}
	public void setHasLast32(Boolean hasLast32) {
		this.hasLast32 = hasLast32;
	}
	public Boolean getHasLast16() {
		return hasLast16;
	}
	public void setHasLast16(Boolean hasLast16) {
		this.hasLast16 = hasLast16;
	}
	public Boolean getHasQF() {
		return hasQF;
	}
	public void setHasQF(Boolean hasQF) {
		this.hasQF = hasQF;
	}
	public Boolean getHasThirdForth() {
		return hasThirdForth;
	}
	public void setHasThirdForth(Boolean hasThirdForth) {
		this.hasThirdForth = hasThirdForth;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	public String getConfederation() {
		return confederation;
	}
	public void setConfederation(String confederation) {
		this.confederation = confederation;
	}
	/**
	 * @return the year
	 */
	public Integer getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(Integer year) {
		this.year = year;
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
	 * @return the runnerUp
	 */
	public String getRunnerUp() {
		return runnerUp;
	}
	/**
	 * @param runnerUp the runnerUp to set
	 */
	public void setRunnerUp(String runnerUp) {
		this.runnerUp = runnerUp;
	}
	/**
	 * @return the highestRun
	 */
	public Integer getHighestRun() {
		return highestRun;
	}
	/**
	 * @param highestRun the highestRun to set
	 */
	public void setHighestRun(Integer highestRun) {
		this.highestRun = highestRun;
	}
	/**
	 * @return the higherAverage
	 */
	public Double getHigherAverage() {
		return higherAverage;
	}
	/**
	 * @param higherAverage the higherAverage to set
	 */
	public void setHigherAverage(Double higherAverage) {
		this.higherAverage = higherAverage;
	}
	/**
	 * @return the organizer
	 */
	public String getOrganizer() {
		return organizer;
	}
	/**
	 * @param organizer the organizer to set
	 */
	public void setOrganizer(String organizer) {
		this.organizer = organizer;
	}
	/**
	 * @return the totalPoints
	 */
	public Integer getTotalPoints() {
		return totalPoints;
	}
	/**
	 * @param totalPoints the totalPoints to set
	 */
	public void setTotalPoints(Integer totalPoints) {
		this.totalPoints = totalPoints;
	}
	/**
	 * @return the worldCupAverage
	 */
	public Double getWorldCupAverage() {
		return worldCupAverage;
	}
	/**
	 * @param worldCupAverage the worldCupAverage to set
	 */
	public void setWorldCupAverage(Double worldCupAverage) {
		this.worldCupAverage = worldCupAverage;
	}
	public Boolean getIsSet() {
		return isSet;
	}
	public void setIsSet(Boolean isSet) {
		this.isSet = isSet;
	}
	
	public String toString() {
		return this.year  + " - " + this.city + ", " + this.country;
	}
	
}
