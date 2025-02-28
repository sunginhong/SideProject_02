package com.example.sunginhong.sideproject_02;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.transition.ChangeBounds;
import android.view.Display;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static ViewPager vp;
    static View pageNav_current;
    public static final int ITEM_COUNT = 5;
    public static final int ITEM_MARGIN = 0;
    private int vpPaddingRate = 6;

    static int screenWidth;
    static int screenHeight;

    static RelativeLayout pvArray[] = new RelativeLayout[ITEM_COUNT];
    static ImageView bgImgArray[] = new ImageView[ITEM_COUNT];
    private Main_CustomViewPager_Interaction Main_CustomViewPager_Interaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;

        ChangeBounds bounds = new ChangeBounds();
        bounds.setDuration(400);
        bounds.setInterpolator(new DecelerateInterpolator(1.5f));
        getWindow().setSharedElementEnterTransition(bounds);


        final ArrayList<String[]> values = new ArrayList<String[]>();
        if(values.size() == 0) {
            values.add(new String[]{"DetailView", "https://cdn.dribbble.com/users/1150809/screenshots/6104124/aplikasi_pelajaran_2x.png", "TITLE0", "STATE-A", "STATE-B"});
            values.add(new String[]{"DetailView", "https://cdn.dribbble.com/users/1967263/screenshots/6191646/dribbble-01_2x.png", "TITLE1", "STATE-A", "STATE-B"});
            values.add(new String[]{"DetailView", "https://cdn.dribbble.com/users/448687/screenshots/6196443/onedayatatime.jpg", "TITLE2", "STATE-A", "STATE-B"});
            values.add(new String[]{"DetailView", "https://cdn.dribbble.com/users/1568655/screenshots/6191621/omelette_mobile_2x.png", "TITLE3", "STATE-A", "STATE-B"});
            values.add(new String[]{"DetailView", "https://cdn.dribbble.com/users/757683/screenshots/5399131/cc_shot_1_2x.jpg", "TITLE4", "STATE-A", "STATE-B"});
        }

        Main_CustomViewPagerAdapter mAdapter = new Main_CustomViewPagerAdapter(this, values);
        vp = (ViewPager)findViewById(R.id.vp);
        vp.setAdapter(mAdapter);
        vp.setClipToPadding(false);
        vp.setOffscreenPageLimit(ITEM_COUNT);
        vp.setPadding(screenWidth/(vpPaddingRate), 0, screenWidth/(vpPaddingRate), 0);
        vp.setCurrentItem(0);

        Main_CustomViewPager_Interaction = new Main_CustomViewPager_Interaction(vp);
    }
}
