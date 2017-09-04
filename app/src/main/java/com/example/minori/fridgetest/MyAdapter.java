package com.example.minori.fridgetest;

import android.app.Activity;
import android.content.Context;
import android.service.autofill.Dataset;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import static android.R.attr.value;
import static android.content.ContentValues.TAG;

/**
 * Created by yahata-minori on 2017/08/21.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private Context context;
    private List<Upload> uploads;
    //private String deleteUrl;
    //private String deleteKey;


    private DatabaseReference mDatabase;


    public MyAdapter(Context context, List<Upload> uploads) {
        this.uploads = uploads;
        this.context = context;
        mDatabase = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_images, parent, false);
        final ViewHolder viewHolder = new ViewHolder(v);

        // 長押しクリックリスナを搭載
        v.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final int position = viewHolder.getAdapterPosition(); //positionを取得
                Upload upload = uploads.get(position);

                Query mDeleteQuery = mDatabase;

                mDeleteQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String value = dataSnapshot.getValue(String.class);
                        Log.d(TAG, "Value is: " + value);
                        //Toast.makeText(context, , Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });



                // deleteUrl = upload.getUrl();
                // deleteKey = mDatabase.child("uploads").child("url").getKey();
                // Toast.makeText(context, deleteKey, Toast.LENGTH_LONG).show();
                /* mDatabase.child("uploads").child("name").child("ghi")
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                               // for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                                    String deleteKey = dataSnapshot.getKey();
                                    Toast.makeText(context, deleteKey, Toast.LENGTH_LONG).show();
                              //  }

                                //Toast.makeText(context, deleteKey, Toast.LENGTH_LONG).show(); //ためしにトーストでポジション表示
                                // mDatabase.child("uploads").child(deleteKey).removeValue();
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                        */

                //   mDatabase.child("upload").updateChildren(null);



                return true;

            }
        });

        return viewHolder;

    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Upload upload = uploads.get(position);

        holder.textViewName.setText(upload.getName());

        Glide.with(context).load(upload.getUrl()).into(holder.imageView);

        holder.textViewDate.setText(upload.getTakenDate());

    }

    @Override
    public int getItemCount() {
        return uploads.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewName;
        public ImageView imageView;
        public TextView textViewDate;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            textViewDate = (TextView) itemView.findViewById(R.id.text_date_taking_picture);
        }
    }



}
