package vn.edu.usth.flickr.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import vn.edu.usth.flickr.R;
import vn.edu.usth.flickr.controller.ProfileVpAdapter;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        ViewPager2 viewPager = findViewById(R.id.profileViewPager);
        ProfileVpAdapter adapter = new ProfileVpAdapter(this);
        viewPager.setAdapter(adapter);

//        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
//        tabLayout.setupWithViewPager(viewPager);


    }
}