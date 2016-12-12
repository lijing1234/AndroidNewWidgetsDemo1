/*
 *
 *  *
 *  *  *
 *  *  *  * ===================================
 *  *  *  * Copyright (c) 2016.
 *  *  *  * 作者：安卓猴
 *  *  *  * 微博：@安卓猴
 *  *  *  * 博客：http://sunjiajia.com
 *  *  *  * Github：https://github.com/opengit
 *  *  *  *
 *  *  *  * 注意**：如果您使用或者修改该代码，请务必保留此版权信息。
 *  *  *  * ===================================
 *  *  *
 *  *  *
 *  *
 *
 */

package com.sunjiajia.beautifullife;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;

import fm.jiecao.jcvideoplayer_lib.JCBuriedPointStandard;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;


//http://172.17.20.44:8080/script/01.mp4
//        http://172.17.20.44:8080/script/04.jpg
public class MovieActivity extends AppCompatActivity {
    JCVideoPlayer.JCAutoFullscreenListener mSensorEventListener;
    SensorManager mSensorManager;
    JCVideoPlayerStandard mJcVideoPlayerStandard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        Log.e("aaaaaa",getIntent().getStringExtra("a"));
        Toolbar toolbar= (Toolbar) findViewById(R.id.id_toolbar);
        toolbar.setTitle("视频");
        setSupportActionBar(toolbar);  // 用ToolBar代替ActionBar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        mJcVideoPlayerStandard = (JCVideoPlayerStandard) findViewById(R.id.jc_video);
        mJcVideoPlayerStandard.setUp("http://video.jiecao.fm/11/23/xin/%E5%81%87%E4%BA%BA.mp4"
                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "視頻");

        Glide.with(this)
                .load("http://img4.jiecaojingxuan.com/2016/11/23/00b026e7-b830-4994-bc87-38f4033806a6.jpg")
                .into(mJcVideoPlayerStandard.thumbImageView);
        mJcVideoPlayerStandard.looping = true;
        JCVideoPlayer.setJcBuriedPoint(new MyJCBuriedPointStandard());
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensorEventListener = new JCVideoPlayer.JCAutoFullscreenListener();


    }
    @Override
    protected void onResume() {
        super.onResume();
        Sensor accelerometerSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(mSensorEventListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }
    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
    class MyJCBuriedPointStandard implements JCBuriedPointStandard {

        @Override
        public void onEvent(int type, String url, int screen, Object... objects) {

        }
    }


}
