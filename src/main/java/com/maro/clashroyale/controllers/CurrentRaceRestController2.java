package com.maro.clashroyale.controllers;

import com.maro.clashroyale.Constants;
import com.maro.clashroyale.beans.ClanRankingHolder;
import com.maro.clashroyale.domain.ClanRanking;
import com.maro.clashroyale.domain.Participant;
import com.maro.clashroyale.domain.clan.Clan;
import com.maro.clashroyale.domain.clan.SimpleClan;
import com.maro.clashroyale.domain.currentriverrace.CurrentRiverRace;
import com.maro.clashroyale.domain.currentriverrace.RiverRace;
import com.maro.clashroyale.domain.member.Member;
import com.maro.clashroyale.domain.member.MemberHolder;
import com.maro.clashroyale.domain.riverracelog.RiverRaceLogHolder;
import com.maro.clashroyale.domain.riverracelog.Season;
import com.maro.clashroyale.services.*;
import com.maro.clashroyale.utils.StringUtil;
import com.maro.clashroyale.utils.TagUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
public class CurrentRaceRestController2 {
    private final ClanService clanService;
    private final MemberService memberService;
    private final CurrentRiverRaceService currentRiverRaceService;
    private final RiverRaceLogService riverRaceLogService;
    private final BattleService battleService;
    private final ClanRankingHolder clanRankingHolder;

    public CurrentRaceRestController2(ClanService clanService, MemberService memberService, CurrentRiverRaceService currentRiverRaceService, RiverRaceLogService riverRaceLogService, BattleService battleService, ClanRankingHolder clanRankingHolder) {
        this.clanService = clanService;
        this.memberService = memberService;
        this.currentRiverRaceService = currentRiverRaceService;
        this.riverRaceLogService = riverRaceLogService;
        this.battleService = battleService;
        this.clanRankingHolder = clanRankingHolder;
    }



    @RequestMapping({"ranking"})
    public String show(@RequestParam(required = false) String clanTag, Model model) {
        model.addAttribute("ranking",this.buildClanRanking(clanTag, true));
        return "ranking/index2";
    }
    //@CrossOrigin(origins = "http://localhost:4200")
    //@GetMapping({"currentrace",""})
    public ClanRanking show(@RequestParam(required = false) String clanTag, @RequestParam(required = false) boolean refresh) {
        String usedTag = TagUtil.formatTag(clanTag!=null?clanTag: Constants.CLAN_ID);
        if(refresh || (clanRankingHolder.getRanking()!=null && !usedTag.equals(TagUtil.formatTag(clanRankingHolder.getRanking().getClan().getTag())))) {
            clanRankingHolder.setRanking(null);
        }
        if(clanRankingHolder.getRanking() == null) {
            clanRankingHolder.setRanking(this.buildClanRanking(usedTag, true));
        }
        return clanRankingHolder.getRanking();
    }

