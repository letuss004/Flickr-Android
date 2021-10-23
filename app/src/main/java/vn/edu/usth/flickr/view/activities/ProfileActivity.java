package vn.edu.usth.flickr.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.media.session.MediaController;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

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

        TabLayout tabLayout = findViewById(R.id.tabLayoutProfile);
        TabLayoutMediator.TabConfigurationStrategy tabConfigurationStrategy = (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Camera Roll");
                    break;
                case 1:
                    tab.setText("Public");
                    break;
                case 2:
                    tab.setText("Albums");
                    break;
                case 3:
                    tab.setText("Groups");
                    break;
                case 4:
                    tab.setText("Stat");
                    break;
            }
        };
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager, true, true, tabConfigurationStrategy);
        tabLayoutMediator.attach();
    }


}