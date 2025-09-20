package com.sandesh.game_app_sdp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sandesh.game_app_sdp.modal.Game;

@Repository
public interface GameRepository extends MongoRepository<Game, String> {

    
}
