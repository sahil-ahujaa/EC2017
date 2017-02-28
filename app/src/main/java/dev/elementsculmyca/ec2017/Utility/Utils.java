package dev.elementsculmyca.ec2017.Utility;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;
import com.tiancaicc.springfloatingactionmenu.OnMenuActionListener;
import com.tiancaicc.springfloatingactionmenu.SpringFloatingActionMenu;

import dev.elementsculmyca.ec2017.AboutActivity;
import dev.elementsculmyca.ec2017.DevelopersActivity;
import dev.elementsculmyca.ec2017.MapsActivity;
import dev.elementsculmyca.ec2017.MyTicketsActivity;
import dev.elementsculmyca.ec2017.R;
import dev.elementsculmyca.ec2017.SponsorsActivity;

/**
 * Created by hemba on 2/5/2017.
 */

public class Utils {
    public static void toastS(Context context, String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }
    public static void toastL(Context context,String message){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }
    public  static  boolean isNetConnected(Context context){
        ConnectivityManager connectivityManager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        return (networkInfo!=null && networkInfo.isConnected());
    }
    public static void makeAlert(String title,String message,Context context){

        final AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

                    }
                })
                .create();
        alertDialog.show();
    }

    public static Drawable getDrawableByName(Context context,String name){
        Resources resources=context.getResources();
        int resId=resources.getIdentifier(fileNameHelper(name),"drawable",context.getPackageName());
        if (resId==0){
            resId= R.drawable.img1;
        }
        return resources.getDrawable(resId);

    }
    public static int getResIdByName(Context context,String name){
        Resources resources=context.getResources();
        int resId=resources.getIdentifier(fileNameHelper(name),"drawable",context.getPackageName());
        if (resId==0){
            resId=R.drawable.zenith;
        }
        return resId;

    }

    public static String fileNameHelper(String fileName){
        fileName=fileName.replaceAll(" ","_");
        fileName.replaceAll(".","_");
        fileName.replaceAll("/","_");
        fileName=fileName.toLowerCase();
        return fileName;
    }

    public static SpringFloatingActionMenu showSpringButton(final Context context){
        final SpringFloatingActionMenu springFloatingActionMenu;
        final FloatingActionButton fab = new FloatingActionButton(context);
        fab.setType(FloatingActionButton.TYPE_NORMAL);
        fab.setImageResource(R.mipmap.ic_launcher);
        fab.setColorPressedResId(R.color.sprBtnPressed);
        fab.setColorNormalResId(R.color.sprIconBg);
        fab.setColorRippleResId(R.color.sprIconBg);
        fab.setShadow(true);
        /*springFloatingActionMenu=*/
          return      new SpringFloatingActionMenu.Builder(context)
                .fab(fab)
                .addMenuItem(R.color.sprIconBg, R.mipmap.ic_launcher, "My Tickets", R.color.springTextCl, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Utils.toastS(context,"Tickets");
                        Intent i=new Intent(context, MyTicketsActivity.class);
                        context.startActivity(i);
                    }
                })
                .addMenuItem(R.color.sprIconBg, R.mipmap.ic_launcher, "Sponsors", R.color.springTextCl, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Utils.toastS(context,"Sponsors");
                        Intent i=new Intent(context, SponsorsActivity.class);
                        context.startActivity(i);
                    }
                }).addMenuItem(R.color.sprIconBg, R.mipmap.ic_launcher, "About", R.color.springTextCl, new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          Utils.toastS(context,"About");
                          Intent i=new Intent(context, AboutActivity.class);
                          context.startActivity(i);
                      }
                  })
                .addMenuItem(R.color.sprIconBg, R.mipmap.ic_launcher, "Location", R.color.springTextCl, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       // Utils.toastS(context,"Location");
                        Intent i=new Intent(context, MapsActivity.class);
                        context.startActivity(i);
                    }
                })
                .addMenuItem(R.color.sprIconBg, R.mipmap.ic_launcher, "Developers", R.color.springTextCl, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Utils.toastS(context,"Developers");
                        Intent i=new Intent(context, DevelopersActivity.class);
                        context.startActivity(i);

                    }
                })
                //you can choose menu layout animation
                .animationType(SpringFloatingActionMenu.ANIMATION_TYPE_TUMBLR)
                //setup reveal color while the menu opening

                .revealColor(R.color.sprBtnRev)
                //set FAB location, only support bottom center and bottom right

                .gravity(Gravity.RIGHT | Gravity.BOTTOM)
                .onMenuActionListner(new OnMenuActionListener() {
                    @Override
                    public void onMenuOpen() {
                        //set FAB icon when the menu opened

                        fab.setImageResource(R.mipmap.ic_launcher);
                    }

                    @Override
                    public void onMenuClose() {
                        //set back FAB icon when the menu closed

                        fab.setImageResource(R.mipmap.ic_launcher);
                    }
                })
                .build();



    }
}
