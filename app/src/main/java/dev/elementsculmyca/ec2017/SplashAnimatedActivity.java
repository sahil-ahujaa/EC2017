package dev.elementsculmyca.ec2017;

import android.content.Intent;
import android.util.Log;

import com.daimajia.androidanimations.library.Techniques;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import dev.elementsculmyca.ec2017.DatabaseHandlers.DbHelper;
import dev.elementsculmyca.ec2017.Utility.ConstantUtils;

public class SplashAnimatedActivity extends AwesomeSplash {
    private static final String TAG="SPLASH";
    private final DbHelper mDbHelper=new DbHelper(SplashAnimatedActivity.this);
    private static final String DROID_LOGO = "M 800.00,0.00\n" +
            "           C 800.00,0.00 800.00,600.00 800.00,600.00\n" +
            "             800.00,600.00 0.00,600.00 0.00,600.00\n" +
            "             0.00,600.00 0.00,0.00 0.00,0.00\n" +
            "             0.00,0.00 800.00,0.00 800.00,0.00 Z\n" +
            "           M 345.00,194.00\n" +
            "           C 347.25,194.00 350.78,194.14 352.82,193.26\n" +
            "             356.61,191.62 365.75,178.00 368.77,174.00\n" +
            "             368.77,174.00 411.37,117.00 411.37,117.00\n" +
            "             411.37,117.00 423.37,101.00 423.37,101.00\n" +
            "             424.91,98.95 428.61,94.39 429.13,92.06\n" +
            "             430.18,87.30 423.89,88.01 421.00,88.00\n" +
            "             421.00,88.00 126.00,88.00 126.00,88.00\n" +
            "             126.00,88.00 126.00,284.00 126.00,284.00\n" +
            "             126.00,284.00 126.00,336.00 126.00,336.00\n" +
            "             126.06,344.13 127.27,343.98 135.00,344.00\n" +
            "             135.00,344.00 308.00,344.00 308.00,344.00\n" +
            "             310.64,343.99 316.51,344.53 316.98,340.85\n" +
            "             317.33,338.07 312.34,332.28 310.63,330.00\n" +
            "             310.63,330.00 289.77,302.00 289.77,302.00\n" +
            "             289.77,302.00 253.87,254.00 253.87,254.00\n" +
            "             251.41,250.75 243.95,239.89 240.83,238.60\n" +
            "             239.08,237.88 235.93,238.00 234.00,238.00\n" +
            "             234.00,238.00 205.00,238.00 205.00,238.00\n" +
            "             205.00,238.00 205.00,194.00 205.00,194.00\n" +
            "             205.00,194.00 345.00,194.00 345.00,194.00 Z\n" +
            "           M 490.00,88.00\n" +
            "           C 487.56,88.00 484.45,87.78 482.29,89.02\n" +
            "             479.26,90.77 467.83,107.06 464.87,111.00\n" +
            "             464.87,111.00 409.71,185.00 409.71,185.00\n" +
            "             409.71,185.00 396.73,202.00 396.73,202.00\n" +
            "             396.73,202.00 353.63,260.00 353.63,260.00\n" +
            "             353.63,260.00 338.63,280.00 338.63,280.00\n" +
            "             336.77,282.48 332.95,287.01 332.56,290.00\n" +
            "             331.93,294.73 339.77,303.19 342.63,307.00\n" +
            "             342.63,307.00 373.13,348.00 373.13,348.00\n" +
            "             373.13,348.00 481.53,493.00 481.53,493.00\n" +
            "             481.53,493.00 509.13,530.00 509.13,530.00\n" +
            "             511.01,532.51 517.84,542.64 520.31,543.28\n" +
            "             524.34,544.33 530.67,533.93 532.87,531.00\n" +
            "             532.87,531.00 566.63,486.00 566.63,486.00\n" +
            "             566.63,486.00 618.12,417.00 618.12,417.00\n" +
            "             621.28,412.71 634.05,396.44 635.28,393.00\n" +
            "             636.13,390.60 636.00,387.54 636.00,385.00\n" +
            "             636.00,385.00 636.00,301.00 636.00,301.00\n" +
            "             636.00,301.00 636.00,254.00 636.00,254.00\n" +
            "             636.00,251.04 636.55,241.66 634.98,239.59\n" +
            "             634.05,238.38 633.23,238.43 632.00,238.00\n" +
            "             632.00,238.00 600.88,280.00 600.88,280.00\n" +
            "             600.88,280.00 546.53,353.00 546.53,353.00\n" +
            "             546.53,353.00 521.00,387.00 521.00,387.00\n" +
            "             521.00,387.00 503.73,363.00 503.73,363.00\n" +
            "             503.73,363.00 470.87,319.00 470.87,319.00\n" +
            "             470.87,319.00 457.37,301.00 457.37,301.00\n" +
            "             455.86,298.98 452.00,294.27 451.56,292.00\n" +
            "             450.98,288.99 453.47,286.25 455.13,284.00\n" +
            "             455.13,284.00 467.79,267.00 467.79,267.00\n" +
            "             467.79,267.00 508.13,213.00 508.13,213.00\n" +
            "             510.94,209.25 519.97,195.93 523.44,194.14\n" +
            "             525.07,193.71 530.00,194.00 532.00,194.14\n" +
            "             532.00,194.14 663.00,194.14 663.00,194.14\n" +
            "             665.36,194.00 668.79,194.13 670.96,193.26\n" +
            "             675.22,191.56 681.92,181.02 684.88,177.00\n" +
            "             684.88,177.00 717.85,133.00 717.85,133.00\n" +
            "             717.85,133.00 738.87,105.00 738.87,105.00\n" +
            "             742.97,99.58 747.46,94.95 748.00,88.00\n" +
            "             748.00,88.00 490.00,88.00 490.00,88.00 Z\n" +
            "           M 126.57,390.43\n" +
            "           C 125.92,391.74 126.01,394.51 126.00,396.00\n" +
            "             126.00,396.00 126.00,414.00 126.00,414.00\n" +
            "             126.00,414.00 126.00,474.00 126.00,474.00\n" +
            "             126.00,477.65 125.05,491.84 127.57,493.83\n" +
            "             129.38,495.25 133.74,495.00 136.00,495.00\n" +
            "             136.00,495.00 420.00,495.00 420.00,495.00\n" +
            "             423.42,494.99 430.30,495.49 429.13,489.98\n" +
            "             428.66,487.75 425.55,483.91 424.13,482.00\n" +
            "             424.13,482.00 413.00,467.00 413.00,467.00\n" +
            "             413.00,467.00 369.58,409.00 369.58,409.00\n" +
            "             366.60,404.89 356.67,390.38 352.83,388.74\n" +
            "             350.77,387.86 347.27,388.00 345.00,388.11\n" +
            "             345.00,388.11 201.00,388.11 201.00,388.11\n" +
            "             201.00,388.11 154.00,388.11 154.00,388.11\n" +
            "             154.00,388.11 129.13,388.11 129.13,388.11\n" +
            "             127.90,389.07 127.35,388.88 126.57,390.43 Z";



