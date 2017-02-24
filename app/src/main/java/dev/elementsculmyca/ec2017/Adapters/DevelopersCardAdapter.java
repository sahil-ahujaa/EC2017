package dev.elementsculmyca.ec2017.Adapters;

import android.support.v7.widget.CardView;

/**
 * Created by hemba on 2/15/2017.
 */

public interface DevelopersCardAdapter {

        int MAX_ELEVATION_FACTOR = 8;

        float getBaseElevation();

        CardView getCardViewAt(int position);

        int getCount();
    }


