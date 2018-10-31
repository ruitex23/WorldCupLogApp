/**
 * 
 */
package main.java.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.java.domains.WorldCup;
import main.java.repositories.WorldCupRepository;

/**
 * @author ruitex23
 *
 */
@Service
@Transactional
public class WorldCupService {
	
	@Autowired
	private WorldCupRepository wcRepo;
	
	public void save(WorldCup worldCup) {
		wcRepo.save(worldCup);
	}

}
