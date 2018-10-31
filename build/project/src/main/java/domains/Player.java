package main.java.domains;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * @author ruitex23
 *
 */
@NodeEntity
public class Player implements Comparable<Player>{
	
	//TODO: is the flag property defined here?
	
	@GraphId
    private Long id;
	
	private String firstName;
	private String lastName;
	private String country;
	private Integer age;
	private String confederation;
	
	
	private Double bestWorldCupAverage;
	private Double seasonAverage;
	private Double careerAverage;
	private Integer yearsPro;
	
	private String profilePicturePath;
	
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
	/**
	 * @return the age
	 */
	public Integer getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * @return the bestWorldCupAverage
	 */
	public Double getBestWorldCupAverage() {
		return bestWorldCupAverage;
	}
	/**
	 * @param bestWorldCupAverage the bestWorldCupAverage to set
	 */
	public void setBestWorldCupAverage(Double bestWorldCupAverage) {
		this.bestWorldCupAverage = bestWorldCupAverage;
	}
	/**
	 * @return the seasonAverage
	 */
	public Double getSeasonAverage() {
		return seasonAverage;
	}
	/**
	 * @param seasonAverage the seasonAverage to set
	 */
	public void setSeasonAverage(Double seasonAverage) {
		this.seasonAverage = seasonAverage;
	}
	/**
	 * @return the careerAverage
	 */
	public Double getCareerAverage() {
		return careerAverage;
	}
	/**
	 * @param careerAverage the careerAverage to set
	 */
	public void setCareerAverage(Double careerAverage) {
		this.careerAverage = careerAverage;
	}
	/**
	 * @return the yearsPro
	 */
	public Integer getYearsPro() {
		return yearsPro;
	}
	/**
	 * @param yearsPro the yearsPro to set
	 */
	public void setYearsPro(Integer yearsPro) {
		this.yearsPro = yearsPro;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getConfederation() {
		return confederation;
	}
	public void setConfederation(String confederation) {
		this.confederation = confederation;
	}
	public String getProfilePicturePath() {
		return profilePicturePath;
	}
	public void setProfilePicturePath(String profilePicturePath) {
		this.profilePicturePath = profilePicturePath;
	}
	@Override
	public String toString() {
		return this.lastName + ", " + this.firstName;
	}
	@Override
	public int compareTo(Player o) {
		return this.toString().compareToIgnoreCase(o.toString());
	}
	
}
