package vn.edu.usth.flickr.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import vn.edu.usth.flickr.model.NewsFeedPost;
import vn.edu.usth.flickr.repository.NewsFeedRepository;

public class NewsFeedViewModel extends ViewModel {
    private static MutableLiveData<ArrayList<NewsFeedPost>> newsFeedPosts = new MutableLiveData<>();
    private static NewsFeedViewModel instance;
    private static NewsFeedRepository newsFeedRepository;
    private static ArrayList<NewsFeedPost> list;

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
        list = newsFeedRepository.fetchNewsFeed(); // get value
        newsFeedPosts = new MutableLiveData<>(list);
    }

    /*
     * updateNewsFeed = new value => append to list
     * => list updated => onChanged => observed
     * */
    public static void updateNewsFeedPosts() {
        ArrayList<NewsFeedPost> updateNewsFeed = newsFeedRepository.updateNewsFeed(); // get val
        list.addAll(updateNewsFeed);
        newsFeedPosts.postValue(list);
    }

    public LiveData<ArrayList<NewsFeedPost>> getNewsFeedPosts() {
        getInstance();
        return newsFeedPosts;
    }


}
