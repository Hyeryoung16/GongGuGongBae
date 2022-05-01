package hello.gonggugongbae.domain.location;

import org.springframework.stereotype.Component;

// @Component
public class LocationPolicyImpl implements LocationPolicy{

    private double maxDistance = 10000;
    @Override
    public boolean isNearLocation(Location loc1, Location loc2) {
        double dist = distance(loc1, loc2);
        if (dist <= maxDistance) {
            return true; // loc2가 loc1의 반경 1000m 이내일 경우
        }
        else {
            return false;
        }
    }

    private static double distance(Location loc1, Location loc2) {
        double lon1 = loc1.getLongitude();
        double lon2 = loc2.getLongitude();
        double lat1 = loc1.getLatitude();
        double lat2 = loc2.getLatitude();

        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));

        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;

        // to Meter
        dist = dist * 1609.344;
        return dist;
    }

    // This function converts decimal degrees to radians
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    // This function converts radians to decimal degrees
    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
}
