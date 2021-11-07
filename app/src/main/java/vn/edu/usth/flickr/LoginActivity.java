package vn.edu.usth.flickr;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import vn.edu.usth.flickr.model.NewsFeedPost;
import vn.edu.usth.flickr.repository.NewsFeedRepository;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        Toolbar myToolbar = (Toolbar) findViewById(R.id.tool_bar);
//        setSupportActionBar(myToolbar);


    }


}

//    NewsFeedRepository newsFeedRepository = NewsFeedRepository.getInstance();
//
//    @SuppressLint("StaticFieldLeak")
//    AsyncTask<String, String, ArrayList<NewsFeedPost>> asyncTask = new AsyncTask<String, String, ArrayList<NewsFeedPost>>() {
//        @Override
//        protected ArrayList<NewsFeedPost> doInBackground(String... strings) {
//            return newsFeedRepository.fetchNewsFeedTest();
//        }
//
//        @Override
//        protected void onPostExecute(ArrayList<NewsFeedPost> newsFeedPosts) {
//            super.onPostExecute(newsFeedPosts);
//            for (int i = 0; i < newsFeedPosts.size(); i++) {
//                System.out.println("getAuthor: " + newsFeedPosts.get(i).getAuthor());
//                System.out.println("getAuthorId: " + newsFeedPosts.get(i).getAuthorId());
//                System.out.println("getDateTaken: " + newsFeedPosts.get(i).getDateTaken());
//                System.out.println("getDescription: " + newsFeedPosts.get(i).getDescription());
//                System.out.println("getLink: " + newsFeedPosts.get(i).getLink());
//                System.out.println("getDateTaken: " + newsFeedPosts.get(i).getDateTaken());
//                System.out.println("getPublished: " + newsFeedPosts.get(i).getPublished());
//                System.out.println("getTitle: " + newsFeedPosts.get(i).getTitle());
//                System.out.println("getTag: " + newsFeedPosts.get(i).getTag());
//            }
//        }
//    };
//        asyncTask.execute();