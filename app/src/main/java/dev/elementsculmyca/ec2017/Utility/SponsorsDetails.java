package dev.elementsculmyca.ec2017.Utility;

import android.graphics.drawable.Drawable;

/**
 * Created by hemba on 2/16/2017.
 */
public class SponsorsDetails{
    String name;
    String title;
    int logo;

    public SponsorsDetails(String name, String title, int logo) {
        this.name = name;
        this.title = title;
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public int getLogo() {
        return logo;
    }
}