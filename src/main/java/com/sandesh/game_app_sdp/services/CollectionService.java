package com.sandesh.game_app_sdp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sandesh.game_app_sdp.exceptions.BusinessException;
import com.sandesh.game_app_sdp.exceptions.IdNotPresentException;
import com.sandesh.game_app_sdp.modal.Collection;
import com.sandesh.game_app_sdp.modal.Member;
import com.sandesh.game_app_sdp.repository.CollectionRepository;
import com.sandesh.game_app_sdp.repository.MemberRepository;

@Service
public class CollectionService {

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private MemberRepository memberRepository;

    public Collection create(Collection collection) throws BusinessException, IdNotPresentException {
        collection.setId(null);
        if (collection.getMemberId() == null || collection.getMemberId().trim().isEmpty()) {
            throw new BusinessException("memberId is required");
        }
        Double amt = collection.getAmount();
        if (amt == null || amt <= 0d) {
            throw new BusinessException("amount must be > 0");
        }

        Optional<Member> memberOpt = memberRepository.findById(collection.getMemberId());
        if (memberOpt.isEmpty()) {
            throw new IdNotPresentException("Member not found: " + collection.getMemberId());
        }

        Member member = memberOpt.get();
        Double balObj = member.getBalance();
        double current = balObj == null ? 0d : balObj;
        double delta = amt;
        member.setBalance(current - delta);
        memberRepository.save(member);

        return collectionRepository.save(collection);
    }

    public List<Collection> findAll() {
        return collectionRepository.findAll();
    }

    public Collection findById(String id) throws IdNotPresentException {
        Optional<Collection> optional = collectionRepository.findById(id);
        if (optional.isEmpty()) {
            throw new IdNotPresentException("Collection not found: " + id);
        }
        return optional.get();
    }

    public boolean delete(String id) throws IdNotPresentException {
        Optional<Collection> optional = collectionRepository.findById(id);
        if (optional.isEmpty()) {
            throw new IdNotPresentException("Collection not found: " + id);
        }
        collectionRepository.deleteById(id);
        return true;
    }
}


