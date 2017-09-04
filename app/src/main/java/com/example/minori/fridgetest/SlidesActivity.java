package com.example.minori.fridgetest;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ViewPropertyAnimatorCompatSet;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.BounceInterpolator;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class SlidesActivity extends AppCompatActivity {

    ImageSwitcher mImageSwitcher;
    int[] mImageResources = {R.drawable.fridge00,R.drawable.fridge01
            ,R.drawable.fridge02,R.drawable.fridge03
            ,R.drawable.fridge04,R.drawable.fridge05
            ,R.drawable.fridge06,R.drawable.fridge07};
    int mPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slides);
        mImageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);
        mImageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView =
                        new ImageView(getApplicationContext());
                return imageView;
            }
        });
        mImageSwitcher.setImageResource((mImageResources[0]));
    }

    public void onAnimationButtonTapped(final View view) {
        float y = view.getY() + 100;
        view.animate().setDuration(1000).setInterpolator(new BounceInterpolator()).y(y);
        // ViewPropertyAnimator animator = view.animate();
        // animator.setDuration(3000);
        // animator.rotation(360.0f * 5.0f);
    }

    private void movePosition(int move) {
        mPosition = mPosition + move;
        if (mPosition >= mImageResources.length) {
            mPosition = 0;
        } else if (mPosition < 0) {
            mPosition = mImageResources.length - 1;
        }
        mImageSwitcher.setImageResource(mImageResources[mPosition]);
    }

    public void onPrevButtonTapped(View view) {
        mImageSwitcher.setInAnimation(this, android.R.anim.fade_in);
        mImageSwitcher.setOutAnimation(this, android.R.anim.fade_out);
        movePosition(-1);
        findViewById(R.id.imageView).animate().setDuration(1000).alpha(0.0f);
    }

    public void onNextButtonTapped(View view) {
        mImageSwitcher.setInAnimation(this, android.R.anim.slide_in_left);
        mImageSwitcher.setOutAnimation(this, android.R.anim.slide_out_right);
        movePosition(1);
        findViewById(R.id.imageView).animate().setDuration(1000).alpha(0.0f);
    }
}
