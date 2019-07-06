package com.lmx.library.media;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 视频播放的 LayoutManager
 */
public class PagerLayoutManager extends LinearLayoutManager implements RecyclerView.OnChildAttachStateChangeListener {
    private PagerSnapHelper mPagerSnapHelper;
    private OnPageChangeListener mOnPageChangeListener;
    private int currentPostion;
    private boolean haveSelect;

    PagerLayoutManager(Context context) {
        super(context);
        mPagerSnapHelper = new PagerSnapHelper();
    }

    @Override
    public void onAttachedToWindow(RecyclerView view) {
        view.addOnChildAttachStateChangeListener(this);
        mPagerSnapHelper.attachToRecyclerView(view);
        super.onAttachedToWindow(view);
    }

    @Override
    public void onChildViewAttachedToWindow(@NonNull View view) {
        if (!haveSelect) {
            haveSelect = true;
            currentPostion = getPosition(view);
            mOnPageChangeListener.onPageSelected(currentPostion, view);
        }
    }

    @Override
    public void onChildViewDetachedFromWindow(@NonNull View view) {
    }

    @Override
    public void onScrollStateChanged(int state) {
        if (state == RecyclerView.SCROLL_STATE_IDLE) {
            View view = mPagerSnapHelper.findSnapView(this);
            if (view != null && mOnPageChangeListener != null) {
                int position = getPosition(view);
                if (currentPostion != position) {
                    currentPostion = position;
                    mOnPageChangeListener.onPageSelected(currentPostion, view);
                }
            }
        }
        super.onScrollStateChanged(state);
    }

    @Override
    public boolean canScrollVertically() {
        return true;
    }

    void setOnPageChangeListener(OnPageChangeListener mOnPageChangeListener) {
        this.mOnPageChangeListener = mOnPageChangeListener;
    }
}