package com.maro.clashroyale.controllers;

import com.maro.clashroyale.domain.battle.Battle;
import com.maro.clashroyale.domain.battle.BattleHolder;
import com.maro.clashroyale.domain.member.Member;
import com.maro.clashroyale.dtos.member.BattleDTO;
import com.maro.clashroyale.dtos.member.MemberDTO;
import com.maro.clashroyale.services.BattleService;
import com.maro.clashroyale.services.MemberService;
import com.maro.clashroyale.utils.StringUtil;
import com.maro.clashroyale.utils.TagUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MemberController {

    private final MemberService memberService;
    private final BattleService battleService;

    public MemberController(MemberService memberService, BattleService battleService) {
        this.memberService = memberService;
        this.battleService = battleService;
    }

    @GetMapping("player")
    public String fetchMember(@RequestParam(required = false) String memberTag, Model model) {
        String formatedTag = TagUtil.formatTag(memberTag);
        Member member = memberService.fetchMember(formatedTag);
        BattleHolder battleHolder = battleService.fetchBattlesByPlayerTag(formatedTag);
        MemberDTO result = new MemberDTO();
        result.setName(member.getName());
        result.setExpLevel(member.getExpLevel());
        BattleDTO battle;
        for (Battle current:battleHolder.getItems()) {
            battle = new BattleDTO();
            battle.setType(current.getType());
            battle.setTime(StringUtil.formatDateString(current.getBattleTime()));
            result.getBattles().add(battle);
        }
        model.addAttribute("member", result);
        return "members/index";
    }
}
