package com.example.writery;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;



public class writeRecyclerViewAdpater extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;

        ViewHolder(View view){
            super(view);
            title = view.findViewById(R.id.write_title);

            view.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Intent intent = new Intent(v.getContext(), WriteContent.class);
                    String title1 = writeArrayList.get(position).title;
                    String contents = writeArrayList.get(position).contents;
                    intent.putExtra("title", title1);
                    intent.putExtra("contents", contents);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    private ArrayList<WriteItem> writeArrayList;
    writeRecyclerViewAdpater(ArrayList<WriteItem> writeArrayList){
        this.writeArrayList = writeArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.write_cardview, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder viewHolder1 = (ViewHolder)viewHolder;
        viewHolder1.title.setText(writeArrayList.get(i).title);
    }

    @Override
    public int getItemCount() {
        return writeArrayList.size();
    }
}