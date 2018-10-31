package main.java.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.java.domains.Game;
import main.java.repositories.GameRepository;


@Service
@Transactional
public class GameService {
	
	@Autowired
	private GameRepository gameRepo;
	
	public void save(Game game) {
		gameRepo.save(game);
	}

}
