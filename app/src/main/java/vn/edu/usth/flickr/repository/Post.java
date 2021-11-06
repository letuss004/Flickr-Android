package vn.edu.usth.flickr.repository;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.Date;

public class Post {
    private ArrayList<Post.Like> likeList;
    private ArrayList<Post.Comment> commentList;
    private Date dateTime;
    private Drawable image, avatarImage;
    private int likeQuantity, commentQuantity;
    private String ownerName, description;
    private String textLikeList, commenterUserName, commentContent, time;

    public Post(Drawable image, Drawable avatarImage) {
        this.image = image;
        this.avatarImage = avatarImage;
    }

    public Post(Drawable image, Drawable avatarImage, int likeQuantity, int commentQuantity, String ownerName, String description, String textLikeList, String commenterUserName, String commentContent, String time) {
        this.image = image;
        this.avatarImage = avatarImage;
        this.likeQuantity = likeQuantity;
        this.commentQuantity = commentQuantity;
        this.ownerName = ownerName;
        this.description = description;
        this.textLikeList = textLikeList;
        this.commenterUserName = commenterUserName;
        this.commentContent = commentContent;
        this.time = time;
    }

    public Post(ArrayList<Like> likeList, ArrayList<Comment> commentList, String ownerName, String description, Drawable image, Drawable avatarImage) {
        this.likeList = likeList;
        this.commentList = commentList;
        this.ownerName = ownerName;
        this.description = description;
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

    public int getLikeQuantity() {
        return likeQuantity;
    }

    public void setLikeQuantity(int likeQuantity) {
        this.likeQuantity = likeQuantity;
    }

    public int getCommentQuantity() {
        return commentQuantity;
    }

    public void setCommentQuantity(int commentQuantity) {
        this.commentQuantity = commentQuantity;
    }

    public String getTextLikeList() {
        return textLikeList;
    }

    public void setTextLikeList(String textLikeList) {
        this.textLikeList = textLikeList;
    }

    public String getCommenterUserName() {
        return commenterUserName;
    }

    public void setCommenterUserName(String commenterUserName) {
        this.commenterUserName = commenterUserName;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    /**
     *
     */
    public static class Comment {
        private String userName, comment;
        private Drawable avatar;

        private User user;
        private Date dateTime;

        public Comment(String userName, String comment, Drawable avatar) {
            this.userName = userName;
            this.comment = comment;
            this.avatar = avatar;
        }

        public Comment(User user, String comment, Date dateTime) {
            this.user = user;
            this.comment = comment;
            this.dateTime = dateTime;
        }

        public Comment(User user, String comment) {
            this.user = user;
            this.comment = comment;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public Drawable getAvatar() {
            return avatar;
        }

        public void setAvatar(Drawable avatar) {
            this.avatar = avatar;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public Date getDateTime() {
            return dateTime;
        }

        public void setDateTime(Date dateTime) {
            this.dateTime = dateTime;
        }
    }


    /**
     *
     */
    public static class Like {
        private User user;
        private Date dateTime;

        public Like(User user, Date dateTime) {
            this.user = user;
            this.dateTime = dateTime;
        }

        public Like(User user) {
            this.user = user;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public Date getDateTime() {
            return dateTime;
        }

        public void setDateTime(Date dateTime) {
            this.dateTime = dateTime;
        }
    }


}
