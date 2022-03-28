package hello.gonggugongbae.domain.party;

import hello.gonggugongbae.domain.location.Location;

import java.util.List;

public interface PartyRepository {
    void save(Party party); // 저장
    Party findById(Long partyId); // 조회
    List<Party> findByLocation(Location location); // 조회
}
