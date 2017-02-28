package dev.elementsculmyca.ec2017.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import dev.elementsculmyca.ec2017.BitmapHandlers.BItmapHelper;
import dev.elementsculmyca.ec2017.DatabaseHandlers.EventDetails;
import dev.elementsculmyca.ec2017.EventDescriptionDetails;
import dev.elementsculmyca.ec2017.R;
import dev.elementsculmyca.ec2017.Utility.Utils;

/**
 * Created by hemba on 2/8/2017.
 */

public class RecyclerViewHorizontalAdapter extends RecyclerView.Adapter<RecyclerViewHorizontalAdapter.RecyclerViewHolder> {
    ArrayList<EventDetails> eventList;
    Context context;
    public RecyclerViewHorizontalAdapter(ArrayList<EventDetails> eventList,Context context) {
        this.eventList = eventList;
        this.context=context;
    }
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_event_card,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {
        holder.eventName.setText(eventList.get(position).getEventName().toUpperCase());
       holder.img.setImageBitmap(BItmapHelper.decodeSampledBitmapFromResource(context.getResources(),
                Utils.getResIdByName(context,eventList.get(position).getEventName().toLowerCase())
                ,holder.img.getMaxWidth(),holder.img.getMaxHeight()));

        //holder.img.setImageResource(R.drawable.auto_design_quiz);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context,EventDescriptionDetails.class);
                i.putExtra("eventName",eventList.get(position).getEventName());
                i.putExtra("eventId",eventList.get(position).getEventId());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        /*if (eventList.size()>3){
            return 3;
        }*/
        return eventList.size();
    }


    protected  class RecyclerViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView eventName;
        CardView cardView;
        public RecyclerViewHolder(View view) {
            super(view);
            img=(ImageView)view.findViewById(R.id.custom_row_card_image_view);
            eventName=(TextView)view.findViewById(R.id.custom_row_card_event_name);
            cardView=(CardView)view.findViewById(R.id.custom_row_cardview);
        }
    }
    public  void s(){

    }
}
