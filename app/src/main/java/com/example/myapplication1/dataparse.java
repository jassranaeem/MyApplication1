package com.example.myapplication1;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class dataparse
{
    // to get the place name latitude and longitude
    private HashMap<String,String> getplace(JSONObject googleplaceJson)
    {
        HashMap<String,String>googleplacemap=new HashMap<>();
        String NameofPlace="-NA-";
        String Vicinity="-NA-"; //vicinity is near by someplace
        String latitude="";
        String longitude="";
        String reference="";
//fetching data
        try
        {
           if(!googleplaceJson.isNull("name"))
           {
               Vicinity = googleplaceJson.getString("name");
           }
            if(!googleplaceJson.isNull("vicinity"))
            {
                Vicinity = googleplaceJson.getString("vicinity");
            }
            latitude = googleplaceJson.getJSONObject("geomatry").getJSONObject("location")
                    .getString("LAT");
            longitude = googleplaceJson.getJSONObject("geomatry").getJSONObject("location")
                    .getString("LNG");
            reference = googleplaceJson.getString("reference");

            googleplacemap.put("PlaceName",NameofPlace);
            googleplacemap.put("vicinity",Vicinity);
            googleplacemap.put("latitude",latitude);
            googleplacemap.put("longitude",longitude);
            googleplacemap.put("reference",reference);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return  googleplacemap;
    }
public List<HashMap<String,String>>getplaces(JSONArray jsonArray) {
    int counter = jsonArray.length();
    List<HashMap<String, String>> Near_By_Blood_Bank_List = new ArrayList<>();
    HashMap<String, String> Near_By_Place_Map = null;
    for (int i = 0; i < counter; i++)
    {
        try
        {
            Near_By_Place_Map = getplace((JSONObject) jsonArray.get(i));
            Near_By_Blood_Bank_List.add(Near_By_Place_Map);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
    return Near_By_Blood_Bank_List;
}
public List<HashMap<String,String>> parse (String jSONdata)
{
    JSONArray jsonArray=null;
    JSONObject jsonObject;

    try
    {
        jsonObject = new JSONObject(jSONdata);
        jsonArray=jsonObject.getJSONArray("results");
    }
    catch (JSONException e)
    {
        e.printStackTrace();
    }
    return getplaces(jsonArray);
}

}
