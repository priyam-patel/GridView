package com.priyampatel.cs478.project2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ImageViewActivity extends Activity {

    int picID;
    String picURL;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the Intent used to start this Activity
        Intent intent = getIntent();

        // Make a new ImageView
        ImageView imageView = new ImageView(getApplicationContext());

        // Get the ID of the image to display and set it as the image for this ImageView
        imageView.setImageResource(intent.getIntExtra(GridLayoutActivity.EXTRA_RES_ID, 0));

        picID = intent.getIntExtra(GridLayoutActivity.EXTRA_RES_ID, 0);

        setContentView(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                picURL = getURL(picID);
                launchBrowser(picURL);
            }
        });

    }

    public void launchBrowser(String url){
        Intent openURL = new Intent(Intent.ACTION_VIEW);
        openURL.setData(Uri.parse(url));
        startActivity(openURL);
    }

    //match each picture ID with the specified URL of the car's manufacturer
    public String getURL(int position){

        String URL = "";

        switch(position){
            case 2130837587: URL = "http://www.audi.com/en.html";
                break;
            case 2130837588: URL = "https://www.bentleymotors.com/en.html";
                break;
            case 2130837589: URL = "https://www.bugatti.com/home/";
                break;
            case 2130837590: URL = "https://www.ferrari.com/en-US";
                break;
            case 2130837591: URL = "https://www.lamborghini.com/en-en/";
                break;
            case 2130837602: URL = "https://www.tesla.com/";
        }

        return URL;
    }

}