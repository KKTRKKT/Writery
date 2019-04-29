package com.example.writery;

import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class SaveMomentDialog {
    public int code;
    public WriteContent context;

    public SaveMomentDialog(WriteContent context, int code){
        this.context = context;
        this.code = code;
    }
    public void callFunction() {

        // 커스텀 다이얼로그를 정의하기위해 Dialog클래스를 생성한다.
        final Dialog dlg = new Dialog(context);

        // 액티비티의 타이틀바를 숨긴다.
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // 커스텀 다이얼로그의 레이아웃을 설정한다.
        dlg.setContentView(R.layout.custom_alterdialog);

        // 커스텀 다이얼로그를 노출한다.
        dlg.show();

        // 커스텀 다이얼로그의 각 위젯들을 정의한다.
        final Button okButton = (Button) dlg.findViewById(R.id.okButton);
        final Button cancelButton = (Button) dlg.findViewById(R.id.cancelButton);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                context.addEpisode();
                EpisodeItem episodeItem = new EpisodeItem(code,
                        context.title.getText().toString(), context.contents.getText().toString());
                context.dbHandler.addEpisode(episodeItem);
                Toast.makeText(context, "임시 저장되었습니다.", Toast.LENGTH_SHORT).show();
                dlg.dismiss();
                context.back();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dlg.dismiss();
                context.back();
            }
        });
    }

}
