package extras;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Tools {

    public static String getFormattedDateSimple(Long dateTime) {
        SimpleDateFormat newFormat = new SimpleDateFormat("MMMM dd, yyyy");
        return newFormat.format(new Date(dateTime));
    }
}
