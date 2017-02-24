package dev.elementsculmyca.ec2017.Adapters;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dev.elementsculmyca.ec2017.R;


/**
 * Created by hemba on 2/15/2017.
 */

    public class DevelopersCardPagerAdapter extends PagerAdapter implements DevelopersCardAdapter {

        private List<CardView> mViews;
        private List<DevelopersCardItem> mData;
        private float mBaseElevation;

        public DevelopersCardPagerAdapter() {
            mData = new ArrayList<>();
            mViews = new ArrayList<>();
        }

        public void addCardItem(DevelopersCardItem item) {
            mViews.add(null);
            mData.add(item);
        }

        public float getBaseElevation() {
            return mBaseElevation;
        }

        @Override
        public CardView getCardViewAt(int position) {
            return mViews.get(position);
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(container.getContext())
                    .inflate(R.layout.developers_card_layout, container, false);
            container.addView(view);
            bind(mData.get(position), view);
            CardView cardView = (CardView) view.findViewById(R.id.cardView);

            if (mBaseElevation == 0) {
                mBaseElevation = cardView.getCardElevation();
            }

            cardView.setMaxCardElevation(mBaseElevation * MAX_ELEVATION_FACTOR);
            mViews.set(position, cardView);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
            mViews.set(position, null);
        }

        private void bind(final DevelopersCardItem item, View view) {
            TextView titleTextView = (TextView) view.findViewById(R.id.devTitle);
            TextView nameTextView = (TextView) view.findViewById(R.id.devName);
            ImageView imageView = (ImageView) view.findViewById(R.id.devImage);
            ImageView linkedinImageView=(ImageView)view.findViewById(R.id.linked_icon);
            linkedinImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(item.getLinkedinHandle()));
                    item.getContext().startActivity(intent);
                }
            });
            titleTextView.setText(item.getTitle());
            nameTextView.setText(item.getName());
            imageView.setImageResource(item.getImage());
        }

    }


