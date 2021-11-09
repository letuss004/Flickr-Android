package vn.edu.usth.flickr;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import vn.edu.usth.flickr.model.VolleyQueueSingleton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigationView = findViewById(R.id.botNav);
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.navHost_fragment);
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(navigationView, navController);
        //
        VolleyQueueSingleton.getInstance(this);
    }
}


//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.newsfeedFragment, R.id.searchFragment)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.navHost_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);