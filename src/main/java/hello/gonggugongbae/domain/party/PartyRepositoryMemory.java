package hello.gonggugongbae.domain.party;

import hello.gonggugongbae.domain.location.Location;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PartyRepositoryMemory implements PartyRepository{

    private static Map<Long, Party> store = new HashMap<>(); // 파티 저장소

    @Override
    public void save(Party party) {
        store.put(party.getPartyId(), party);
    }

    @Override
    public Party findById(Long partyId) {
        return store.get(partyId);
    }

    @Override
    public List<Party> findByLocation(Location location) {
        return null; // TODO
    }
}
