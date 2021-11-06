package vn.edu.usth.flickr;

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

        NewsFeedRepository newsFeedRepository = NewsFeedRepository.getInstance();
        ArrayList<NewsFeedPost> newsFeedPosts = newsFeedRepository.fetchNewsFeedTest();
        for (int i = 0; i <newsFeedPosts.size(); i++) {
            System.out.println(newsFeedPosts.get(i).toString());
        }

    }


}