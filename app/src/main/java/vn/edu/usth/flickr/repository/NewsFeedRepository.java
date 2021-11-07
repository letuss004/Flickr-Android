package vn.edu.usth.flickr.repository;

import android.annotation.SuppressLint;

import androidx.lifecycle.MutableLiveData;

import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.people.PeopleInterface;
import com.flickr4java.flickr.people.User;
import com.flickr4java.flickr.photos.comments.Comment;
import com.flickr4java.flickr.photos.comments.CommentsInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import vn.edu.usth.flickr.api.FlickrApi;
import vn.edu.usth.flickr.api.NewsFeedApiGetter;
import vn.edu.usth.flickr.model.NewsFeed;
import vn.edu.usth.flickr.model.NewsFeedPost;
import vn.edu.usth.flickr.model.RequestUrl;

public class NewsFeedRepository {
    private JSONObject jsonObject;
    private static NewsFeedRepository instance;
    private NewsFeed newsFeed;
    private JSONArray jsonArray;
    private PeopleInterface peopleInterface;
    private CommentsInterface commentsInterface;
    private FlickrApi flickrApi;

    private NewsFeedRepository() {
        flickrApi = FlickrApi.getInstance();
        peopleInterface = flickrApi.getPeopleInterface();
    }

    public static NewsFeedRepository getInstance() {
        if (instance == null) instance = new NewsFeedRepository();
        return instance;
    }

    public MutableLiveData<ArrayList<NewsFeedPost>> fetchNewsFeed() {
        ArrayList<NewsFeedPost> list = new ArrayList<>();
        try {
            setUpDataForNewsFeedPosts(list);
        } catch (IOException | JSONException | ParseException | FlickrException e) {
            e.printStackTrace();
        }
        return new MutableLiveData<>(list);
    }

    @Deprecated
    public ArrayList<NewsFeedPost> fetchNewsFeedTest() {
        ArrayList<NewsFeedPost> list = new ArrayList<>();
        try {
            setUpDataForNewsFeedPosts(list);
        } catch (IOException | JSONException | ParseException | FlickrException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void setUpDataForNewsFeedPosts(ArrayList<NewsFeedPost> list) throws IOException, JSONException, ParseException, FlickrException {
        peopleInterface = flickrApi.getPeopleInterface();
        commentsInterface = flickrApi.getCommentsInterface();
        jsonObject = NewsFeedApiGetter.readJsonFromUrl(RequestUrl.MY_NEWSFEED_FRIEND_STREAM);
        jsonArray = jsonObject.getJSONArray("items");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject tmp = (JSONObject) jsonArray.get(i);
            // Ugly but good =))
            JSONObject mediaJO = (JSONObject) tmp.get("media");
            ArrayList<String> media = new ArrayList<>();
            String author = (String) tmp.get("author");
            String authorId = (String) tmp.get("author_id");
            String description = (String) tmp.get("description"); //
            String link = (String) tmp.get("link");
            String tags = ""; //Todo: Missing tag feature
            String title = (String) tmp.get("title");
            Date dateTaken = parseFlickrDate((String) tmp.get("date_taken"));
            Date published = parseFlickrDate((String) tmp.get("published"));
            String photoId = getPhotoIdFromLink(link);
            //
            getMediaList(mediaJO, media);
            User user = peopleInterface.getInfo(authorId);
            ArrayList<Comment> comments = (ArrayList<Comment>) commentsInterface.getList(photoId);
            //
            list.add(new NewsFeedPost(author, authorId, description, link, tags,
                    title, dateTaken, published, media, user, comments));
        }
    }

    private String getPhotoIdFromLink(String link) {
        return link.split("/")[5].split("\\\\")[0];
    }

    private void getMediaList(JSONObject mediaJO, ArrayList<String> media) throws JSONException {
        for (int j = 0; j < mediaJO.length(); j++) {
            media.add((String) mediaJO.get("m")); // Todo: missing length > 1
        }
    }

    private Date parseFlickrDate(String time) throws ParseException {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat flickFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return flickFormat.parse(time);
    }

}
