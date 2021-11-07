package vn.edu.usth.flickr;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        String a = "<p><a href=\\\"https:\\/\\/www.flickr.com\\/people\\/144312453@N08\\/\\\">Dackelpup<\\/a> posted a photo:<\\/p> <p><a href=\\\"https:\\/\\/www.flickr.com\\/photos\\/144312453@N08\\/51649045431\\/\\\" title=\\\"Theia\\\"><img src=\\\"https:\\/\\/live.staticflickr.com\\/65535\\/51649045431_234f6c5234_m.jpg\\\" width=\\\"240\\\" height=\\\"160\\\" alt=\\\"Theia\\\" \\/><\\/a><\\/p>";
        String[] s = a.split("\"");
        System.out.println(s[7]);

    }
}