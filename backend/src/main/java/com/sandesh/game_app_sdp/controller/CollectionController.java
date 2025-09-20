package com.sandesh.game_app_sdp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sandesh.game_app_sdp.exceptions.BusinessException;
import com.sandesh.game_app_sdp.exceptions.IdNotPresentException;
import com.sandesh.game_app_sdp.modal.Collection;
import com.sandesh.game_app_sdp.services.CollectionService;

@RestController
@RequestMapping(path = "/collections")
public class CollectionController {

    @Autowired
    private CollectionService service;

    @PostMapping
    public ResponseEntity<Collection> create(@RequestBody Collection collection) {
        try {
            Collection created = service.create(collection);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (BusinessException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (IdNotPresentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Collection>> findAll() {
        List<Collection> all = service.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Collection> findById(@PathVariable String id) {
        try {
            Collection found = service.findById(id);
            return new ResponseEntity<>(found, HttpStatus.OK);
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


