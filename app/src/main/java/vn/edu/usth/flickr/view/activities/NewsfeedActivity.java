package vn.edu.usth.flickr.view.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import vn.edu.usth.flickr.R;

public class NewsfeedActivity extends AppCompatActivity {

    private boolean isOnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsfeed);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
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
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("Log test", "onSaveInstanceState");
    }

    @Override
    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
        Log.e("Log test", "onRestoreInstanceState");

    }



    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
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

