package com.example.minori.fridgetest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import java.io.IOException;
import java.io.InputStream;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();

        ImageView imageView3 = (ImageView)findViewById(R.id.image_view_3);
        try {
            InputStream istream = getResources().getAssets().open("img_format_4.jpg");
            Bitmap bitmap = BitmapFactory.decodeStream(istream);
            imageView3.setImageBitmap(bitmap);
        } catch (IOException e) {
            Log.d("Assets","Error");
        }
    }
}