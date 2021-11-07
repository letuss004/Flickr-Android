package vn.edu.usth.flickr.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.edu.usth.flickr.R;
import vn.edu.usth.flickr.adapter.NewsFeedAdapterRV;
import vn.edu.usth.flickr.api.FlickrApi;
import vn.edu.usth.flickr.model.NewsFeedPost;
import vn.edu.usth.flickr.viewmodel.NewsFeedViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsFeedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@RequiresApi(api = Build.VERSION_CODES.O)
public class NewsFeedFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "NewsFeedFragment";
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private NewsFeedAdapterRV adapter;
    private Context activityContext;
    private NewsFeedViewModel feedViewModel;
    private ArrayList<NewsFeedPost> newsFeedPosts;

    public NewsFeedFragment() {
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

    private void setUpRecyclerViewData(View view) {
        setRecyclerViewWaiter(view);
        //
        afterFinishGetDataOnBackGround(() -> {
            setRecyclerViewRealData();
//            observeData();
        });
    }

    private void observeData() {
        feedViewModel.getNewsFeedPosts().observe(getViewLifecycleOwner(), newsFeedPosts1 -> {
            adapter.notifyDataSetChanged();
//            setRecyclerViewRealData();
        });
    }

    private void afterFinishGetDataOnBackGround(CallBackListener callBackListener) {
        @SuppressLint("StaticFieldLeak")
        AsyncTask<String, String, MutableLiveData<List<NewsFeedPost>>> asyncTask = new AsyncTask<String, String, MutableLiveData<List<NewsFeedPost>>>() {
            @Override
            protected MutableLiveData<List<NewsFeedPost>> doInBackground(String... strings) {
                feedViewModel = NewsFeedViewModel.getInstance(); //set up data
                return null;
            }

            @Override
            protected void onPostExecute(MutableLiveData<List<NewsFeedPost>> listMutableLiveData) {
                super.onPostExecute(listMutableLiveData);
                newsFeedPosts = feedViewModel.getNewsFeedPosts().getValue();
                callBackListener.finished();
            }
        };
        asyncTask.execute();
    }

    //
    private void setRecyclerViewWaiter(View view) {
        recyclerView = view.findViewById(R.id.rv_newsfeed);

        LinearLayoutManager llm = new LinearLayoutManager(activityContext);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        ArrayList<NewsFeedPost> tmp = new ArrayList<>();
        adapter = new NewsFeedAdapterRV(tmp, activityContext, this);
        recyclerView.setAdapter(adapter);
    }

    private void setRecyclerViewRealData() {
        adapter = new NewsFeedAdapterRV(newsFeedPosts, activityContext, this);
        recyclerView.setAdapter(adapter);
    }

    private interface CallBackListener {
        void finished();
    }


}


