package vn.edu.usth.flickr.view.fragments;

import android.annotation.SuppressLint;
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

import vn.edu.usth.flickr.controller.NewsfeedAdapterRV;
import vn.edu.usth.flickr.model.Post;
import vn.edu.usth.flickr.model.User;
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
        setUpPostListInformation();
//        setUpNavItemOnClick(view);

        return view;
    }

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

    private void setUpNavItemOnClick(View view) {
//        ImageButton newsfeed, search, addImage, notification, profile;
//        newsfeed = view.findViewById(R.id.newsfeed_icon);
//        search = view.findViewById(R.id.search_icon);
//        addImage = view.findViewById(R.id.addImage_icon);
//        notification = view.findViewById(R.id.notification_icon);
//        profile = view.findViewById(R.id.profile_icon);
//
//        newsfeed.setOnClickListener(v -> {
//            Intent intent = new Intent(activityContext, NewsfeedActivity.class);
//            startActivity(intent);
//        });
//
//        search.setOnClickListener(v -> {
//            Intent intent = new Intent(activityContext, SearchActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//            startActivity(intent);
//        });
//
//        addImage.setOnClickListener(v -> {
//
//        });
//
//        notification.setOnClickListener(v -> {
//            Intent intent = new Intent(activityContext, NotificationActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//            startActivity(intent);
//        });
//
//        profile.setOnClickListener(v -> {
//            Intent intent = new Intent(activityContext, ProfileActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//            startActivity(intent);
//        });
    }

    //
    private void setUpRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.rv_newsfeed);

        recViewLM = new LinearLayoutManager(activityContext);
        recyclerView.setLayoutManager(recViewLM);

        adapter = new NewsfeedAdapterRV(postList, getContext(), this);
        recyclerView.setAdapter(adapter);
    }


    // dont touch this
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


