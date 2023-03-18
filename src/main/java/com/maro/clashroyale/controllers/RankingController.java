package com.maro.clashroyale.controllers;

import com.maro.clashroyale.Constants;
import com.maro.clashroyale.domain.ClanRanking;
import com.maro.clashroyale.domain.Participant;
import com.maro.clashroyale.domain.clan.Clan;
import com.maro.clashroyale.domain.currentriverrace.CurrentRiverRace;
import com.maro.clashroyale.domain.clan.SimpleClan;
import com.maro.clashroyale.domain.currentriverrace.RiverRace;
import com.maro.clashroyale.domain.member.Member;
import com.maro.clashroyale.domain.member.MemberHolder;
import com.maro.clashroyale.domain.riverracelog.RiverRaceLogHolder;
import com.maro.clashroyale.domain.riverracelog.Season;
import com.maro.clashroyale.services.*;
import com.maro.clashroyale.utils.TagUtil;
import com.maro.clashroyale.utils.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
public class RankingController {
    private final ClanService clanService;
    private final MemberService memberService;
    private final CurrentRiverRaceService currentRiverRaceService;
    private final RiverRaceLogService riverRaceLogService;
    private final BattleService battleService;

    public RankingController(ClanService clanService, MemberService memberService, CurrentRiverRaceService currentRiverRaceService, RiverRaceLogService riverRaceLogService, BattleService battleService) {
        this.clanService = clanService;
        this.memberService = memberService;
        this.currentRiverRaceService = currentRiverRaceService;
        this.riverRaceLogService = riverRaceLogService;
        this.battleService = battleService;
    }

    //@RequestMapping({"","/","ranking"})
    public String show(@RequestParam(required = false) String clanTag, Model model) {
        model.addAttribute("ranking",this.buildClanRanking(clanTag, true));
        return "ranking/index";
    }

