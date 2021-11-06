package vn.edu.usth.flickr.api;

class FlickrApi {
    private final String API_KEY, API_SECRET, TOKEN_ACCESS, TOKEN_SECRET, NSID;
    private static final String TAG = "Auth";

    public FlickrApi() {
        API_KEY = "a8db956a048e0a962a1f88168d7ad545";
        API_SECRET = "8a32f6762e1abc37";
        TOKEN_ACCESS = "72157720820879664-28f918ba7e67f57d";
        TOKEN_SECRET = "255ef5dfe708b9b2";
        NSID = "194108707@N08";
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
