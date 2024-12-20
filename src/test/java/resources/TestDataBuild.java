package resources;

import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {
    public AddPlace addPlacePayload(String name, String language, String address){
        AddPlace ap = new AddPlace();
        ap.setAccuracy(50);
        ap.setName(name);
        ap.setPhone_number("55555555");
        ap.setAddress(address);
        ap.setLanguage(language);
        ap.setWebsite("https://testing.com");
        List<String> myList = new ArrayList<String>();
        myList.add("Testing");
        myList.add("API");
        myList.add("Serialization");
        ap.setTypes(myList);
        Location l = new Location();
        l.setLat(33.123123);
        l.setLng(-44.456456);
        ap.setLocation(l);
        return ap;
    }

    public String deletePlacePayload(String placeID){
        return "{\n" +
                "    \"place_id\":\""+placeID+"\"\n" +
                "}";
    }
}
