package com.example.sicaksumobileapp.activities;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;


import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sicaksumobileapp.R;
import com.example.sicaksumobileapp.SicakSuApp;
import com.example.sicaksumobileapp.models.SicakSuEvent;
import com.example.sicaksumobileapp.models.SicakSuProfile;
import com.example.sicaksumobileapp.repository.EventRepo;
import com.google.android.material.snackbar.Snackbar;

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
         * Images are recycled by the framework
         * we manage the images ourselves so we don't recycle them
         *  */
        holder.setIsRecyclable(false);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeedListViewHolder holder, int position) {

        SicakSuApp app = (SicakSuApp)((Activity)context).getApplication();

        //then manage the list selection/click event

        holder.row.setOnClickListener(v->{

//            Intent i = new Intent(context,ActivityDetails.class);
//            i.putExtra("id",data.get(position).getId());
//
//            ((Activity)context).startActivity(i);
            Log.e("Beyza","BASILDIM");
        });
        holder.joinButton.setOnClickListener(v->{
            /*
            //todo: when add profile change here to your profile
            SicakSuProfile yourProfile = new SicakSuProfile("6471dc1fe27cea661daa54b9","Hello","Carta","Nakama");

            if(sicakEvent.getJoinedPeople().contains(yourProfile)
            && sicakEvent.getLimit() > sicakEvent.getJoinCount()){
                EventRepo repo = new EventRepo();

                int answer = repo.joinEvent(yourProfile.getId(),sicakEvent.getId());
                if(answer == 1){
                    List<SicakSuProfile> newPro = new ArrayList<>();
                    newPro.addAll(data.get(position).getJoinedPeople());
                    newPro.add(yourProfile);


                    data.get(position).setJoinedPeople(newPro);
                    SicakSuEvent sicakEvent = data.get(position);
                    data.get(position).setJoinCount(data.get(position).getJoinCount()+1);
                    String joinCount = String.valueOf(data.get(position).getJoinCount())+"/"+ String.valueOf(data.get(position).getLimit());
                    holder.rowJoinCount.setText(joinCount);
                }

            }
                */

            Log.e("JoinButton","pressed");
        });
        holder.downloadImage(app.srv,data.get(position).getCreatedBy().getImageUrl());
        holder.rowHeadline.setText(data.get(position).getHeadline());
        holder.rowContent.setText(data.get(position).getContent());
        String joinCount = String.valueOf(data.get(position).getJoinCount())+"/"+ String.valueOf(data.get(position).getLimit());
        holder.rowJoinCount.setText(joinCount);
        // Define the desired date-time format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH");
        // Format the LocalDateTime object as a string
        String formattedDateTime = data.get(position).getRequestDate().format(formatter);
        holder.rowRequestDate.setText(formattedDateTime);
        holder.name.setText(data.get(position).getCreatedBy().getName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    class FeedListViewHolder extends RecyclerView.ViewHolder{
        LinearLayoutCompat row;
        TextView rowHeadline;
        TextView rowContent;
        TextView rowRequestDate;
        TextView rowJoinCount;
        TextView name;
        ImageView rowProfilePicture;
        Button joinButton;
        boolean imageDownloaded = false;

        public FeedListViewHolder(@NonNull View itemView) {
            super(itemView);
            row = itemView.findViewById(R.id.row_list_item);
            rowHeadline = itemView.findViewById(R.id.row_headline);
            rowContent = itemView.findViewById(R.id.row_content);
            rowRequestDate = itemView.findViewById(R.id.row_request_date);
            rowJoinCount = itemView.findViewById(R.id.row_join_count);
            name = itemView.findViewById(R.id.row_name);
            rowProfilePicture = itemView.findViewById(R.id.row_profile_picture);
            joinButton = itemView.findViewById(R.id.row_join_button);
        }

        Handler imageHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {

                rowProfilePicture.setImageBitmap((Bitmap) msg.obj);
                imageDownloaded = true;

                return true;
            }
        });

        public void downloadImage(ExecutorService srv, String path){

            if(imageDownloaded==false){
                EventRepo repo = new EventRepo();
                repo.downloadImage(srv,imageHandler,path);
            }

        }

    }
}
