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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberRestController {

    private final MemberService memberService;
    private final BattleService battleService;

    public MemberRestController(MemberService memberService, BattleService battleService) {
        this.memberService = memberService;
        this.battleService = battleService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("member1")
    public MemberDTO fetchMember(@RequestParam(required = false) String memberTag) {
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
        return result;
    }
}
