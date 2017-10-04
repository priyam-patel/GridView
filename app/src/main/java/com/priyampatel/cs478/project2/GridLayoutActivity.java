package com.priyampatel.cs478.project2;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.widget.Adapter;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.net.Uri;
import android.widget.AdapterView.AdapterContextMenuInfo;

//This application uses some deprecated methods.
//See UIViewPager for a more modern version of this application

public class GridLayoutActivity extends Activity {

    protected static final String EXTRA_RES_ID = "POS";

    //pass the GridView ID as a global variable
    public int picID;
    int pos;

    private ArrayList<Integer> mThumbIdsCars = new ArrayList<Integer>(
            Arrays.asList(R.drawable.audi, R.drawable.bentley,
                    R.drawable.bugatti, R.drawable.ferrari, R.drawable.lambo,
                    R.drawable.tesla));

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        GridView gridview = (GridView) findViewById(R.id.gridview);

        // Create a new ImageAdapter and set it as the Adapter for this GridView
        gridview.setAdapter(new ImageAdapter(this, mThumbIdsCars));

        // Set an setOnItemClickListener on the GridView
        gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                //Create an Intent to start the ImageViewActivity
                Intent intent = new Intent(GridLayoutActivity.this,
                        ImageViewActivity.class);

                // Add the ID of the thumbnail to display as an Intent Extra
                intent.putExtra(EXTRA_RES_ID, (int) id);

                pos = position;

                String IDTest = String.valueOf(id);

                Log.i("ID OF PICTURE:", IDTest);

                // Start the ImageViewActivity
                startActivity(intent);

            }
        });

        registerForContextMenu(gridview);

    }

    //Create menu to be shown when a long click occurs
    //Have menu be adaptable to the GridView and store the ID value that is passed around the grid
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,v,menuInfo);
        GridView gv = (GridView) v;
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;

        picID = (int)info.id;

        menu.setHeaderTitle("Select the Action");
        menu.add(0,v.getId(),0,"View Entire Picture");
        menu.add(0, v.getId(),0, "Visit Website");
        menu.add(0, v.getId(),0, "Find a Car Dealer");

    }

    public boolean onContextItemSelected(MenuItem item){
        if(item.getTitle()=="View Entire Picture"){

            // Make a new ImageView
            ImageView imageView = new ImageView(getApplicationContext());

            // Get the ID of the image to display and set it as the image for this ImageView
            imageView.setImageResource(picID);

            setContentView(imageView);
        }

        else if(item.getTitle()=="Visit Website"){
            String url;
            ImageViewActivity IA = new ImageViewActivity();
            url = IA.getURL(picID);
            Intent openURL = new Intent(Intent.ACTION_VIEW);
            openURL.setData(Uri.parse(url));
            startActivity(openURL);
        }

        else if(item.getTitle()=="Find a Car Dealer"){

        }



        return true;
    }


/*
    public void launchBrowser(String url){
        Intent openURL = new Intent(Intent.ACTION_VIEW);
        openURL.setData(Uri.parse(url));
        startActivity(openURL);
    }

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
*/

}