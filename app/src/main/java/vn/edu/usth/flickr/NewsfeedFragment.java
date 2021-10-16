package vn.edu.usth.flickr;

import android.content.Context;
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

    // TODO: Rename and change types of parameters
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
        Post post = new Post(AppCompatResources.getDrawable(activityContext, R.drawable.bird),
                AppCompatResources.getDrawable(activityContext, R.drawable.user_ava));
        postList.add(post);

        Post post1 = new Post(AppCompatResources.getDrawable(activityContext, R.drawable.img),
                AppCompatResources.getDrawable(activityContext, R.drawable.user_ava));
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

    /**
     *
     */
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


