package com.lmx.videoplay;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.lmx.library.media.VideoPlayRecyclerView;

public class MainActivity extends Activity {
    private VideoPlayRecyclerView mRvVideo;
    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        findViewById(R.id.ibBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mRvVideo = findViewById(R.id.rvVideo);
        adapter = new MainAdapter(this);
        mRvVideo.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapter.release();
    }
}