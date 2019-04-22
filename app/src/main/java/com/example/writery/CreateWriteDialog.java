package com.example.writery;

import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class CreateWriteDialog {
    public MainActivity context;

    public CreateWriteDialog(MainActivity context){
        this.context = context;
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
        final TextView title = dlg.findViewById(R.id.dlg_title);
        final Button yesButton = (Button) dlg.findViewById(R.id.okButton);
        final Button noButton = (Button) dlg.findViewById(R.id.cancelButton);

        title.setText("새로운 소설을 생성하시겠습니까?");

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.list.add(new nobelItem());
                Intent intent = new Intent(context, WriteActivity.class);
                view.getContext().startActivity(intent);
                dlg.dismiss();
            }
        });
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dlg.dismiss();
            }
        });
    }
}
