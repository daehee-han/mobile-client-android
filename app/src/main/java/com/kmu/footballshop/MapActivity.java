package com.kmu.footballshop;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

class MyLatLng {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("lat")
    @Expose
    private float lat;
    @SerializedName("lng")
    @Expose
    private float lng;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public float getLat() {
        return lat;
    }
    public void setLat(float lat) {
        this.lat = lat;
    }
    public float getLng() {
        return lng;
    }
    public void setLng(float lng) {
        this.lng = lng;
    }
}

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private MyLatLng[] locations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // 서버로부터 위치정보를 받아온다
        if (isNetworkAvailable()) {
            DownloadTask downloadTask = new DownloadTask();
            downloadTask.execute("http://10.0.2.2:9001/api/shop");
        } else {
            Toast.makeText(getBaseContext(),
                    "Network is not Available", Toast.LENGTH_SHORT)
                    .show();
        }

        LatLng center = new LatLng(37.585922, 127.004049);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(center, 13.0f));
    }

    private boolean isNetworkAvailable() {
        boolean available = false;
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isAvailable())
            available = true;

        return available;
    }

    private String downloadUrl(String strUrl) throws IOException {
        try {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(strUrl)
                    .build();

            Response response = client.newCall(request).execute();
            return response.body().string();

        } catch (Exception e) {
            Log.d("Exception download", e.toString());
        } finally {

        }
        return "{}";
    }

    private class DownloadTask extends AsyncTask<String, Integer, String> {
        String s = null;

        @Override
        protected String doInBackground(String... url) {
            try {
                s = downloadUrl(url[0]);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return s;
        }

        @Override
        protected void onPostExecute(String result) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            // 마커 추가
            if (!result.equals("{}")) {
                locations = gson.fromJson(result, MyLatLng[].class);
            }
            if (locations != null) {
                for (MyLatLng myLatLng : locations) {
                    LatLng location = new LatLng((double) myLatLng.getLat(), (double) myLatLng.getLng());
                    mMap.addMarker(new MarkerOptions().position(location).title(myLatLng.getName()));
                }
            } else {
                Toast.makeText(getBaseContext(),
                        "서버로부터 정보를 받아오지 못하였습니다.", Toast.LENGTH_SHORT)
                        .show();
                finish();
            }
        }
    }

}
