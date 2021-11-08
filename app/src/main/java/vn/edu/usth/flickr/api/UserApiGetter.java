package vn.edu.usth.flickr.api;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class UserApiGetter {

    private static final String GET_INFO_URL = "https://www.flickr.com/services/rest/?method=flickr.people.getInfo&format=json";
    private static final String TAG = "UserApiGetter";

    private UserApiGetter() {

    }

    public static JSONObject getUserInformation(String user_id) throws IOException, JSONException {
        return ApiGetter.readJsonFromUrl(0, 0, getGetInfoUrl(user_id));
    }


    private static String getGetInfoUrl(String user_id) {
        return GET_INFO_URL + "&api_key=a8db956a048e0a962a1f88168d7ad545" + "&user_id=" + user_id + "&nojsoncallback=1";
    }
}
