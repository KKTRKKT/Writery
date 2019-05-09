package com.example.writery;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;

public class WriteSetting extends Activity {
    NobelDBHandler dbHandler = new NobelDBHandler(this, null, null, 1);
    Bundle extras;

    private static final int PICK_FROM_ALBUM = 1;
    private File tempFile;
    private String TAG = "chek";

    EditText title;
    EditText info;
    ImageView image;

    byte[] img;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_setting_layout);

        extras = getIntent().getExtras();

        title = findViewById(R.id.write_setting_title);
        info = findViewById(R.id.write_setting_info);
        image = findViewById(R.id.write_setting_img);

        if (extras == null) {
            return;
        }

        NobelItem nobelItem = dbHandler.findNobel(extras.getInt("ID"));
        title.setText(nobelItem.getTitle());
        info.setText(nobelItem.getInfo());
        try {
            Bitmap bitmap = BitmapFactory.decodeByteArray(nobelItem.getImage(), 0, nobelItem.getImage().length);
            image.setImageBitmap(bitmap);
            img = nobelItem.getImage();
        }catch (Exception e){
        }

    }

    //돌아가기 버튼
    public void gotoWrite(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_static, R.anim.anim_static);
    }

    //확인 버튼
    public void checkbtn(View view) {
        if (extras == null) {
            if (title.getText().toString().length() == 0) {
                Toast.makeText(this, "제목을 입력하세요", Toast.LENGTH_LONG).show();
            } else {
                String t = title.getText().toString();
                Log.d("title", t);
                String i = info.getText().toString();
                Log.d("info", i);


                NobelItem nobelItem = new NobelItem(img, t, i);
                dbHandler.addWrite(nobelItem);

                title.setText("");
                info.setText("");

                Intent intent = new Intent(this, WriteActivity.class);
                intent.putExtra("ID", dbHandler.getID());
                startActivity(intent);
                overridePendingTransition(R.anim.anim_static, R.anim.anim_static);

            }
        } else {
            int id = extras.getInt("ID");
            String t = title.getText().toString();
            String i = info.getText().toString();
            dbHandler.update(id, t, i, img);

            Intent intent = new Intent(this, WriteActivity.class);
            intent.putExtra("ID", id);
            startActivity(intent);
            overridePendingTransition(R.anim.anim_static, R.anim.anim_static);
        }
    }

    private void tedPermission() {

        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                // 권한 요청 성공
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            }
        };

        TedPermission.with(this)
                .setPermissionListener(permissionListener)
                .setRationaleMessage(getResources().getString(R.string.permission_2))
                .setDeniedMessage(getResources().getString(R.string.permission_1))
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .check();
    }

    private void goToAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PICK_FROM_ALBUM);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            Toast.makeText(this, "취소 되었습니다.", Toast.LENGTH_SHORT).show();

            if(tempFile != null) {
                if (tempFile.exists()) {
                    if (tempFile.delete()) {
                        Log.e(TAG, tempFile.getAbsolutePath() + " 삭제 성공");
                        tempFile = null;
                    }
                }
            }

            return;
        }

        if (requestCode == PICK_FROM_ALBUM) {

            Uri photoUri = data.getData();

            Cursor cursor = null;

            try {

                /*
                 *  Uri 스키마를
                 *  content:/// 에서 file:/// 로  변경한다.
                 */
                String[] proj = { MediaStore.Images.Media.DATA };

                assert photoUri != null;
                cursor = getContentResolver().query(photoUri, proj, null, null, null);

                assert cursor != null;
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

                cursor.moveToFirst();

                tempFile = new File(cursor.getString(column_index));

            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }

            setImage();

        }
    }

    private void setImage() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap originalBm = BitmapFactory.decodeFile(tempFile.getAbsolutePath(), options);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        originalBm.compress(Bitmap.CompressFormat.PNG, 0, stream);
        img = stream.toByteArray();
        image.setImageBitmap(originalBm);
    }

    public void gotoAlbum(View view) {
        tedPermission();
        goToAlbum();
    }

//    public byte[] getByteArrayFromDrawble(Drawable d){
//        Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//
//        return stream.toByteArray();
//    }

}
