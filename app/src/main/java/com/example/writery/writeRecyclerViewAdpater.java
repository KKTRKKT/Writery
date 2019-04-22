package com.example.writery;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.button.MaterialButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;



public class writeRecyclerViewAdpater extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        MaterialButton button;

        ViewHolder(View view){
            super(view);
            title = view.findViewById(R.id.write_title);
            button = view.findViewById(R.id.modify_button);

            //수정 버튼 클릭 시 WriteContent 클래스에 제목과 내용 전달
            button.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Intent intent = new Intent(v.getContext(), WriteContent.class);
                    String title = writeArrayList.get(position).episodeTitle;
                    String contents = writeArrayList.get(position).contents;
                    intent.putExtra("title", title);
                    intent.putExtra("contents", contents);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    private ArrayList<EpisodeItem> writeArrayList;
    writeRecyclerViewAdpater(ArrayList<EpisodeItem> writeArrayList){
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
        viewHolder1.title.setText(writeArrayList.get(i).episodeTitle);
    }

    @Override
    public int getItemCount() {
        return writeArrayList.size();
    }
}