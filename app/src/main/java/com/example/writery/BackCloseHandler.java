package com.example.writery;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

public class BackCloseHandler {
    private long backKeyClickTime = 0;
    private Activity activity;
    private Toast toast;

    public BackCloseHandler(Activity activity) {
        this.activity = activity;
    }

    public void onBackPressed() {
        if (System.currentTimeMillis() > backKeyClickTime + 2000) {
            backKeyClickTime = System.currentTimeMillis();
            showToast();
            return;
        }
        if (System.currentTimeMillis() <= backKeyClickTime + 2000) {
            toast.cancel();

            Intent t = new Intent(activity, MainActivity.class);
            activity.startActivity(t);

            activity.moveTaskToBack(true);
            activity.finish();
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }

    public void showToast() {
        toast = Toast.makeText(activity, "한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT);
        toast.show();
    }

}
