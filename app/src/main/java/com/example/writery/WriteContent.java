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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.on_wirte_layout);

        Save = findViewById(R.id.save);
        contents = findViewById(R.id.on_write_contents);
    }


    @Override
    public void onBackPressed() {

    }

    public void back(){
        super.onBackPressed();
    }

    public void erase(View view) {
        contents.setText("");
    }

    public void save(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 커스텀 다이얼로그를 생성한다. 사용자가 만든 클래스이다.
                SaveMomentDialog customDialog = new SaveMomentDialog(WriteContent.this);
            }
        });
    }
}
