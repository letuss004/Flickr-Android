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
    private static MutableLiveData<ArrayList<NewsFeedPost>> newsFeedPosts = new MutableLiveData<>();
    private static NewsFeedViewModel instance;
    private static NewsFeedRepository newsFeedRepository;

    private NewsFeedViewModel() {
    }

    public static NewsFeedViewModel getInstance() {
        if (instance == null) {
            instance = new NewsFeedViewModel();
            newsFeedRepository = NewsFeedRepository.getInstance();
            setNewsFeedPosts();
        }
        return instance;
    }

    public static void setNewsFeedPosts() {
        newsFeedPosts = newsFeedRepository.fetchNewsFeed();
    }

    public LiveData<ArrayList<NewsFeedPost>> getNewsFeedPosts() {
        getInstance();
        return newsFeedPosts;
    }


}
