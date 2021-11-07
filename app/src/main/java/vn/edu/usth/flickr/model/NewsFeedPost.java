package vn.edu.usth.flickr.model;

import com.flickr4java.flickr.people.PeopleInterface;
import com.flickr4java.flickr.people.PersonTag;
import com.flickr4java.flickr.people.PersonTagList;
import com.flickr4java.flickr.people.User;
import com.flickr4java.flickr.photos.comments.Comment;

import java.util.ArrayList;
import java.util.Date;

public class NewsFeedPost {
    private String author, authorId, description, link, tag, title;
    private Date published, dateTaken;
    private ArrayList<String> media;
    private User user;
    private PersonTagList<PersonTag> personTags;
    private ArrayList<Comment> comments;

    public NewsFeedPost(String author, String authorId, String description,
                        String link, String tag, String title,
                        Date dateTaken, Date published, ArrayList<String> media) {
        this.author = author;
        this.authorId = authorId;
        this.description = description;
        this.link = link;
        this.tag = tag;
        this.title = title;
        this.published = published;
        this.dateTaken = dateTaken;
        this.media = media;
    }

    public NewsFeedPost(String author, String authorId, String description, String link,
                        String tag, String title, Date published, Date dateTaken,
                        ArrayList<String> media, User user) {
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
    }

    public NewsFeedPost(String author, String authorId, String description,
                        String link, String tags, String title, Date dateTaken,
                        Date published, ArrayList<String> media, User user,
                        ArrayList<Comment> comments, PersonTagList<PersonTag> personTags) {
        this.author = author;
        this.authorId = authorId;
        this.description = description;
        this.link = link;
        this.tag = tags;
        this.title = title;
        this.published = published;
        this.dateTaken = dateTaken;
        this.media = media;
        this.user = user;
        this.comments = comments;
        this.personTags = personTags;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
                '}';
    }

    public PersonTagList<PersonTag> getPersonTags() {
        return personTags;
    }

    public void setPersonTags(PersonTagList<PersonTag> personTags) {
        this.personTags = personTags;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
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
}
