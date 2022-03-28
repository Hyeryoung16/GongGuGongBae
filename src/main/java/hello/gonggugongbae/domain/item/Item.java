package hello.gonggugongbae.domain.item;

public class Item {
    private String itemName; // 구매처
    private String refLink; // 참고링크
    private int minOrderPrice; // 최소주문금액
    private int deliveryPrice; // 배송(배달)비

    public Item(String itemName, String refLink, int minOrderPrice, int deliveryPrice) {
        this.itemName = itemName;
        this.refLink = refLink;
        this.minOrderPrice = minOrderPrice;
        this.deliveryPrice = deliveryPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getRefLink() {
        return refLink;
    }

    public void setRefLink(String refLink) {
        this.refLink = refLink;
    }

    public int getMinOrderPrice() {
        return minOrderPrice;
    }

    public void setMinOrderPrice(int minOrderPrice) {
        this.minOrderPrice = minOrderPrice;
    }

    public int getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(int deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }
}
