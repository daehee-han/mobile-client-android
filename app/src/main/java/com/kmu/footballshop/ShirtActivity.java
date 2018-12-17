package com.kmu.footballshop;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class ShirtActivity extends AppCompatActivity {

    final String URI_RM = "https://www.prodirectsoccer.com/products/adidas-18-19-Real-Madrid-Home-Jersey-Long-Sleeved-Core-White-Black-Mens-Replica-dq0869-189502.aspx";
    final String URI_PSG = "https://www.prodirectsoccer.com/products/Jordan-Paris-Saint-Germain-2018-19-Womens-Home-3rd-Stadium-SS-Shirt-Womens-Replica-Shirts-Black-White-188775.aspx";
    final String URI_MANU = "https://www.prodirectsoccer.com/products/adidas-Manchester-United-2018-19-Away-Authentic-Shirt-Mens-Replica-Shirts-Icey-Pink-Trace-Pink-Black-182473.aspx";
    final String URI_BM = "https://www.prodirectsoccer.com/products/New-Balance-Liverpool-FC-18-19-Home-Short-Sleeve-Shirt-Mens-Replica-Red-186214.aspx";
    final String URI_LIVERPOOL = "https://www.prodirectsoccer.com/products/New-Balance-Liverpool-FC-18-19-Home-Short-Sleeve-Shirt-Mens-Replica-Red-186214.aspx";
    final String URI_CHELSEA = "https://www.prodirectsoccer.com/products/Nike-Chelsea-FC-2018-19-Stadium-Home-SS-Shirt-Mens-Replica-Shirts-Rush-Blue-White-180411.aspx";

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_shirt);
        setTitle("Shirt");
        ImageButton shirtRM = (ImageButton) findViewById(R.id.shirt_rm);
        ImageButton shirtPSG = (ImageButton) findViewById(R.id.shirt_psg);
        ImageButton shirtManU = (ImageButton) findViewById(R.id.shirt_manu);
        ImageButton shirtBM = (ImageButton) findViewById(R.id.shirt_bm);
        ImageButton shirtLiverpool = (ImageButton) findViewById(R.id.shirt_liverpool);
        ImageButton shirtChelsea = (ImageButton) findViewById(R.id.shirt_chelsea);

        shirtRM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(URI_RM));
                startActivity(intent);
            }
        });
        shirtPSG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(URI_PSG));
                startActivity(intent);
            }
        });
        shirtManU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(URI_MANU));
                startActivity(intent);
            }
        });
        shirtBM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(URI_BM));
                startActivity(intent);
            }
        });
        shirtLiverpool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(URI_LIVERPOOL));
                startActivity(intent);
            }
        });
        shirtChelsea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(URI_CHELSEA));
                startActivity(intent);
            }
        });
    }
}
