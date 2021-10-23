package vn.edu.usth.flickr.controller;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import vn.edu.usth.flickr.view.fragments.ProfileAlbumFragment;
import vn.edu.usth.flickr.view.fragments.ProfileCameraFragment;
import vn.edu.usth.flickr.view.fragments.ProfileGroupFragment;
import vn.edu.usth.flickr.view.fragments.ProfilePublicFragment;
import vn.edu.usth.flickr.view.fragments.ProfileStatsFragment;

public class ProfileVpAdapter extends FragmentStateAdapter {
    private static final int NUM_PAGES = 5;

    public ProfileVpAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }


    @Override
    public Fragment createFragment(int position) {
        Log.e("createFragment: ", "" + position);
        switch (position) {
            case 0:
                return new ProfileCameraFragment();
            case 1:
                return new ProfilePublicFragment();
            case 2:
                return new ProfileAlbumFragment();
            case 3:
                return new ProfileGroupFragment();
            case 4:
                return new ProfileStatsFragment();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return NUM_PAGES;
    }
}
