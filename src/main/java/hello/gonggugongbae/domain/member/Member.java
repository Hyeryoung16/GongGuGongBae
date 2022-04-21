package hello.gonggugongbae.domain.member;

import hello.gonggugongbae.domain.location.Location;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Data
public class Member {
    private Long id;

    //@NotEmpty
    private String loginId; // 로그인 아이디
    //@NotEmpty
    private String username; // 사용자 이름
    //@NotEmpty
    private String password; // 로그인 비밀번호
    //@NotNull
    private Double latitude; // 주소-위도
    //@NotNull
    private Double longitude; // 주소-경도
    private List<Long> parties = new ArrayList<>();

    public Member(){}

    public Member(String loginId, String username, String password, Double latitude, Double longitude) {
        this.loginId = loginId;
        this.username = username;
        this.password = password;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Location getAddress(){
        return new Location(this.latitude, this.longitude);
    }

    public void addParty(Long partyId) {
        parties.add(partyId);
    }
}
