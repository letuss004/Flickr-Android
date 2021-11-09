package vn.edu.usth.flickr.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.people.PeopleInterface;
import com.flickr4java.flickr.people.User;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;

import vn.edu.usth.flickr.R;
import vn.edu.usth.flickr.api.FlickrApi;
import vn.edu.usth.flickr.api.UserApiGetter;
import vn.edu.usth.flickr.model.NewsFeedPost;
import vn.edu.usth.flickr.ui.NewsFeedFragment;
import vn.edu.usth.flickr.viewmodel.NewsFeedViewModel;

/**
 *
 */
public class NewsFeedAdapterRV extends RecyclerView.Adapter<NewsFeedAdapterRV.NewsFeedViewHolder> {
    private static final String TAG = "NewsFeedAdapterRV";
    private final Context context;
    private NewsFeedFragment newsfeedFragment;
    private ArrayList<NewsFeedPost> newsFeedPosts;


    public NewsFeedAdapterRV(ArrayList<NewsFeedPost> newsFeedPosts,
                             NewsFeedFragment newsfeedFragment) {
        this.newsFeedPosts = newsFeedPosts;
        this.newsfeedFragment = newsfeedFragment;
        this.context = newsfeedFragment.getContext();
        //
    }


    @NonNull
    @Override
    public NewsFeedAdapterRV.NewsFeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newsfeed_post_row, parent, false);
        return new NewsFeedAdapterRV.NewsFeedViewHolder(view);
    }

    private static boolean ready = false;

    @Override
    @SuppressLint("StaticFieldLeak")
    public void onBindViewHolder(@NonNull NewsFeedAdapterRV.NewsFeedViewHolder holder, @SuppressLint("RecyclerView") int position) {
        try {
            AsyncTask<String, String, String> task = new AsyncTask<String, String, String>() {
                @Override
                protected String doInBackground(String... strings) {
                    ready = false;
                    NewsFeedViewModel.updateNewsFeedPosts();
                    return null;
                }

                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    ready = true;
                }
            };
            if (position == newsFeedPosts.size() - 10 && ready) {
                task.execute();
            } else if (newsFeedPosts.size() - 10 < 0 && ready) {
                task.execute();
            }
            //
            setUpDataForViewHolder(holder, position);
        } catch (URISyntaxException | JSONException e) {
            e.printStackTrace();
        }
    }

    public void setNewsFeedPosts(ArrayList<NewsFeedPost> newsFeedPosts) {
        this.newsFeedPosts.addAll(newsFeedPosts);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static boolean isReady() {
        return ready;
    }

    public static void setReady(boolean ready) {
        NewsFeedAdapterRV.ready = ready;
    }

    private void setUpDataForViewHolder(NewsFeedViewHolder holder, int position) throws URISyntaxException, JSONException {
        String imageUri = getImageLinkFromDescription(position);
        String avatarUri = getImageLinkFromDescription(position);
        String author = getOwnerName(newsFeedPosts.get(position).getAuthor());
        String title = newsFeedPosts.get(position).getTitle();
        String likeQuantity = getLikeQuantity(position);
        String commentQuantity = getCommentQuantity(position);
        String textOfComment = getCommentText(position);
        Log.e(TAG, "setUpDataForViewHolder: " + textOfComment);
        Picasso.get().load(imageUri).into(holder.mainImage);
        Picasso.get().load(avatarUri).into(holder.avaImage);
        holder.postOwnerName.setText(author);
        holder.postTitle.setText(title);
        holder.likeQuantity.setText(likeQuantity);
        holder.commentQuantity.setText(commentQuantity);
//        holder.userNameComment.setText(postList.get(position).getCommenterUserName());
        holder.commentContent.setText(textOfComment);
        holder.time.setText(getTime(newsFeedPosts.get(position).getPublished()));
    }

    private String getCommentText(int position) throws JSONException {
        JSONObject photo = newsFeedPosts.get(position).getCommentsList().getJSONObject("comments");
        try {
            return photo.getJSONArray("comment").getJSONObject(0).getString("_content");
        } catch (JSONException e) {
            return "";
        }
    }


    private String getCommentQuantity(int position) throws JSONException {
        JSONObject photo = newsFeedPosts.get(position).getCommentsList().getJSONObject("comments");
        try {
            return String.valueOf(photo.getJSONArray("comment").length());
        } catch (JSONException e) {
            return "0";
        }
    }
//        Log.e(TAG, "getLikeQuantity: " + newsFeedPosts.get(position).getFaveList());

    private String getLikeQuantity(int position) throws JSONException {
        JSONObject photo = newsFeedPosts.get(position).getFaveList().getJSONObject("photo");
        return String.valueOf(photo.getInt("pages"));
    }

    @NonNull
    private String getAvatarPhoto(int position) throws JSONException { // secure failure
        JSONObject user = newsFeedPosts.get(position).getUser();
        JSONObject person = user.getJSONObject("person");
        Integer user_farm = person.getInt("iconfarm");
        String user_server = person.getString("iconserver");
        String user_nsid = person.getString("nsid");
        Log.e(TAG, "getAvatarPhoto: " + UserApiGetter.getAvatarPhoto(user_farm, user_server, user_nsid));
        return UserApiGetter.getAvatarPhoto(user_farm, user_server, user_nsid);
    }

    private String getTime(Date published) {
        long time = new Date().getTime() - published.getTime() / 1000; // second
        int second, minute, hour, day, month, year;
        second = 1;
        minute = second * 60;
        hour = minute * 60;
        day = hour * 24;
        month = day * 30;
        year = month * 365;
        if (time < minute) {
            return "now";
        } else if (time > minute && time < hour) {
            return time / minute + "m";
        } else if (time > hour && time < day) {
            return time / hour + "h";
        } else if (time > day && time < month) {
            return time / day + "d";
        }
        return "long ago";
    }

    private String getOwnerName(String author) {
        return author.split("\"")[1];
    }

    private String getImageLinkFromDescription(int position) {
        String description = newsFeedPosts.get(position).getDescription();
        String[] tmp = description.split("\"");
        return tmp[7];
    }

    @Override
    public int getItemCount() {
        //
        return newsFeedPosts.size();
    }


    /**
     * ------------------------------------------
     */
    public static class NewsFeedViewHolder extends RecyclerView.ViewHolder {
        private final ImageView mainImage, avaImage, likeButton, commentButton, shareButton;
        private final TextView likeQuantity, commentQuantity, postOwnerName, postTitle, userNameComment, commentContent, time;

        public NewsFeedViewHolder(@NonNull View itemView) {
            super(itemView);
            mainImage = itemView.findViewById(R.id.imagePost);
            avaImage = itemView.findViewById(R.id.avaImage);
            likeQuantity = itemView.findViewById(R.id.likeOfPost);
            commentQuantity = itemView.findViewById(R.id.commentOfPost);
            postOwnerName = itemView.findViewById(R.id.userName_newsfeed);
            postTitle = itemView.findViewById(R.id.title_newsFeed);
            userNameComment = itemView.findViewById(R.id.userName_comment_nf);
            commentContent = itemView.findViewById(R.id.commentContent_newsfeed);
            likeButton = itemView.findViewById(R.id.likeButton_nf);
            commentButton = itemView.findViewById(R.id.commentButton_nf);
            shareButton = itemView.findViewById(R.id.shareButton_nf);
            time = itemView.findViewById(R.id.timeOfPost);
        }

    }


}
//@Deprecated
//    private void oldSetUpBindView(@NonNull NewsFeedViewHolder holder, int position) {
//        holder.mainImage.setImageDrawable(postList.get(position).getImage());
//        holder.avaImage.setImageDrawable(postList.get(position).getAvatarImage());
//        holder.postOwnerName.setText(postList.get(position).getOwnerName());
//        holder.postDescription.setText(postList.get(position).getDescription());
//        holder.likeQuantity.setText(String.valueOf(postList.get(position).getLikeQuantity()));
//        holder.commentQuantity.setText(String.valueOf(postList.get(position).getCommentQuantity()));
//        holder.textListOfLike.setText(postList.get(position).getTextLikeList());
//        holder.userNameComment.setText(postList.get(position).getCommenterUserName());
//        holder.commentContent.setText(postList.get(position).getCommentContent());
//        holder.time.setText(postList.get(position).getTime());
//
//
//        holder.commentButton.setOnClickListener(v -> {
//            assert newsfeedFragment.getFragmentManager() != null;
//            newsfeedFragment.getFragmentManager().beginTransaction()
//                    .addToBackStack("Replace newsfeed by comment")
//                    .replace(R.id.navHost_fragment, new CommentFragment())
//                    .commit();
//        });
//    }
//
//    // Don't touch this
//    @Deprecated
//    private void extracted(@NonNull NewsFeedAdapterRV.NewsFeedViewHolder holder, int position) {
//        holder.mainImage.setImageDrawable(postList.get(position).getImage());
//        holder.avaImage.setImageDrawable(postList.get(position).getAvatarImage());
//        holder.postOwnerName.setText(postList.get(position).getOwnerName());
//        holder.postDescription.setText(postList.get(position).getDescription());
//        holder.likeQuantity.setText(postList.get(position).getLikeList().size());
//        holder.commentQuantity.setText(postList.get(position).getCommentList().size());
//        holder.userNameComment.setText(postList.get(position).getCommenterUserName());
//        holder.commentContent.setText(postList.get(position).getCommentContent());
//        holder.time.setText(postList.get(position).getTime());
//
////        holder.textListOfLike.setText(getTextListOfLike(position));
//
//        //
//        setUpCommentInNewsfeed(holder, position);
//
//
//        // onclick
//        holder.commentButton.setOnClickListener(v -> {
//            newsfeedFragment.getFragmentManager().beginTransaction()
//                    .addToBackStack("Replace newsfeed by comment")
//                    .replace(R.id.navHost_fragment, new CommentFragment())
//                    .commit();
//        });
//
//    }
//
//    @Deprecated
//    private void setUpCommentInNewsfeed(@NonNull NewsFeedAdapterRV.NewsFeedViewHolder holder, int position) {
//        int size = postList.get(position).getCommentList().size();
//        if (size != 0) {
//            holder.userNameComment.setText(postList.get(position).getCommentList().get(size - 1).getUser().getName());
//            holder.commentContent.setText(postList.get(position).getCommentList().get(size - 1).getComment());
//        }
//    }