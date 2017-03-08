package com.pooja.volley;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Pooja on 2/24/2017.
 */

public class VolleySingleton {

    private static VolleySingleton volleyinstance;
    private static RequestQueue requestQueue;

    public  VolleySingleton(Context context)

    {
      requestQueue=getRequestQueue(context);
    }

    public RequestQueue getRequestQueue(Context context) {
        if(requestQueue==null)
        {
            requestQueue= Volley.newRequestQueue(context);
        }
        return requestQueue;
    }
public static VolleySingleton getInstance(Context context)
{
    if(volleyinstance==null)
    {
        volleyinstance=new VolleySingleton(context);
    }
    return volleyinstance;
}
}
