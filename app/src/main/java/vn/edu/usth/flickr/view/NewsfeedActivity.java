package vn.edu.usth.flickr.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import vn.edu.usth.flickr.R;

public class NewsfeedActivity extends AppCompatActivity {

    private boolean isOnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsfeed);

    }

    @Override
    public Intent getIntent() {
        return super.getIntent();
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.isOnStop = true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        isOnStop = false;
        System.out.println("Newsfeed activity is on STOP !!!!!!!!!!!!!!!!!!!!!!!!");
    }

    public boolean isOnStop() {
        return isOnStop;
    }

    public void setOnStop(boolean onStop) {
        isOnStop = onStop;
    }
}

