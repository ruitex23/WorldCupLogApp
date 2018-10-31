/**
 * 
 */
package main.java.repositories;

import java.util.ArrayList;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import main.java.domains.Player;


/**
 * @author ruitex23
 *
 */
@Repository
public interface PlayerRepository extends GraphRepository<Player>  {
	
	Player findPlayerByName(@Param("name") String name);
	
	@Query("MATCH (n:Player) RETURN n")
	ArrayList<Player> findAll();

}
