package com.sandesh.game_app_sdp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sandesh.game_app_sdp.exceptions.*;
import com.sandesh.game_app_sdp.modal.Member;
import com.sandesh.game_app_sdp.repository.MemberRepository;

@Service
public class MemberService {

    @Autowired
    private MemberRepository repo;

    public Member create(Member member) throws BusinessException {
        member.setId(null);
        if (member.getName() == null || member.getName().trim().isEmpty()) {
            throw new BusinessException("Member name is required");
        }
        if (member.getBalance() == null) {
            member.setBalance(0.0);
        }
        if (member.getPhone() == null || member.getPhone().trim().isEmpty()) {
            throw new BusinessException("Member phone is required");
        }
        if (repo.existsByPhone(member.getPhone())) {
            throw new BusinessException("Phone already exists: " + member.getPhone());
        }
        return repo.save(member);
    }

    public List<Member> findAll() {
        return repo.findAll();
    }

    public Member findById(String id) throws IdNotPresentException {
        Optional<Member> optional = repo.findById(id);
        if (optional.isEmpty()) {
            throw new IdNotPresentException("Member not found: " + id);
        }
        return optional.get();
    }

    public Member update(String id, Member member) throws IdNotPresentException, BusinessException {
        Optional<Member> optional = repo.findById(id);
        if (optional.isEmpty()) {
            throw new IdNotPresentException("Member not found: " + id);
        }
        Member existing = optional.get();

        if (member.getName() != null && !member.getName().trim().isEmpty()) {
            existing.setName(member.getName());
        }
        if (member.getBalance() != null) {
            existing.setBalance(member.getBalance());
        }
        if (member.getPhone() != null && !member.getPhone().trim().isEmpty()) {
            if (!member.getPhone().equals(existing.getPhone()) && repo.existsByPhone(member.getPhone())) {
                throw new BusinessException("Phone already exists: " + member.getPhone());
            }
            existing.setPhone(member.getPhone());
        }

        return repo.save(existing);
    }

    public boolean delete(String id) throws IdNotPresentException {
        Optional<Member> optional = repo.findById(id);
        if (optional.isEmpty()) {
            throw new IdNotPresentException("Member not found: " + id);
        }
        repo.deleteById(id);
        return true;
    }
}


