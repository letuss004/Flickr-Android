package vn.edu.usth.flickr.model;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleyQueueSingleton {

    private static VolleyQueueSingleton instance;
    private static RequestQueue requestQueue;
    private static Context ctx;

    private VolleyQueueSingleton(Context context) {
        ctx = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized VolleyQueueSingleton getInstance(Context context) {
        if (instance == null) {
            instance = new VolleyQueueSingleton(context);
        }
        return instance;
    }

    public static synchronized VolleyQueueSingleton getInstance() {
        if (instance == null) {
            instance = new VolleyQueueSingleton(ctx);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

}
