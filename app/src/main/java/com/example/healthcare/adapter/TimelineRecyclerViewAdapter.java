package com.example.healthcare.adapter;

import java.util.List;

import com.example.healthcare.R;
import com.example.healthcare.entity.TimelineDO;
import com.github.vipulasri.timelineview.TimelineView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by karangarg on 20/03/17.
 */

public class TimelineRecyclerViewAdapter extends
    RecyclerView.Adapter<TimelineRecyclerViewAdapter.TimelineViewHolder> {

    private String TAG = TimelineRecyclerViewAdapter.class.getSimpleName();
    private List<TimelineDO> timelineDOs;

    public TimelineRecyclerViewAdapter(Context context, List<TimelineDO> timelineDOs) {
        this.timelineDOs = timelineDOs;
    }

    @Override
    public int getItemCount() {
        return timelineDOs.size();
    }


    @Override
    public void onBindViewHolder(final TimelineViewHolder TimelineViewHolder, int i) {

        TimelineDO timelineDO = timelineDOs.get(i);
        TimelineViewHolder.timelineMonth.setText(timelineDO.getTimeline());
        if (TimelineDO.Type.BEFORE_BIRTH.name().equals(timelineDO.getType().name())) {
            TimelineViewHolder.timelineNutrition.setText(String.valueOf(timelineDO.getNutrition()));
            TimelineViewHolder.timelineVaccination.setText(String.valueOf(timelineDO.getVaccination()));
            TimelineViewHolder.timeLineTextView1.setText("Nutrition");
            TimelineViewHolder.timeLineTextView3.setText("Vaccination");
        }else {
            TimelineViewHolder.timelineNutrition.setText(String.valueOf(timelineDO.getVaccination()));
            TimelineViewHolder.timelineVaccination.setText(String.valueOf(timelineDO.getNutrition()));
            TimelineViewHolder.timeLineTextView1.setText("Vaccination");
            TimelineViewHolder.timeLineTextView3.setText("Nutrition");
        }

        TimelineViewHolder.timelineSuppliments.setText(String.valueOf(timelineDO.getSuppliments()));
    }

    @Override
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position,getItemCount());
    }

    @Override
    public TimelineViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View itemView;
        itemView = LayoutInflater.from(viewGroup.getContext()).inflate(
            R.layout.item_timeline_recyclerview, viewGroup, false);
        return new TimelineViewHolder(itemView, viewType);

    }

    static class TimelineViewHolder extends RecyclerView.ViewHolder{

        TextView timelineMonth, timelineNutrition, timelineSuppliments, timelineVaccination, timeLineTextView1, timeLineTextView3;
        TimelineView timelineView;

        TimelineViewHolder(View v, int viewType) {
            super(v);
            timelineMonth = (TextView) v.findViewById(R.id.timelineMonth);
            timelineNutrition = (TextView) v.findViewById(R.id.timelineNutrition);
            timeLineTextView1 = (TextView) v.findViewById(R.id.timelineTextview1);
            timeLineTextView3 = (TextView) v.findViewById(R.id.timelineTextview3);
            timelineSuppliments = (TextView) v.findViewById(R.id.timelineSuppliments);
            timelineVaccination = (TextView) v.findViewById(R.id.timelineVaccination);
            timelineView = (TimelineView) v.findViewById(R.id.timelineMarker);
            timelineView.initLine(viewType);

        }
    }


}