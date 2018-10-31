/**
 * 
 */
package main.java.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.java.domains.Player;
import main.java.repositories.PlayerRepository;

/**
 * @author ruitex23
 *
 */
@Service
public class PlayerService {
	
	@Autowired
	private PlayerRepository playerRepo;
	
	@Transactional
	public void save(Player player) {
		playerRepo.save(player);
	}
	
	public ArrayList<Player> getAllPlayers() {
		return (ArrayList<Player>) playerRepo.findAll();
	}

}
