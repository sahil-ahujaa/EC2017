package dev.elementsculmyca.ec2017;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import dev.elementsculmyca.ec2017.DatabaseHandlers.DbHelper;
import dev.elementsculmyca.ec2017.DatabaseHandlers.EventDetails;
import dev.elementsculmyca.ec2017.Utility.Utils;

public class EventDescriptionDetails extends AppCompatActivity {
    TextView venueTV,descTV,rulesTV,timeTV,feeTV;
    Button registerBtn;
    final DbHelper dbHelper=new DbHelper(EventDescriptionDetails.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_description_details);
        venueTV=(TextView)findViewById(R.id.event_details_loc_text);
        descTV=(TextView)findViewById(R.id.event_details_description_text);
        rulesTV=(TextView)findViewById(R.id.event_details_rules_text);
        timeTV=(TextView)findViewById(R.id.event_details_time_text);
        registerBtn=(Button)findViewById(R.id.event_details_register_btn);
        Intent i=getIntent();
        final String eventName=i.getStringExtra("eventName");
        final String eventId=i.getStringExtra("eventId");
        setTitle(eventName);
        EventDetails eventDetails=dbHelper.retriveEventDetails(eventId);
        venueTV.setText(eventDetails.getVenue());
        descTV.setText(eventDetails.getDescription());
        rulesTV.setText(eventDetails.getRules());
        timeTV.setText(eventDetails.getStartTime()+" to "+eventDetails.getEndTime());
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(EventDescriptionDetails.this,RegisterationActivity.class);
                i.putExtra("eventName",eventName);
                i.putExtra("eventId",eventId);
                startActivity(i);
                overridePendingTransition(R.anim.right_to_left_slide,R.anim.right_to_left_slide);
            }
        });

    }
}
