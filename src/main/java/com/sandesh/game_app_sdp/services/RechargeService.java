package com.sandesh.game_app_sdp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sandesh.game_app_sdp.exceptions.BusinessException;
import com.sandesh.game_app_sdp.exceptions.IdNotPresentException;
import com.sandesh.game_app_sdp.modal.Member;
import com.sandesh.game_app_sdp.modal.Recharge;
import com.sandesh.game_app_sdp.repository.MemberRepository;
import com.sandesh.game_app_sdp.repository.RechargeRepository;

@Service
public class RechargeService {

    @Autowired
    private RechargeRepository rechargeRepository;

    @Autowired
    private MemberRepository memberRepository;

    public Recharge create(Recharge recharge) throws BusinessException, IdNotPresentException {
        recharge.setId(null);
        if (recharge.getMemberId() == null || recharge.getMemberId().trim().isEmpty()) {
            throw new BusinessException("memberId is required");
        }
        Double amt = recharge.getAmount();
        if (amt == null || amt <= 0d) {
            throw new BusinessException("amount must be > 0");
        }

        Optional<Member> memberOpt = memberRepository.findById(recharge.getMemberId());
        if (memberOpt.isEmpty()) {
            throw new IdNotPresentException("Member not found: " + recharge.getMemberId());
        }

        Member member = memberOpt.get();
        Double balObj = member.getBalance();
        double current = balObj == null ? 0d : balObj;
        double delta = amt;
        member.setBalance(current + delta);
        memberRepository.save(member);

        return rechargeRepository.save(recharge);
    }

    public List<Recharge> findAll() {
        return rechargeRepository.findAll();
    }

    public Recharge findById(String id) throws IdNotPresentException {
        Optional<Recharge> optional = rechargeRepository.findById(id);
        if (optional.isEmpty()) {
            throw new IdNotPresentException("Recharge not found: " + id);
        }
        return optional.get();
    }

    public boolean delete(String id) throws IdNotPresentException {
        Optional<Recharge> optional = rechargeRepository.findById(id);
        if (optional.isEmpty()) {
            throw new IdNotPresentException("Recharge not found: " + id);
        }
        rechargeRepository.deleteById(id);
        return true;
    }
}


