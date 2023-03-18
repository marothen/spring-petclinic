package com.maro.clashroyale.services;

import com.maro.clashroyale.Constants;
import com.maro.clashroyale.domain.clan.SimpleClan;
import com.maro.clashroyale.domain.currentriverrace.CurrentRiverRace;
import com.maro.clashroyale.domain.riverracelog.RiverRaceLogHolder;
import com.maro.clashroyale.utils.StringUtil;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrentRiverRaceService extends ClashRoyaleService {
    private static final String URL_TAIL = "/clans/%s/currentriverrace";

    public CurrentRiverRace fetchCurrentRiverRace(String clanTag) {
        CurrentRiverRace result = null;
        try {
            ResponseEntity<CurrentRiverRace> responseEntity = new RestTemplate().exchange(this.buildRequestEntity(URL_TAIL, clanTag), new ParameterizedTypeReference<CurrentRiverRace>() {});
            result = responseEntity.getBody();
        } catch (HttpClientErrorException.NotFound e) {
            //no current riverrace
        }
        if(result != null) {
            for (SimpleClan clan : result.getClans()) {
                clan.setFinishTime(StringUtil.formatDateString(clan.getFinishTime()));
            }
        }
        return result;
    }
}
