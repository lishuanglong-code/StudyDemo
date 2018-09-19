package study.demo.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageReader;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Size;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import study.demo.App;
import study.demo.R;

public class CameraActivity extends AppCompatActivity {

    public static final String TAG = "Camera_Activity";

    //相机状态
    private static int CAMERA_STATE = -1;
    private static final int CAMERA_STATE_PREVIEW = 0;
    private static final int CAMERA_STATE_PICTURE = 1;

    //image 保存和读取的路径
    public static final String IMAGE_FLIE = "/sdcard/DCIM/Camera/";
    public static final String IMAGE_NAME = "Camera2Fragment.jpg";

    //设备闪光灯是否可用，默认为不可用
    private boolean isFlashInfoAvailable = false;

    //获取 SurfaceTexture 使用的尺寸大小列表
    private Size[] mOutputSizes;

    //创建工作线程
    private Handler mHandler;
    private HandlerThread mHandlerThread;

    private CameraManager mCameraManager;
    private CameraDevice camera;
    private CameraCaptureSession session;
    private ImageReader mImageReader;
    private Image mImage;
    private SurfaceTexture surfaces;
    private Integer mSensorOrientation;
    private Size mLargest;


    private TextureView mPreview;
    private Button mTakePhoto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        mPreview = findViewById(R.id.tv_preview);
        mTakePhoto = findViewById(R.id.btn_take_photo);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //为 TextureView 设置监听
        mPreview.setSurfaceTextureListener(new SurfaceTextureListener());
        //点击拍照
        mTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //切换为拍照状态
                CAMERA_STATE = CAMERA_STATE_PICTURE;
            }
        });
        //默认相机为预览状态
        CAMERA_STATE = CAMERA_STATE_PREVIEW;
    }

    @Override
    protected void onStop() {
        super.onStop();
        //回收资源
        recycle();
    }

    /**
     * 点击拍照后应该做的事情
     */
    private void 做你想做的事情() {
        startActivity(new Intent(App.context, ShowPictureActivity.class));
    }


    /**
     * TextureView 表面构造监听，在监听里面打开相机
     */
    private class SurfaceTextureListener implements TextureView.SurfaceTextureListener {
        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
            CameraActivity.this.surfaces = surface;
            openCamera2Device();
        }

        @Override
        public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
        }

        @Override
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
            if (surface != null) {
                surface.release();
            }
            return false;
        }

        @Override
        public void onSurfaceTextureUpdated(SurfaceTexture surface) {

        }
    }


    /**
     * 打开相机设备，
     */
    private void openCamera2Device() {
        try {

            if (ActivityCompat.checkSelfPermission(App.context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(App.context, "Please turn on camera permissions.", Toast.LENGTH_SHORT).show();
                return;
            }

            //创建工作线程
            mHandler = creationWorkThread("Camera");

            //获取 CameraManager 对象。
            mCameraManager = (CameraManager) App.context.getSystemService(Context.CAMERA_SERVICE);

            //获取当前连接的所有 CameraId 列表。
            String[] cameraIdList = mCameraManager.getCameraIdList();

            for (String id : cameraIdList) {
                Log.d(TAG, "CameraId --> " + id);

                //查询相机设备是否可用和可用的功能
                if (!queryCameraDeviceCharacteristics(id)) {
                    continue;
                }

                //创建ImageReader
                mImageReader = ImageReader.newInstance(mLargest.getWidth(), mLargest.getHeight(), ImageFormat.JPEG, 2);
                ImageAvailableListener imageAvailableListener = new ImageAvailableListener();
                mImageReader.setOnImageAvailableListener(imageAvailableListener, mHandler);

                //打开给定ID的相机的连接。
                CameraDeviceStateCallback cameraDeviceStateCallback = new CameraDeviceStateCallback();
                mCameraManager.openCamera(id, cameraDeviceStateCallback, mHandler);
            }

        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }


    /**
     * CameraDevice 连接状态监听，在监听里面创建预览请求
     */
    private class CameraDeviceStateCallback extends CameraDevice.StateCallback {

        @Override
        public void onOpened(@NonNull CameraDevice camera) {
            CameraActivity.this.camera = camera;
            try {
                surfaces.setDefaultBufferSize(mOutputSizes[0].getWidth(), mOutputSizes[0].getHeight());
                List<Surface> surfaceList = new ArrayList<>();
                Surface surface = new Surface(surfaces);
                surfaceList.add(surface);
                surfaceList.add(mImageReader.getSurface());

                //如果相机正常开启，就创建一个预览请求
                CaptureRequest.Builder captureRequest = camera.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
                captureRequest.addTarget(surfaceList.get(0));


                //设置请求相机的参数
                captureRequest.set(CaptureRequest.CONTROL_AF_MODE, CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE);
                captureRequest.setTag(camera);

                //创建捕获会话
                CameraCaptureSessionStateCallback cameraCaptureSessionStateCallback = new CameraCaptureSessionStateCallback(captureRequest.build());
                camera.createCaptureSession(surfaceList, cameraCaptureSessionStateCallback, mHandler);

            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onDisconnected(@NonNull CameraDevice camera) {
            if (camera != null) {
                camera.close();
            }
        }

        @Override
        public void onError(@NonNull CameraDevice camera, int error) {
            printError(error);
            if (camera != null) {
                camera.close();
            }
        }
    }


    /**
     * 相机参数请求会话的状态监听
     */
    private class CameraCaptureSessionStateCallback extends CameraCaptureSession.StateCallback {

        private CaptureRequest request;

        public CameraCaptureSessionStateCallback(CaptureRequest request) {
            this.request = request;
        }

        @Override
        public void onConfigured(@NonNull CameraCaptureSession session) {
            CameraActivity.this.session = session;
            try {
                CameraCaptureCallback cameraCaptureCallback = new CameraCaptureCallback();
                session.setRepeatingRequest(request, cameraCaptureCallback, mHandler);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onConfigureFailed(@NonNull CameraCaptureSession session) {
            if (session != null) {
                session.close();
            }
        }
    }


    /**
     * 相机捕获回调
     */
    private class CameraCaptureCallback extends CameraCaptureSession.CaptureCallback {

        @Override
        public void onCaptureCompleted(@NonNull CameraCaptureSession session, @NonNull CaptureRequest request, @NonNull TotalCaptureResult result) {
            super.onCaptureCompleted(session, request, result);
            CameraActivity.this.session = session;
            switch (CAMERA_STATE) {
                case CAMERA_STATE_PREVIEW:


                    break;
                case CAMERA_STATE_PICTURE:

                    try {

                        //设置为拍照完毕状态
                        CAMERA_STATE = CAMERA_STATE_PREVIEW;

                        CameraDevice cameraDevice = (CameraDevice) request.getTag();
                        if (cameraDevice != null) {

                            //创建静态图片捕捉请求
                            CaptureRequest.Builder captureRequest = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE);
                            captureRequest.addTarget(mImageReader.getSurface());
                            //为此次请求配置请求参数
                            captureRequest.set(CaptureRequest.CONTROL_AF_MODE, CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE);
                            //开启闪光灯
                            if (isFlashInfoAvailable) {
                                captureRequest.set(CaptureRequest.FLASH_MODE, CaptureRequest.FLASH_MODE_SINGLE);
                            }
                            //设置图片的显示方向
                            captureRequest.set(CaptureRequest.JPEG_ORIENTATION, mSensorOrientation);
                            //设置图片的压缩质量
                            captureRequest.set(CaptureRequest.JPEG_QUALITY, (byte) 100);
                            //开启光学防抖动模式
                            captureRequest.set(CaptureRequest.LENS_OPTICAL_STABILIZATION_MODE, CaptureRequest.LENS_OPTICAL_STABILIZATION_MODE_ON);

                            //捕获图片
                            CameraCaptureCallback cameraCaptureCallback = new CameraCaptureCallback();
                            session.capture(captureRequest.build(), cameraCaptureCallback, mHandler);
                        }
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }

                    break;
            }

        }

        @Override
        public void onCaptureFailed(@NonNull CameraCaptureSession session, @NonNull CaptureRequest request, @NonNull CaptureFailure failure) {
            super.onCaptureFailed(session, request, failure);
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * 监听 Image 是否可用
     */
    private class ImageAvailableListener implements ImageReader.OnImageAvailableListener {

        @Override
        public void onImageAvailable(ImageReader reader) {

            Log.d(TAG, "ImageAvailableListener --> onImageAvailable");

            //获取最新的Image，从ImageReader的队列里面，会丢弃旧的图片
            mImage = reader.acquireLatestImage();
            //保存Image
            saveImage(mImage, new File(IMAGE_FLIE + IMAGE_NAME));

            //做你想做的事情
            做你想做的事情();

        }
    }


    /**
     * 保存 Image 到文件
     */
    private void saveImage(Image image, File file) {

        //获取图片的像素阵列
        Image.Plane[] planes = image.getPlanes();
        //获取包含帧数据的直接ByteBuffer
        ByteBuffer buffer = planes[0].getBuffer();
        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);
        FileOutputStream output = null;
        try {
            output = new FileOutputStream(file);
            output.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            image.close();
            if (null != output) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 检查获取相机设备功能是否可用
     */
    private boolean queryCameraDeviceCharacteristics(String cameraId) throws CameraAccessException {

        CameraCharacteristics cameraCharacteristics = mCameraManager.getCameraCharacteristics(cameraId);
        //判断相机摄像头如果不是后摄像头就跳过本次循环
        Integer integer = cameraCharacteristics.get(CameraCharacteristics.LENS_FACING);
        if (integer == null && integer != cameraCharacteristics.LENS_FACING_BACK) {
            return false;
        }
        //此摄像机设备支持的可用流配置; 还包括每个格式/大小组合的最小帧持续时间和停顿持续时间。
        StreamConfigurationMap map = cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
        if (map == null) {
            return false;
        } else {
            mLargest = Collections.max(Arrays.asList(map.getOutputSizes(ImageFormat.JPEG)), new CompareSizesByArea());
            mOutputSizes = map.getOutputSizes(SurfaceTexture.class);
        }

        //获取传感器的方向
        mSensorOrientation = cameraCharacteristics.get(CameraCharacteristics.SENSOR_ORIENTATION);
        //获取摄像头设备是否可用
        isFlashInfoAvailable = cameraCharacteristics.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
        return true;
    }


    /**
     * Compares two {@code Size}s based on their areas.
     */
    static class CompareSizesByArea implements Comparator<Size> {

        @Override
        public int compare(Size lhs, Size rhs) {
            // We cast here to ensure the multiplications won't overflow
            return Long.signum((long) lhs.getWidth() * lhs.getHeight() -
                    (long) rhs.getWidth() * rhs.getHeight());
        }

    }

    /**
     * 创建工作线程
     */
    private Handler creationWorkThread(String name) {
        mHandlerThread = new HandlerThread(name);
        mHandlerThread.start();
        return new Handler(mHandlerThread.getLooper());
    }

    /**
     * 打印相机异常
     */
    private void printError(int error) {
        switch (error) {
            case CameraDevice.StateCallback.ERROR_CAMERA_IN_USE:
                Log.d(TAG,
                        "An error code that can be reported by onError(CameraDevice, int) indicating that the camera device is in use already.\n" +
                                "\n" +
                                "This error can be produced when opening the camera fails due to the camera being used by a higher-priority camera API client.");
                break;
            case CameraDevice.StateCallback.ERROR_MAX_CAMERAS_IN_USE:
                Log.d(TAG,
                        "An error code that can be reported by onError(CameraDevice, int) indicating that the camera device could not be opened because there are too many other open camera devices.\n" +
                                "\n" +
                                "The system-wide limit for number of open cameras has been reached, and more camera devices cannot be opened until previous instances are closed.\n" +
                                "\n" +
                                "This error can be produced when opening the camera fails.");
                break;
            case CameraDevice.StateCallback.ERROR_CAMERA_DISABLED:
                Log.d(TAG,
                        "An error code that can be reported by onError(CameraDevice, int) indicating that the camera device could not be opened due to a device policy.");
                break;
            case CameraDevice.StateCallback.ERROR_CAMERA_DEVICE:
                Log.d(TAG,
                        "An error code that can be reported by onError(CameraDevice, int) indicating that the camera device has encountered a fatal error.\n" +
                                "\n" +
                                "The camera device needs to be re-opened to be used again.");
                break;
            case CameraDevice.StateCallback.ERROR_CAMERA_SERVICE:
                Log.d(TAG,
                        "An error code that can be reported by onError(CameraDevice, int) indicating that the camera service has encountered a fatal error.\n" +
                                "\n" +
                                "The Android device may need to be shut down and restarted to restore camera function, or there may be a persistent hardware problem.\n" +
                                "\n" +
                                "An attempt at recovery may be possible by closing the CameraDevice and the CameraManager, and trying to acquire all resources again from scratch.");
                break;
        }
    }


    /**
     * 回收资源
     */
    private void recycle() {

        if (mCameraManager != null) {
            mCameraManager = null;
        }

        if (mImageReader != null) {
            mImageReader.close();
            mImageReader = null;
        }

        if (mImage != null) {
            mImage.close();
            mImage = null;
        }

        if (session != null) {
            session.close();
            session = null;
        }

        if (surfaces != null) {
            surfaces.release();
            surfaces = null;
        }

        if (mHandlerThread != null) {
            mHandlerThread = null;
        }

        if (mHandler != null) {
            mHandler = null;
        }

        if (camera != null) {
            camera.close();
            camera = null;
        }

    }



}
