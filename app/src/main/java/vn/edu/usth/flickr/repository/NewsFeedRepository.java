package vn.edu.usth.flickr.repository;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.favorites.FavoritesInterface;
import com.flickr4java.flickr.interestingness.InterestingnessInterface;
import com.flickr4java.flickr.people.PeopleInterface;
import com.flickr4java.flickr.people.PersonTag;
import com.flickr4java.flickr.people.PersonTagList;
import com.flickr4java.flickr.people.User;
import com.flickr4java.flickr.photos.PhotosInterface;
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
    private FlickrApi flickrApi;
    private static final String TAG = "NewsFeedRepository";
    private JSONObject jsonObject;
    private static NewsFeedRepository instance;
    private NewsFeed newsFeed;
    private JSONArray jsonArray;
    private PeopleInterface peopleInterface;
    private final CommentsInterface commentsInterface;
    private final PhotosInterface photosInterface;
    private ArrayList<NewsFeedPost> list;

    private NewsFeedRepository() {
        flickrApi = FlickrApi.getInstance();
        peopleInterface = flickrApi.getPeopleInterface();
        peopleInterface = flickrApi.getPeopleInterface();
        commentsInterface = flickrApi.getCommentsInterface();
        photosInterface = flickrApi.getPhotosInterface();
        fetchNewsFeed();
    }

    public static NewsFeedRepository getInstance() {
        if (instance == null) instance = new NewsFeedRepository();
        return instance;
    }

    public MutableLiveData<ArrayList<NewsFeedPost>> fetchNewsFeed() {
        list = new ArrayList<>();
        try {
            setUpDataForNewsFeedPosts();
        } catch (IOException | JSONException | ParseException | FlickrException e) {
            e.printStackTrace();
        }
        return new MutableLiveData<>(list);
    }

    public MutableLiveData<ArrayList<NewsFeedPost>> updateNewsFeed() {
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
    private void setUpDataForNewsFeedPosts() throws IOException, JSONException, ParseException, FlickrException {
        Log.e(TAG, "setUpDataForNewsFeedPosts: start");
        jsonObject = NewsFeedApiGetter.readJsonFromUrl(RequestUrl.MY_NEWSFEED_FRIEND_STREAM);
        jsonArray = jsonObject.getJSONArray("items");
        //
        for (int i = 0; i < jsonArray.length(); i++) {
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
            getMediaList(mediaJO, media);
            //
            list.add(new NewsFeedPost(author, authorId, description, link, tags,
                    title, dateTaken, published, media));
            fetchUserOnBackground(i, authorId);
            fetchCommentOnBackground(i, photoId);
            fetchPersonTagOnBackground(i, photoId);
            //
//            if (false) {
//                User user = peopleInterface.getInfo(authorId);
//                ArrayList<Comment> comments = (ArrayList<Comment>) commentsInterface.getList(photoId);
//                PersonTagList<PersonTag> personTags = peopleInterface.getList(photoId);
//                list.add(new NewsFeedPost(author, authorId, description, link, tags,
//                        title, dateTaken, published, media, user, comments, personTags));
//            } else {
//                list.add(new NewsFeedPost(author, authorId, description, link, tags,
//                        title, dateTaken, published, media));
//                fetchUserOnBackground(i, authorId);
//                fetchCommentOnBackground(i, photoId);
//                fetchPersonTagOnBackground(i, photoId);
//            }
            //
            Log.e(TAG, "setUpDataForNewsFeedPosts: loop" + i + "end");
        }
        Log.e(TAG, "setUpDataForNewsFeedPosts: finished");

    }

    @SuppressLint("StaticFieldLeak")

    private void fetchPersonTagOnBackground(int position, String photoId) {
        AsyncTask<String, String, PersonTagList<PersonTag>> asyncTask = new AsyncTask<String, String, PersonTagList<PersonTag>>() {
            @Override
            protected PersonTagList<PersonTag> doInBackground(String... strings) {
                PersonTagList<PersonTag> personTags = null;
                try {
                    personTags = peopleInterface.getList(photoId);
                } catch (FlickrException e) {
                    e.printStackTrace();
                }
                return personTags;
            }

            @Override
            protected void onPostExecute(PersonTagList<PersonTag> personTags) {
                super.onPostExecute(personTags);
                list.get(position).setPersonTags(personTags);
            }
        };
        asyncTask.execute();
    }

    @SuppressLint("StaticFieldLeak")
    private void fetchCommentOnBackground(int position, String photoId) {
        AsyncTask<String, String, ArrayList<Comment>> asyncTask = new AsyncTask<String, String, ArrayList<Comment>>() {
            @Override
            protected ArrayList<Comment> doInBackground(String... strings) {
                ArrayList<Comment> comments = null;
                try {
                    comments = (ArrayList<Comment>) commentsInterface.getList(photoId);
                } catch (FlickrException e) {
                    e.printStackTrace();
                }
                return comments;
            }

            @Override
            protected void onPostExecute(ArrayList<Comment> comments) {
                super.onPostExecute(comments);
                list.get(position).setComments(comments);
            }
        };
        asyncTask.execute();
    }

    @SuppressLint("StaticFieldLeak")
    private void fetchUserOnBackground(int position, String authorId) {
        AsyncTask<String, String, User> asyncTask = new AsyncTask<String, String, User>() {
            @Override
            protected User doInBackground(String... strings) {
                User user = null;
                try {
                    user = peopleInterface.getInfo(authorId);
                } catch (FlickrException e) {
                    e.printStackTrace();
                }
                return user;
            }

            @Override
            protected void onPostExecute(User user) {
                super.onPostExecute(user);
                list.get(position).setUser(user);
            }
        };
        asyncTask.execute();
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


    private interface CallBackListener {
        void finished();
    }
}
