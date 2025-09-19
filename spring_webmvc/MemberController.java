package com.hulahoopblue.blue.controller;

import com.hulahoopblue.blue.config.AddressConfig;
import com.hulahoopblue.blue.model.dto.MemberDTO;
import com.hulahoopblue.blue.model.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final AddressConfig addressConfig;

    public MemberController(MemberService memberService, AddressConfig addressConfig) {
        this.memberService = memberService;
        this.addressConfig = addressConfig;
    }

    @GetMapping("/MemberInsert")
    public String signupForm(Model model) {
        model.addAttribute("apiKey", addressConfig.getApiKey());
        model.addAttribute("apiUrl", addressConfig.getApiUrl());
        return "member/MemberInsert"; // ✅ 디렉토리 명 포함

    }

    @PostMapping("/MemberInsert")
    public String insertMember(MemberDTO newMember, RedirectAttributes rttr) {
        memberService.insertNewMember(newMember);
        return "redirect:/?success=1";
    }



    @GetMapping("/checkId")
    @ResponseBody
    public String checkDuplicateId(@RequestParam String id) {
        boolean isDuplicate = memberService.isIdDuplicate(id);
        return isDuplicate ? "duplicate" : "available";
    }

    @GetMapping("/MemberSelect")
    public String findMemberList(Model model) {
        List<MemberDTO> memberList = memberService.findAllMember();
        model.addAttribute("memberList", memberList);
        return "member/MemberSelect";
    }

    @GetMapping(value = "/ms", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<MemberDTO> findMemberListJson() {
        return memberService.findAllMember();
    }

    @GetMapping(value = "/Nm", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<MemberDTO> findNmList() {
        return memberService.findAllMember();
    }

    @GetMapping("/mypage")
    public String showMyPage(HttpSession session, Model model) {
        MemberDTO loginMember = (MemberDTO) session.getAttribute("loginMember");
        if (loginMember == null) {
            return "redirect:/login"; // 로그인 안 되어 있으면 로그인 페이지로
        }

        model.addAttribute("loginMember", loginMember); // ✅ 로그인한 사용자 정보 전달
        return "member/mypage";
    }


    @PostMapping("/login")
    public String login(@RequestParam String id, @RequestParam String pw, HttpSession session, RedirectAttributes rttr) {
        MemberDTO loginMember = memberService.login(id, pw); // 로그인 로직
        if (loginMember != null) {
            session.setAttribute("loginMember", loginMember); // ✅ 전체 정보 저장
            return "redirect:/member/mypage";
        } else {
            rttr.addFlashAttribute("error", "로그인 실패");
            return "redirect:/login";
        }
    }

    @GetMapping("/MemberUpdate")
    public void updatePage() {}

    @PostMapping("/MemberUpdate")
//    public String updateMember(MemberDTO newMember,HttpSession session, RedirectAttributes rttr) {
//        memberService.updateNewMember(newMember);
//        session.setAttribute("loginMember", memberService.findById(newMember.getId())); // ✅ 세션 갱신
//        rttr.addFlashAttribute("successMessage", "신규 회원 수정에 성공하였습니다.");
//
//        //return "redirect:/login";
//        return "redirect:/login?successMessage=회원정보가 성공적으로 수정되었습니다.";
//    }

    public Map<String, String> updateMemberAjax(@RequestBody MemberDTO newMember) {
        memberService.updateNewMember(newMember);
        Map<String, String> result = new HashMap<>();
        result.put("redirectUrl", "/login?successMessage=회원정보가 성공적으로 수정되었습니다.");
        return result;
    }




    @GetMapping("/MemberDelete")
    public void deletePage() {}

    @PostMapping("/MemberDelete")
    public String deleteMember(MemberDTO newMember, RedirectAttributes rttr) {
        memberService.deleteNewMember(newMember);
        rttr.addFlashAttribute("successMessage", "신규 회원 삭제에 성공하였습니다.");


        return "redirect:/login";
    }



}

