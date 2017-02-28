package dev.elementsculmyca.ec2017.Adapters;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;

import dev.elementsculmyca.ec2017.BitmapHandlers.BItmapHelper;
import dev.elementsculmyca.ec2017.DatabaseHandlers.EventDetails;
import dev.elementsculmyca.ec2017.R;
import dev.elementsculmyca.ec2017.Utility.ConstantUtils;

/**
 * Created by hemba on 2/14/2017.
 */

public class EventCategoryViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<String> categoryName;
    ArrayList<Integer> imagesList;
    ArrayList<ArrayList<EventDetails>> eventList;
    Context context;
    ArrayList<Integer> viewType;

    public EventCategoryViewAdapter( ArrayList<Integer> itemType,ArrayList<Integer> imagesList,
                                    ArrayList<String> categoryName, ArrayList<ArrayList<EventDetails>> eventList, Context context) {

        this.imagesList=imagesList;
        this.categoryName = categoryName;
        this.eventList = eventList;
        this.context = context;
        this.viewType=itemType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType== ConstantUtils.CATEGORY_EVENT_TYPE){
            return new RecyclerViewHolder(LayoutInflater.from(context).inflate(R.layout.custom_category_row,parent,false));
        }
        else if (viewType==ConstantUtils.EVENT_CATEGORY_STRING_TYPE){
            return new CategoryTextViewHolder(LayoutInflater.from(context).inflate(R.layout.event_category_string_layout,parent,false));
        }
        else {
            return new CarouselViewHolder(LayoutInflater.from(context).inflate(R.layout.carousel_view_layout,parent,false));
        }
        //return new RecyclerViewHolder(LayoutInflater.from(context).inflate(R.layout.custom_category_row,parent,false));
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return ConstantUtils.CAROUSEL_VIEW_TYPE;
        }
        else if (position==1){
            return ConstantUtils.EVENT_CATEGORY_STRING_TYPE;
        }
        return ConstantUtils.CATEGORY_EVENT_TYPE;
        /*return viewType.get(position);*/
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RecyclerViewHolder){
            ((RecyclerViewHolder) holder).categoryTitle.setText(categoryName.get(position-2));
            /*((RecyclerViewHolder) holder).categoryTitle.setPaintFlags(((RecyclerViewHolder) holder).categoryTitle.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);*/
           LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            ((RecyclerViewHolder) holder).recyclerViewH.setLayoutManager(linearLayoutManager);
            ((RecyclerViewHolder) holder).recyclerViewH.setAdapter(new RecyclerViewHorizontalAdapter(eventList.get(position-2),context));

        }
        else if (holder instanceof CarouselViewHolder){
            ((CarouselViewHolder) holder).carouselView.setPageCount(imagesList.size());
            ((CarouselViewHolder) holder).carouselView.setImageListener(carouselImageListener);
        }

    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }
    protected class RecyclerViewHolder extends RecyclerView.ViewHolder{

        RecyclerView recyclerViewH;
        TextView categoryTitle;

        public RecyclerViewHolder (View view) {
            super(view);
            recyclerViewH=(RecyclerView)view.findViewById(R.id.event_horizontal_recycler_view);
            categoryTitle=(TextView)view.findViewById(R.id.custom_row_title);
        }
    }
    protected class CategoryTextViewHolder extends RecyclerView.ViewHolder{
        public CategoryTextViewHolder(View view) {
            super(view);
        }
    }

    protected class CarouselViewHolder extends RecyclerView.ViewHolder{
        CarouselView carouselView;
        public CarouselViewHolder(View itemView) {
            super(itemView);
            carouselView=(CarouselView)itemView.findViewById(R.id.custom_carousel);
        }
    }


    ImageListener carouselImageListener=new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {

            imageView.setImageBitmap(BItmapHelper.decodeSampledBitmapFromResource(
                    context.getResources(),imagesList.get(position),imageView.getMaxWidth()/2,imageView.getMaxHeight()/2));
        }
    };


}
