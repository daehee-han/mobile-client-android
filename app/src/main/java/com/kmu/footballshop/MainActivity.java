package com.kmu.footballshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        bootsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ShirtActivity.class);
                startActivity(intent);
            }
        });
        ImageButton glovesButton = (ImageButton) findViewById(R.id.glovesButton);
        bootsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GlovesActivity.class);
                startActivity(intent);
            }
        });
    }
}
