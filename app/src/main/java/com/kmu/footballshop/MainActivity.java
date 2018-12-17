package com.kmu.footballshop;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;

class HotItem {
    public String name;
    public int image;

    HotItem(String name, int image) {
        this.name = name;
        this.image = image;
    }
}

public class MainActivity extends AppCompatActivity {

    final HotItem[] hotItems = {
            new HotItem("Adidas Spectral Mode", R.drawable.adidas_spectral_mode),
            new HotItem("Nike Always Forward", R.drawable.nike_always_forward)
    };
    int position = 0;
    int maxPosition = hotItems.length - 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Football Shop");

        // 검색 기능
        final EditText itemName = (EditText) findViewById(R.id.searchInput);
        ImageButton enterButton = (ImageButton) findViewById(R.id.searchButton);
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchContents = itemName.getText().toString().toUpperCase();
                Intent intent;
                switch (searchContents) {
                    case "BOOTS":
                        intent = new Intent(getApplicationContext(), BootsActivity.class);
                        startActivity(intent);
                        break;
                    case "SHIRT":
                        intent = new Intent(getApplicationContext(), ShirtActivity.class);
                        startActivity(intent);
                        break;
                    case "GLOVES":
                        intent = new Intent(getApplicationContext(), GlovesActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });

        // Hot Items
        ImageButton leftButton = (ImageButton) findViewById(R.id.hotItemsLeftButton);
        ImageButton rightButton = (ImageButton) findViewById(R.id.hotItemsRightButton);
        setImageAndName(position);
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position = (position != 0) ? position - 1 : maxPosition;
                setImageAndName(position);
            }
        });
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position = (position != maxPosition) ? position + 1 : 0;
                setImageAndName(position);
            }
        });


        // 카테고리 기능
        ImageButton bootsButton = (ImageButton) findViewById(R.id.bootsButton);
        bootsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BootsActivity.class);
                startActivity(intent);
            }
        });
        ImageButton shirtButton = (ImageButton) findViewById(R.id.shirtButton);
        shirtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ShirtActivity.class);
                startActivity(intent);
            }
        });
        ImageButton glovesButton = (ImageButton) findViewById(R.id.glovesButton);
        glovesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GlovesActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setImageAndName(int position) {
        ImageButton hotItem = (ImageButton) findViewById(R.id.hotItem);
        TextView hotItemText = (TextView) findViewById(R.id.hotItemText);
        hotItem.setImageDrawable(getDrawable(hotItems[position].image));
        hotItemText.setText(hotItems[position].name);
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
            Toast.makeText(getBaseContext(),
                    "Web page downloaded successfully", Toast.LENGTH_SHORT)
                    .show();

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            HotItem[] hotItems = gson.fromJson(result, HotItem[].class);
//            byte[] rawImage = Base64.decode(h.getImage().replace('/', '_').replace('+', '-'), Base64.NO_WRAP);
//            Bitmap bmp = BitmapFactory.decodeByteArray(rawImage, 0, rawImage.length);
//            ImageButton hotItem = (ImageButton) findViewById(R.id.hotItem);
//            hotItem.setImageBitmap(bmp);
        }
    }
}
