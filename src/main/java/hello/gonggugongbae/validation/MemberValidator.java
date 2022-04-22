package hello.gonggugongbae.validation;

import hello.gonggugongbae.domain.member.Member;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

//@Component
public class MemberValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Member.class.isAssignableFrom(clazz); // TODO
    }

    @Override
    public void validate(Object target, Errors errors) {
        Member member = (Member) target;

        //검증로직1 : 로그인 아이디 필수
        if(!StringUtils.hasText(member.getLoginId())) {
            errors.rejectValue("loginId", "required");
        }

        //검증로직2 : 사용자 이름 필수
        if(!StringUtils.hasText(member.getUsername())) {
            errors.rejectValue("username", "required");
        }

        //검증로직3 : 비밀번호 필수
        if(!StringUtils.hasText(member.getPassword())) {
            errors.rejectValue("password", "required");
        }

        //검증로직4 : 위도 필수 & 범위는 -90~90 까지 허용
        if(member.getLatitude() == null
                || member.getLatitude() < -90
                || member.getLatitude() > 90) {
            errors.rejectValue("latitude", "range", new Object[]{-90, 90}, null);
        }

        //검증로직5 : 경도 필수 & 범위는 -180~180 까지 허용
        if(member.getLongitude() == null
                || member.getLongitude() < -180
                || member.getLongitude() > 180) {
            errors.rejectValue("longitude", "range", new Object[]{-180, 180}, null);
        }
    }
}
