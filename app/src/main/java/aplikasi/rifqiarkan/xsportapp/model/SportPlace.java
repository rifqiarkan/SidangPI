package aplikasi.rifqiarkan.xsportapp.model;

import java.util.ArrayList;

public class SportPlace {
    private String id, name, icon;
    private ArrayList<Place> place;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Place> getPlaces() {
        return place;
    }

    public String getIcon() {
        return icon;
    }
}
