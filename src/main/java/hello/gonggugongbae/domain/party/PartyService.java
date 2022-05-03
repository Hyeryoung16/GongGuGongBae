package hello.gonggugongbae.domain.party;

import java.util.List;


public interface PartyService {
    Party createParty(Party party); // party 생성

    Party findPartyById(Long partyId); // party 조회
    List<Party> findPartyByMemberId(Long memberId); // member 가 참가한 파티 party 조회
    List<Party> findPartyAroundMember(Long memberId); // member 의 위치를 기반으로 위치 기반 정책에 따른 party 조회
    List<Party> findAllParties(); // TODO : temp

    void participateParty(Long partyId, Long memberId); // party 참가

    boolean deletePartyById(Long partyId); // party 삭제

}
