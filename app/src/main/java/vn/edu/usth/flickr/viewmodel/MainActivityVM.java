package vn.edu.usth.flickr.viewmodel;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import vn.edu.usth.flickr.model.NewsFeedPost;
import vn.edu.usth.flickr.repository.NewsFeedRepository;

public class MainActivityVM {
    private MutableLiveData<List<NewsFeedPost>> newsFeedPosts;
    private static MainActivityVM instance;
    private NewsFeedRepository newsFeedRepository;

    public static MainActivityVM getInstance() {
        if (instance == null) instance = new MainActivityVM();
        return instance;
    }



}
