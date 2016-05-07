package bit.simle1.usinglocation;

import android.graphics.Bitmap;

import java.util.Random;

/**
 * Created by Asus on 6/5/2016.
 */
public class Location {

    //Attributes
    private double latVal;
    private double longVal;

    private double maxLong;
    private double minLong;

    private double maxLat;
    private double minLat;

    private String cityName;
    private String countryCode;
    private String closestCity;

    private Bitmap cityImage;

    //Constructor
    public Location()
    {
        setCityName("");

        setLatVal(0);
        setLongVal(0);

        // The range to random Longitude
        maxLong = 180;
        minLong = -180;

        // The range to random Latitude
        maxLat = 90;
        minLat = -90;
    }

    //Methods
    public void GenerateRandomValues()
    {
        // Generate random number
        Random rand = new Random();

        // Initialise longitude and latitude with random values
        // (max - min) gives the range
        // +1 includes the last value of max value\
        // +min gives it the start value
        setLongVal(minLong + ((maxLong - minLong) + 1) * rand.nextDouble());
        setLatVal(minLat + ((maxLat - minLat) + 1) * rand.nextDouble());
    }

    // Properties
    public double getLatVal() {
        return latVal;
    }

    public void setLatVal(double latVal) {
        this.latVal = latVal;
    }

    public double getLongVal() {
        return longVal;
    }

    public void setLongVal(double longVal) {
        this.longVal = longVal;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getClosestCity() {
        return closestCity;
    }

    public void setClosestCity(String closestCity) {
        this.closestCity = closestCity;
    }

    public Bitmap getCityImage() {
        return cityImage;
    }

    public void setCityImage(Bitmap cityImage) {
        this.cityImage = cityImage;
    }
}
