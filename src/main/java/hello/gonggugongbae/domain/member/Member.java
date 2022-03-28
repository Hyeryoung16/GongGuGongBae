package hello.gonggugongbae.domain.member;

import hello.gonggugongbae.domain.location.Location;

import java.util.ArrayList;
import java.util.List;

public class Member {
    private Long id;
    private String username;
    private Location address;
    private List<Long> parties = new ArrayList<>();

    public Member(String username, Location address) {
        this.username = username;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Location getAddress() {
        return address;
    }

    public void setAddress(Location address) {
        this.address = address;
    }

    public List<Long> getParties() {
        return parties;
    }

    public void setParties(List<Long> parties) {
        this.parties = parties;
    }

    public void addParty(Long partyId) {
        parties.add(partyId);
    }
}
