package hello.gonggugongbae.domain.party;

import hello.gonggugongbae.domain.item.Item;
import hello.gonggugongbae.domain.location.Location;

import java.util.List;

public interface PartyService {
    Party createParty(Long memberId, Item item, int partyMemberNum, int duration, int minOrderPricePerMember, Location location); // party 생성
    List<Party> findPartyAroundMember(Long memberId); // member 의 위치를 기반으로 위치 기반 정책에 따른 party 조회
    void updateParty(Long partyId); // party 수정
    void deleteParty(Long partyId); // party 삭제
}
