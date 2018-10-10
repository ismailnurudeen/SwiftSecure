package com.ss.swiftsecure;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView
        .Adapter<MyRecyclerViewAdapter
        .DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private ArrayList<Report> mDataset;
    private static MyClickListener myClickListener;
    public static class DataObjectHolder extends RecyclerView.ViewHolder implements View
            .OnClickListener {

        private TextView reportTimeStamp,reportLocation,reportDescription;
        ImageView reportThumbnail;

        public DataObjectHolder(View itemView) {
            super(itemView);
            reportDescription = itemView.findViewById(R.id.report_desc_summary);
            reportThumbnail =  itemView.findViewById(R.id.report_thumbnail);
            reportTimeStamp =  itemView.findViewById(R.id.report_time_stamp);
            reportLocation =  itemView.findViewById(R.id.report_location);
            Log.i(LOG_TAG, "Adding Listener");
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
// myClickListener.onItemClick(getAdapterPosition(), v);
// Toast.makeText(this,"This is card View",Toast.LENGTH_LONG).show();
        }
    }
    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }
    public MyRecyclerViewAdapter(ArrayList<Report> myDataset) {
        mDataset = myDataset;
    }
    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_row, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.reportDescription.setText(mDataset.get(position).getDescription());
        holder.reportThumbnail.setImageBitmap(mDataset.get(position).getImage());
        holder.reportTimeStamp.setText(mDataset.get(position).getTimeStamp());
        holder.reportLocation.setText(mDataset.get(position).getLocation());
    }
    public void addItem(Report dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }
    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}
