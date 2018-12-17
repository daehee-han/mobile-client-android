package com.kmu.footballshop;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class AdidasSpectralModeActivity extends AppCompatActivity {

    final String URI_PREDATOR = "https://www.prodirectsoccer.com/products/adidas-Predator-18-FG-Mens-Boots-Firm-Ground-Clear-Orange-Clear-Orange-Trace-Pink-185887.aspx";
    final String URI_X18 = "https://www.prodirectsoccer.com/products/adidas-X-18-FG-Off-White-White-Core-Black-Firm-Ground-Mens-Boots-188059.aspx";
    final String URI_NEMEZIZ = "https://www.prodirectsoccer.com/products/adidas-Nemeziz-Messi-181-FG-Mens-Boots-Firm-Ground-Ash-Blue-Ash-Blue-Raw-Grey-185954.aspx";
    final String URI_COPA18_1 = "https://www.prodirectsoccer.com/products/adidas-Copa-181-FG-Mens-Boots-Firm-Ground-Clear-Mint-Clear-Mint-Metallic-Gold-185962.aspx";
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_adidas_spectral_mode);
        setTitle("Adidas Spectral Mode");
        ImageButton predator = (ImageButton) findViewById(R.id.adidas_spectral_mode_predator);
        ImageButton x18 = (ImageButton) findViewById(R.id.adidas_spectral_mode_x18);
        ImageButton nemeziz = (ImageButton) findViewById(R.id.adidas_spectral_mode_nemeziz);
        ImageButton copa18_1 = (ImageButton) findViewById(R.id.adidas_spectral_mode_copa18_1);

        predator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(URI_PREDATOR));
                startActivity(intent);
            }
        });
        x18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(URI_X18));
                startActivity(intent);
            }
        });
        nemeziz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(URI_NEMEZIZ));
                startActivity(intent);
            }
        });
        copa18_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(URI_COPA18_1));
                startActivity(intent);
            }
        });
    }
}
