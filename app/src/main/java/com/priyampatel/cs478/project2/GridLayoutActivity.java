package com.priyampatel.cs478.project2;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.TextView;
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
    final Context context = this;

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
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                String[] dealerinfo;
                dealerinfo = getDealerInfo(picID);
                builder.setTitle(getTitle(picID))
                        .setMessage(dealerinfo[0] + "\n" + dealerinfo[1] + "\n" + dealerinfo[2]);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }

        return true;
    }

    public String getTitle(int position){
        String title = "";
        switch (position) {
            case 2130837587: title = "Audi Dealers";
                break;
            case 2130837588: title = "Bentley Dealers";
                break;
            case 2130837589: title = "Bugatti Dealers";
                break;
            case 2130837590: title = "Ferrari Dealers";
                break;
            case 2130837591: title = "Lamborghini Dealers";
                break;
            case 2130837602: title = "Tesla Dealers";
        }

        return title;
    }

    public String[] getDealerInfo(int position){
        String[] dealerinfo = new String[]{};
        switch (position) {
            case 2130837587: dealerinfo = new String[] {"Fletcher Jones Audi - 1523 W North Ave", "Greater Chicago Motors - 1850 N Elston", "Audi Morton Grove - 7000 Golf Rd."};
                break;
            case 2130837588: dealerinfo = new String[] {"Bentley Gold Coast - 834 N Rush St", "Bentley Downers Grove - 330 Ogden Ave", "Bentley Northbrook - 100 Skokie Blvd"};
                break;
            case 2130837589: dealerinfo = new String[] {"Bugatti Gold Coast - 834 N Rush St", "Maserati of Chicago - 530 W Chicago Ave", "Luxury Dealers - 120 E Chicago Ave"};
                break;
            case 2130837590: dealerinfo = new String[] {"McLaren Chicago Showroom - 645 W Randolph St", "Bentley Gold Coast - 834 N Rush St", "Maserati of Chicago - 834 N Rush St"};
                break;
            case 2130837591: dealerinfo = new String[] {"Lamborghini Gold Coast Showroom - 834 N Rush St", "Perillo Downers Grove - 330 Ogden Ave", "Global Luxury Imports - 101 Tower Rd."};
                break;
            case 2130837602: dealerinfo = new String[] {"Tesla - 1053 W Grand Ave", "Tesla Motors Old Orchard - 4999 Old Orchard Shop Center", "Tesla - 58 Oakbrook Center"};
        }

        return dealerinfo;
    }
}