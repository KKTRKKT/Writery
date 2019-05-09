package com.example.writery;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class ModifyAndDeleteDialog {
    public MainActivity context;
    public int position;

    public ModifyAndDeleteDialog(MainActivity context, int position){
        this.context = context;
        this.position = position;
    }

    public void callFunction() {

        // 커스텀 다이얼로그를 정의하기위해 Dialog클래스를 생성한다.
        final Dialog dlg = new Dialog(context);

        // 액티비티의 타이틀바를 숨긴다.
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // 커스텀 다이얼로그의 레이아웃을 설정한다.
        dlg.setContentView(R.layout.longtouch_menu);

        dlg.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        // 커스텀 다이얼로그를 노출한다.
        dlg.show();

        // 커스텀 다이얼로그의 각 위젯들을 정의한다.
        final Button modifyButton = (Button) dlg.findViewById(R.id.modify_btn);
        final Button delButton = (Button) dlg.findViewById(R.id.del_btn);

        modifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WriteActivity.class);
                Log.d("ID", Integer.toString(position));
                intent.putExtra("ID", position);
                context.startActivity(intent);
                context.overridePendingTransition(R.anim.anim_static, R.anim.anim_static);
                dlg.dismiss();
            }
        });
        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.delNobel(position);
                context.showNobel();
                context.adapterList();
                dlg.dismiss();
            }
        });
    }
}
