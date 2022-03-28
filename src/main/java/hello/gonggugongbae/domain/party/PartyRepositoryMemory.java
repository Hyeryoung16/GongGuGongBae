package hello.gonggugongbae.domain.party;

import hello.gonggugongbae.domain.location.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PartyRepositoryMemory implements PartyRepository{

    private static Map<Long, Party> store = new HashMap<>(); // 파티 저장소
    private static long sequence = 0L;

    @Override
    public Party save(Party party) {
        party.setPartyId(++sequence);
        store.put(party.getPartyId(), party);
        return party;
    }

    @Override
    public Party findById(Long partyId) {
        return store.get(partyId);
    }

    @Override
    public List<Party> findAll(){
        return new ArrayList<>(store.values());
    }

    @Override
    public void deleteById(Long partyId){
        store.remove(partyId);
    }
    @Override
    public void clearStore(){
        store.clear();
    }
}
