package com.mca.csrs.csrs;

import android.content.Context;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

     /*   mTask = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {

                try {
                    strJson = getJsonFromServer("http://jaison.in/csrs");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }
        };*/
        //String strJson2 = "{\"data\":[{\"0\":\"5764\",\"count(crime_s_id)\":\"5764\"}]}";
       String strJson = "{\"data\":[{\"0\":\"Agra\",\"city\":\"Agra\"},{\"0\":\"Ahmedabad\",\"city\":\"Ahmedabad\"},{\"0\":\"Allahabad\",\"city\":\"Allahabad\"},{\"0\":\"Amritsar\",\"city\":\"Amritsar\"},{\"0\":\"Asansol\",\"city\":\"Asansol\"},{\"0\":\"Aurangabad\",\"city\":\"Aurangabad\"},{\"0\":\"Bengaluru\",\"city\":\"Bengaluru\"},{\"0\":\"Bhopal\",\"city\":\"Bhopal\"},{\"0\":\"Chandigarh\",\"city\":\"Chandigarh\"},{\"0\":\"Chennai\",\"city\":\"Chennai\"},{\"0\":\"Coimbatore\",\"city\":\"Coimbatore\"},{\"0\":\"Delhi\",\"city\":\"Delhi\"},{\"0\":\"Dhanbad\",\"city\":\"Dhanbad\"},{\"0\":\"Durg-Bhilainagar\",\"city\":\"Durg-Bhilainagar\"}]}";
        String data = "";
        try {
            JSONObject jsonRootObject = new JSONObject(strJson);

            //Get the instance of JSONArray that contains JSONObjects
            JSONArray jsonArray = jsonRootObject.optJSONArray("data");

            //Iterate the jsonArray and print the info of JSONObjects
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

              //  int id = Integer.parseInt(jsonObject.optString("0").toString());
                String city = jsonObject.optString("0");
              //  float salary = Float.parseFloat(jsonObject.optString("salary").toString());

                try {
                 //   String location[] = {"Malappuram","Agra","Bengaluru"};
                 //   int len=location.length;
                    Geocoder gc = new Geocoder(this);
                //    for(int i=0;i<len;i++){
                        List<Address> addresses= gc.getFromLocationName(city, 2); // get the found Address Objects
                        for(Address a : addresses){
                            googleMap.addCircle(new CircleOptions()
                                    .center(new LatLng(a.getLatitude(), a.getLongitude()))
                                    .radius(100000)
                                    .strokeColor(Color.RED)
                                    .fillColor(Color.BLUE));
                        }
               //     }
                } catch (IOException e) {
                    // handle the exception
                }
                data += " " + city;
            }
            //  output.setText(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        LatLng india = new LatLng(21.7679,  78.8718);
       // mMap.addMarker(new MarkerOptions().position(india).title(data));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(india));
        Context context=getApplicationContext();
        Toast.makeText(context,"hello"+data,Toast.LENGTH_LONG).show();
    }

    /*public static String getJsonFromServer(String url) throws IOException {

        BufferedReader inputStream = null;

        URL jsonUrl = new URL(url);
        URLConnection dc = jsonUrl.openConnection();

        dc.setConnectTimeout(5000);
        dc.setReadTimeout(5000);

        inputStream = new BufferedReader(new InputStreamReader(
                dc.getInputStream()));

        // read the JSON results into a string
        String jsonResult = inputStream.readLine();
        return jsonResult;
    }
*/
}
