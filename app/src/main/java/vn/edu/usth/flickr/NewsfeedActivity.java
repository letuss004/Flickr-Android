package vn.edu.usth.flickr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class NewsFeedActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager recViewLM;

    private ArrayList<Post> postList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsfeed);

        setUpPostList();
        setUpRecyclerView();
    }


    private void setUpRecyclerView() {
        recyclerView = findViewById(R.id.rv_newsfeed);

        recViewLM = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recViewLM);

        adapter = new RvAdapter(postList, this);
        recyclerView.setAdapter(adapter);
    }


    private void setUpPostList() {
        Post post = new Post(AppCompatResources.getDrawable(this, R.drawable.bird),
                AppCompatResources.getDrawable(this, R.drawable.user_ava));
        postList.add(post);

        Post post1 = new Post(AppCompatResources.getDrawable(this, R.drawable.img),
                AppCompatResources.getDrawable(this, R.drawable.user_ava));
        postList.add(post1);

    }
}

class RvAdapter extends RecyclerView.Adapter<RvAdapter.NewsFeedViewHolder> {
    private ArrayList<Post> postList;
    private Context context;

    public RvAdapter(ArrayList<Post> postList, Context context) {
        this.postList = postList;
        this.context = context;
    }

    public RvAdapter(ArrayList<Post> postList) {
        this.postList = postList;
    }

    @NonNull
    @Override
    public NewsFeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newsfeed_post, parent, false);
        NewsFeedViewHolder holder = new NewsFeedViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsFeedViewHolder holder, int position) {
        holder.mainImage.setImageDrawable(postList.get(position).getImage());
        holder.avaImage.setImageDrawable(postList.get(position).getAvatarImage());
//        holder.likeQuantity.setText(postList.get(position).);
//        holder.commentQuantity.setText(postList.get(position).);
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public static class NewsFeedViewHolder extends RecyclerView.ViewHolder {
        private ImageView mainImage, avaImage, star, comment, share;
        private TextView likeQuantity, commentQuantity;

        public NewsFeedViewHolder(@NonNull View itemView) {
            super(itemView);
            mainImage = itemView.findViewById(R.id.imagePost);
            avaImage = itemView.findViewById(R.id.avaImage);
            star = itemView.findViewById(R.id.starIcon);
            comment = itemView.findViewById(R.id.commentIcon);
            share = itemView.findViewById(R.id.shareIcon);
            likeQuantity = itemView.findViewById(R.id.likeOfPost);
            commentQuantity = itemView.findViewById(R.id.commentOfPost);
        }
    }
}