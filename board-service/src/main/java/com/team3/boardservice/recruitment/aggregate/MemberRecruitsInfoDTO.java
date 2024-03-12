package com.team3.boardservice.recruitment.aggregate;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "member_recruit_info")
@Table(name = "member_recruit_info")
public class MemberRecruitsInfoDTO {

    @Id
    @Column(name = "member_recruit_info_id")
    private int memberRecruitInfoId;

    @JoinColumn(name="recruit_id")
    @ManyToOne
    private RecruitDTO recruitDTO;

    @Enumerated(EnumType.STRING)
    @Column(name="recruit_status")
    private RecruitStatus recruitStatus;

    @JoinColumn(name="member_id")
    @ManyToOne
    private MemberInfoDTO memberInfoDTO;

}
