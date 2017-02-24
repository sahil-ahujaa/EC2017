package dev.elementsculmyca.ec2017.Adapters;

import android.content.Context;

/**
 * Created by hemba on 2/15/2017.
 */

public class DevelopersCardItem {
    private int mNameResource;
    private int mTitleResource;
    private int mImageResourceId;
    private String linkedinHandle;
    Context context;

    public DevelopersCardItem(int mNameResource, int mTitleResource, int mImageResourceId,String linkedinHandle,Context context) {
        this.mNameResource = mNameResource;
        this.mTitleResource = mTitleResource;
        this.context=context;
        this.mImageResourceId = mImageResourceId;
        this.linkedinHandle=linkedinHandle;
    }

    public Context getContext() {
        return context;
    }

    public int getName() {
        return mNameResource;
    }

    public String getLinkedinHandle() {
        return linkedinHandle;
    }

    public int getTitle() {
        return mTitleResource;
    }

    public int getImage() { return mImageResourceId; }
}
