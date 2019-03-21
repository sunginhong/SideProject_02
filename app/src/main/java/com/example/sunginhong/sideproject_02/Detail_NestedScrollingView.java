package com.example.sunginhong.sideproject_02;

import android.content.Context;
import android.os.Handler;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import com.example.sunginhong.sideproject_02.Utils_Folder.Utils_Calc;

public class Detail_NestedScrollingView extends NestedScrollView {
    private int mState = RecyclerView.SCROLL_STATE_IDLE;

    private int HIDE_THRESHOLD = 20;
    private int scrolledDistance = 0;
    private boolean appbarVisible = false;
    private Handler mHandler;
    private Runnable mRunnable;
    private String scrollDirection = "none";

    public interface NestedScrollViewScrollStateListener {
        void onNestedScrollViewStateChanged(int state);
    }

    public void setScrollListener(NestedScrollViewScrollStateListener scrollListener) {
        this.mScrollListener = scrollListener;
    }

    private NestedScrollViewScrollStateListener mScrollListener;

    public Detail_NestedScrollingView(Context context) {
        super(context);
    }

    public Detail_NestedScrollingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Detail_NestedScrollingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void stopNestedScroll() {
        super.stopNestedScroll();
        dispatchScrollState(RecyclerView.SCROLL_STATE_IDLE);
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        dispatchScrollState(RecyclerView.SCROLL_STATE_DRAGGING);
        return super.onStartNestedScroll(child, target, nestedScrollAxes);
    }


    @Override
    public boolean startNestedScroll(int axes) {
        boolean superScroll = super.startNestedScroll(axes);
        dispatchScrollState(RecyclerView.SCROLL_STATE_DRAGGING);
        return superScroll;
    }

    private void dispatchScrollState(int state) {
        if (state == 1){
            /// scrollStart

        }
        if (state == 0){
            /// scrollEnd

        }
        if (mScrollListener != null && mState != state) {
            mScrollListener.onNestedScrollViewStateChanged(state);
            mState = state;
        }
    }

    @Override
    protected void onScrollChanged(int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        super.onScrollChanged(scrollX, scrollY, oldScrollX, oldScrollY);
        scrolledDistance = scrollY;

        mRunnable = new Runnable() {
            @Override
            public void run() { Main_DetailActivity.detail_header.setY(Utils_Calc.dpToPx(-Main_DetailActivity.detail_header_HEIGHT_dp)); }
        };

        if (scrollY <= Main_DetailActivity.detatil_topiv.getHeight()){ Main_DetailActivity.detatil_topiv.setY(scrollY/2); }

        scrollDirectionCheck(scrollY, oldScrollY);

        if (scrollY > Utils_Calc.dpToPx(200)){
            if (scrollDirection == "UP" && scrolledDistance > HIDE_THRESHOLD && !appbarVisible) {
                ScrollHederAnim.HeaderHide(Main_DetailActivity.detail_header, Utils_Calc.dpToPx(-56), Utils_Calc.dpToPx(0), 400);
                Main_DetailActivity.detail_header.setY(Utils_Calc.dpToPx(0));
                appbarVisible = true;
                scrolledDistance = 0;
            } else if (scrollDirection == "DOWN" && scrolledDistance > HIDE_THRESHOLD && appbarVisible) {
                ScrollHederAnim.HeaderHide(Main_DetailActivity.detail_header, Utils_Calc.dpToPx(0), Utils_Calc.dpToPx(-56), 400);
                mHandler = new Handler();
                mHandler.postDelayed(mRunnable, 400);
                appbarVisible = false;
                scrolledDistance = 0;
            }
        } else {
            if (scrolledDistance < Utils_Calc.dpToPx(200) && appbarVisible) {
                ScrollHederAnim.HeaderHide(Main_DetailActivity.detail_header, Utils_Calc.dpToPx(0), Utils_Calc.dpToPx(-56), 400);
                mHandler = new Handler();
                mHandler.postDelayed(mRunnable, 400);
                appbarVisible = false;
                scrolledDistance = 0;
            }
        }
    }

    private void scrollDirectionCheck(int scrollY, int oldScrollY){
        if ((scrollY > oldScrollY) && (scrollY - oldScrollY) > 10) { scrollDirection = "UP"; }
        else if((scrollY < oldScrollY) && (oldScrollY - scrollY) > 10) { scrollDirection = "DOWN"; }
    }

}