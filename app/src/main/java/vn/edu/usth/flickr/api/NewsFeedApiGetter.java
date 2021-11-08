package vn.edu.usth.flickr.api;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public final class NewsFeedApiGetter {
    private static final String TAG = "NewsFeedApi";
    private static final String PUBLIC_URL = "https://www.flickr.com/services/feeds/photos_public.gne/?format=json";
    public static final String FRIENDS_URL = "https://www.flickr.com/services/feeds/photos_friends.gne/?format=json";
    public static final String FAVE_LIST_URL = "https://www.flickr.com/services/rest/?method=flickr.photos.getFavorites&format=json";
    public static final String COMMENT_LIST_URL = "https://www.flickr.com/services/rest/?method=flickr.photos.comments.getList&format=json";

    private NewsFeedApiGetter() {
    }


    public static JSONObject getPublicFeedFriendStream(String userId, int friend, int displayAll)
            throws IOException, JSONException {
        Log.e(TAG, "getPublicFeedFriendStream: " + getFriendsUrl(userId, friend, displayAll));
        return ApiGetter.readJsonFromUrl(15, 1, getFriendsUrl(userId, friend, displayAll));
    }

    public static JSONObject getPostFaveList(String photo_id) throws IOException, JSONException {
        return ApiGetter.readJsonFromUrl(14, 1, getFaveListUrl(photo_id));
    }

    private static String getFaveListUrl(String photo_id) {
        return FAVE_LIST_URL + "&api_key=" + FlickrApi.API_KEY + "&photo_id=" + photo_id;
    }


    public static JSONObject getPublicFeed() throws IOException, JSONException {
        return ApiGetter.readJsonFromUrl(15, 1, getPublicUrl());
    }

    private static String getPublicUrl() {
        return PUBLIC_URL;
    }

    private static String getFriendsUrl(String id) {
        return getFriendsUrl(id, 1, 1);
    }

    private static String getFriendsUrl(String userId, int friend, int displayAll) {
        return FRIENDS_URL + "&user_id=" + userId + "&display_all=" + displayAll + "&friends=" + friend;
    }


    public static JSONObject getPostCommentsList(String photo_id) throws IOException, JSONException {
        Log.e(TAG, "getPostCommentsList: " + getCommentsListUrl(photo_id));
        return ApiGetter.readJsonFromUrl(14, 1, getCommentsListUrl(photo_id));
    }

    private static String getCommentsListUrl(String photo_id) {
        return COMMENT_LIST_URL + "&api_key=" + FlickrApi.API_KEY + "&photo_id=" + photo_id;
    }
}
