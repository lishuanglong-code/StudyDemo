package study.demo.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import study.demo.R;

public class ShowPictureActivity extends AppCompatActivity {

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            Bitmap bitmap = (Bitmap) msg.obj;
            if (bitmap != null) {
                Log.d(CameraActivity.TAG, "getWidth --> " + bitmap.getWidth() +
                        ", getHeight --> " + bitmap.getHeight());
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

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;

                BitmapFactory.decodeFile(CameraActivity.IMAGE_FLIE + CameraActivity.IMAGE_NAME, options);

                options.inSampleSize = calculateInSampleSize(options, 480, 854);

                options.inJustDecodeBounds = false;
                Bitmap bitmap = BitmapFactory.decodeFile(CameraActivity.IMAGE_FLIE + CameraActivity.IMAGE_NAME, options);
                Message message = mHandler.obtainMessage();
                message.obj = bitmap;
                mHandler.sendMessage(message);
            }
        }).start();
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }
}
