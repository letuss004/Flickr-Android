package vn.edu.usth.flickr.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import vn.edu.usth.flickr.R;
import vn.edu.usth.flickr.controller.NotificationAdapter;


public class NotificationActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager recViewLM;
    private ArrayList<Object> notificationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        recyclerView = findViewById(R.id.notification_rv);

        recViewLM = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recViewLM);

        adapter = new NotificationAdapter(notificationList, this);
        recyclerView.setAdapter(adapter);
    }


}