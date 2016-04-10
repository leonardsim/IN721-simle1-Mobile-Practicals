package bit.simle1.wtdlistview;

import android.graphics.drawable.Drawable;

/**
 * Created by simle1 on 1/04/2016.
 */
public class FTTD {

    //Attributes
    Drawable placeImage;
    String placeName;

    //Constructor
    public FTTD(Drawable placeImage, String placeName)
    {
        this.placeImage = placeImage;
        this.placeName = placeName;
    }

    //ToString method
    @Override
    public String toString() {
        return placeName;
    }
}
