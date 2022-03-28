package hello.gonggugongbae.domain.location;

public class LocationPolicyImpl implements LocationPolicy{
    @Override
    public boolean isNearLocation(Location loc1, Location loc2) {
        return true; // TODO : 일단 어떤 위치든 상관없이 허용
    }
}
