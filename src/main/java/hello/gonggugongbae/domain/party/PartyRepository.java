package hello.gonggugongbae.domain.party;

import java.util.List;

public interface PartyRepository {
    Party save(Party party); // 저장
    Party findById(Long partyId); // 조회
    List<Party> findAll();
    void deleteById(Long partyId); // 삭제
    void clearStore();
}
