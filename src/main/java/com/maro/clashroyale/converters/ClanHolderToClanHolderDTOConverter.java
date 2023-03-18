package com.maro.clashroyale.converters;

import com.maro.clashroyale.controllers.Clan;
import com.maro.clashroyale.controllers.ClanHolder;
import com.maro.clashroyale.dtos.clans.clanlist.ClansHolderDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ClanHolderToClanHolderDTOConverter implements Converter<ClanHolder, ClansHolderDTO> {
    private final ClanToClanDTOConverter clanToClanDTOConverter;

    public ClanHolderToClanHolderDTOConverter(ClanToClanDTOConverter clanToClanDTOConverter) {
        this.clanToClanDTOConverter = clanToClanDTOConverter;
    }

    @Override
    public ClansHolderDTO convert(ClanHolder clanHolder) {
        ClansHolderDTO result = null;
        if(clanHolder != null) {
            result = new ClansHolderDTO();
            for (Clan clan:clanHolder.getClans()) {
                result.getClans().add(clanToClanDTOConverter.convert(clan));
            }
        }
        return result;
    }
}
