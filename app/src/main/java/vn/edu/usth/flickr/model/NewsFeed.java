package vn.edu.usth.flickr.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Date;

public class NewsFeed {
    private String description, generator, link, title;
    private Date modified;
    private JSONArray feedPosts;

    public NewsFeed(String description, String generator, String link, String title, Date modified) {
        this.description = description;
        this.generator = generator;
        this.link = link;
        this.title = title;
        this.modified = modified;
    }

    public NewsFeed(String description, String generator, String link, String title, Date modified, JSONArray feedPosts) {
        this.description = description;
        this.generator = generator;
        this.link = link;
        this.title = title;
        this.modified = modified;
        this.feedPosts = feedPosts;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenerator() {
        return generator;
    }

    public void setGenerator(String generator) {
        this.generator = generator;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public JSONArray getFeedPosts() {
        return feedPosts;
    }

    public void setFeedPosts(JSONArray feedPosts) {
        this.feedPosts = feedPosts;
    }
}
