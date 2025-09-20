package com.sandesh.game_app_sdp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sandesh.game_app_sdp.exceptions.BusinessException;
import com.sandesh.game_app_sdp.exceptions.IdNotPresentException;
import com.sandesh.game_app_sdp.modal.Member;
import com.sandesh.game_app_sdp.services.MemberService;

@RestController
@RequestMapping(path = "/members")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174"})
public class MemberController {
    
    @Autowired
    private MemberService service;
    
    // Add home endpoint for root path
    @GetMapping("/")
    public String home() {
        return "Member API is running! Use the following endpoints:\n" +
               "GET /members - Get all members\n" +
               "POST /members - Create a new member\n" +
               "GET /members/{id} - Get member by ID\n" +
               "PUT /members/{id} - Update member\n" +
               "DELETE /members/{id} - Delete member";
    }
    
    @PostMapping
    public Member create(@RequestBody Member member) throws BusinessException {
        return service.create(member);
    }
    
    @GetMapping
    public List<Member> findAll() {
        return service.findAll();
    }
    
    @GetMapping(path = "/{id}")
    public Member findById(@PathVariable String id) throws IdNotPresentException {
        return service.findById(id);
    }
    
    @PutMapping(path = "/{id}")
    public Member update(@PathVariable String id, @RequestBody Member member) throws IdNotPresentException, BusinessException {
        return service.update(id, member);
    }
    
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable String id) throws IdNotPresentException {
        service.delete(id);
    }
}
