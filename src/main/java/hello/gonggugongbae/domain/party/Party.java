package hello.gonggugongbae.domain.party;

import hello.gonggugongbae.domain.item.Item;
import hello.gonggugongbae.domain.location.Location;

import java.util.ArrayList;
import java.util.List;

public class Party {

    private Long memberId; // 팟 생성 회원
    private Item item; // 대상물건(음식)
    private int partyMemberNum; // 인원
    private int duration; // 모집기간
    private int minOrderPricePerMember; // 인당 최소 주문 금액
    private Location receiveLocation; // 수령 장소
    private List<Long> partyMembers = new ArrayList<>(); // 팟 참여 멤버

    public Party(Long memberId, Item item, int partyMemberNum, int duration, int minOrderPricePerMember, Location receiveLocation) {
        this.memberId = memberId;
        this.item = item;
        this.partyMemberNum = partyMemberNum;
        this.duration = duration;
        this.minOrderPricePerMember = minOrderPricePerMember;
        this.receiveLocation = receiveLocation;
        this.partyMembers.add(memberId);
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getPartyMemberNum() {
        return partyMemberNum;
    }

    public void setPartyMemberNum(int partyMemberNum) {
        this.partyMemberNum = partyMemberNum;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getMinOrderPricePerMember() {
        return minOrderPricePerMember;
    }

    public void setMinOrderPricePerMember(int minOrderPricePerMember) {
        this.minOrderPricePerMember = minOrderPricePerMember;
    }

    public Location getReceiveLocation() {
        return receiveLocation;
    }

    public void setReceiveLocation(Location receiveLocation) {
        this.receiveLocation = receiveLocation;
    }

    @Override
    public String toString() {
        return "Party{" +
                "memberId=" + memberId +
                ", item=" + item +
                ", partyMemberNum=" + partyMemberNum +
                ", duration=" + duration +
                ", minOrderPricePerMember=" + minOrderPricePerMember +
                ", receiveLocation=" + receiveLocation +
                ", partyMembers=" + partyMembers + // TODO : 이후에 PartyMembers 는 출력 안되도록 제거
                '}';
    }
}
