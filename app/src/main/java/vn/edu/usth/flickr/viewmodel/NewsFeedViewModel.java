package vn.edu.usth.flickr.viewmodel;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import vn.edu.usth.flickr.model.NewsFeedPost;
import vn.edu.usth.flickr.repository.NewsFeedRepository;

public class NewsFeedViewModel extends ViewModel {
    private MutableLiveData<ArrayList<NewsFeedPost>> newsFeedPosts;
    private static NewsFeedViewModel instance;
    private final NewsFeedRepository newsFeedRepository;

    private NewsFeedViewModel() {
        newsFeedRepository = NewsFeedRepository.getInstance();
        setNewsFeedPosts();
    }

    public static NewsFeedViewModel getInstance() {
        if (instance == null) instance = new NewsFeedViewModel();
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
