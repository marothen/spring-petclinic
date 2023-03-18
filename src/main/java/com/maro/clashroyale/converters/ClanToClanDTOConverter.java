package com.maro.clashroyale.converters;

import com.maro.clashroyale.controllers.Clan;
import com.maro.clashroyale.dtos.clans.clanlist.ClanDTO;
import com.maro.clashroyale.utils.TagUtil;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ClanToClanDTOConverter implements Converter<Clan, ClanDTO> {
    @Override
    public ClanDTO convert(Clan clan) {
        ClanDTO result = null;
        if(clan != null) {
            result = new ClanDTO();
            result.setMembers(clan.getMembers());
            result.setName(clan.getName());
            result.setTag(clan.getTag());
            result.setDisplayTag(TagUtil.unformatTag(clan.getTag()));
            result.setFame(clan.getFame());
        }
        return result;
    }
}
