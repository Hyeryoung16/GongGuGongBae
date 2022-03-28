package hello.gonggugongbae.domain.party;

import hello.gonggugongbae.domain.item.Item;
import hello.gonggugongbae.domain.location.Location;
import hello.gonggugongbae.domain.member.MemberRepository;
import hello.gonggugongbae.domain.member.MemberRepositoryMemory;

import java.util.List;

public class PartyServiceImpl implements PartyService{

    private final PartyRepository partyRepository = new PartyRepositoryMemory(); // 파티 저장소

    @Override
    public Party createParty(Long memberId, Item item, int partyMemberNum, int duration, int minOrderPricePerMember, Location location) {
        return null;
    }

    @Override
    public List<Party> findPartyAroundMember(Long memberId) {
        return null; // TODO
    }

    @Override
    public void updateParty(Long partyId) {
        // TODO
    }

    @Override
    public void deleteParty(Long partyId) {
        // TODO
    }
}
