package com.example.writery;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.button.MaterialButton;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;



public class writeRecyclerViewAdpater extends RecyclerView.Adapter<writeRecyclerViewAdpater.ViewHolder> {

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        MaterialButton Modifybutton;

        ViewHolder(View view){
            super(view);
            title = view.findViewById(R.id.write_cardview_title);
            Modifybutton = view.findViewById(R.id.write_card_modify_button);

            //수정 버튼 클릭 시 WriteContent 클래스에 제목과 내용 전달
            Modifybutton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Intent intent = new Intent(v.getContext(), WriteContent.class);
                    int id = writeArrayList.get(position).getID();
                    Log.d("ID", Integer.toString(id));
                    Log.d("CODE", Integer.toString(code));
                    intent.putExtra("ID", id);
                    intent.putExtra("code", code);
                    v.getContext().startActivity(intent);
                }
            });
            //ReadActivity에 제목과 내용 전달
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Intent intent = new Intent(v.getContext(), ReadActivity.class);
                    int id = writeArrayList.get(position).getID();
                    Log.d("ID", Integer.toString(id));
                    Log.d("CODE", Integer.toString(code));
                    intent.putExtra("ID", id);
                    intent.putExtra("code", code);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    private ArrayList<EpisodeItem> writeArrayList;
    private int code;
    writeRecyclerViewAdpater(ArrayList<EpisodeItem> writeArrayList, int code){
        this.writeArrayList = writeArrayList;
        this.code = code;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.write_cardview, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Log.d("epiosodeID", Integer.toString(i));
        viewHolder.title.setText(writeArrayList.get(i).getEpisodeTitle());
    }

    @Override
    public int getItemCount() {
        return writeArrayList.size();
    }
}