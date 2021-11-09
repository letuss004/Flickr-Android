package vn.edu.usth.flickr.ui;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vn.edu.usth.flickr.R;
import vn.edu.usth.flickr.adapter.CommentAdapterRV;
import vn.edu.usth.flickr.model.NewsFeedPost;
import vn.edu.usth.flickr.model.Post;
import vn.edu.usth.flickr.repository.NewsFeedRepository;


public class CommentFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "CommentFragment";

    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Post.Comment> commentArrayList = new ArrayList<>();
    private ArrayList<NewsFeedPost> list = new ArrayList<>();
    private Context activityContext;
    private ImageView backButton;
    private int position;


    public CommentFragment() {
    }

    public static CommentFragment newInstance(String param1, String param2) {
        CommentFragment fragment = new CommentFragment();
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
        position = this.getArguments().getInt("position");
        list = NewsFeedRepository.getInstance().getList();
        Log.e(TAG, "onCreate: " + list.get(0));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_comment, container, false);
        activityContext = view.getContext();

        backButton = view.findViewById(R.id.backButton_cmt);
        backButton.setOnClickListener(v -> {
            this.requireActivity().onBackPressed();
        });

        setUpRecyclerView(view);

        Post.Comment comment = new Post.Comment("Le Anh Tu", "Nice", getResources().getDrawable(R.drawable.user_ava));
        commentArrayList.add(comment);

        return view;
    }

    private void setUpRecyclerView(View view) {
        if (recyclerView == null) {
            recyclerView = view.findViewById(R.id.commentRV);

            layoutManager = new LinearLayoutManager(activityContext);
            recyclerView.setLayoutManager(layoutManager);

            adapter = new CommentAdapterRV(commentArrayList, view.getContext());
            recyclerView.setAdapter(adapter);
        }
    }


}


