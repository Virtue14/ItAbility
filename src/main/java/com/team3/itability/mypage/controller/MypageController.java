package com.team3.itability.mypage.controller;

import com.team3.itability.mypage.dto.*;
import com.team3.itability.mypage.entity.MemberAndRemainRecruitCategoryEntity;
import com.team3.itability.mypage.entity.MemberAndRemainSkillEntity;
import com.team3.itability.mypage.service.MypageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/mypage")
public class MypageController {
    private final MypageService mypageService;

    @Autowired
    public MypageController(MypageService mypageService) {
        this.mypageService = mypageService;
    }

    /**
     * <h1>MyPage</h1>
     * 마이페이지 컨트롤러
     * */
    @GetMapping("/{memberId}")
    public String printMypage(@PathVariable long memberId, Model model){
        MemberProfileDTO profile = mypageService.printMypageData(memberId);
        System.out.println("profile = " + profile);
        List<CareerDTO> careerList = mypageService.printCareerList(memberId);
        MemberAndRemainSkillEntity skillDTOS = mypageService.printMemberSkillList(memberId);
        model.addAttribute("skill",skillDTOS);
        model.addAttribute("careerList",careerList);
        model.addAttribute("profile", profile);

        return "mypage/mypage";
    }
    /**
     * <h1>modify-name</h1>
     * 개인정보 수정 컨트롤러
     * */
    @GetMapping("/{memberId}/modify-name")
    public String modifyMypage(Model model, @PathVariable long memberId){
        MemberProfileDTO profile = mypageService.printMypageData(memberId);
        model.addAttribute("profile", profile);
        return "mypage/modify";
    }
    @PostMapping("/modify-name")
    public String modify(Model model, @RequestParam long memberId, @RequestParam String nickname, @RequestParam String name, @RequestParam String phone, @RequestParam String birthDate){
        MemberProfileDTO profile = mypageService.modifyMypage(memberId,nickname,name,phone,birthDate);
        model.addAttribute(memberId);
        String redirectUrl = "redirect:/mypage/" + memberId;
        return redirectUrl;
    }
    /**
     * <h1>modify-degree</h1>
     * 학력 수정 컨트롤러
     * */
    @GetMapping("/{memberId}/modify-degree")
    public String modifyDegree(Model model, @PathVariable long memberId){
        MemberProfileDTO profile = mypageService.printMypageData(memberId);
        model.addAttribute("profile", profile);
        return "mypage/modify-degree";
    }
    @PostMapping("/modify-degree")
    public String modifySubmitDegree(Model model, @RequestParam long memberId, @ModelAttribute DegreeDTO degreeDTO){
        MemberProfileDTO profile = mypageService.modifyDegree(memberId, degreeDTO);
        model.addAttribute(profile);
        String redirectUrl = "redirect:/mypage/" + profile.getMemberId();
        return redirectUrl;
    }

    /**<h1>modify-career</h1>
     * 경력 수정 컨트롤러*/
    @GetMapping("/{memberId}/career-list")
    public String printCareerList(Model model, @PathVariable long memberId){
        List<CareerDTO> careerList = mypageService.printCareerList(memberId);
        model.addAttribute("memberId",memberId);
        model.addAttribute("careerList",careerList);
        return "mypage/career-list";
    }
    @GetMapping("/{memberId}/modify-career/{careerId}")
    public String modifyCareer(Model model, @PathVariable long memberId, @PathVariable int careerId){
        CareerDTO career = mypageService.printCareer(careerId);
        model.addAttribute("career", career);
        model.addAttribute("memberId",memberId);
        return "mypage/modify-career";
    }
    @PostMapping("/modify-career")
    public String modifySubmitCareer(Model model, @ModelAttribute CareerDTO careerDTO){
        System.out.println("careerDTO = " + careerDTO);
        CareerDTO career = mypageService.modifyCareer(careerDTO);
        System.out.println("career = " + career);
        return "redirect:/mypage/" + careerDTO.getMemberId().getMemberId();
    }
    @GetMapping("/{memberId}/add-career")
    public String showAddCareer(Model model, @PathVariable long memberId){
        model.addAttribute(memberId);
        return "mypage/add-career";
    }
    @PostMapping("/add-career")
    public String AddCareer(Model model, @ModelAttribute CareerDTO careerDTO, @RequestParam long memberId){
        CareerDTO career = mypageService.addCareer(careerDTO,memberId);
        System.out.println("career = " + career);
        return "redirect:/mypage/" + memberId;
    }

    /**<h2>modify-Image</h2>*/
    @GetMapping("/{memberId}/modify-image")
    public String showModifyImage(@PathVariable long memberId, Model model){
        ImageDTO image = mypageService.getImageDTO(memberId);
        System.out.println("image = " + image);
        model.addAttribute(image);
        model.addAttribute(memberId);
        return "mypage/modify-image";
    }@PostMapping("/{memberId}/mypage/modify-image")
    public String modifyImage(Model model, @RequestParam MultipartFile imgFile, RedirectAttributes rttr, @RequestParam long memberId) throws IOException {

        ImageDTO image = mypageService.modifyImageDTO(memberId,imgFile);
        System.out.println("컨트롤러에 들어온 image = " + image);

        return "redirect:/mypage/" + memberId;
        //설명. 계속 새로고침하여 insert되는 것을 막기 위함!!
    }

    /**<h1>modify-MemberSkill</h1>*/
    @GetMapping("/{memberId}/member-skill-list")
    public String showMemberSkillList(@PathVariable Long memberId, Model model ){
        MemberAndRemainSkillEntity skillDTOS = mypageService.printMemberSkillList(memberId);
        model.addAttribute("skill",skillDTOS);
        return "mypage/member-skill-list";
    }

    @Transactional
    @GetMapping("{memberId}/remove-skill/{skillId}")
    public String removeSkill(@PathVariable long memberId, @PathVariable int skillId, Model model){
        mypageService.removeMemberSkill(memberId,skillId);
        MemberAndRemainSkillEntity skillDTOS = mypageService.printMemberSkillList(memberId);
        model.addAttribute("skill",skillDTOS);
        System.out.println("이전 페이지로 돌아갑니다.");
        return "mypage/member-skill-list";
    }
    @Transactional
    @GetMapping("{memberId}/add-skill/{skillId}")
    public String addSkill(@PathVariable long memberId, @PathVariable int skillId, Model model){
        mypageService.addMemberSkill(memberId,skillId);
        MemberAndRemainSkillEntity skillDTOS = mypageService.printMemberSkillList(memberId);
        model.addAttribute("skill",skillDTOS);
        System.out.println("이전 페이지로 돌아갑니다.");
        return "mypage/member-skill-list";
    }


    /**<h1>Modify Member Recruit Category</h1>*/
    @GetMapping("{memberId}/MemberRecruitCategory")
    public String printMemberRecruitCategory(@PathVariable Long memberId, Model model ) {
        MemberAndRemainRecruitCategoryEntity RecruitCategoryDTOS = mypageService.printMemberRecruitList(memberId);
        model.addAttribute("recruits", RecruitCategoryDTOS);
        System.out.println("recruitcategory 조회 완");
        return "mypage/member-recruit-category-list";
    }

}
