package com.team3.itability.mypage.entity;

//import com.team3.itability.mypage.MemberProfileDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "member_skill_dto")
@Table(name = "member_skill")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MemberSkillDTO {
    @EmbeddedId
    private MemberSkillId id;
}
