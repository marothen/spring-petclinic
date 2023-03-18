package com.maro.clashroyale.services;

import com.maro.clashroyale.domain.battle.Battle;
import com.maro.clashroyale.domain.battle.BattleHolder;
import com.maro.clashroyale.domain.clan.Clan;
import com.maro.clashroyale.domain.clan.SimpleClanHolder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class BattleService extends ClashRoyaleService {
    private static final String BATTLELOG_BY_PLAYER_TAG_URL_TAIL = "/players/%s/battlelog";


    public BattleHolder fetchBattlesByPlayerTag(String playerTag) {
        ResponseEntity<List<Battle>> responseEntity = new RestTemplate().exchange(this.buildRequestEntity(BATTLELOG_BY_PLAYER_TAG_URL_TAIL, playerTag), new ParameterizedTypeReference<List<Battle>>() {});
        BattleHolder result = new BattleHolder();
        result.setItems(responseEntity.getBody());
        return result;
    }
}

