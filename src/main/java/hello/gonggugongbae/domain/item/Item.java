package hello.gonggugongbae.domain.item;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Item {
    @NotBlank
    private String itemName; // 상품이름(구매처)
    @NotBlank
    private String itemRefLink; // 참고링크
    @NotNull
    @Min(0)
    private Integer itemMinOrderPrice; // 최소주문금액
    @NotNull
    @Min(0)
    private Integer itemDeliveryPrice; // 배송(배달)비

    public Item() {}

    public Item(String itemName, String itemRefLink, Integer itemMinOrderPrice, Integer itemDeliveryPrice) {
        this.itemName = itemName;
        this.itemRefLink = itemRefLink;
        this.itemMinOrderPrice = itemMinOrderPrice;
        this.itemDeliveryPrice = itemDeliveryPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemRefLink() {
        return itemRefLink;
    }

    public void setItemRefLink(String itemRefLink) {
        this.itemRefLink = itemRefLink;
    }

    public Integer getItemMinOrderPrice() {
        return itemMinOrderPrice;
    }

    public void setItemMinOrderPrice(Integer itemMinOrderPrice) {
        this.itemMinOrderPrice = itemMinOrderPrice;
    }

    public Integer getItemDeliveryPrice() {
        return itemDeliveryPrice;
    }

    public void setItemDeliveryPrice(Integer itemDeliveryPrice) {
        this.itemDeliveryPrice = itemDeliveryPrice;
    }
}
