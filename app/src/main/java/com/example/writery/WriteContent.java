package com.example.writery;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.view.View;
import android.widget.EditText;

public class WriteContent extends Activity {
    MaterialButton Save;
    EditText contents;
    EditText title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.on_wirte_layout);

        Bundle extras = getIntent().getExtras();
        if(extras == null){
            return;
        }

        Save = findViewById(R.id.save);
        contents = findViewById(R.id.on_write_contents);
        title = findViewById(R.id.on_write_title);

        title.setText(extras.getString("title"));
        contents.setText(extras.getString("contents"));
    }


    @Override
    public void onBackPressed() {
        SaveMomentDialog customDialog = new SaveMomentDialog(WriteContent.this);
        customDialog.callFunction();
    }

    public void back(){
        super.onBackPressed();
    }

    public void erase(View view) {
        contents.setText("");
    }

    public void save(View view) {
        back();
    }
}
