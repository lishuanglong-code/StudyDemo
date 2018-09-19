package study.demo.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import study.demo.R;

public class ShowPictureActivity extends AppCompatActivity {

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            Bitmap bitmap = (Bitmap) msg.obj;
            if (bitmap != null) {
                mShowPicture.setImageBitmap(bitmap);
                mShowPicture.invalidate();
            }

            return false;
        }
    });

    private ImageView mShowPicture;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_picture);

        mShowPicture = findViewById(R.id.iv_show_picture);

        new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = BitmapFactory.decodeFile(CameraActivity.IMAGE_FLIE + CameraActivity.IMAGE_NAME);
                Message message = mHandler.obtainMessage();
                message.obj = bitmap;
                mHandler.sendMessage(message);
            }
        }).start();
    }
}
