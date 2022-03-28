package hello.gonggugongbae.domain.party;

import hello.gonggugongbae.domain.item.Item;
import hello.gonggugongbae.domain.location.Location;
import hello.gonggugongbae.domain.location.MyLocation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PartyRepositoryTest {

    PartyRepository partyRepository = new PartyRepositoryMemory();

    @AfterEach
    void afterEach(){
        partyRepository.clearStore();
    }

    @Test
    @DisplayName("partyRepository party 저장 테스트")
    void save(){
        // given
        Item item = new Item("hello", "www.hello.com", 10000, 2000);
        Location location = new Location(MyLocation.GYM_LAT, MyLocation.GYM_LON);
        Party party = new Party(1L, item, 3, 2,
                10000, location);

        // when
        Party savedParty = partyRepository.save(party);

        // then
        Party findParty = partyRepository.findById(party.getPartyId());
        assertThat(findParty).isEqualTo(savedParty);
    }

    @Test
    @DisplayName("partyRepository 모든 party 찾기 테스트")
    void findALl(){
        // given 
        Item item1 = new Item("hello", "www.hello.com", 10000, 2000);
        Location location1 = new Location(MyLocation.GYM_LAT, MyLocation.GYM_LON);
        Party party1 = new Party(1L, item1, 3, 2,
                10000, location1);

        Item item2 = new Item("hello2", "www.hello2.com", 0, 3000);
        Location location2 = new Location(MyLocation.GYM_LAT, MyLocation.GYM_LON);
        Party party2 = new Party(1L, item2, 2, 24,
                1000, location2);
        
        partyRepository.save(party1);
        partyRepository.save(party2);
        
        // when
        List<Party> result = partyRepository.findAll();
        
        // then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(party1, party2);
    }
}