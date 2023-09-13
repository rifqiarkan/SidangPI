package aplikasi.rifqiarkan.xsportapp.model;

public class Place {
    private String icon, location, name, phoneNumber, operational, information, price;
    private String imageMaps;
    private Double distance;

    private double latitude, longitude;

    public Place(String icon, String location, String name, String phoneNumber, String operational, String information, double latitude, double longitude, String price, String imageMaps, Double distance) {
        this.icon = icon;
        this.location = location;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.operational = operational;
        this.information = information;
        this.latitude = latitude;
        this.longitude = longitude;
        this.price = price;
        this.imageMaps = imageMaps;
        this.distance = distance;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getOperational() {
        return operational;
    }

    public String getInformation() {
        return information;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getImageMaps() {
        return imageMaps;
    }

    public String getPrice() {
        return price;
    }

    public Double getDistance() {
        return distance;
    }

}
