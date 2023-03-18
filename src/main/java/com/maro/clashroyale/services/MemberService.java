package com.maro.clashroyale.services;

import com.maro.clashroyale.Constants;
import com.maro.clashroyale.domain.member.Member;
import com.maro.clashroyale.domain.member.MemberHolder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class MemberService extends ClashRoyaleService {
    private static final String CLAN_MEMBERS_URL_TAIL = "/clans/%s/members";
    private static final String PLAYER_URL_TAIL = "/players/%s";


    public MemberHolder fetchMemberHolder(String clanTag) {
        ResponseEntity<MemberHolder> responseEntity = new RestTemplate().exchange(this.buildRequestEntity(CLAN_MEMBERS_URL_TAIL, clanTag), new ParameterizedTypeReference<MemberHolder>() {});
        return responseEntity.getBody();
    }

    public Member fetchMember(String memberTag) {
        ResponseEntity<Member> responseEntity = new RestTemplate().exchange(this.buildRequestEntity(PLAYER_URL_TAIL, memberTag), new ParameterizedTypeReference<Member>() {});
        return responseEntity.getBody();
    }
}
