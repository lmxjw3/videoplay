package com.lmx.videoplay;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.lmx.library.media.VideoPlayAdapter;
import com.lmx.videoplay.view.VideoLoadingProgressbar;

public class MainAdapter extends VideoPlayAdapter<MainAdapter.ViewHolder> {
    private Context mContext;

    private int mCurrentPosition;
    private ViewHolder mCurrentHolder;

    private VideoPlayer videoPlayer;
    private TextureView textureView;

    public MainAdapter(Context mContext) {
        this.mContext = mContext;
        videoPlayer = new VideoPlayer();
        textureView = new TextureView(mContext);
        videoPlayer.setTextureView(textureView);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_main, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RequestOptions options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(mContext).load("https://tx2.a.yximgs.com/upic/2020/04/24/20/BMjAyMDA0MjQyMDExMDRfNTkzMzMzMzJfMjczMzU3NDA0ODVfMV8z_B26385cd47079260e711ba54cdf65b594.jpg").apply(options).into(holder.ivCover);
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    @Override
    public void onPageSelected(int itemPosition, View itemView) {
        mCurrentPosition = itemPosition;
        mCurrentHolder = new ViewHolder(itemView);
        playVideo();
    }

    private void playVideo() {
        videoPlayer.reset();
        mCurrentHolder.pbLoading.setVisibility(View.VISIBLE);
        videoPlayer.setOnStateChangeListener(new VideoPlayer.OnStateChangeListener() {
            @Override
            public void onReset() {
                mCurrentHolder.ivCover.setVisibility(View.VISIBLE);
                mCurrentHolder.pbLoading.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onRenderingStart() {
                mCurrentHolder.ivCover.setVisibility(View.GONE);
                mCurrentHolder.pbLoading.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onProgressUpdate(float per) {
            }

            @Override
            public void onPause() {
                mCurrentHolder.pbLoading.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onStop() {
                mCurrentHolder.ivCover.setVisibility(View.VISIBLE);
                mCurrentHolder.pbLoading.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onComplete() {
                videoPlayer.start();
            }
        });
        if (textureView.getParent() != mCurrentHolder.flVideo) {
            if (textureView.getParent() != null) {
                ((FrameLayout) textureView.getParent()).removeView(textureView);
            }
            mCurrentHolder.flVideo.addView(textureView);
        }
        videoPlayer.setDataSource("https://txmov2.a.yximgs.com/bs2/newWatermark/MjczMzU3NDA0ODU_zh_3.mp4");
        videoPlayer.prepare();
    }

    public void release() {
        videoPlayer.release();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private FrameLayout flVideo;
        private ImageView ivCover;
        private VideoLoadingProgressbar pbLoading;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            flVideo = itemView.findViewById(R.id.flVideo);
            ivCover = itemView.findViewById(R.id.ivCover);
            pbLoading = itemView.findViewById(R.id.pbLoading);
        }
    }
}
