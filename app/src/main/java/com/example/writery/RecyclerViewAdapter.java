package com.example.writery;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private final ArrayList<NobelItem> list;

    RecyclerViewAdapter(ArrayList<NobelItem> list) { this.list = list;}

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView image ;

        public MyViewHolder(View itemView) {
            super(itemView);
            int position = getAdapterPosition();
            image = itemView.findViewById(list.get(position).getImage());


            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    MainActivity mainActivity = (MainActivity) view.getContext();
                    ModifyAndDeleteDialog modifyAndDeleteDialog = new ModifyAndDeleteDialog(mainActivity);
                    modifyAndDeleteDialog.callFunction();
                    return true;
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Intent intent = new Intent(view.getContext(), WriteActivity.class);
                    intent.putExtra("title", list.get(position).getTitle());
                    intent.putExtra("info", list.get(position).getInfo());
                    intent.putExtra("image", list.get(position).getImage());
                    String[] contents = new String[70];
                    String[] episodeTitle = new String[70];
                    for(int i = 0; list.get(position).writeItem.get(position) != null; i++) {
                        contents[i] = list.get(position).writeItem.get(position).getContents();
                        episodeTitle[i] = list.get(position).writeItem.get(position).getEpisodeTitle();
                    }
                    intent.putExtra("contents", contents);
                    intent.putExtra("episodeTitle", episodeTitle);
                    view.getContext().startActivity(intent);
                }
            });
    }
}


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview, viewGroup, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.image.setImageResource(list.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
