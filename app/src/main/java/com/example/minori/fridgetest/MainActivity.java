package com.example.minori.fridgetest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;



public class MainActivity extends AppCompatActivity {

    private final static int RESULT_CAMERA = 1001;

    // view objects
    private ImageView photoImageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar topToolbar = (Toolbar) findViewById(R.id.top_tool_bar);
        Toolbar bottomToolbar = (Toolbar) findViewById(R.id.bottom_tool_bar);

        photoImageView = (ImageView)findViewById(R.id.photo_image_view);

        // タイトルを設定
        topToolbar.setTitle("FridgeTest");

        // ナビゲーションアイコンの設定、クリック処理
        topToolbar.setNavigationIcon(R.drawable.common_google_signin_btn_icon_dark);
        topToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ナビゲーションアイコンクリック時の処理
            }
        });

        // メニューのインフレート、メニューアイテムのクリック処理
        bottomToolbar.inflateMenu(R.menu.menu_fridgeye_list);
        bottomToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_item_take_photo:
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, RESULT_CAMERA);
                        break;
                    case R.id.menu_item_add_from_gallery:
                        break;
                    case R.id.menu_item_web_camera:
                        break;
                }

                // メニューのクリック処理
                return true;
            }
        });
    }

    // カメラ撮影画面から帰ってきてからの処理、Bitmapを取得してImageViewに表示する
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RESULT_CAMERA) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            photoImageView.setImageBitmap(bitmap);
        }
    }

    public void onButtonTapped(View view) {
        Intent intent = new Intent(this, SlidesActivity.class);
        startActivity(intent);
    }

    public void onButtonTapped2(View view) {
        Intent intent = new Intent(this, StorageActivity.class);
        startActivity(intent);
    }



}
