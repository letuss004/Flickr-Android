package vn.edu.usth.flickr;


import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.groups.Group;
import com.flickr4java.flickr.people.PeopleInterface;
import com.flickr4java.flickr.people.User;
import com.squareup.picasso.Picasso;

import org.junit.Test;

import java.util.Date;

import vn.edu.usth.flickr.model.RequestUrl;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        Flickr flickr = new Flickr("a8db956a048e0a962a1f88168d7ad545", "8a32f6762e1abc37", new REST());
        String a = "jsonFlickrApi({\"person\":";
        System.out.println(a.substring(14));


//        try {
//            a = flickr.getUrlsInterface().getUserPhotos("194108707@N08");
//            Group group = new Group();
//            group.getBuddyIconUrl();
//
//            User person = iface.getInfo("nsid");
//            System.out.println(person.getSecureBuddyIconUrl());
//
//        } catch (FlickrException e) {
//            e.printStackTrace();
//        }
//        System.out.println(a);
    }
}