package vn.edu.usth.flickr;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsfeedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsfeedFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameter
    private String mParam1;
    private String mParam2;

    public NewsfeedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewsfeedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewsfeedFragment newInstance(String param1, String param2) {
        NewsfeedFragment fragment = new NewsfeedFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager recViewLM;
    private ArrayList<Post> postList = new ArrayList<>();
    private Context activityContext;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_newsfeed, container, false);
        activityContext = view.getContext();

        setUpPostList(view);
        setUpRecyclerView(view);

        return view;
    }

    private void setUpRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.rv_newsfeed);

        recViewLM = new LinearLayoutManager(activityContext);
        recyclerView.setLayoutManager(recViewLM);

        adapter = new RvAdapter(postList, view.getContext());
        recyclerView.setAdapter(adapter);
    }

    private void setUpPostList(View view) {
        Drawable bird = AppCompatResources.getDrawable(activityContext, R.drawable.bird);
        Drawable flowers = AppCompatResources.getDrawable(activityContext, R.drawable.flowers);
        Drawable latAvatar = AppCompatResources.getDrawable(activityContext, R.drawable.user_ava);

        ArrayList<Post.Like> likeList = new ArrayList<>();
        likeList.add(new Post.Like(new User("Do Thi Ngoc An")));
        likeList.add(new Post.Like(new User("Le Duy")));
        likeList.add(new Post.Like(new User("Tuan Thanh")));

        ArrayList<Post.Comment> commentList = new ArrayList<>();
        commentList.add(new Post.Comment(new User("Do Thi Ngoc An"), "Nice!"));
        commentList.add(new Post.Comment(new User("Tuan Thanh"), "Great!"));

        Post post = new Post(likeList, commentList, "Le Anh Tu", "Studio Workshop 2022", bird, latAvatar);
        postList.add(post);

        Post post1 = new Post(likeList, commentList, "Le Anh Tu", "Puerta de Alcala Madrid", flowers, latAvatar);
        postList.add(post1);
    }

}

/**
 *
 */
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
        return new NewsFeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsFeedViewHolder holder, int position) {
        holder.mainImage.setImageDrawable(postList.get(position).getImage());
        holder.avaImage.setImageDrawable(postList.get(position).getAvatarImage());
        holder.postOwnerName.setText(postList.get(position).getOwnerName());
        holder.postDescription.setText(postList.get(position).getDescription());
        holder.likeQuantity.setText(String.valueOf(postList.get(position).getLikeList().size()));
        holder.commentQuantity.setText(String.valueOf(postList.get(position).getCommentList().size()));
        holder.textListOfLike.setText(getTextListOfLike(position));

        //
        setUpCommentInNewsfeed(holder, position);
    }

    private void setUpCommentInNewsfeed(@NonNull NewsFeedViewHolder holder, int position) {
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
            if (i != postList.get(position).getLikeList().size() -1 ) {
                res += ", ";
            }
        }
        return res;
    }


    /**
     *
     */
    public static class NewsFeedViewHolder extends RecyclerView.ViewHolder {
        private final ImageView mainImage, avaImage;
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
        }

    }

}


