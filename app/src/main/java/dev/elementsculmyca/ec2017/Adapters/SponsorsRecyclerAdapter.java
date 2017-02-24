package dev.elementsculmyca.ec2017.Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import dev.elementsculmyca.ec2017.R;
import dev.elementsculmyca.ec2017.Utility.SponsorsDetails;

/**
 * Created by hemba on 2/15/2017.
 */

public class SponsorsRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<SponsorsDetails> sponsorList;
    Context context;

    public SponsorsRecyclerAdapter(ArrayList<SponsorsDetails> sponsorList, Context context) {
        this.sponsorList = sponsorList;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==0){
            return new SponsorsLeftHolder(LayoutInflater.from(context).inflate(R.layout.sponsors_left_layout,parent,false));
        }else {
            return new SponsorsRightHolder(LayoutInflater.from(context).inflate(R.layout.sponsors_right_layout,parent,false));
        }

    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SponsorsLeftHolder){
            ((SponsorsLeftHolder) holder).imageView.setImageResource(sponsorList.get(position).getLogo());
            ((SponsorsLeftHolder) holder).nameText.setText(sponsorList.get(position).getName());
            ((SponsorsLeftHolder) holder).titleText.setText(sponsorList.get(position).getTitle());
        }else if (holder instanceof SponsorsRightHolder) {

            ((SponsorsRightHolder) holder).imageView.setImageResource(sponsorList.get(position).getLogo());
            ((SponsorsRightHolder) holder).nameText.setText(sponsorList.get(position).getName());
            ((SponsorsRightHolder) holder).titleText.setText(sponsorList.get(position).getTitle());

        }
        else {
            return ;
        }

    }

    @Override
    public int getItemCount() {
        return sponsorList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position%2;
    }

    protected class SponsorsLeftHolder extends RecyclerView.ViewHolder{
        TextView nameText,titleText;
        ImageView imageView;
        public SponsorsLeftHolder(View itemView) {
            super(itemView);
            nameText=(TextView)itemView.findViewById(R.id.sponsor_left_name_text);
            titleText=(TextView)itemView.findViewById(R.id.sponsor_left_title_text);
            imageView=(ImageView)itemView.findViewById(R.id.sponsor_left_logo);

        }
    }
    protected class SponsorsRightHolder extends RecyclerView.ViewHolder{

        TextView nameText,titleText;
        ImageView imageView;
        public SponsorsRightHolder(View itemView) {
            super(itemView);
            nameText=(TextView)itemView.findViewById(R.id.sponsor_right_name_text);
            titleText=(TextView)itemView.findViewById(R.id.sponsor_right_title_text);
            imageView=(ImageView)itemView.findViewById(R.id.sponsor_right_logo);
        }
    }


}
