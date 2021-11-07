package vn.edu.usth.flickr.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import vn.edu.usth.flickr.R;
import vn.edu.usth.flickr.model.NewsFeedPost;
import vn.edu.usth.flickr.ui.NewsFeedFragment;

/**
 *
 */
public class NewsFeedAdapterRV extends RecyclerView.Adapter<NewsFeedAdapterRV.NewsFeedViewHolder> {
    private static final String TAG = "NewsFeedAdapterRV";
    private final Context context;
    private NewsFeedFragment newsfeedFragment;
    private ArrayList<NewsFeedPost> newsFeedPosts;
    private final ImageLoader imageLoader = ImageLoader.getInstance(); // Get singleton instance


    public NewsFeedAdapterRV(ArrayList<NewsFeedPost> newsFeedPosts, Context context, NewsFeedFragment newsfeedFragment) {
        this.newsFeedPosts = newsFeedPosts;
        this.context = context;
        this.newsfeedFragment = newsfeedFragment;
    }


    @NonNull
    @Override
    public NewsFeedAdapterRV.NewsFeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newsfeed_post_row, parent, false);
        return new NewsFeedAdapterRV.NewsFeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsFeedAdapterRV.NewsFeedViewHolder holder, int position) {
        try {
            setUpDataForViewHolder(holder, position);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void setUpDataForViewHolder(NewsFeedViewHolder holder, int position) throws URISyntaxException {
        String imageUri = getImageLinkFromDescription(position);
        Glide.with(context).load(imageUri).into(holder.mainImage);
        Log.e(TAG, "setUpDataForViewHolder: " + getImageLinkFromDescription(position));
//        holder.mainImage.setImageDrawable(postList.get(position).getImage());
//        holder.avaImage.setImageDrawable(postList.get(position).getAvatarImage());
//        holder.postOwnerName.setText(postList.get(position).getOwnerName());
//        holder.postTitle.setText(postList.get(position).getDescription());
//        holder.likeQuantity.setText(String.valueOf(postList.get(position).getLikeQuantity()));
//        holder.commentQuantity.setText(String.valueOf(postList.get(position).getCommentQuantity()));
//        holder.textListOfLike.setText(postList.get(position).getTextLikeList());
//        holder.userNameComment.setText(postList.get(position).getCommenterUserName());
//        holder.commentContent.setText(postList.get(position).getCommentContent());
//        holder.time.setText(postList.get(position).getTime());
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
     *
     */
    public static class NewsFeedViewHolder extends RecyclerView.ViewHolder {
        private final ImageView mainImage, avaImage, likeButton, commentButton, shareButton;
        private final TextView likeQuantity, commentQuantity, postOwnerName, postTitle, textListOfLike, userNameComment, commentContent, time;

        public NewsFeedViewHolder(@NonNull View itemView) {
            super(itemView);
            mainImage = itemView.findViewById(R.id.imagePost);
            avaImage = itemView.findViewById(R.id.avaImage);
            likeQuantity = itemView.findViewById(R.id.likeOfPost);
            commentQuantity = itemView.findViewById(R.id.commentOfPost);
            postOwnerName = itemView.findViewById(R.id.userName_newsfeed);
            postTitle = itemView.findViewById(R.id.title_newsFeed);
            textListOfLike = itemView.findViewById(R.id.listOfLike);
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