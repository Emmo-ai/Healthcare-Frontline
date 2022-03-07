package extras;

import android.view.View;

import java.util.List;

public class utils {
    public static void showViews(List<View> views) {
        for (View view : views){
            view.setVisibility(View.VISIBLE);
        }
    }

    public static void hideViews(List<View> views){
        for (View view : views){
            view.setVisibility(View.GONE);
        }
    }

}
