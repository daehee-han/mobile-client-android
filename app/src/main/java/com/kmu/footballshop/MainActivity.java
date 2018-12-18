package com.kmu.footballshop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

class HotItem {
    public String name;
    public int image;
    Class type;

    HotItem(String name, int image, Class type) {
        this.name = name;
        this.image = image;
        this.type = type;
    }
}

public class MainActivity extends AppCompatActivity {

    final HotItem[] hotItems = {
            new HotItem("Adidas Spectral Mode", R.drawable.adidas_spectral_mode, AdidasSpectralModeActivity.class),
            new HotItem("Nike Always Forward", R.drawable.nike_always_forward, NikeAlwaysForwardActivity.class)
    };
    int position = 0;
    int maxPosition = hotItems.length - 1;
    // 검색내용 저장
    public static final String PREFS_NAME = "MyPrefs";
    EditText searchInput;
    String searchContents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Football Shop");

        // 검색내용 복구
        searchInput = (EditText) findViewById(R.id.searchInput);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        searchContents = settings.getString("searchContents", "");
        searchInput.setText(searchContents);

        // 검색 기능
        ImageButton enterButton = (ImageButton) findViewById(R.id.searchButton);
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                searchContents = searchInput.getText().toString().toUpperCase();
                switch (searchContents) {
                    case "BOOTS":
                    case "BOOT":
                        intent = new Intent(getApplicationContext(), BootsActivity.class);
                        startActivity(intent);
                        break;
                    case "SHIRT":
                    case "SHIRTS":
                        intent = new Intent(getApplicationContext(), ShirtActivity.class);
                        startActivity(intent);
                        break;
                    case "GLOVES":
                    case "GLOVE":
                        intent = new Intent(getApplicationContext(), GlovesActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        Toast.makeText(getBaseContext(),
                                "검색 결과가 없습니다", Toast.LENGTH_SHORT)
                                .show();
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
        ImageButton hotItemImageButtom = (ImageButton) findViewById(R.id.hotItem);
        hotItemImageButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), hotItems[position].type);
                startActivity(intent);
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

        // 국민대 근처 축구용품점 찾기
        Button findingShopButton = (Button) findViewById(R.id.findingShopButton);
        ImageButton findingShopArrowButton = (ImageButton) findViewById(R.id.findingShopArrowButton);
        findingShopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                startActivity(intent);
            }
        });
        findingShopArrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        searchContents = searchInput.getText().toString();
        editor.putString("searchContents", searchContents);
        editor.commit();
    }

    private void setImageAndName(int position) {
        ImageButton hotItem = (ImageButton) findViewById(R.id.hotItem);
        TextView hotItemText = (TextView) findViewById(R.id.hotItemText);
        hotItem.setImageDrawable(getDrawable(hotItems[position].image));
        hotItemText.setText(hotItems[position].name);
    }
}
