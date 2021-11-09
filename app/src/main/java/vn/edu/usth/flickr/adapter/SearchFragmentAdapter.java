package vn.edu.usth.flickr.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import vn.edu.usth.flickr.R;
import vn.edu.usth.flickr.ui.SearchFragment;


/**
 *
 */
public class SearchFragmentAdapter extends RecyclerView.Adapter<SearchFragmentAdapter.SearchViewHolder> {
    private static final String TAG = "SearchFragmentAdapter";
    private ArrayList<Drawable> imageList;
    private SearchFragment searchFragment;
    private Context context;
    private ArrayList<String> imageLinkList;


    public SearchFragmentAdapter(ArrayList<String> imageLinkList, SearchFragment searchFragment) {
        this.imageLinkList = imageLinkList;
        this.searchFragment = searchFragment;
        this.context = searchFragment.getContext();
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_row, parent, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        Glide.with(context).load(imageLinkList.get(position)).into(holder.firstImage);
    }

    @Override
    public int getItemCount() {
        Log.e(TAG, "getItemCount: " + imageLinkList.size());
        return imageLinkList.size();
    }


    /**
     *
     */
    public static class SearchViewHolder extends RecyclerView.ViewHolder {
        private ImageView firstImage, secondImage;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            firstImage = itemView.findViewById(R.id.firstImage_search);

        }
    }
}
