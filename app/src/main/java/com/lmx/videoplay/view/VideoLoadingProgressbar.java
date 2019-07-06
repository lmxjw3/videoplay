package com.lmx.videoplay.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 视频加载进度条
 */
public class VideoLoadingProgressbar extends View {
    /*
     * view width and height
     */
    private int mWidth, mHeight;
    /*
     * current width
     */
    private int mProgressWidth = 100;
    /*
     * minWidth
     */
    private int mMinProgressWidth = 100;
    /*
     * color of progress
     */
    private int mColor = 0xffffffff;

    private Paint mPaint;
    private Handler mHandler;
    private int mTimePeriod = 20;

    public VideoLoadingProgressbar(Context context) {
        this(context, null);
    }

    public VideoLoadingProgressbar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VideoLoadingProgressbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setColor(mColor);

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                invalidate();
                this.sendEmptyMessageDelayed(1, mTimePeriod);
            }
        };
        if (getVisibility() == VISIBLE) {
            mHandler.sendEmptyMessageDelayed(1, mTimePeriod);
        }
    }

    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (visibility == VISIBLE) {
            mHandler.sendEmptyMessageDelayed(1, mTimePeriod);
        } else {
            mHandler.removeCallbacksAndMessages(null);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mHandler.removeCallbacksAndMessages(null);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mPaint.setStrokeWidth(h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mProgressWidth += 30;
        if (mProgressWidth > mWidth) {
            mProgressWidth = mMinProgressWidth;
        }
        // 计算透明度
        int alph = 255 - 255 * mProgressWidth / mWidth;
        if (alph < 30) {
            alph = 30;
        }
        mPaint.setAlpha(alph);
        canvas.drawLine(mWidth / 2f - mProgressWidth / 2f, 0, mWidth / 2f + mProgressWidth / 2f, mHeight, mPaint);
    }
}