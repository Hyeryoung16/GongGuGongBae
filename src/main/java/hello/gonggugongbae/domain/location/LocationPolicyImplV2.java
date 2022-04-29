package hello.gonggugongbae.domain.location;

import org.springframework.stereotype.Component;

//@Component
public class LocationPolicyImplV2 implements LocationPolicy{
    @Override
    public boolean isNearLocation(Location loc1, Location loc2) {
        return true; // 모든 거리에 대해 true
    }
}
