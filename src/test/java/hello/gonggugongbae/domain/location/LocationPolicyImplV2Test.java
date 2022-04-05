package hello.gonggugongbae.domain.location;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LocationPolicyImplV2Test {
    LocationPolicy locationPolicy = new LocationPolicyImplV2();

    @Test
    @DisplayName("거리에 상관 없이 전부 'near'")
    void allNear() {
        // given
        Location location1 = new Location(MyLocation.GYM_LAT, MyLocation.GYM_LON);
        Location location2 = new Location(MyLocation.PARK_LAT, MyLocation.PARK_LON);
        Location location3 = new Location(MyLocation.STATION_LAT, MyLocation.STATION_LON);

        // when
        boolean isNear1 = locationPolicy.isNearLocation(location1, location2);
        boolean isNear2 = locationPolicy.isNearLocation(location1, location3);
        boolean isNear3 = locationPolicy.isNearLocation(location2, location3);

        // then
        assertThat(isNear1).isTrue();
        assertThat(isNear2).isTrue();
        assertThat(isNear3).isTrue();
    }
}
