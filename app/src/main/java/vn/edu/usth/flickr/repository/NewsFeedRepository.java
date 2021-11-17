package vn.edu.usth.flickr.repository;

import android.annotation.SuppressLint;
import android.util.Log;

import com.flickr4java.flickr.FlickrException;

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
import vn.edu.usth.flickr.api.UserApiGetter;
import vn.edu.usth.flickr.model.NewsFeedPost;

public class NewsFeedRepository {
    private static final String TAG = "NewsFeedRepository";
    private static NewsFeedRepository instance;
    private ArrayList<NewsFeedPost> list = new ArrayList<>();

    public ArrayList<NewsFeedPost> getList() {
        return list;
    }

    private NewsFeedRepository() {
    }

    public static NewsFeedRepository getInstance() {
        if (instance == null) instance = new NewsFeedRepository();
        return instance;
    }

    public ArrayList<NewsFeedPost> fetchNewsFeed() {
        try {
            setUpDataForNewsFeedPosts();
        } catch (IOException | JSONException | ParseException | FlickrException e) {
            e.printStackTrace();
        }
        return list;
    }

    /*
     * return a new list of post in Flickr
     * Note: this.list != list
     * */
    public ArrayList<NewsFeedPost> updateNewsFeed() {
        try {
            return updateDataForNewsFeedPosts();
        } catch (IOException | FlickrException | ParseException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * ----------------------------------- private place -------------------------------------
     *
     * @throws IOException
     * @throws JSONException
     * @throws ParseException
     * @throws FlickrException
     */
    private synchronized void setUpDataForNewsFeedPosts()
            throws IOException, JSONException, ParseException, FlickrException {
        Log.e(TAG, "setUpDataForNewsFeedPosts: start");
        JSONObject jsonObject = NewsFeedApiGetter.getPublicFeedFriendStream(FlickrApi.NSID, 0, 1);
        JSONArray jsonArray = jsonObject.getJSONArray("items");
        //
        for (int i = 0; i < 2; i++) {
            Log.e(TAG, "setUpDataForNewsFeedPosts: loop" + i + "start");
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
            JSONObject user = UserApiGetter.getUserInformation(authorId);
            JSONObject faveList = NewsFeedApiGetter.getPostFaveList(photoId);
            JSONObject commentsList = NewsFeedApiGetter.getPostCommentsList(photoId);
            //
            getMediaList(mediaJO, media);
            list.add(new NewsFeedPost(author, authorId, description, link, tags,
                    title, dateTaken, published, media, user, faveList, commentsList));
            Log.e(TAG, "setUpDataForNewsFeedPosts: loop" + i + "end");
        }
        Log.e(TAG, "setUpDataForNewsFeedPosts: finished");
    }


    private synchronized ArrayList<NewsFeedPost> updateDataForNewsFeedPosts()
            throws IOException, JSONException, ParseException, FlickrException {
        Log.e(TAG, "updateDataForNewsFeedPosts: start");
        JSONObject jsonObject = NewsFeedApiGetter.getPublicFeed();
        JSONArray jsonArray = jsonObject.getJSONArray("items");
        ArrayList<NewsFeedPost> list = new ArrayList<>();
        int count = 5;
        //
        for (int i = 0; i < 4; i++) {
            Log.e(TAG, "updateDataForNewsFeedPosts: loop" + i + " start");
            ///
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
            JSONObject user = UserApiGetter.getUserInformation(authorId);
            JSONObject faveList = NewsFeedApiGetter.getPostFaveList(photoId);
            JSONObject commentsList = NewsFeedApiGetter.getPostCommentsList(photoId);
            //
            getMediaList(mediaJO, media);
            list.add(new NewsFeedPost(author, authorId, description, link, tags,
                    title, dateTaken, published, media, user, faveList, commentsList));
//            if (count < 15) {
//                count = count + 5;
//            }
            Log.e(TAG, "updateDataForNewsFeedPosts: loop" + i + " end");
        }
        Log.e(TAG, "updateDataForNewsFeedPosts: end");
        return list;
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
