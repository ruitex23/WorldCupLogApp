/**
 * 
 */
package main.java.repositories;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import main.java.domains.WorldCup;

/**
 * @author ruitex23
 *
 */
@Repository
public interface WorldCupRepository extends GraphRepository<WorldCup> {
	
	WorldCup findByWinner(@Param("winner") String winner);
	

}
