package vn.edu.usth.flickr.controller;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vn.edu.usth.flickr.view.activities.NotificationActivity;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ItemViewHolder> {
    private ArrayList<Object> notificationList;
    private NotificationActivity notificationActivity;


    public NotificationAdapter(ArrayList<Object> notificationList, NotificationActivity notificationActivity) {
        this.notificationActivity = notificationActivity;
        this.notificationList = notificationList;
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}