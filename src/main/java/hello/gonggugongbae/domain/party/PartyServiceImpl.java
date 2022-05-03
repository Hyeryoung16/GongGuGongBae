package hello.gonggugongbae.domain.party;

import hello.gonggugongbae.domain.location.Location;
import hello.gonggugongbae.domain.location.LocationPolicy;
import hello.gonggugongbae.domain.location.LocationPolicyImpl;
import hello.gonggugongbae.domain.member.Member;
import hello.gonggugongbae.domain.member.MemberRepository;
import hello.gonggugongbae.domain.member.MemberRepositoryMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PartyServiceImpl implements PartyService{

    private final MemberRepository memberRepository;
    private final PartyRepository partyRepository;
    private final LocationPolicy locationPolicy;

    @Autowired
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
    public List<Party> findPartyByMemberId(Long memberId){
        List<Party> parties = new ArrayList<>();
        Member member = memberRepository.findById(memberId);
        member.getParties().forEach(partyId -> parties.add(partyRepository.findById(partyId)));
        return parties;
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
    public void participateParty(Long partyId, Long memberId) {
        Party party = partyRepository.findById(partyId);
        party.getPartyMembers().add(memberId); // 팟에 멤버 추가

        Member member = memberRepository.findById(memberId);
        member.getParties().add(partyId); // 멤버에 참가 팟 추가
    }

    @Override
    public boolean deletePartyById(Long partyId){
        /*
        if( findPartyById(partyId).getMemberId() == memberId) {
            partyRepository.deleteById(partyId);
            return true;
        }
        else {
            return false; // TODO : 삭제 실패 했을 때 에러 처리
        }*/
        partyRepository.deleteById(partyId);
        return true;
    }

    /*Temp*/
    @Override
    public List<Party> findAllParties(){
        return partyRepository.findAll();
    }
}
