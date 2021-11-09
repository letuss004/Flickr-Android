package vn.edu.usth.flickr.api;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import vn.edu.usth.flickr.model.VolleyQueueSingleton;

public class ApiGetter {
    private static final String TAG = "ApiGetter";

        private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(int start, int end, String url)
            throws IOException, JSONException {
        try (InputStream is = new URL(url).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String jsonText = readAll(rd);
            //
            if (start != 0 || end != 0) {
                jsonText = jsonText.substring(start, jsonText.length() - end);

            }

            is.close();
            return new JSONObject(jsonText);
        }
    }
//    private static JSONObject object = null;
//
//
//    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
//                response -> object = response,
//                Throwable::printStackTrace);
//        // Access the RequestQueue through your singleton class.
//        VolleyQueueSingleton.getInstance().addToRequestQueue(jsonObjectRequest);
//        return object;
//    }

}
