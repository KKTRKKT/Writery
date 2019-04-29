package com.example.writery;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class WriteContent extends Activity {
    public EpisodeDBHandler dbHandler = new EpisodeDBHandler(this, null, null, 0);

    Bundle extras;
    int code;

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
            return;
        }

        code = extras.getInt("ID");
    }

    @Override
    public void onBackPressed() {
        SaveMomentDialog customDialog = new SaveMomentDialog(WriteContent.this, code);
        customDialog.callFunction();
    }

    public void back(){
        super.onBackPressed();
    }

    public void erase(View view) {
        contents.setText("");
    }

    public void save(View view) {
        if(dbHandler.)
        addEpisode();
    }

    public void addEpisode(){
        String episodeTitle = title.getText().toString();
        String content = contents.getText().toString();
        EpisodeItem episodeItem = new EpisodeItem(code, episodeTitle, content);
        dbHandler.addEpisode(episodeItem);
        Toast.makeText(this, "저장 되었습니다.", Toast.LENGTH_SHORT).show();
    }
}