    private ClanRanking buildClanRanking(String receivedTag, boolean collectOthers) {
        String tag = TagUtil.formatTag(receivedTag);
        ClanRanking result = new ClanRanking();
        Clan clan = clanService.fetchClanByTag(tag);
        result.setClan(clan);
        if(clan != null) {
            MemberHolder memberHolder = memberService.fetchMemberHolder(tag);
            Map<String, Member> memberMap = memberHolder.getItems()
                    .stream().map(m->{m.setDayNotSeen(m.getLastSeen()!=null?ChronoUnit.DAYS.between(StringUtil.getDate(m.getLastSeen()), LocalDate.now()):-1); m.setLastSeen(m.getLastSeen()!=null?StringUtil.formatDateString(m.getLastSeen()):"unknown");m.setTag(TagUtil.formatTag(m.getTag()));return m;})
                    .collect(Collectors.toMap(Member::getTag, Function.identity()));


            RiverRaceLogHolder riverRaceLogHolder = riverRaceLogService.fetchRiverRaceLogHolder(tag);
            List<Season> sortedSeasons = riverRaceLogHolder.getItems().stream().sorted((s1, s2) -> s2.getCreatedDate().compareTo(s1.getCreatedDate())).collect(Collectors.toList());
            CurrentRiverRace currentRiverRace = currentRiverRaceService.fetchCurrentRiverRace(tag);

			result.getClan().setPeriodPoints(currentRiverRace.getClan().getPeriodPoints());
            if (currentRiverRace != null) {
                Member member;
                for (Participant participant : currentRiverRace.getClan().getParticipants().stream().map(p->{p.setTag(TagUtil.formatTag(p.getTag())); return p;}).collect(Collectors.toList())) {

					member = memberMap.get(participant.getTag());
                    if (member != null) {
                        member.setCurrentPoints(participant.getPoints());
                        member.setParticipant(true);
                        member.setDecksUsed(participant.getDecksUsed());
                        member.setDecksUsedToday(participant.getDecksUsedToday());
                        member.setBoatAttacks(participant.getBoatAttacks());

						result.getClan().setPossibleDecksToday(result.getClan().getPossibleDecksToday()+4);
						result.getClan().setDecksUsedToday(result.getClan().getDecksUsedToday()+member.getDecksUsedToday());
						result.getClan().setBoatAttacks(result.getClan().getBoatAttacks()+member.getBoatAttacks());
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
                        seasonsParticipants.add(seasonClan.getParticipants().stream().map(p->{p.setTag(TagUtil.formatTag(p.getTag())); return p;}).filter(p -> memberMap.containsKey(p.getTag())).collect(Collectors.toList()));
                    }
                }

                List<Integer> points;
                int expecteSize = 0;
                for (List<Participant> singleSeasonParticipants : seasonsParticipants) {
                    for (Participant participant : singleSeasonParticipants) {
                        member = memberMap.get(participant.getTag());
                        if (member != null) {
                            member.getPoints().add(participant.getPoints());
                        }
                    }
                    expecteSize++;
                    for (Member current:memberMap.values()) {
                        if(current.getPoints().size()<expecteSize) {
                            current.getPoints().add(-1);
                        }
                    }
                }
                boolean outOfClan;
                boolean reentered;
                boolean first;
                for (Member current: memberMap.values()) {
                    outOfClan = false;
                    reentered = false;
                    first = true;
                    for (Integer seasonPoints:current.getPoints()) {
                        if(first) {
                            first = false;
                        } else {
                            if (!outOfClan && seasonPoints.intValue() == -1) {
                                outOfClan = true;
                            } else {
                                if(outOfClan && seasonPoints.intValue() != -1) {
                                    reentered = true;
                                }
                            }
                        }
                    }
                    current.setReentered(reentered);
                }


                int sum;
                int count;
                double average;
                for (Map.Entry<String, Member> entry : memberMap.entrySet()) {
                    sum = 0;
                    count = 0;
                    average = 0;
                    entry.getValue().setCountablePoints(entry.getValue().getPoints().stream().filter(p -> p>-1).collect(Collectors.toList()));

						double realPoints = entry.getValue().getCurrentPoints();
						for (Integer p:entry.getValue().getCountablePoints()
							 ) {
							realPoints = realPoints + p.doubleValue();
						}
						entry.getValue().setRealAverage(realPoints/(entry.getValue().getCountablePoints().size()+1));

                    if (entry.getValue().getCountablePoints().size() > Constants.SAVE_WEEK_COUNT) {
                        for (int i = 0; i < Constants.AVTIVE_WEEK_COUNT; i++) {
                            count++;
                            if (!Constants.ACTIVE_WEEK_MARGIN || i < entry.getValue().getCountablePoints().size()) {
                                sum = sum + entry.getValue().getCountablePoints().get(i);
                            }
                        }
                        count++;
                        sum = Math.max(sum + entry.getValue().getCurrentPoints(),0);
                        if (count > 0) {
                            if(sum > 0) {
                                average = sum / count;
                            } else {
                                average = 0;
                            }
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
                    if(current.getRealAverage() != -1) {
                        clanAverageSum = clanAverageSum + current.getRealAverage();
                        averageMemberCount++;
                    }
                }
                result.setClanAverageSum(this.roundToInt(clanAverageSum));
                result.setClanAverage(this.roundToInt(clanAverageSum/averageMemberCount));
                double minimumPoints = this.round(Constants.DYNAMIC_MINIMUM_POINTS?(Math.max(Constants.MINIMUM_POINTS, result.getClanAverage()))*Constants.MINIMUM_POINTS_FACTOR:Constants.MINIMUM_POINTS);
                double upperPoints = this.round(Constants.DYNAMIC_UPPER_POINTS?(Math.max(Constants.MINIMUM_POINTS, result.getClanAverage()) * Constants.UPPER_POINTS_FACTOR):Constants.UPPER_POINTS);
                double downgradePoints = Constants.DOWNGRADE_POINTS;
                result.setMinimumPoints(minimumPoints);
                result.setUpperPoints(upperPoints);
                result.setAllMembers(sortedMembers);
                result.setNewMembers(sortedMembers.stream().filter(m->m.getCountablePoints().isEmpty()).collect(Collectors.toList()));
                result.setUnplayedMembers(sortedMembers.stream().filter(m->m.getCurrentPoints()<=0 && !m.getCountablePoints().isEmpty()).collect(Collectors.toList()));
                result.setKickMembers(sortedMembers.stream().filter(current -> "member".equals(current.getRole()) && ((current.getAverage() != -1 && current.getAverage() < minimumPoints && current.getCurrentPoints() < minimumPoints) || current.getDayNotSeen() >= Constants.MAX_DAYS_NOT_SEEN)).collect(Collectors.toList()));
                //result.setDowngradeMembers(sortedMembers.stream().filter(current -> !"member".equals(current.getRole()) && ((current.getAverage() != -1 && current.getAverage() < minimumPoints && current.getCurrentPoints() < minimumPoints) || current.getDayNotSeen() > 7)).collect(Collectors.toList()));
                result.setDowngradeMembers(sortedMembers.stream().filter(current -> "elder".equals(current.getRole()) && ((current.getAverage() != -1 && current.getAverage() < downgradePoints) || current.getDayNotSeen() > 7)).collect(Collectors.toList()));

                result.setUpgradeMembers(sortedMembers.stream().filter(current -> current.getAverage() != -1 && current.getAverage() >= upperPoints && current.getCountablePoints().size() > Constants.WEEKS_TO_UPGRADE && "member".equals(current.getRole())).collect(Collectors.toList()));

                result.setReenteredMembers(sortedMembers.stream().filter(current -> current.isReentered()).collect(Collectors.toList()));
                result.setAverageMembers(sortedMembers.stream().sorted((m1,m2)->m2.getAverage().compareTo(m1.getAverage())).collect(Collectors.toList()));
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
						current.setFameAsNumber(currentRanking.getClan().getPeriodPoints());
						current.setFame(String.valueOf(current.getFameAsNumber()));
                        current.setClanAverage(currentRanking.getClanAverage());
                        current.setClanAverageSum(currentRanking.getClanAverageSum());
                        current.setMembers(currentRanking.getAllMembers().size());
                        current.setDifference(this.roundToInt(Integer.valueOf(current.getFame())-current.getClanAverageSum()));
                        current.setDifferencePercent(this.round((100/((double)current.getClanAverageSum()))*Integer.valueOf(current.getFame())));

						current.setPossibleDecksToday(currentRanking.getClan().getPossibleDecksToday());
						current.setDecksUsedToday(currentRanking.getClan().getDecksUsedToday());
						current.setBoatAttacks(currentRanking.getClan().getBoatAttacks());
					}
					result.getClans().sort((c1,c2) -> new Integer(c2.getFameAsNumber()).compareTo(c1.getFameAsNumber()));
                    result.setRiverRace(new RiverRace());
                    result.getRiverRace().setState(currentRiverRace.getState());
                    result.getRiverRace().setCollectionEndTime(StringUtil.formatDateString(currentRiverRace.getCollectionEndTime()));
                    result.getRiverRace().setWarEndTime(StringUtil.formatDateString(currentRiverRace.getWarEndTime()));

					DecimalFormat df = new DecimalFormat();
					df.setMaximumFractionDigits(2);

					SimpleClan baseClan = result.getClans().stream().filter(sc -> sc.getTag().equals(this.getModifiedTag(result.getClan().getTag()))).findFirst().orElse(null);

					for (SimpleClan current:result.getClans()) {
						current.setStrength(df.format( (float)current.getClanAverageSum()/(float)baseClan.getClanAverageSum()));
					}
					for (SimpleClan current:result.getClans()) {
						current.setMemberStrength(df.format( (float)current.getClanAverage()/(float)baseClan.getClanAverage()));
					}
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
        clan.setTag(this.getModifiedTag(clan.getTag()));
    }

	private String getModifiedTag(String tag) {
		return Constants.TAG_PREFIX + tag.substring(1);
	}
}
