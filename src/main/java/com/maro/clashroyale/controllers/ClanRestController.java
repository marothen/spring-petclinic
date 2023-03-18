package com.maro.clashroyale.controllers;

import com.maro.clashroyale.converters.ClanHolderToClanHolderDTOConverter;
import com.maro.clashroyale.domain.clan.SimpleClanHolder;
import com.maro.clashroyale.domain.currentriverrace.CurrentRiverRace;
import com.maro.clashroyale.dtos.clans.clanlist.ClansHolderDTO;
import com.maro.clashroyale.services.ClanService;
import com.maro.clashroyale.services.CurrentRiverRaceService;
import com.maro.clashroyale.utils.TagUtil;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
public class ClanRestController {
    private final ClanService clanService;
    private final CurrentRiverRaceService currentRiverRaceService;
    private final ClanHolderToClanHolderDTOConverter clanHolderToClanHolderDTOConverter;

    public ClanRestController(ClanService clanService, CurrentRiverRaceService currentRiverRaceService, ClanHolderToClanHolderDTOConverter clanHolderToClanHolderDTOConverter) {
        this.clanService = clanService;
        this.currentRiverRaceService = currentRiverRaceService;
        this.clanHolderToClanHolderDTOConverter = clanHolderToClanHolderDTOConverter;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/clans")
    public ClansHolderDTO searchClans(@RequestParam(required = false) String searchName,@RequestParam(required = false) String clanTag, @RequestParam(required = false) boolean searchFame) {
        SimpleClanHolder holder = null;
        if("null".equals(clanTag)) {
            holder = clanService.fetchClansByName(searchName);
        } else {
            holder = clanService.fetchSimpleClanByTag(TagUtil.formatTag(clanTag));
        }
        holder.setItems(holder.getItems().stream().map(c->{c.setTag(TagUtil.formatTag(c.getTag()));return c;}).sorted((c1, c2)->Integer.valueOf(c2.getMembers()).compareTo(Integer.valueOf(c1.getMembers()))).collect(Collectors.toList()));
        holder.setSearchName(searchName);
        ClanHolder ch = new ClanHolder();
        ch.setClans(holder.getItems().stream().map(sc->{Clan c = new Clan(); c.setName(sc.getName()); c.setMembers(sc.getMembers()); c.setTag(sc.getTag());return c;}).collect(Collectors.toList()));
        if(searchFame) {
            this.fetchCurrentRiverRaces(ch);
        }
        return clanHolderToClanHolderDTOConverter.convert(ch);
    }

    private void fetchCurrentRiverRaces(ClanHolder ch) {
        CurrentRiverRace race;
        long fame;
        for (Clan clan:ch.getClans()) {
            race = currentRiverRaceService.fetchCurrentRiverRace(clan.getTag());
            if(race != null) {
                fame = race.getClan().getFame();
            } else {
                fame = -1;
            }
            clan.setFame(fame);
        }
        ch.setClans(ch.getClans().stream().sorted((c1, c2)->Long.valueOf(c2.getFame()).compareTo(Long.valueOf(c1.getFame()))).collect(Collectors.toList()));
    }
}
