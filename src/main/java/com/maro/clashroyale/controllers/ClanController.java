package com.maro.clashroyale.controllers;

import com.maro.clashroyale.converters.ClanHolderToClanHolderDTOConverter;
import com.maro.clashroyale.domain.clan.SimpleClanHolder;
import com.maro.clashroyale.dtos.clans.clanlist.ClansHolderDTO;
import com.maro.clashroyale.services.ClanService;
import com.maro.clashroyale.services.CurrentRiverRaceService;
import com.maro.clashroyale.utils.TagUtil;
import com.maro.clashroyale.utils.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.stream.Collectors;

@Controller
public class ClanController {
    private final ClanService clanService;
    private final CurrentRiverRaceService currentRiverRaceService;
    private final ClanHolderToClanHolderDTOConverter clanHolderToClanHolderDTOConverter;

    public ClanController(ClanService clanService, CurrentRiverRaceService currentRiverRaceService, ClanHolderToClanHolderDTOConverter clanHolderToClanHolderDTOConverter) {
        this.clanService = clanService;
        this.currentRiverRaceService = currentRiverRaceService;
        this.clanHolderToClanHolderDTOConverter = clanHolderToClanHolderDTOConverter;
    }
    @RequestMapping({"", "/","/search"})
    public String searchClans(@RequestParam(required = false) String searchName, @RequestParam(required = false) String crr, @RequestParam(required = false) String clanTag, @RequestParam(required = false) boolean searchFame, Model model) {
        if(searchName != null && searchName.length() >= 3) {
			SimpleClanHolder holder = null;
			if (clanTag == null) {
				holder = clanService.fetchClansByName(searchName);
			} else {
				holder = clanService.fetchSimpleClanByTag(TagUtil.formatTag(clanTag));
			}
			holder.setItems(holder.getItems().stream().map(c -> {
				c.setTag(TagUtil.formatTag(c.getTag()));
				return c;
			}).sorted((c1, c2) -> Integer.valueOf(c2.getMembers()).compareTo(Integer.valueOf(c1.getMembers()))).collect(Collectors.toList()));
			holder.setSearchName(searchName);
			ClanHolder ch = new ClanHolder();
			ch.setClans(holder.getItems().stream().map(sc -> {
				Clan c = new Clan();
				c.setName(sc.getName());
				c.setMembers(sc.getMembers());
				c.setTag(sc.getTag());
				return c;
			}).collect(Collectors.toList()));
			//if(searchFame) {
			//this.fetchCurrentRiverRaces(ch);
			//}
			ClansHolderDTO dto = clanHolderToClanHolderDTOConverter.convert(ch);


			boolean usedCrr = !StringUtil.isNullOrEmpty(crr);
			//SimpleClanHolder holder = clanService.fetchClansByName(searchName);
			//holder.setItems(holder.getItems().stream().map(c->{c.setTag(TagUtil.formatTag(c.getTag()));return c;}).filter(c->!usedCrr || currentRiverRaceService.fetchCurrentRiverRace(c.getTag())!=null).sorted((c1, c2)->Integer.valueOf(c2.getMembers()).compareTo(Integer.valueOf(c1.getMembers()))).collect(Collectors.toList()));
			//holder.setSearchName(searchName);
			holder.setSearchActiveClans(usedCrr);

			model.addAttribute("clanHolder",dto);
		} else {
			model.addAttribute("clanHolder",new ClansHolderDTO());
		}
        return "clans/index";
    }
}
