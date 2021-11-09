package vn.edu.usth.flickr.api;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class UserApiGetter {

    private static final String GET_INFO_URL = "https://www.flickr.com/services/rest/?method=flickr.people.getInfo&format=json";
    private static final String TAG = "UserApiGetter";

    private UserApiGetter() {

    }

    public static String getAvatarPhoto(Integer icon_farm, String icon_server, String nsid) {
        return "http://farm" + icon_farm + ".staticflickr.com/" + icon_server + "/buddyicons/" + nsid + ".jpg" + "&api_key=a8db956a048e0a962a1f88168d7ad545&auth_token=72157720820879664-28f918ba7e67f57d";
    }

    public static JSONObject getUserInformation(String user_id) throws IOException, JSONException {
        return ApiGetter.readJsonFromUrl(0, 0, getGetInfoUrl(user_id));
    }


    private static String getGetInfoUrl(String user_id) {
        return GET_INFO_URL + "&api_key=a8db956a048e0a962a1f88168d7ad545" + "&user_id=" + user_id + "&nojsoncallback=1";
    }
}
