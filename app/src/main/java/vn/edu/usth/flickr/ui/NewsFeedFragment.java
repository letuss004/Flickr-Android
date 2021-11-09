package vn.edu.usth.flickr.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
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
import vn.edu.usth.flickr.model.NewsFeedPost;
import vn.edu.usth.flickr.viewmodel.NewsFeedViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsFeedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@RequiresApi(api = Build.VERSION_CODES.O)
public class NewsFeedFragment extends Fragment implements NewsFeedAdapterRV.OnRvItemListener {
    private RecyclerView recyclerView;
    private NewsFeedAdapterRV adapter;
    private Context activityContext;
    private NewsFeedViewModel feedViewModel;
    private ArrayList<NewsFeedPost> newsFeedPosts;
    private int countCreate = 0;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "NewsFeedFragment";
    private int mParam1 = 1;
    private String mParam2;

    public static NewsFeedFragment newInstance(String param1, String param2) {
        NewsFeedFragment fragment = new NewsFeedFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, 1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public NewsFeedFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            countCreate = getArguments().getInt(ARG_PARAM1);
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
        Log.e(TAG, "onCreateView: fajsfjdsalkfjsadfjlas");

        if (feedViewModel != null) {
            observeData();
        }
//        adapter.notifyDataSetChanged();
        return view;
    }

    /*
     * */
    public void setUpRecyclerViewData(View view) {
        if (countCreate == 0) {
            setRecyclerViewWaiter(view);
            afterFinishGetDataOnBackGround(() -> {
                setRecyclerViewRealData();
                NewsFeedAdapterRV.setReady(true);
                observeData();
            });
            countCreate = 1;
            Log.e(TAG, "setUpRecyclerViewData: --------------------only 1 and count =" + countCreate);
        } else {
            feedViewModel = NewsFeedViewModel.getInstance(); //set up data
            newsFeedPosts = feedViewModel.getNewsFeedPosts().getValue();
            adapter = new NewsFeedAdapterRV(newsFeedPosts, this, this);
            recyclerView.setAdapter(adapter);
            Log.e(TAG, "setUpRecyclerViewData: more than 2 -------------------");
        }


    }


    /*
     * observe always call at the first time
     * => update right here only 1
     * => after that only set notification*/
    @SuppressLint({"NotifyDataSetChanged", "StaticFieldLeak"})
    private void observeData() {
        feedViewModel.getNewsFeedPosts().observe(getViewLifecycleOwner(), newsFeedPosts1 -> {
            Log.e(TAG, "observeData: method started");
            adapter.notifyDataSetChanged();
        });

    }

    private void afterFinishGetDataOnBackGround(CallBackListener callBackListener) {
        @SuppressLint("StaticFieldLeak")
        AsyncTask<String, String, MutableLiveData<List<NewsFeedPost>>> asyncTask = new AsyncTask<String, String, MutableLiveData<List<NewsFeedPost>>>() {
            @Override
            protected MutableLiveData<List<NewsFeedPost>> doInBackground(String... strings) {
                feedViewModel = NewsFeedViewModel.getInstance(); //set up data
                newsFeedPosts = feedViewModel.getNewsFeedPosts().getValue();
                return null;
            }

            @Override
            protected void onPostExecute(MutableLiveData<List<NewsFeedPost>> listMutableLiveData) {
                super.onPostExecute(listMutableLiveData);
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
        adapter = new NewsFeedAdapterRV(tmp, this, this);
        recyclerView.setAdapter(adapter);
    }

    private void setRecyclerViewRealData() {
        adapter = new NewsFeedAdapterRV(newsFeedPosts, this, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position) {
        newsFeedPosts.get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        CommentFragment fragment = new CommentFragment();
        fragment.setArguments(bundle);
        getParentFragmentManager().beginTransaction().replace(R.id.navHost_fragment, fragment).addToBackStack("newsfeed").commit();
    }

    private interface CallBackListener {
        void finished();
    }


}


