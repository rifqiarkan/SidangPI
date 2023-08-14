package aplikasi.rifqiarkan.xsportapp.model;

import java.util.ArrayList;

public class SportPlace {
    private String id, name, icon;
    private ArrayList<PlaceResponse> placeResponse;

    public String getId() {return id;}

    public String getName() {return name;}

    public ArrayList<PlaceResponse> getPlaces() {return placeResponse;}

    public String getIcon() {
        return icon;
    }
}
