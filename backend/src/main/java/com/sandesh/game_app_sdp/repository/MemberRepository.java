package com.sandesh.game_app_sdp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sandesh.game_app_sdp.modal.Member;

@Repository
public interface MemberRepository extends MongoRepository<Member, String> {
    boolean existsByPhone(String phone);
}
