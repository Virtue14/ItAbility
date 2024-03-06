package com.team3.itability.recruitment.dto;

import com.team3.itability.member.dto.MemberInfoDTO;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "ref_recruit_category")
@Entity(name = "recruit")
@Table(name = "recruit")
public class RecruitDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recruit_id")
    private Integer recruitId;

    @Enumerated(EnumType.STRING)
    @Column(name = "recruit_type")
    private RecruitType recruitType;

    @Column(name = "recruit_title")
    private String recruitTitle;

    @Column(name = "recruit_content")
    private String recruitContent;

    @Column(name = "recruit_expdate")
    private String recruitExpDate;

    @Column(name = "recruit_mb_cnt")
    private Integer recruitMbCnt;

    @JoinColumn(name="member_id")
    @ManyToOne
    private MemberInfoDTO memberInfoDTO;
}
