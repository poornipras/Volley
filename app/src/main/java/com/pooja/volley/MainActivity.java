package com.pooja.volley;

import android.app.DownloadManager;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    private static final String TAG =MainActivity.class.getName();
    private static final String REQUESTTAG = "First string request";
    private Button send_request;
    private RequestQueue mrequestque;
    private StringRequest mstringrequest;
    private String url="http://api.themoviedb.org/3/tv/top_rated?api_key=8496be0b2149805afa458ab8ec27560c";
    Button showimage;
    private String imageurl="https://thumbs.dreamstime.com/z/golden-fish-9113229.jpg";
    ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageview=(ImageView)findViewById(R.id.imageView);
        showimage=(Button)findViewById(R.id.button_display_image);
        showimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayimage();
            }
        });

        //volley for parsing json and sending http request
        send_request=(Button)findViewById(R.id.button_send_request);
        send_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               sendrequestandPrintresponse();
            }
            
        });
    }

    private void sendrequestandPrintresponse() {
       // mrequestque= Volley.newRequestQueue(this);
        mrequestque=VolleySingleton.getInstance(this.getApplicationContext()).getRequestQueue(this.getApplicationContext());
        mstringrequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i(TAG,"Response"+response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG,"Error:"+error.toString());
            }
        });
         mstringrequest.setTag(REQUESTTAG);
        mrequestque.add(mstringrequest);

    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mrequestque!=null)
        {
            mrequestque.cancelAll(REQUESTTAG);
        }
    }

    private void displayimage()
    {
       // mrequestque=Volley.newRequestQueue(this.getApplicationContext());
        mrequestque=VolleySingleton.getInstance(this.getApplicationContext()).getRequestQueue(this.getApplicationContext());
        final ImageRequest imgrequest=new ImageRequest(imageurl, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                imageview.setImageBitmap(bitmap);
            }
        }, 0, 0, null, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                imageview.setImageResource(R.drawable.error_img);
            }
        });

        mrequestque.add(imgrequest);
    }
}
