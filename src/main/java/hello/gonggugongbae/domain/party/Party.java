package hello.gonggugongbae.domain.party;

import hello.gonggugongbae.domain.item.Item;
import hello.gonggugongbae.domain.location.Location;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Party {

    private Long partyId;
    private Long memberId; // 팟 생성 회원
    private Item item; // 대상물건(음식)
    private Integer partyMemberNum; // 인원
    // TODO : 팟 생성 시간
    private Integer duration; // 모집기간
    private Integer minOrderPricePerMember; // 인당 최소 주문 금액
    private Location receiveLocation; // 수령 장소
    private List<Long> partyMembers = new ArrayList<>(); // 팟 참여 멤버

    public Party(){}

    public Party(Long memberId, Item item, Integer partyMemberNum, Integer duration, Integer minOrderPricePerMember, Location receiveLocation) {
        this.memberId = memberId;
        this.item = item;
        this.partyMemberNum = partyMemberNum;
        this.duration = duration;
        this.minOrderPricePerMember = minOrderPricePerMember;
        this.receiveLocation = receiveLocation;
        this.partyMembers.add(memberId);
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
