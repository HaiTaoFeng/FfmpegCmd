package com.example.ffmpegdemo;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.TextView;

import com.coder.ffmpeg.call.CommonCallBack;
import com.coder.ffmpeg.jni.FFmpegCommand;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FfmpegActivity extends AppCompatActivity {

    private int PICK_VIDEO_REQUEST = 0x2;
    private String mVideoPath;
    private String outPath;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ffmpeg);
        mVideoPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/cc.mp4";
        outPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/out.yuv";
        tv = findViewById(R.id.tv_text);
        tv.setOnClickListener(v -> {
            Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, PICK_VIDEO_REQUEST);
        });

        findViewById(R.id.bt_button).setOnClickListener(v -> {
//            decode(mVideoPath,outPath);
            printAudioInfo(mVideoPath,outPath);
//            tranCode();
        });
    }

    private void tranCode(){
        String cmd = "ffmpeg -i %s -vcodec copy -acodec copy %s";
        String result = String.format(cmd, mVideoPath, outPath);
        long startTime = System.currentTimeMillis();
        FFmpegCommand.runAsync(result.split(" "), new CommonCallBack() {
            @Override
            public void onComplete() {
                Log.d("FFmpegTest", "run: 耗时：" + (System.currentTimeMillis() - startTime));
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_VIDEO_REQUEST && resultCode == RESULT_OK && null != data) {
            Uri selectedVideo = data.getData();
            String[] filePathColumn = {MediaStore.Video.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedVideo,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            mVideoPath = cursor.getString(columnIndex);
//            mVideoPath = "/sdcard/Download/gamepp/KSP.mkv";
            cursor.close();
            tv.setText(mVideoPath);
        }
    }


    /**
     * 使用ffmpeg命令行进行视频剪切
     *
     * @param srcFile    源文件
     * @param startTime  剪切的开始时间(单位为秒)
     * @param duration   剪切时长(单位为秒)
     * @param targetFile 目标文件
     * @return 剪切后的文件
     */
    public static String[] cutVideo(String srcFile, int startTime, int duration, String targetFile) {
        String cutVideoCmd = "ffmpeg -i %s -ss %d -t %d %s";
        cutVideoCmd = String.format(cutVideoCmd, srcFile, startTime, duration, targetFile);
        return cutVideoCmd.split(" ");//以空格分割为字符串数组
    }

    public native void decode(String int_url, String out_url);
    public native int printAudioInfo(String int_url,String out_url);
}
