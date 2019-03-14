package com.example.sunginhong.sideproject_02;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pools;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.sunginhong.sideproject_02.Utils_Folder.Utils_Animation;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

public class Main_CustomViewPagerAdapter extends PagerAdapter implements View.OnClickListener, View.OnTouchListener {
    List<String> items = new ArrayList<String>();

    RelativeLayout rl_containArray[] = new RelativeLayout[MainActivity.ITEM_COUNT];
    RelativeLayout rl_mainArray[] = new RelativeLayout[MainActivity.ITEM_COUNT];
    RelativeLayout rl_subArray[] = new RelativeLayout[MainActivity.ITEM_COUNT];

    ArrayList<String[]> arrayList;
    Context context;
    private LayoutInflater mInflater;
    private Pools.SimplePool< View > mMyViewPool;
    private static Handler mHandler;
    private static final int MAX_POOL_SIZE = 10;
    private float touchStartX = 0;
    private float touchStartY = 0;
    private float touchMoveX = 0;
    private float touchMoveY = 0;
    private int lastAction = 0;
    private boolean dragState = false;
    private float DRAGMOVE_RATE = 4;


    public Main_CustomViewPagerAdapter(Context context, ArrayList<String[]> list) {
        arrayList = list;
        this.context = context;
        mInflater = LayoutInflater.from(context);
        mMyViewPool = new Pools.SynchronizedPool< >(MAX_POOL_SIZE);
    }

    public void add(String item) {
        items.add(item);
        notifyDataSetChanged();
    }

    public Fragment getItem(int position) { // (3)
        return Main_PagerFragment.newInstance("" + position);
    }
    @Override
    public int getCount() {
        return MainActivity.ITEM_COUNT;
    }

    @Override
    public float getPageWidth(int position) {
        return 1.0f;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = null;
        itemView = mInflater.inflate(R.layout.item_vp_cardlayout, null);

        String[] detail = arrayList.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootView = inflater.inflate(R.layout.item_vp_cardlayout, container, false);


        RelativeLayout rl_contain = (RelativeLayout)itemView.findViewById(R.id.item_vp_cardlayout_contain);
        RelativeLayout rl_sub = (RelativeLayout)itemView.findViewById(R.id.item_vp_cardlayout_detailview);
        TextView rl_sub_tv = (TextView)itemView.findViewById(R.id.item_vp_cardlayout_subtitle_top);
        rl_sub_tv.setText(detail[0]);

        RelativeLayout rl_main = (RelativeLayout)itemView.findViewById(R.id.item_vp_main_cardview_rl_main);
        ImageView rl_main_iv = (ImageView)itemView.findViewById(R.id.item_vp_cardlayout_thumb_main);
        TextView rl2_tv_main = (TextView)itemView.findViewById(R.id.item_vp_cardlayout_title_top);
        TextView rl2_tv_left = (TextView)itemView.findViewById(R.id.item_vp_cardlayout_title_left);
        TextView rl2_tv_right = (TextView)itemView.findViewById(R.id.item_vp_cardlayout_title_right);

        rl_contain.setId(position);
        rl_containArray[position] = rl_contain;

        rl_main.setId(position);
        rl_main.setOnClickListener(this);
        rl_main.setOnTouchListener(this);
        rl_mainArray[position] = rl_main;

        rl_sub.setId(position);
        rl_subArray[position] = rl_sub;

        Uri libThumb = Uri.parse(detail[1]);
        Picasso.get().load(libThumb).into(rl_main_iv);
        rl2_tv_main.setText(detail[2]);
        rl2_tv_left.setText(detail[3]);
        rl2_tv_right.setText(detail[4]);

        ((ViewPager)container).addView(itemView, position);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object view) {
        ((ViewPager)container).removeView((ViewGroup)view);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            dragState = false;
        }
    };

    public void onClick(View view) {
//        Log.d("ssss", "클릭"+view.getId());
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View view, MotionEvent event) {
        View dragView = view;
        RelativeLayout getContainView = rl_containArray[dragView.getId()];
        RelativeLayout getMainView = rl_mainArray[dragView.getId()];
        RelativeLayout getChildView = rl_subArray[dragView.getId()];

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                touchStartX = getContainView.getX() - event.getRawX();
                touchStartY = getContainView.getY() - event.getRawY();
                lastAction = MotionEvent.ACTION_DOWN;
                break;

            case MotionEvent.ACTION_MOVE:
                dragState = true;
                touchMoveX = event.getRawX() + touchStartY;
                touchMoveY = event.getRawY() + touchStartY;

                getContainView.setY(touchMoveY);
                lastAction = MotionEvent.ACTION_MOVE;

                if (-getContainView.getY() >= getContainView.getHeight()/DRAGMOVE_RATE){
                    getContainView.setY(-getContainView.getHeight()/DRAGMOVE_RATE);
                    touchMoveY = getContainView.getY();
                }
               if (getContainView.getY() >= getContainView.getHeight()/DRAGMOVE_RATE){
                   getContainView.setY(getContainView.getHeight()/DRAGMOVE_RATE);
                   touchMoveY = getContainView.getY();
                }
                break;

            case MotionEvent.ACTION_UP:
                if (dragState) {
                    if (-getContainView.getY() >= getContainView.getHeight()/DRAGMOVE_RATE){
                        if (getChildView.getScaleX() == 1.0f){
                            Log.d("sssss", "1-2");
                        } else {
                            Log.d("sssss", "1-1");
                        }
                    } else if (-getContainView.getY() >= 0){
                        if (getChildView.getScaleX() == 0.5f){
                            Log.d("sssss", "2");
                        } else {
                            Log.d("sssss", "3");
                        }
                    }
                    if (getContainView.getY() >= getContainView.getHeight()/DRAGMOVE_RATE){
                        if (getChildView.getScaleX() == 1.0f){
                            Log.d("sssss", "4-2");
                        } else {
                            Log.d("sssss", "4-1");
                        }
                    } else if (getContainView.getY() >= 0){
                        if (getChildView.getScaleX() == 0.5f){
                            Log.d("sssss", "5");
                        } else {
                            Log.d("sssss", "6");
                        }
                    }
                    getContainView.setY(0);
                    Utils_Animation.TransAnim(getContainView, 0f, 0f, touchMoveY, 0f, 400);
                }
                break;

            default:
                break;
        }

        return false;
    }

    private void child_StateAnim(boolean bool, View view){
        if (bool && view.getScaleX() == 0.5f){

        }
        if (!bool && view.getScaleX() == 1.0f){

        }
    }

}