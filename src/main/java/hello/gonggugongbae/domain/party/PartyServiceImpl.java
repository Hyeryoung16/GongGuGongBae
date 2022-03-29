package hello.gonggugongbae.domain.party;

import hello.gonggugongbae.domain.location.Location;
import hello.gonggugongbae.domain.location.LocationPolicy;
import hello.gonggugongbae.domain.location.LocationPolicyImpl;
import hello.gonggugongbae.domain.member.Member;
import hello.gonggugongbae.domain.member.MemberRepository;
import hello.gonggugongbae.domain.member.MemberRepositoryMemory;

import java.util.ArrayList;
import java.util.List;

public class PartyServiceImpl implements PartyService{

    // private final MemberRepository memberRepository = new MemberRepositoryMemory(); // 멤버 저장소
    // private final PartyRepository partyRepository = new PartyRepositoryMemory(); // 파티 저장소
    // private final LocationPolicy locationPolicy = new LocationPolicyImpl(); // 위치 정책

    private final MemberRepository memberRepository;
    private final PartyRepository partyRepository;
    private final LocationPolicy locationPolicy;

    public PartyServiceImpl(MemberRepository memberRepository, PartyRepository partyRepository, LocationPolicy locationPolicy) {
        this.memberRepository = memberRepository;
        this.partyRepository = partyRepository;
        this.locationPolicy = locationPolicy;
    }
    @Override
    public Party createParty(Party party) {
        Member host = memberRepository.findById(party.getMemberId()); // 팟 호스트
        Location hostAddress = host.getAddress(); // 팟 호스트의 주소
        boolean nearLocation = locationPolicy.isNearLocation(hostAddress, party.getReceiveLocation()); // 팟 열수 있는 위치인지 여부
        if (nearLocation) {
            Party savedParty = partyRepository.save(party);
            host.addParty(savedParty.getPartyId());
            return party;
        }
        else {
            return null; // TODO : 불가할 경우 에러 발생시키도록
        }
    }

    @Override
    public Party findPartyById(Long partyId) {
        return partyRepository.findById(partyId);
    }

    @Override
    public List<Party> findPartyAroundMember(Long memberId) {
        Member member = memberRepository.findById(memberId);
        List<Party> parties = partyRepository.findAll();
        List<Party> nearParties = new ArrayList<>();
        parties.forEach(party -> {
            if ( locationPolicy.isNearLocation(member.getAddress(), party.getReceiveLocation()) ) {
                nearParties.add(party);
            }
        });
        return nearParties; // TODO : null 일 경우 처리 방식
    }

    @Override
    public boolean deletePartyById(Long memberId, Long partyId){
        if( findPartyById(partyId).getMemberId() == memberId) {
            partyRepository.deleteById(partyId);
            return true;
        }
        else {
            return false; // TODO : 삭제 실패 했을 때 에러 처리
        }
    }
}
