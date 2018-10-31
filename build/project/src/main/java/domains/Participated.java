package main.java.domains;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "PARTICIPATED")
public class Participated {
	
	@GraphId
	private Long id;
	
	@StartNode
	private Player player;
	
	@EndNode
	private WorldCup worldCup;

	public Participated (Player player, WorldCup worldCup) {
		this.player = player;
		this.worldCup = worldCup;
	}
	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * @param player the player to set
	 */
	public void setPlayer(Player player) {
		this.player = player;
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
	
	

}
