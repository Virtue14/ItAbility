package com.team3.itability.blackllist.dto;


import com.team3.itability.member.dto.MemberInfoDTO;
import jakarta.persistence.*;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class BlacklistDTO {

    private Long blacklistId;
    private java.util.Date blackist_date;
    private Long memberId;

}
