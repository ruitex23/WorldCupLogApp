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
public class Phase {
	
	@GraphId
    private Long id;
	
	private String phaseDescription;
	
	private Double average;
	
	private Boolean isSets;
	
	public Double getAverage() {
		return average;
	}
	public void setAverage(Double average) {
		this.average = average;
	}
	/**
	 * @return the phaseDescription
	 */
	public String getPhaseDescription() {
		return phaseDescription;
	}
	/**
	 * @param phaseDescription the phaseDescription to set
	 */
	public void setPhaseDescription(String phaseDescription) {
		this.phaseDescription = phaseDescription;
	}
	/**
	 * @return the isSets
	 */
	public Boolean getIsSets() {
		return isSets;
	}
	/**
	 * @param isSets the isSets to set
	 */
	public void setIsSets(Boolean isSets) {
		this.isSets = isSets;
	}
	

}
