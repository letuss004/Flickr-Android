package vn.edu.usth.flickr.View;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import vn.edu.usth.flickr.Controller.NewsfeedAdapterRV;
import vn.edu.usth.flickr.Model.Post;
import vn.edu.usth.flickr.Model.User;
import vn.edu.usth.flickr.R;

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

        setUpRecyclerView(view);
        setUpPostList(view);

        return view;
    }

    //
    private void setUpRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.rv_newsfeed);

        recViewLM = new LinearLayoutManager(activityContext);
        recyclerView.setLayoutManager(recViewLM);

        adapter = new NewsfeedAdapterRV(postList, getContext(), this);
        recyclerView.setAdapter(adapter);
    }

    private void setUpPostList(View view) {
        if (postList.size() == 0) {
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

}


