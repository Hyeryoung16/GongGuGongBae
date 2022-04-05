package hello.gonggugongbae.domain.location;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LocationPolicyImplTest {

    LocationPolicy locationPolicy = new LocationPolicyImpl();

    @Test
    @DisplayName("반경 1000m 이내이면 'near'")
    void near() {
        // given
        Location location1 = new Location(MyLocation.GYM_LAT, MyLocation.GYM_LON);
        Location location2 = new Location(MyLocation.PARK_LAT, MyLocation.PARK_LON);

        // when
        boolean isNear = locationPolicy.isNearLocation(location1, location2);

        // then
        assertThat(isNear).isTrue();
    }

    @Test
    @DisplayName("반경 1000m 밖이면 'not near'")
    void notNear() {
        // given
        Location location1 = new Location(MyLocation.GYM_LAT, MyLocation.GYM_LON);
        Location location2 = new Location(MyLocation.STATION_LAT, MyLocation.STATION_LON);

        // when
        boolean isNear2 = locationPolicy.isNearLocation(location1, location2);

        // then
        assertThat(isNear2).isFalse();
    }



}