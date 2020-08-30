package com.zoro.studdis.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.zoro.studdis.R;
import com.zoro.studdis.data.EventsModel;

import java.util.ArrayList;
import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventsViewHolder> {

    private Context context;
    private List<EventsModel> eventsList = new ArrayList<>();

    public EventsAdapter(Context context) {
        this.context = context;
    }

    public EventsAdapter(Context context, List<EventsModel> list) {
        this.context = context;
        this.eventsList = list;
    }

    public void setItems(List<EventsModel> list) {
        eventsList = list;
        notifyDataSetChanged();
    }

    public void clearItems() {
        eventsList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EventsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.events_list_item_layout, parent, false);
        return new EventsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventsViewHolder holder, int position) {
        holder.bind(context, eventsList.get(position));
    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }

    class EventsViewHolder extends RecyclerView.ViewHolder {

        TextView evItemsTitle, evItemsDate, evItemsTime, evItemsLoc, evItemsText;
        ImageView evOpenIc, evCloseIc;
        LinearLayout evTextLay;
        CardView cardView;

        public boolean isOpen;

        EventsViewHolder(@NonNull View itemView) {
            super(itemView);

            isOpen = false;

            evItemsTitle = itemView.findViewById(R.id.evItemTitle);
            evItemsDate = itemView.findViewById(R.id.evItemDate);
            evItemsTime = itemView.findViewById(R.id.evItemTime);
            evItemsLoc = itemView.findViewById(R.id.evItemLoc);
            evItemsText = itemView.findViewById(R.id.evItemText);

            evOpenIc = itemView.findViewById(R.id.evOpenIc);
            evCloseIc = itemView.findViewById(R.id.evCloseIc);

            evTextLay = itemView.findViewById(R.id.evTextLay);
            cardView = itemView.findViewById(R.id.evItemCard);
        }

        public void bind(Context context, EventsModel model) {
            evItemsTitle.setText(model.getTitle());
            evItemsText.setText(model.getText());
            evItemsDate.setText(model.getDate());
            evItemsTime.setText(model.getTime());
            evItemsLoc.setText(model.getLoc());

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ( !evTextLay.isShown() ) {
                        // slideDown(context, evTextLay);
                        expand(evTextLay);
                        evOpenIc.setVisibility(View.GONE);
                        evCloseIc.setVisibility(View.VISIBLE);
                    } else {
                        evOpenIc.setVisibility(View.VISIBLE);
                        evCloseIc.setVisibility(View.GONE);
                        // slideUp(context, evTextLay);
                        collapse(evTextLay);
                    }
                }
            });
        }

    }

    public static void expand(final View v) {
        int matchParentMeasureSpec = View.MeasureSpec.makeMeasureSpec(((View) v.getParent()).getWidth(), View.MeasureSpec.EXACTLY);
        int wrapContentMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        v.measure(matchParentMeasureSpec, wrapContentMeasureSpec);
        final int targetHeight = v.getMeasuredHeight();

        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        v.getLayoutParams().height = 1;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? ViewGroup.LayoutParams.WRAP_CONTENT
                        : (int)(targetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // Expansion speed of 1dp/ms
        a.setDuration((int)(targetHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    public static void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if(interpolatedTime == 1) {
                    v.setVisibility(View.GONE);
                }else{
                    v.getLayoutParams().height = initialHeight - (int)(initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // Collapse speed of 1dp/ms
        a.setDuration((int)(initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    public static void slideDown(Context ctx, View v) {
        Animation a = AnimationUtils.loadAnimation(ctx, R.anim.slide_down);
        if( a != null ) {
            a.reset();
            if ( v != null ){
                v.clearAnimation();
                v.startAnimation(a);
            }
        }
    }

    public static void slideUp(Context ctx, View v) {
        Animation a = AnimationUtils.loadAnimation(ctx, R.anim.slide_up);
        if( a != null ) {
            a.reset();
            if ( v != null ){
                v.clearAnimation();
                v.startAnimation(a);
            }
        }
    }

}
