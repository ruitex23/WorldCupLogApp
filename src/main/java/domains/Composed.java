package domains;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "COMPOSED")
public class Composed {
	
	@GraphId
	private Long id;
	
	@StartNode
	private WorldCup worldCup;
	
	@EndNode
	private Phase phase;
	
	public Composed (Phase phase, WorldCup worldCup) {
		this.phase = phase;
		this.worldCup = worldCup;
	}

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
	
	

}
