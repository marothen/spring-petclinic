package com.maro.clashroyale.services;

import com.maro.clashroyale.Constants;
import com.maro.clashroyale.domain.member.MemberHolder;
import com.maro.clashroyale.domain.riverracelog.RiverRaceLogHolder;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class RiverRaceLogService extends ClashRoyaleService {
    private static final String URL_TAIL = "/clans/%s/riverracelog";

    public RiverRaceLogHolder fetchRiverRaceLogHolder(String clanTag) {
        ResponseEntity<RiverRaceLogHolder> responseEntity = new RestTemplate().exchange(this.buildRequestEntity(URL_TAIL, clanTag), new ParameterizedTypeReference<RiverRaceLogHolder>() {});
        return responseEntity.getBody();
    }
}
