package com.sandesh.game_app_sdp.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sandesh.game_app_sdp.exceptions.IdNotPresentException;
import com.sandesh.game_app_sdp.modal.Game;
import com.sandesh.game_app_sdp.repository.GameRepository;

@Service
public class GameServiceImpl extends GameService {
    @Autowired
    private GameRepository repo;
    
    private static final Logger logger = LoggerFactory.getLogger(GameServiceImpl.class);

    @Override
    public Game create(Game game) {
        logger.info("Creating new game: {}", game.getName());
        game.setId(null);
        Game savedGame = repo.save(game);
        return savedGame;
    }

    @Override
    public List<Game> findAll() {
        logger.info("Fetching all games");
        List<Game> games = repo.findAll();
        return games;
    }

    @Override
    public Game findById(String id) throws IdNotPresentException {
        logger.info("Fetching game with id: {}", id);
        Optional<Game> optionalGame = repo.findById(id);
        if(optionalGame.isEmpty()) {
            logger.error("Game with id {} not found", id);
            throw new IdNotPresentException("Game not found: " + id);
        }
        return optionalGame.get();
    }

    @Override
    public Game update(String id, Game game) throws IdNotPresentException {
        logger.info("Updating game with id: {}", id);
        Optional<Game> optionalGame = repo.findById(id);
        if(optionalGame.isEmpty()) {
            logger.error("Game with id {} not found", id);
            throw new IdNotPresentException("Game not found " + id);
        }
        Game oldGame = optionalGame.get();
        oldGame.setName(game.getName());
        oldGame.setDescription(game.getDescription());
        oldGame.setPrice(game.getPrice());
        
        
        logger.info("Game updated successfully: {}", oldGame.getName());
        Game updatedGame = repo.save(oldGame);
        return updatedGame;
    }

    @Override
    public boolean delete(String id) throws IdNotPresentException {
        logger.info("Deleting game with id: {}", id);
        Optional<Game> optionalGame = repo.findById(id);
        if(optionalGame.isEmpty()) {
            logger.error("Game with id {} not found", id);
            throw new IdNotPresentException("Game not found " + id);
        }
        repo.deleteById(id);
        return true;
    }
}
