package com.example.writery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class WriteContent extends Activity {
    public EpisodeDBHandler dbHandler = new EpisodeDBHandler(this, null, null, 0);

    EpisodeItem episodeItem;
    ArrayList<EpisodeItem> episodeItemArrayList = new ArrayList<>();

    Bundle extras;
    int code;
    int ID;

    MaterialButton Save;
    EditText contents;
    EditText title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.on_wirte_layout);

        Save = findViewById(R.id.save);
        contents = findViewById(R.id.on_write_contents);
        title = findViewById(R.id.on_write_title);

        extras = getIntent().getExtras();
        if(extras == null){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        code = extras.getInt("code");
        ID = extras.getInt("ID");
        title.setText(dbHandler.findEpisode(ID).getEpisodeTitle());
        contents.setText(dbHandler.findEpisode(ID).getContents());
    }

    @Override
    public void onBackPressed() {
        SaveMomentDialog customDialog = new SaveMomentDialog(WriteContent.this, code, ID);
        customDialog.callFunction();
    }


    public void erase(View view) {
        contents.setText("");
    }

    public void savebtn(View view) {
        save();
        Toast.makeText( this, "저장 되었습니다", Toast.LENGTH_SHORT).show();
    }

    public void save() {
        String episodeTitle = title.getText().toString();
        String content = contents.getText().toString();
        dbHandler.update(ID, episodeTitle, content);
//        episodeItem = dbHandler.findEpisode(ID);
//        Log.d("episode", episodeItem.getEpisodeTitle());
//        episodeItemArrayList = dbHandler.showEpisode(code);
    }
}
