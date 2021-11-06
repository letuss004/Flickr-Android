package vn.edu.usth.flickr.repository;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import vn.edu.usth.flickr.api.NewsFeedApiGetter;
import vn.edu.usth.flickr.model.NewsFeed;
import vn.edu.usth.flickr.model.NewsFeedPost;
import vn.edu.usth.flickr.viewmodel.RequestUrl;

public class NewsFeedRepository {
    private JSONObject jsonObject;
    private static NewsFeedRepository instance;
    private NewsFeed newsFeed;
    private JSONArray jsonArray;

    private NewsFeedRepository() {
    }

    public static NewsFeedRepository getInstance() {
        if (instance == null) instance = new NewsFeedRepository();
        return instance;
    }

    public MutableLiveData<ArrayList<NewsFeedPost>> fetchNewsFeed() {
        ArrayList<NewsFeedPost> list = new ArrayList<>();
        try {
            setUpDataForNewsFeedPosts(list);
        } catch (IOException | JSONException | ParseException e) {
            e.printStackTrace();
        }
        return new MutableLiveData<>(list);
    }

    public ArrayList<NewsFeedPost> fetchNewsFeedTest() {
        ArrayList<NewsFeedPost> list = new ArrayList<>();
        try {
            setUpDataForNewsFeedPosts(list);
        } catch (IOException | JSONException | ParseException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void setUpDataForNewsFeedPosts(ArrayList<NewsFeedPost> list) throws IOException, JSONException, ParseException {
        jsonObject = NewsFeedApiGetter.readJsonFromUrl(RequestUrl.MY_NEWSFEED_FRIEND_STREAM);
        jsonArray = jsonObject.getJSONArray("items");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject tmp = (JSONObject) jsonArray.get(i);
            //
            JSONObject mediaJO = (JSONObject) tmp.get("media");
            ArrayList<String> media = new ArrayList<>();
            String author = (String) tmp.get("author");
            String authorId = (String) tmp.get("author_id");
            String description = (String) tmp.get("description"); //
            String link = (String) tmp.get("link");
            String tags = (String) tmp.get("tags");
            String title = (String) tmp.get("title");
            Date dateTaken = parseFlickrDate((String) tmp.get("date_taken"));
            Date published = parseFlickrDate((String) tmp.get("published"));
            for (int j = 0; j < mediaJO.length(); j++) {
                media.add((String) mediaJO.get("m")); // missing length > 1
            }
            list.add(new NewsFeedPost(author, authorId, description, link, tags,
                    title, dateTaken, published, media));
        }
    }

    private Date parseFlickrDate(String time) throws ParseException {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat flickFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return flickFormat.parse(time);
    }

}
