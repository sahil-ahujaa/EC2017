package dev.elementsculmyca.ec2017;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.tiancaicc.springfloatingactionmenu.*;

import java.util.ArrayList;

import dev.elementsculmyca.ec2017.Adapters.EventCategoryViewAdapter;
import dev.elementsculmyca.ec2017.DatabaseHandlers.DbHelper;
import dev.elementsculmyca.ec2017.DatabaseHandlers.EventDetails;
import dev.elementsculmyca.ec2017.Utility.ConstantUtils;

public class EventCategoryActivity extends AppCompatActivity {
    SpringFloatingActionMenu springFloatingActionMenu;
    ArrayList<Integer> carouselImages=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_category);
        setRecyclerView();
        springFloatingActionMenu= dev.elementsculmyca.ec2017.Utility.Utils.showSpringButton(EventCategoryActivity.this);
    }
    private void setRecyclerView(){
                carouselImages.clear();
                carouselImages.add(R.drawable.img1);
                carouselImages.add(R.drawable.img2);
                carouselImages.add(R.drawable.img3);
                ArrayList<Integer> itemtype=new ArrayList<>();
                itemtype.add(ConstantUtils.CAROUSEL_VIEW_TYPE);
                final DbHelper db=new DbHelper(EventCategoryActivity.this);
                ArrayList<String> categories=db.retriveCategory();
                ArrayList<ArrayList<EventDetails>> eventList=new ArrayList<>();
                int s=categories.size();
                Log.d("Main","as"+s);
                for (int i=0;i<s;i++){
                    ArrayList<EventDetails> e=new ArrayList<EventDetails>();
                    e=db.retrieveEventsByCategory(categories.get(i));
                    Log.d("cat=",categories.get(i));
                    eventList.add(e);
                    itemtype.add(ConstantUtils.CATEGORY_EVENT_TYPE);
                }
                RecyclerView recyclerView=(RecyclerView)findViewById(R.id.category_activity_recycler_view);
               LinearLayoutManager layoutManager=new LinearLayoutManager(EventCategoryActivity.this);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(new EventCategoryViewAdapter(itemtype,carouselImages,categories,eventList,EventCategoryActivity.this));
            }
    @Override
    public void onBackPressed() {
        if(springFloatingActionMenu.isMenuOpen()){
            springFloatingActionMenu.hideMenu();
        }else {
            super.onBackPressed();
        }

    }
}
