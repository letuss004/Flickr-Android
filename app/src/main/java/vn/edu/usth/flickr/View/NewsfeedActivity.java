package vn.edu.usth.flickr.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

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