    @Override
    public void initSplash(ConfigSplash configSplash) {

        setTheme(R.style.SplashTheme);

            /* you don't have to override every property */

        //Customize Circular Reveal
        configSplash.setBackgroundColor(R.color.Splashbg); //any color you want form colors.xml
        configSplash.setAnimCircularRevealDuration(500); //int ms
        configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);  //or Flags.REVEAL_LEFT
        configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM); //or Flags.REVEAL_TOP

        //Choose LOGO OR PATH; if you don't provide String value for path it's logo by default

        //Customize Logo
        configSplash.setLogoSplash(R.mipmap.ic_launcher); //or any other drawable
        configSplash.setAnimLogoSplashDuration(500); //int ms
        configSplash.setAnimLogoSplashTechnique(Techniques.Bounce); //choose one form Techniques (ref: https://github.com/daimajia/AndroidViewAnimations)


        //Customize Path
        configSplash.setPathSplash(DROID_LOGO); //set path String
        configSplash.setOriginalHeight(800); //in relation to your svg (path) resource
        configSplash.setOriginalWidth(800); //in relation to your svg (path) resource
        configSplash.setAnimPathStrokeDrawingDuration(500);
        configSplash.setPathSplashStrokeSize(3); //I advise value be <5
        configSplash.setPathSplashStrokeColor(R.color.SplashStrokecolor); //any color you want form colors.xml
        configSplash.setAnimPathFillingDuration(500);
        configSplash.setPathSplashFillColor(R.color.SplashLogoFillBg); //path object filling color


        //Customize Title
        configSplash.setTitleSplash("Elements Culmyca 2017");
        configSplash.setTitleTextColor(R.color.strokeColor);
        configSplash.setTitleTextSize(30f); //float value
        configSplash.setAnimTitleDuration(500);
        configSplash.setAnimTitleTechnique(Techniques.FadeInRight);
//        configSplash.setTitleFont("fonts/myfont.ttf"); //provide string to your font located in assets/fonts/


    }

    @Override
    public void animationsFinished() {
        saveInitialList(ConstantUtils.EVENT_LIST_INITIAL1);
        saveInitialList(ConstantUtils.EVENT_LIST_INITIAL2);
        Intent i=new Intent(SplashAnimatedActivity.this,EventCategoryActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
        overridePendingTransition(R.anim.right_to_left_slide,R.anim.activity_close_translate);
    }
    private void saveInitialList(String list){
        try{

            JSONArray jsonArray=new JSONArray(list);
            int numEvents=jsonArray.length();
            int i;

            for(i=0;i<numEvents;i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                        mDbHelper.addEntryToDb(jsonObject.getString("_id"),
                                jsonObject.getString("eventName"),
                                jsonObject.getString("club"),
                                jsonObject.getString("category"),
                                jsonObject.getString("description"),
                                jsonObject.getString("rules"),
                                jsonObject.getString("venue"),
                                jsonObject.getString("fee"),
                                jsonObject.getString("startTime"),
                                jsonObject.getString("endTime")

                        );
                String s = jsonObject.toString();
                Log.d(TAG, s);

            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                }
            });
        }catch (JSONException e){
            Log.d(TAG,"JSON error");
        }

    }

}
