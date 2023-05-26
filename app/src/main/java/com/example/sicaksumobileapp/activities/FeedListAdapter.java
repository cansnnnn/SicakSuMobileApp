package com.example.sicaksumobileapp.activities;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.ExecutorService;


import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sicaksumobileapp.R;
import com.example.sicaksumobileapp.SicakSuApp;
import com.example.sicaksumobileapp.models.SicakSuEvent;

import java.util.List;

public class FeedListAdapter extends RecyclerView.Adapter<FeedListAdapter.FeedListViewHolder>{
    Context context;
    List<SicakSuEvent> data;

    public FeedListAdapter(Context context, List<SicakSuEvent> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public FeedListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View root=
                LayoutInflater.from(context).inflate(R.layout.activity_feed_row,parent,false);
        FeedListViewHolder holder = new FeedListViewHolder(root);

        /*
        //ACTIVATE IF IT IS USING IMAGE
         * Images are recycled by the framework
         * we manage the images ourselves so we don't recycle them

        holder.setIsRecyclable(false);
        *  */
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeedListViewHolder holder, int position) {
        holder.txtHeadline.setText(data.get(position).getHeadline());
        holder.txtContent.setText(data.get(position).getContent());
        SicakSuApp app = (SicakSuApp)((Activity)context).getApplication();

        //then manage the list selection/click event

//        holder.row.setOnClickListener(v->{
//
//            Intent i = new Intent(context,ActivityDetails.class);
//            i.putExtra("id",data.get(position).getId());
//
//            ((Activity)context).startActivity(i);
//
//
//        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class FeedListViewHolder extends RecyclerView.ViewHolder{
        ConstraintLayout row;
        TextView txtHeadline;
        TextView txtContent;
        //todo : diger fieldlari da ekle
        public FeedListViewHolder(@NonNull View itemView) {
            super(itemView);
            row = itemView.findViewById(R.id.row_list_item);
            txtContent = itemView.findViewById(R.id.rowContent);
            txtHeadline = itemView.findViewById(R.id.rowHeadline);
        }

    }
}
