package com.example.program1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ImageItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_item);
        Intent intent = getIntent();
        int index = intent.getIntExtra("com.example.program1.ITEM_INDEX", -1);

        if (index > -1){
            int pic = getImage(index);
            ImageView imageView = (ImageView) findViewById(R.id.imageView);
            scaleImg(imageView, pic);
        }
    }

    private int getImage(int index){
        switch (index){
            case 0: return R.drawable.rice;
            case 1: return R.drawable.potatoes;
            case 2: return R.drawable.fish;
            case 3: return R.drawable.chicken;
            default: return -1;
        }
    }

    private void scaleImg(ImageView img, int picIndex){
        Display display = getWindowManager().getDefaultDisplay();
        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), picIndex, options);

        int imgWidth = options.outWidth;
        int screenWidth = display.getWidth();

        if (imgWidth > screenWidth){
            int ratio = Math.round( (float)imgWidth/(float)screenWidth );
            options.inSampleSize = ratio;
        }

        options.inJustDecodeBounds=false;
        Bitmap scaledImage = BitmapFactory.decodeResource(getResources(), picIndex, options);
        img.setImageBitmap(scaledImage);
    }

}
