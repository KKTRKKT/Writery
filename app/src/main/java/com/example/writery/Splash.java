package com.example.writery;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class Splash extends Activity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash_layout);
        imageView = findViewById(R.id.splash_img);

        setImage();

        Handler hd = new Handler();
        hd.postDelayed(new splashhandler(), 3000); // 1초 후에 hd handler 실행  3000ms = 3초

    }

    private class splashhandler implements Runnable{
        public void run(){
            startActivity(new Intent(getApplication(), MainActivity.class));
            overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
            Splash.this.finish();
        }
    }

    @Override
    public void onBackPressed() {
        //초반 플래시 화면에서 넘어갈때 뒤로가기 버튼 못누르게 함
    }

    public void setImage(){
        Bitmap bitmap =  ((BitmapDrawable)getResources().getDrawable(R.drawable.background)).getBitmap();
        imageView.setImageBitmap(bitmap);
    }

}
