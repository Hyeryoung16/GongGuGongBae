package hello.gonggugongbae.domain.party;
import hello.gonggugongbae.domain.item.Item;
import hello.gonggugongbae.domain.location.Location;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class PartyCreateForm {

    @Valid
    private Item item;

    @NotNull
    @Range(min=2, max=10)
    private Integer partyMemberNum; // 인원
    
    // TODO : 팟 생성 시간

    @NotNull
    @Range(min=0, max=240)
    private Integer duration; // 모집기간

    @NotNull
    @Min(0)
    private Integer minOrderPricePerMember; // 인당 최소 주문 금액

    @Valid
    private Location receiveLocation;
}
