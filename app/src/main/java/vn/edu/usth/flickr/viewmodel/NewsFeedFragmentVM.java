package vn.edu.usth.flickr.viewmodel;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import vn.edu.usth.flickr.api.NewsFeedApiGetter;
import vn.edu.usth.flickr.model.NewsFeedPost;
import vn.edu.usth.flickr.repository.NewsFeedRepository;

public class NewsFeedFragmentVM {
    private MutableLiveData<ArrayList<NewsFeedPost>> newsFeedPosts;
    private static NewsFeedFragmentVM instance;
    private final NewsFeedRepository newsFeedRepository;

    private NewsFeedFragmentVM() {
        newsFeedRepository = NewsFeedRepository.getInstance();
        setNewsFeedPosts();
    }

    public static NewsFeedFragmentVM getInstance() {
        if (instance == null) instance = new NewsFeedFragmentVM();
        return instance;
    }

    private void setNewsFeedPosts() {
        @SuppressLint("StaticFieldLeak")
        AsyncTask<String, String, MutableLiveData<List<NewsFeedPost>>> asyncTask = new AsyncTask<String, String, MutableLiveData<List<NewsFeedPost>>>() {
            @Override
            protected MutableLiveData<List<NewsFeedPost>> doInBackground(String... strings) {
                newsFeedPosts = newsFeedRepository.fetchNewsFeed();
                return null;
            }
        };
        asyncTask.execute();
    }

    public LiveData<ArrayList<NewsFeedPost>> getNewsFeedPosts() {
        getInstance();
        return newsFeedPosts;
    }


}
