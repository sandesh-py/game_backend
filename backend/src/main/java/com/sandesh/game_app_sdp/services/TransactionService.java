package com.sandesh.game_app_sdp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sandesh.game_app_sdp.exceptions.BusinessException;
import com.sandesh.game_app_sdp.exceptions.IdNotPresentException;
import com.sandesh.game_app_sdp.modal.Member;
import com.sandesh.game_app_sdp.modal.Transaction;
import com.sandesh.game_app_sdp.repository.MemberRepository;
import com.sandesh.game_app_sdp.repository.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private MemberRepository memberRepository;

    public Transaction create(Transaction tx) throws BusinessException, IdNotPresentException {
        tx.setId(null);
        if (tx.getMemberId() == null || tx.getMemberId().trim().isEmpty()) {
            throw new BusinessException("memberId is required");
        }
        Double amt = tx.getAmount();
        if (amt == null || amt == 0d) {
            throw new BusinessException("amount must be non-zero");
        }

        Optional<Member> memberOpt = memberRepository.findById(tx.getMemberId());
        if (memberOpt.isEmpty()) {
            throw new IdNotPresentException("Member not found: " + tx.getMemberId());
        }

        Member member = memberOpt.get();
        Double balObj = member.getBalance();
        double current = balObj == null ? 0d : balObj;
        double delta = amt;
        member.setBalance(current + delta);
        memberRepository.save(member);

        return transactionRepository.save(tx);
    }

    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    public Transaction findById(String id) throws IdNotPresentException {
        Optional<Transaction> optional = transactionRepository.findById(id);
        if (optional.isEmpty()) {
            throw new IdNotPresentException("Transaction not found: " + id);
        }
        return optional.get();
    }

    public boolean delete(String id) throws IdNotPresentException {
        Optional<Transaction> optional = transactionRepository.findById(id);
        if (optional.isEmpty()) {
            throw new IdNotPresentException("Transaction not found: " + id);
        }
        transactionRepository.deleteById(id);
        return true;
    }
}


