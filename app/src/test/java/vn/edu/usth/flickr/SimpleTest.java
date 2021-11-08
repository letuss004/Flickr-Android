package vn.edu.usth.flickr;

import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.blogs.BlogsInterface;
import com.flickr4java.flickr.people.PeopleInterface;
import com.flickr4java.flickr.people.PersonTag;
import com.flickr4java.flickr.people.PersonTagList;
import com.flickr4java.flickr.people.User;
import com.flickr4java.flickr.photos.comments.Comment;
import com.flickr4java.flickr.photos.comments.CommentsInterface;

import org.json.JSONException;
import org.junit.Test;

import java.io.IOException;
import java.security.Identity;
import java.util.ArrayList;
import java.util.List;

import vn.edu.usth.flickr.api.FlickrApi;
import vn.edu.usth.flickr.api.NewsFeedApiGetter;
import vn.edu.usth.flickr.api.UserApiGetter;

public class SimpleTest {
    @Test
    public void test() throws IOException, JSONException {
        String a = "a" + null;

//        System.out.println("start");
//        FlickrApi flickrApi = FlickrApi.getInstance();
//        List<Comment> personTags = null;
//        ArrayList<Comment> comments = null;
//        User user = null;
//        try {
//            personTags = flickrApi.getCommentsInterface().getList("51661120537");
////            comments = (ArrayList<Comment>) flickrApi.getCommentsInterface().getList("51661120537");
////            user = flickrApi.getPeopleInterface().getInfo("95314539@N05");
//
//        } catch (FlickrException e) {
//            e.printStackTrace();
//        }
////        NewsFeedApiGetter.readJsonFromUrl("https://www.flickr.com/services/feeds/photos_public.gne?format=json");
//        System.out.println("end");
////        System.out.println(personTags.size());
////        System.out.println(comments.size());
////        System.out.println(user);

    }
}
