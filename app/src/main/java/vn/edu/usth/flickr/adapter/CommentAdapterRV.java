package vn.edu.usth.flickr.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vn.edu.usth.flickr.R;
import vn.edu.usth.flickr.model.Post;

/**
 *
 */
public class CommentAdapterRV extends RecyclerView.Adapter<CommentAdapterRV.CommentViewHolder> {
    private ArrayList<Post.Comment> commentArrayList;
    private Context context;

    public CommentAdapterRV(ArrayList<Post.Comment> commentArrayList, Context context) {
        this.commentArrayList = commentArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CommentAdapterRV.CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_row, parent, false);
        return new CommentAdapterRV.CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapterRV.CommentViewHolder holder, int position) {
        holder.avaUserComment.setImageDrawable(commentArrayList.get(position).getAvatar());
        holder.userName.setText(commentArrayList.get(position).getUserName());
        holder.userComment.setText(commentArrayList.get(position).getComment());



    }

    @Override
    public int getItemCount() {
        return commentArrayList.size();
    }


    /**
     *
     */
    public static class CommentViewHolder extends RecyclerView.ViewHolder {
        private final ImageView avaUserComment;
        private final TextView userName, userComment;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            avaUserComment = itemView.findViewById(R.id.avatarUserComment);
            userName = itemView.findViewById(R.id.userNameComment_cmt);
            userComment = itemView.findViewById(R.id.commentContent_cmt);
        }
    }
}
