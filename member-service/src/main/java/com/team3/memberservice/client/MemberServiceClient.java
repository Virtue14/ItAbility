package com.team3.memberservice.client;

import com.team3.memberservice.mypage.entity.SkillEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "swcamp-order-service", url = "localhost:8282") // gateway에 갈 클라이언트의 이름, gateway 주소
public interface MemberServiceClient{

    // gateway가 알고 있는 마이크로 서비스의 접두사(라우팅 시 설정한 요청 경로)를 추가하여 요청 경로를 작성한다.
    @GetMapping("/board-service/users/{userId}")
    List<SkillEntity> getUserOrders(@PathVariable String userId);
}