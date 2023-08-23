package aplikasi.rifqiarkan.xsportapp.utis;

import android.location.Location;
import android.util.Log;

import java.text.DecimalFormat;

public class DistanceCalculator {
    public static double latitudeUser = 0.0;
    public static double longitudeUser = 0.0;
    public static final double EARTH_RADIUS = 6371; // Radius of the Earth in kilometers

    public static double calculateDistance(double lat2, double lon2) {
        // Convert latitude and longitude from degrees to radians
        double lat1Rad = Math.toRadians(latitudeUser);
        double lon1Rad = Math.toRadians(longitudeUser);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        // Difference in coordinates
        double dLat = lat2Rad - lat1Rad;
        double dLon = lon2Rad - lon1Rad;

        // Haversine formula
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Calculate the distance
        double distance = EARTH_RADIUS * c;
        distance = Math.round(distance * 100.0) / 100.0;
        return distance;
    }
}
