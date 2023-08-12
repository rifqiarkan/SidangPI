package aplikasi.rifqiarkan.xsportapp.model;

import java.util.ArrayList;

public class Place {
    private String icon, location, name, phoneNumber, operational, information, latitude, longitude, price;
    private String imageMaps;

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

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getImageMaps() {
        return imageMaps;
    }

    public String getPrice() {
        return price;
    }
}
