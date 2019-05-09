package com.example.writery;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class NobelRecyclerViewAdapter extends RecyclerView.Adapter<NobelRecyclerViewAdapter.MyViewHolder> {

    private final ArrayList<NobelItem> list;

    NobelRecyclerViewAdapter(ArrayList<NobelItem> list) { this.list = list;}

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView image ;

        public MyViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.card_view_image);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int position = getAdapterPosition();
                    int id = list.get(position).getID();
                    MainActivity mainActivity = (MainActivity) view.getContext();
                    ModifyAndDeleteDialog modifyAndDeleteDialog = new ModifyAndDeleteDialog(mainActivity, id);
                    modifyAndDeleteDialog.callFunction();
                    Toast.makeText(view.getContext(), list.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    int id = list.get(position).getID();
                    Intent intent = new Intent(view.getContext(), WriteActivity.class);
                    intent.putExtra("ID", id);
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
        try {
            byte[] img = list.get(position).getImage();
            holder.image.setImageBitmap(BitmapFactory.decodeByteArray(img, 0, img.length));
        }catch (Exception e){
            holder.image.setImageResource(R.drawable.front);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