    private ClanRanking buildClanRanking(String receivedTag, boolean collectOthers) {
        String tag = TagUtil.formatTag(receivedTag);
        ClanRanking result = new ClanRanking();
        Clan clan = clanService.fetchClanByTag(tag);
        result.setClan(clan);
        if(clan != null) {
            MemberHolder memberHolder = memberService.fetchMemberHolder(tag);
            Map<String, Member> memberMap = memberHolder.getItems()
                    .stream().map(m->{m.setDayNotSeen(ChronoUnit.DAYS.between(StringUtil.getDate(m.getLastSeen()), LocalDate.now())); m.setLastSeen(StringUtil.formatDateString(m.getLastSeen())); return m;})
                    .collect(Collectors.toMap(Member::getTag, Function.identity()));

            //Member member1 = memberMap.values().stream().filter(m->"Antherabae".equals(m.getName())).collect(Collectors.toList()).get(0);
            //BattleHolder holder = battleService.fetchBattlesByPlayerTag(TagUtil.formatClanTag(member1.getTag()));


            RiverRaceLogHolder riverRaceLogHolder = riverRaceLogService.fetchRiverRaceLogHolder(tag);
            List<Season> sortedSeasons = riverRaceLogHolder.getItems().stream().sorted((s1, s2) -> s2.getCreatedDate().compareTo(s1.getCreatedDate())).collect(Collectors.toList());
            CurrentRiverRace currentRiverRace = currentRiverRaceService.fetchCurrentRiverRace(tag);
            if (currentRiverRace != null) {
                Member member;
                for (Participant participant : currentRiverRace.getClan().getParticipants()) {
                    member = memberMap.get(participant.getTag());
                    if (member != null) {
                        member.setCurrentPoints(participant.getPoints());
                        member.setParticipant(true);
                    }
                }
                for (Member current:
                     memberMap.values()) {
                    if(!current.isParticipant()) {
                        current.setCurrentPoints(-1);
                    }
                }

                String clanTag = currentRiverRace.getClan().getTag();

                List<List<Participant>> seasonsParticipants = new ArrayList<>();
                //seasonsParticipants.add(currentRiverRace.getClan().getParticipants().stream().filter(p -> memberMap.containsKey(p.getTag())).collect(Collectors.toList()));
                long fame = currentRiverRace.getClan().getFame();
                Clan seasonClan;
                for (Season season : sortedSeasons) {
                    seasonClan = season.getStandings().stream().filter(s -> s.getClan().getTag().equals(clanTag)).findFirst().orElse(null).getClan();
                    if(Constants.IGNORE_DIFFERENT_RACE_MODES || (fame != 35000 || seasonClan.getFame() == 35000)) {
                        seasonsParticipants.add(seasonClan.getParticipants().stream().filter(p -> memberMap.containsKey(p.getTag())).collect(Collectors.toList()));
                    }
                }
                List<Integer> points;
                for (List<Participant> singleSeasonParticipants : seasonsParticipants) {
                    for (Participant participant : singleSeasonParticipants) {
                        member = memberMap.get(participant.getTag());
                        if (member != null) {
                            member.getPoints().add(participant.getPoints());
                        }
                    }
                }


                int sum;
                int count;
                double average;
                for (Map.Entry<String, Member> entry : memberMap.entrySet()) {
                    sum = 0;
                    count = 0;
                    average = 0;
                    if (entry.getValue().getPoints().size() > Constants.SAVE_WEEK_COUNT) {
                        for (int i = 0; i < entry.getValue().getPoints().size(); i++) {
                            if ((!Constants.ACTIVE_WEEK_MARGIN || i <= Constants.AVTIVE_WEEK_COUNT) && (i < entry.getValue().getPoints().size() - 1 || entry.getValue().getPoints().get(i) >= Constants.MINIMUM_POINTS)) {
                                count++;
                                sum = sum + entry.getValue().getPoints().get(i);
                            }
                        }
                        if (count > 0) {
                            average = sum / count;
                        }
                    } else {
                        average = -1;
                    }
                    entry.getValue().setAverage(average);
                }
                Member currentMember;
                List<Member> sortedMembers = memberMap.values().stream().sorted((m1, m2) -> m1.getName().toLowerCase().compareTo(m2.getName().toLowerCase())).collect(Collectors.toList());
                for (int i = 0; i < sortedMembers.size(); i++) {
                    currentMember = sortedMembers.get(i);
                    currentMember.setRanking(i + 1);
                    if(currentMember.getAverage() != -1) {
                        currentMember.setDifference(this.round(currentMember.getCurrentPoints() - currentMember.getAverage()));
                        if(currentMember.getAverage() > 0) {
                            currentMember.setPercent(this.round((100 / currentMember.getAverage()) * currentMember.getCurrentPoints()));
                        }
                    }
                }
                int averageMemberCount = 0;
                double clanAverageSum = 0;
                for (Member current:sortedMembers ) {
                    if(current.getAverage() != -1) {
                        clanAverageSum = clanAverageSum + current.getAverage();
                        averageMemberCount++;
                    }
                }
                result.setClanAverageSum(this.roundToInt(clanAverageSum));
                result.setClanAverage(this.roundToInt(clanAverageSum/averageMemberCount));
                double minimumPoints = this.round(Constants.DYNAMIC_MINIMUM_POINTS?(Math.max(Constants.MINIMUM_POINTS, result.getClanAverage()))*Constants.MINIMUM_POINTS_FACTOR:Constants.MINIMUM_POINTS);
                double upperPoints = this.round(Constants.DYNAMIC_UPPER_POINTS?(Math.max(Constants.MINIMUM_POINTS, result.getClanAverage()) * Constants.UPPER_POINTS_FACTOR):Constants.UPPER_POINTS);
                result.setMinimumPoints(minimumPoints);
                result.setUpperPoints(upperPoints);
                result.setAllMembers(sortedMembers);
                result.setNewMembers(sortedMembers.stream().filter(m->m.getPoints().isEmpty()).collect(Collectors.toList()));
                result.setUnplayedMembers(sortedMembers.stream().filter(m->m.getCurrentPoints()<=0 && !m.getPoints().isEmpty()).collect(Collectors.toList()));
                result.setKickMembers(sortedMembers.stream().filter(current -> "member".equals(current.getRole()) && ((current.getAverage() != -1 && current.getAverage() < minimumPoints && current.getCurrentPoints() < minimumPoints) || current.getDayNotSeen() >= Constants.MAX_DAYS_NOT_SEEN)).collect(Collectors.toList()));
                result.setDowngradeMembers(sortedMembers.stream().filter(current -> !"member".equals(current.getRole()) && ((current.getAverage() != -1 && current.getAverage() < minimumPoints && current.getCurrentPoints() < minimumPoints) || current.getDayNotSeen() > 7)).collect(Collectors.toList()));
                result.setUpgradeMembers(sortedMembers.stream().filter(current -> current.getAverage() != -1 && current.getCurrentPoints() >= minimumPoints && current.getAverage() >= upperPoints && current.getPoints().size() > Constants.WEEKS_TO_UPGRADE && "member".equals(current.getRole())).collect(Collectors.toList()));
                result.setClans(currentRiverRace.getClans().stream().sorted((c1, c2) -> Integer.valueOf(c2.getFame()).compareTo(Integer.valueOf(c1.getFame()))).map(c->{this.modifyTag(c); return c;}).collect(Collectors.toList()));


                if(collectOthers) {
                    Map<String,ClanRanking> otherClanRankings = this.collectOtherClanRankings(result);
                    ClanRanking currentRanking;
                    for (SimpleClan current:result.getClans()) {
                        if(current.getTag().equals(clanTag)) {
                           currentRanking = result;
                        } else {
                            currentRanking = otherClanRankings.get(current.getTag());
                        }
                        current.setClanAverage(currentRanking.getClanAverage());
                        current.setClanAverageSum(currentRanking.getClanAverageSum());
                        current.setMembers(currentRanking.getAllMembers().size());
                        current.setDifference(this.roundToInt(Integer.valueOf(current.getFame())-current.getClanAverageSum()));
                        current.setDifferencePercent(this.round((100/((double)current.getClanAverageSum()))*Integer.valueOf(current.getFame())));
                    }
                    result.setRiverRace(new RiverRace());
                    result.getRiverRace().setState(currentRiverRace.getState());
                    result.getRiverRace().setCollectionEndTime(StringUtil.formatDateString(currentRiverRace.getCollectionEndTime()));
                    result.getRiverRace().setWarEndTime(StringUtil.formatDateString(currentRiverRace.getWarEndTime()));
                }
            } else {
                RiverRace race = new RiverRace();
                race.setState("no race available");
                result.setAllMembers(memberMap.values().stream().collect(Collectors.toList()));
                result.setRiverRace(race);
            }
        }
    return result;
    }

    private double round(double d) {
        return Math.round(d*100)/100;
    }

    private int roundToInt(double d) {
        return (int)round(d);
    }

    private Map<String,ClanRanking> collectOtherClanRankings(ClanRanking ranking) {
        return ranking.getClans().stream().filter(r->!r.getTag().equals(ranking.getClan().getTag())).map(r->this.buildClanRanking(r.getTag(), false)).collect(Collectors.toMap(r-> TagUtil.formatTag(r.getClan().getTag()),Function.identity()));
    }

    private void modifyTag(SimpleClan clan) {
        clan.setTag(Constants.TAG_PREFIX + clan.getTag().substring(1));
    }
}
