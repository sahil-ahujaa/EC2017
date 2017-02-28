package dev.elementsculmyca.ec2017;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import dev.elementsculmyca.ec2017.Adapters.SponsorsRecyclerAdapter;
import dev.elementsculmyca.ec2017.BitmapHandlers.BItmapHelper;
import dev.elementsculmyca.ec2017.Utility.SponsorsDetails;

public class SponsorsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsors);
        setTitle("Sponsors");
        recyclerView=(RecyclerView)findViewById(R.id.sponsors_recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(SponsorsActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<SponsorsDetails> sponsorList=new ArrayList<>();
        SponsorsDetails sponsorsDetails=new SponsorsDetails("Hackerearth","(Tech Sponsor)",R.drawable.hackerearth_identity_white);
        sponsorList.add(sponsorsDetails);
        sponsorList.add(sponsorsDetails);
        //sponsorList.add(sponsorsDetails);
        recyclerView.setAdapter(new SponsorsRecyclerAdapter(sponsorList,SponsorsActivity.this));

    }
}
