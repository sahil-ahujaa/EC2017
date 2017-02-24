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

import dev.elementsculmyca.ec2017.Utility.Utils;

public class EventDescriptionDetails extends AppCompatActivity {
    Toolbar titleTb;
    TextView venueText,descText,rulesText,startTimeTV,endTimeTV,clubNameTV;
    ImageView venueImageView;
    Button registerBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_description_details);
        titleTb = (Toolbar) findViewById(R.id.toolbar);
        registerBtn=(Button)findViewById(R.id.register_btn);
        startTimeTV=(TextView)findViewById(R.id.start_time_tv);
        endTimeTV=(TextView)findViewById(R.id.end_time_tv);
       venueImageView=(ImageView)findViewById(R.id.venue_image_view);
        clubNameTV=(TextView)findViewById(R.id.club_name_text_view);
       // dateTV=(TextView)findViewById(R.id.date_textView);
        /*
        Resources resources=getResources();
        final int resId=resources.getIdentifier("ic_location1","drawable",getPackageName());*/

        venueImageView.setImageDrawable(Utils.getDrawableByName(EventDescriptionDetails.this,"ic_location1"));
        venueText=(TextView)findViewById(R.id.location_textview);
        descText=(TextView)findViewById(R.id.details_text_view);
        rulesText=(TextView)findViewById(R.id.rules_text_view);
        Intent i=getIntent();
        final String title=i.getStringExtra("title");
        String desc=i.getStringExtra("desc");
        String venue=i.getStringExtra("venue");
        String rules=i.getStringExtra("rules");
        clubNameTV.setText(i.getStringExtra("clubname"));

        final String eventId=i.getStringExtra("eventId");
        String eventEndTime=i.getStringExtra("endTime");
        String eventStartTime=i.getStringExtra("startTime");
        endTimeTV.setText(eventEndTime);
        startTimeTV.setText(eventStartTime);
        /*String date=i.getStringExtra("date");*/
        Log.d("as",desc);
        titleTb.setTitle(title);

        descText.setText(desc);
        venueText.setText(venue);
        rulesText.setText(rules);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(EventDescriptionDetails.this,RegisterationActivity.class);
                i.putExtra("eventId",eventId);
                i.putExtra("eventName",title);
                startActivity(i);
            }
        });


    }
}
