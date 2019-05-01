package com.example.writery;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ReadRecyclerView extends RecyclerView.Adapter<ReadRecyclerView.ViewHolder> {
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.on_read_layout, viewGroup, false);
        return new ViewHolder(v);
    }

    private ArrayList<ReadItem> readItems = new ArrayList<>();
    public ReadRecyclerView(ArrayList<ReadItem> readItems){
        this.readItems = readItems;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.contents.setText(readItems.get(i).page);
    }

    @Override
    public int getItemCount() {
        return readItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView contents;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            contents = itemView.findViewById(R.id.on_read_contents);
        }
    }
}
