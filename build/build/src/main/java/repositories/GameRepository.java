/**
 * 
 */
package main.java.repositories;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import main.java.domains.Game;


/**
 * @author ruitex23
 *
 */
@Repository
public interface GameRepository extends GraphRepository<Game> {
	
	Game findByWinner(@Param("winner") String winner);

}
