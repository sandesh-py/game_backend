package com.sandesh.game_app_sdp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sandesh.game_app_sdp.exceptions.IdNotPresentException;
import com.sandesh.game_app_sdp.modal.Game;
import com.sandesh.game_app_sdp.services.GameService;

@RestController
@RequestMapping(path = "/admin/games")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174"})
public class GameController {
    
    @Autowired
    private GameService service;
    
    // Add home endpoint for root path
    @GetMapping("/")
    public String home() {
        return "Game App API is running! Use the following endpoints:\n" +
               "GET /admin/games - Get all games\n" +
               "POST /admin/games - Create a new game\n" +
               "GET /admin/games/{id} - Get game by ID\n" +
               "PUT /admin/games/{id} - Update game\n" +
               "DELETE /admin/games/{id} - Delete game";
    }
    
    @PostMapping
    public ResponseEntity<Game> create(@RequestBody Game game) {
        Game createdGame = service.create(game);
        return new ResponseEntity<>(createdGame, HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<Game>> findAll() {
        List<Game> games = service.findAll();
        return new ResponseEntity<>(games, HttpStatus.OK);
    }
    
    @GetMapping(path = "/{id}")
    public ResponseEntity<Game> findById(@PathVariable String id) {
        try {
            Game game = service.findById(id);
            return new ResponseEntity<>(game, HttpStatus.OK);
        } catch (IdNotPresentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping(path = "/{id}")
    public ResponseEntity<Game> update(@PathVariable String id, @RequestBody Game game) {
        try {
            Game updatedGame = service.update(id, game);
            return new ResponseEntity<>(updatedGame, HttpStatus.OK);
        } catch (IdNotPresentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable String id) {
        try {
            service.delete(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IdNotPresentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}