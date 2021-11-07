package vn.edu.usth.flickr.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vn.edu.usth.flickr.R;
import vn.edu.usth.flickr.adapter.NewsFeedAdapterRV;
import vn.edu.usth.flickr.model.Post;
import vn.edu.usth.flickr.model.User;
import vn.edu.usth.flickr.viewmodel.NewsFeedViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsFeedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsFeedFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager recViewLM;
    private Context activityContext;
    private NewsFeedViewModel feedViewModel;

    @Deprecated
    private ArrayList<Post> postList = new ArrayList<>();

    public NewsFeedFragment() {
        // Required empty public constructor
    }

    public static NewsFeedFragment newInstance(String param1, String param2) {
        NewsFeedFragment fragment = new NewsFeedFragment();
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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_newsfeed, container, false);
        activityContext = view.getContext();
        setUpRecyclerViewData(view);
        return view;
    }

    @SuppressLint("NotifyDataSetChanged")
    private void setUpRecyclerViewData(View view) {
        feedViewModel = new ViewModelProvider(requireActivity()).get(NewsFeedViewModel.class);
        feedViewModel.getNewsFeedPosts().observe(getViewLifecycleOwner(), newsFeedPosts -> {
            adapter.notifyDataSetChanged();
            setUpRecyclerView(view);
        });
    }

    //
    private void setUpRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.rv_newsfeed);

        recViewLM = new LinearLayoutManager(activityContext);
        recyclerView.setLayoutManager(recViewLM);

        adapter = new NewsFeedAdapterRV(feedViewModel.getNewsFeedPosts().getValue(), getContext(), this);
        recyclerView.setAdapter(adapter);
    }


    // ---------------------------------------------Deprecated---------------------------------------------
    @Deprecated
    @SuppressLint("UseCompatLoadingForDrawables")
    private void setUpPostListInformation() {
        Drawable image, avatarImage;
        int likeQuantity, commentQuantity;
        String owner, description, textLikeList, commenterUserName, commentContent, time;

        image = getResources().getDrawable(R.drawable.anh);
        avatarImage = getResources().getDrawable(R.drawable.ava1);
        likeQuantity = 327;
        commentQuantity = 18;
        owner = "John Taylor";
        description = "A day";
        textLikeList = "Bruce Thompson, Theo Antonin, Dennis, John K, Stefan Kamert +" + likeQuantity;
        commenterUserName = "Stephen Taylor";
        commentContent = "Lovely picture well exposed and developed";
        time = "5m";
        postList.add(new Post(image, avatarImage, likeQuantity, commentQuantity, owner, description, textLikeList, commenterUserName, commentContent, time));

        image = getResources().getDrawable(R.drawable.anh_post_2);
        avatarImage = getResources().getDrawable(R.drawable.ava_post_2);
        likeQuantity = 155;
        commentQuantity = 11;
        owner = "Cole Chase Photography";
        description = "Excellent";
        textLikeList = "aha52, Elzbieta Szewczuk, M- Hang Bui, vedebe, PR vonB, GregJ +" + likeQuantity;
        commenterUserName = "Listenwave Photography";
        commentContent = "Congratulations on Explore! It’s really magical!";
        time = "17d";
        postList.add(new Post(image, avatarImage, likeQuantity, commentQuantity, owner, description, textLikeList, commenterUserName, commentContent, time));

        image = getResources().getDrawable(R.drawable.anhpost3);
        avatarImage = getResources().getDrawable(R.drawable.avapost3);
        likeQuantity = 160;
        commentQuantity = 10;
        owner = "Tina Sosna";
        description = "Cold hands warm each other";
        textLikeList = "Pascal LH, Artem Pankov, L2032, Splash, Chi Go,windingnumbers +" + likeQuantity;
        commenterUserName = "ghostdog imaging";
        commentContent = "great work! congrats!";
        time = "5d";
        postList.add(new Post(image, avatarImage, likeQuantity, commentQuantity, owner, description, textLikeList, commenterUserName, commentContent, time));

        image = getResources().getDrawable(R.drawable.anhpost4);
        avatarImage = getResources().getDrawable(R.drawable.avapost4);
        likeQuantity = 302;
        commentQuantity = 16;
        owner = "Jake Rogers";
        description = "the pasture";
        textLikeList = "Roger Jeremiah, Joanne O'Connor, Rick Williams, Artem Kalenko +" + likeQuantity;
        commenterUserName = "Martin Cauchon";
        commentContent = "Awesome! Keep up the good work.";
        time = "10d";
        postList.add(new Post(image, avatarImage, likeQuantity, commentQuantity, owner, description, textLikeList, commenterUserName, commentContent, time));

        image = getResources().getDrawable(R.drawable.anhpost5);
        avatarImage = getResources().getDrawable(R.drawable.avapost5);
        likeQuantity = 185;
        commentQuantity = 18;
        owner = "Tina Sosna";
        description = "Cold hands warm each other";
        textLikeList = "Desailly, Agustin Hernandez, Delissa McWilliams, Olivier, Saikat Chanda +" + likeQuantity;
        commenterUserName = "ghostdog imaging";
        commentContent = "great work! congrats!";
        time = "22d";
        postList.add(new Post(image, avatarImage, likeQuantity, commentQuantity, owner, description, textLikeList, commenterUserName, commentContent, time));

    }


    // dont touch this
    @Deprecated
    private void setUpPostList() {
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


