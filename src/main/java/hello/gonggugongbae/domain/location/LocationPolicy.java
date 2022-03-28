package hello.gonggugongbae.domain.location;

public interface LocationPolicy {
    boolean isNearLocation(Location loc1, Location loc2);
}
