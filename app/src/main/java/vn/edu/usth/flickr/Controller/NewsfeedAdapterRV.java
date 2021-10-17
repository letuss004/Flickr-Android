package vn.edu.usth.flickr.Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vn.edu.usth.flickr.View.CommentFragment;
import vn.edu.usth.flickr.Model.Post;
import vn.edu.usth.flickr.View.NewsfeedFragment;
import vn.edu.usth.flickr.R;

/**
 *
 */
public class NewsfeedAdapterRV extends RecyclerView.Adapter<NewsfeedAdapterRV.NewsFeedViewHolder> {
    private ArrayList<Post> postList;
    private Context context;
    private NewsfeedFragment newsfeedFragment;

    public NewsfeedAdapterRV(ArrayList<Post> postList, Context context) {
        this.postList = postList;
        this.context = context;
    }

    public NewsfeedAdapterRV(ArrayList<Post> postList, View view) {
        context = view.getContext();
    }

    public NewsfeedAdapterRV(ArrayList<Post> postList, Context context, NewsfeedFragment newsfeedFragment) {
        this.postList = postList;
        this.context = context;
        this.newsfeedFragment = newsfeedFragment;
    }


    @NonNull
    @Override
    public NewsfeedAdapterRV.NewsFeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newsfeed_post_row, parent, false);
        return new NewsfeedAdapterRV.NewsFeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsfeedAdapterRV.NewsFeedViewHolder holder, int position) {
        extracted(holder, position);
    }

    // Don't touch this
    private void extracted(@NonNull NewsfeedAdapterRV.NewsFeedViewHolder holder, int position) {
        holder.mainImage.setImageDrawable(postList.get(position).getImage());
        holder.avaImage.setImageDrawable(postList.get(position).getAvatarImage());
        holder.postOwnerName.setText(postList.get(position).getOwnerName());
        holder.postDescription.setText(postList.get(position).getDescription());
        holder.likeQuantity.setText(String.valueOf(postList.get(position).getLikeList().size()));
        holder.commentQuantity.setText(String.valueOf(postList.get(position).getCommentList().size()));
        holder.textListOfLike.setText(getTextListOfLike(position));

        //
        setUpCommentInNewsfeed(holder, position);


        // onclick
        holder.commentButton.setOnClickListener(v -> {
            newsfeedFragment.getFragmentManager().beginTransaction()
                    .addToBackStack("Replace newsfeed by comment")
                    .replace(R.id.newsfeed_fragment_ctn, new CommentFragment())
                    .commit();
        });

    }



    private void setUpCommentInNewsfeed(@NonNull NewsfeedAdapterRV.NewsFeedViewHolder holder, int position) {
        int size = postList.get(position).getCommentList().size();
        if (size != 0) {
            holder.userNameComment.setText(postList.get(position).getCommentList().get(size - 1).getUser().getName());
            holder.commentContent.setText(postList.get(position).getCommentList().get(size - 1).getComment());
        }
    }

    @Override
    public int getItemCount() {
        //
        return postList.size();
    }

    private String getTextListOfLike(int position) {
        String res = "";
        for (int i = 0; i < postList.get(position).getLikeList().size(); i++) {
            res += postList.get(position).getLikeList().get(i).getUser().getName();
            if (i != postList.get(position).getLikeList().size() - 1) {
                res += ", ";
            }
        }
        return res;
    }


    /**
     *
     */
    public static class NewsFeedViewHolder extends RecyclerView.ViewHolder {
        private final ImageView mainImage, avaImage, likeButton, commentButton, shareButton;
        private final TextView likeQuantity, commentQuantity, postOwnerName, postDescription, textListOfLike, userNameComment, commentContent;

        public NewsFeedViewHolder(@NonNull View itemView) {
            super(itemView);
            mainImage = itemView.findViewById(R.id.imagePost);
            avaImage = itemView.findViewById(R.id.avaImage);
            likeQuantity = itemView.findViewById(R.id.likeOfPost);
            commentQuantity = itemView.findViewById(R.id.commentOfPost);
            postOwnerName = itemView.findViewById(R.id.userName_newsfeed);
            postDescription = itemView.findViewById(R.id.description);
            textListOfLike = itemView.findViewById(R.id.listOfLike);
            userNameComment = itemView.findViewById(R.id.userName_comment_nf);
            commentContent = itemView.findViewById(R.id.commentContent_newsfeed);
            likeButton = itemView.findViewById(R.id.likeButton_nf);
            commentButton = itemView.findViewById(R.id.commentButton_nf);
            shareButton = itemView.findViewById(R.id.shareButton_nf);

        }

    }

}