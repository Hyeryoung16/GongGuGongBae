package hello.gonggugongbae.domain.member;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class MemberEditForm {

    @NotNull
    private Long id;

    @NotBlank
    private String loginId; // 로그인 아이디

    @NotBlank
    private String username; // 사용자 이름

    @NotBlank
    private String password; // 로그인 비밀번호

    @NotNull
    @Range(min=-90, max=90)
    private Double latitude; // 주소-위도

    @NotNull
    @Range(min=-180, max=180)
    private Double longitude; // 주소-경도
}
