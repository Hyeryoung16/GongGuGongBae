package hello.gonggugongbae.domain.location;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

public class Location {
    @NotNull
    @Range(min=-90, max=90)
    Double latitude; // 위도
    @NotNull
    @Range(min=-180, max=180)
    Double longitude; // 경도

    public Location() {}

    public Location(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
