package com.team3.memberservice.snsapi.kakao.repository;

import com.team3.memberservice.snsapi.kakao.aggregate.Kakaouser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KakaoRepository extends JpaRepository<Kakaouser, String> {

}
