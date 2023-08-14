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
        Log.d("latUser", String.valueOf(latitudeUser));
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
        return distance;
    }

    public static Double getDistanceUserToLocation(
            Double latitudeLocation,
            Double longitudeLocation
    ) {
        Location locationUser = new Location("");
        locationUser.setLatitude(latitudeUser);
        locationUser.setLongitude(longitudeUser);

        Log.d("test", String.valueOf(latitudeUser));
        Log.d("test", String.valueOf(longitudeUser));
        Log.d("test", String.valueOf(latitudeLocation));
        Log.d("test", String.valueOf(longitudeLocation));

        Location locationRestaurant = new Location("");
        if (latitudeLocation != null) locationRestaurant.setLatitude(latitudeLocation);
        if (longitudeLocation != null) locationRestaurant.setLongitude(longitudeLocation);

        float distanceInMeters = locationUser.distanceTo(locationRestaurant) / 1000;
        DecimalFormat format = new DecimalFormat("#.#");
        Log.d("distanceInMeters", String.valueOf(distanceInMeters));

        return Double.valueOf(format.format(distanceInMeters)) ;
    }

    public static void main(String[] args) {
        // Koordinat lokasi user
        double userLat = 52.5200; // Contoh latitude
        double userLon = 13.4050; // Contoh longitude

        // Koordinat lokasi tujuan
        double destinationLat = 48.8566; // Contoh latitude
        double destinationLon = 2.3522; // Contoh longitude

        // Hitung jarak antara user dan lokasi tujuan
        double distance = calculateDistance(destinationLat, destinationLon);

        System.out.println("Jarak antara user dan lokasi tujuan: " + distance + " km");
    }
}
