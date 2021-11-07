package vn.edu.usth.flickr.api;


import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.Transport;

public class FlickrApi extends Flickr {
    private static final String API_KEY = "a8db956a048e0a962a1f88168d7ad545";
    private static final String API_SECRET = "8a32f6762e1abc37";
    private static final String TOKEN_ACCESS = "72157720820879664-28f918ba7e67f57d";
    private static final String TOKEN_SECRET = "255ef5dfe708b9b2";
    private static final String NSID = "194108707@N08";
    private static final String TAG = "FlickrApi";
    private static FlickrApi instance;

    private FlickrApi() {
        super(API_KEY, API_SECRET, new REST());
    }

    public static FlickrApi getInstance() {
        if (instance == null) {
            instance = new FlickrApi();
        }
        return instance;
    }

    public String getApiKey() {
        return API_KEY;
    }

    public String getApiSecret() {
        return API_SECRET;
    }

    public String getTokenAccess() {
        return TOKEN_ACCESS;
    }

    public String getTokenSecret() {
        return TOKEN_SECRET;
    }

    public String getNSID() {
        return NSID;
    }

    public String getTAG() {
        return TAG;
    }

}
