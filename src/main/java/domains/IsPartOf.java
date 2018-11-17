/**
 * 
 */
package domains;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 * @author ruitex23
 *
 */
@RelationshipEntity(type = "IS_PART_OF")
public class IsPartOf {
	
	@GraphId
	private Long id;
	
	@StartNode
	private Game game;
	
	@EndNode
	private Phase phase;

	public IsPartOf (Game game, Phase phase) {
		this.game = game;
		this.phase = phase;
	}
	/**
	 * @return the game
	 */
	public Game getGame() {
		return game;
	}

	/**
	 * @param game the game to set
	 */
	public void setGame(Game game) {
		this.game = game;
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
