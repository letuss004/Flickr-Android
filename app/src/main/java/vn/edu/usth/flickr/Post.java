package vn.edu.usth.flickr;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Date;

public class Post {
    private ArrayList<Post.Like> likeList;
    private ArrayList<Post.Comment> commentList;
    private String ownerName, description;
    private Date dateTime;
    private Drawable image, avatarImage;

    public Post(Drawable image, Drawable avatarImage) {
        this.image = image;
        this.avatarImage = avatarImage;
    }

    public Post(ArrayList<Like> likeList, ArrayList<Comment> commentList, String ownerName, String description, Date dateTime, Drawable image, Drawable avatarImage) {
        this.likeList = likeList;
        this.commentList = commentList;
        this.ownerName = ownerName;
        this.description = description;
        this.dateTime = dateTime;
        this.image = image;
        this.avatarImage = avatarImage;
    }

    public Post(ArrayList<Like> likeList, ArrayList<Comment> commentList, String ownerName, String description, Date dateTime, Drawable image) {
        this.likeList = likeList;
        this.commentList = commentList;
        this.ownerName = ownerName;
        this.description = description;
        this.dateTime = dateTime;
        this.image = image;
    }

    public Drawable getAvatarImage() {
        return avatarImage;
    }

    public void setAvatarImage(Drawable avatarImage) {
        this.avatarImage = avatarImage;
    }

    public ArrayList<Like> getLikeList() {
        return likeList;
    }

    public void setLikeList(ArrayList<Like> likeList) {
        this.likeList = likeList;
    }

    public ArrayList<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(ArrayList<Comment> commentList) {
        this.commentList = commentList;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public static class Comment {
        private User user;
        private String comment;
        private Date dateTime;
    }

    public static class Like {
        private User user;
        private Date dateTime;
    }


}
