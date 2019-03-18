package com.example.sunginhong.sideproject_02;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import com.example.sunginhong.sideproject_02.Utils_Folder.Utils_Animation;
import com.example.sunginhong.sideproject_02.Utils_Folder.Utils_Delay;

public class Main_CustomViewPager_Interaction implements ViewPager.OnPageChangeListener, ViewPager.PageTransformer, View.OnTouchListener {

    private ViewPager vp;
    private Context mContext = null;
    private int currentIdx = 0;
    private RelativeLayout getChildView;


    public Main_CustomViewPager_Interaction(ViewPager viewPager) {
        vp = viewPager;
        viewPager.addOnPageChangeListener(this);
        viewPager.setOnTouchListener(this);
        vp.setCurrentItem(0);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_UP:
                if (getChildView.getScaleX() == 1.0f){
                    Utils_Animation.SclaeAnim(getChildView, 1.0f, 0.5f, 1.0f, 0.5f, 0.5f,  0.5f, 400);
                    Utils_Delay.delay(4, new Utils_Delay.DelayCallback() {
                        @Override
                        public void afterDelay() {
                            getChildView.setScaleX(0.5f);
                            getChildView.setScaleY(0.5f);
                        }
                    });
                }
                break;
            case MotionEvent.ACTION_MOVE:

                getChildView = Main_CustomViewPagerAdapter.rl_subArray[Main_CustomViewPagerAdapter.lastSelItem];

                break;
        }
        return false;
    }

    @Override
    public void transformPage(View page, float position) {
    }

    @Override
    public void onPageScrolled(final int position, float positionOffset, int positionOffsetPixels) {
        currentIdx = position;
    }

    @Override
    public void onPageSelected(final int position) {
        final int pageWidth = vp.getWidth();
        Main_CustomViewPagerAdapter.child_StateAnim(false, Main_CustomViewPagerAdapter.rl_subArray[Main_CustomViewPagerAdapter.lastSelItem]);
    }


    @Override
    public void onPageScrollStateChanged(int state) {

    }

}