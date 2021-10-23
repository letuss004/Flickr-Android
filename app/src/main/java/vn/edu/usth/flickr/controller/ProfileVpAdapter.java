package vn.edu.usth.flickr.controller;

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
    private static final int NUM_PAGES = 4;

    public ProfileVpAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public ProfileVpAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public ProfileVpAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @Override
    public Fragment createFragment(int position) {
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
