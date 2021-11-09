package vn.edu.usth.flickr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.junit.Test;

import java.util.Date;

public class ObserveTest extends AppCompatActivity {
    private MutableLiveData<String> data;
    @Test
    public void a() {
        data = new MutableLiveData<>();

    }
}
