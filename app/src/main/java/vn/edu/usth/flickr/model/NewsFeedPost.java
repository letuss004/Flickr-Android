package vn.edu.usth.flickr.model;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class NewsFeedPost {
    private String author, authorId, description, link, tag, title;
    private Date published, dateTaken;
    private ArrayList<String> media;
    private JSONObject user;
    private JSONObject faveList;
    private JSONObject commentsList;

    public NewsFeedPost() {
    }

    public NewsFeedPost(String author, String authorId, String description, String link,
                        String tag, String title, Date published, Date dateTaken,
                        ArrayList<String> media, JSONObject user, JSONObject faveList,
                        JSONObject commentsList) {
        this.author = author;
        this.authorId = authorId;
        this.description = description;
        this.link = link;
        this.tag = tag;
        this.title = title;
        this.published = published;
        this.dateTaken = dateTaken;
        this.media = media;
        this.user = user;
        this.faveList = faveList;
        this.commentsList = commentsList;
    }

    @Override
    public String toString() {
        return "NewsFeedPost{" +
                "author='" + author + '\'' +
                ", authorId='" + authorId + '\'' +
                ", description='" + description + '\'' +
                ", link='" + link + '\'' +
                ", tag='" + tag + '\'' +
                ", title='" + title + '\'' +
                ", published=" + published +
                ", dateTaken=" + dateTaken +
                ", media=" + media +
                ", user=" + user +
                ", faveList=" + faveList +
                ", commentsList=" + commentsList +
                '}';
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPublished() {
        return published;
    }

    public void setPublished(Date published) {
        this.published = published;
    }

    public Date getDateTaken() {
        return dateTaken;
    }

    public void setDateTaken(Date dateTaken) {
        this.dateTaken = dateTaken;
    }

    public ArrayList<String> getMedia() {
        return media;
    }

    public void setMedia(ArrayList<String> media) {
        this.media = media;
    }

    public JSONObject getUser() {
        return user;
    }

    public void setUser(JSONObject user) {
        this.user = user;
    }

    public JSONObject getFaveList() {
        return faveList;
    }

    public void setFaveList(JSONObject faveList) {
        this.faveList = faveList;
    }

    public JSONObject getCommentsList() {
        return commentsList;
    }

    public void setCommentsList(JSONObject commentsList) {
        this.commentsList = commentsList;
    }
}
