package com.maro.clashroyale.services;

import com.maro.clashroyale.domain.clan.Clan;
import com.maro.clashroyale.domain.clan.SimpleClan;
import com.maro.clashroyale.domain.clan.SimpleClanHolder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ClanService extends ClashRoyaleService {
    private static final String CLAN_BY_TAG_URL_TAIL = "/clans/%s";
    private static final String CLANS_BY_NAME_URL_TAIL = "/clans?name=%s";


    public Clan fetchClanByTag(String clanTag) {
        ResponseEntity<Clan> responseEntity = new RestTemplate().exchange(this.buildRequestEntity(CLAN_BY_TAG_URL_TAIL, clanTag), new ParameterizedTypeReference<Clan>() {});
        return responseEntity.getBody();
    }

    public SimpleClanHolder fetchClansByName(String clanName) {
        ResponseEntity<SimpleClanHolder> responseEntity = new RestTemplate().exchange(this.buildRequestEntity(CLANS_BY_NAME_URL_TAIL, clanName), new ParameterizedTypeReference<SimpleClanHolder>() {});
        return responseEntity.getBody();
    }


    public SimpleClanHolder fetchSimpleClanByTag(String clanTag) {
        ResponseEntity<SimpleClan> responseEntity = new RestTemplate().exchange(this.buildRequestEntity(CLAN_BY_TAG_URL_TAIL, clanTag), new ParameterizedTypeReference<SimpleClan>() {});
        SimpleClanHolder result = new SimpleClanHolder();
        result.getItems().add(responseEntity.getBody());
        return result;
    }
}

