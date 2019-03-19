package com.example.sunginhong.sideproject_02;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v4.util.Pools;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.sunginhong.sideproject_02.Utils_Folder.Utils_Animation;
import com.example.sunginhong.sideproject_02.Utils_Folder.Utils_Calc;
import com.example.sunginhong.sideproject_02.Utils_Folder.Utils_Delay;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

public class Main_CustomViewPagerAdapter extends PagerAdapter implements View.OnClickListener, View.OnTouchListener {
    List<String> items = new ArrayList<String>();

    static RelativeLayout rl_containArray[] = new RelativeLayout[MainActivity.ITEM_COUNT];
    static RelativeLayout rl_mainArray[] = new RelativeLayout[MainActivity.ITEM_COUNT];
    static RelativeLayout rl_subArray[] = new RelativeLayout[MainActivity.ITEM_COUNT];
    static ImageView rl_main_ImgArray[] = new ImageView[MainActivity.ITEM_COUNT];
    static TextView rl_main_TITLEArray[] = new TextView[MainActivity.ITEM_COUNT];

    static String rl_main_iv_TITLEArray[] = new String[MainActivity.ITEM_COUNT];
    static Uri rl_main_iv_URLArray[] = new Uri[MainActivity.ITEM_COUNT];

    ArrayList<String[]> arrayList;
    private static Context context;
    private LayoutInflater mInflater;
    private Pools.SimplePool< View > mMyViewPool;
    private static Handler mHandler;
    private static final int MAX_POOL_SIZE = 10;
    private float touchStartX = 0;
    private float touchStartY = 0;
    private float touchMoveX = 0;
    private float touchMoveY = 0;
    static String dragDrirection = "NONE";
    private int lastAction = 0;
    static int lastSelItem = 0;
    private boolean dragState = false;
    private float DRAGMOVE_RATE = 4;

    static RelativeLayout getContainView;
    static RelativeLayout getMainView ;
    static RelativeLayout getChildView;
    static TextView getTitleView;

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

        rl_main_TITLEArray[position] = rl2_tv_main;
        rl_contain.setId(position);
        rl_containArray[position] = rl_contain;

        rl_main.setId(position);
        rl_main.setOnClickListener(this);
        rl_main.setOnTouchListener(this);
        rl_mainArray[position] = rl_main;

        rl_sub.setId(position);
        rl_sub.setScaleX(0.5f);
        rl_sub.setScaleY(0.5f);
//        rl_sub.setY(Utils_Calc.dpToPx(330)/4);
        rl_subArray[position] = rl_sub;

        Uri libThumb = Uri.parse(detail[1]);
        Picasso.get().load(libThumb).into(rl_main_iv);
//        GradientDrawable drawable=
//                (GradientDrawable) context.getDrawable(R.drawable.background_rounding);
//        rl_main_iv.setBackground(drawable);
//        rl_main_iv.setClipToOutline(true);


        rl_main_ImgArray[position] = rl_main_iv;
        rl_main_iv_URLArray[position] = libThumb;
        rl_main_iv_TITLEArray[position] = detail[2];

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
        getChildView = rl_subArray[view.getId()];
        if (getChildView.getScaleX() == 0.5){
            child_StateAnim(true, getChildView);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View view, MotionEvent event) {
        View dragView = view;
        getContainView = rl_containArray[dragView.getId()];
        getMainView = rl_mainArray[dragView.getId()];
        getChildView = rl_subArray[dragView.getId()];

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                MainActivity.vp.setCurrentItem(dragView.getId(), true);
//                child_StateAnim(false, rl_subArray[lastSelItem]);

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
                child_dragDrirectionDetect(getContainView);

                break;

            case MotionEvent.ACTION_UP:
                if (dragState) {
                    dragState = false;
                    if (-getContainView.getY() >= getContainView.getHeight()/DRAGMOVE_RATE){
                        if (getChildView.getScaleX() == 1.0f){
//                            Log.d("sssss", "1-2");
                            child_DetailAnim(getContainView);
                            Utils_Delay.delay(4, new Utils_Delay.DelayCallback() {
                                @Override
                                public void afterDelay() { child_posOrigin(getContainView, touchMoveY, 0); }
                            });
                        } else if (dragDrirection == "UP"){
//                            Log.d("sssss", "1-1");
                            child_StateAnim(true, getChildView);
                            child_posOrigin(getContainView, touchMoveY, 400);
                        }
                    } else if (-getContainView.getY() >= 0){
                        if (getChildView.getScaleX() == 0.5f){
//                            Log.d("sssss", "2");
                            child_posOrigin(getContainView, touchMoveY, 400);
                        } else {
//                            Log.d("sssss", "3");
                            child_StateAnim(false, getChildView);
                            child_posOrigin(getContainView, touchMoveY, 400);
                        }
                    }
                    if (getContainView.getY() >= getContainView.getHeight()/DRAGMOVE_RATE){
                        if (getChildView.getScaleX() == 1.0f){
//                            Log.d("sssss", "4-2");
                            child_DetailAnim(getContainView);
                            Utils_Delay.delay(4, new Utils_Delay.DelayCallback() {
                                @Override
                                public void afterDelay() { child_posOrigin(getContainView, touchMoveY, 0); }
                            });
                        } else {
//                            Log.d("sssss", "4-1");
                            child_StateAnim(true, getChildView);
                            child_posOrigin(getContainView, touchMoveY, 400);
                        }
                    } else if (getContainView.getY() >= 0){
                        if (getChildView.getScaleX() == 0.5f){
//                            Log.d("sssss", "5");
                            child_posOrigin(getContainView, touchMoveY, 400);
                        } else if (dragDrirection == "DOWN"){
//                            Log.d("sssss", "6");
                            child_StateAnim(false, getChildView);
                            child_posOrigin(getContainView, touchMoveY, 400);
                        }
                    }
                }
                break;

            default:
                break;
        }

        return false;
    }

    static void child_posOrigin(final View view, float touchMoveY, int duration){
        view.setY(0);
        Utils_Animation.TransAnim(view, 0f, 0f, touchMoveY, 0f, duration);
    }

    static void child_StateAnim(boolean bool, final View view){
        final View childView = view;
        if (bool && view.getScaleX() == 0.5f){
            childView.setY(Utils_Calc.dpToPx(330)/4);
            lastSelItem = view.getId();
            Utils_Animation.SclaeAnim(childView, 0.5f, 1.0f, 0.5f, 1.0f, 0.5f,  0.5f, 400);
//            Utils_Animation.TransAnim(view, 0, 0, 400, 400, 0);
            childView.setScaleX(1);
            childView.setScaleY(1);
        }
        if (!bool && view.getScaleX() == 1.0f){
            Utils_Animation.SclaeAnim(childView, 1.0f, 0.5f, 1.0f, 0.5f, 0.5f,  0.5f, 400);
            Utils_Delay.delay(4, new Utils_Delay.DelayCallback() {
                @Override
                public void afterDelay() {
                    childView.setY(0);
                    childView.setScaleX(0.5f);
                    childView.setScaleY(0.5f);
                }
            });
        }
    }

    static  void child_DetailAnim(final  View view){
        final View getView = view;
        View getImgView = view;
        View getTitleView = view;

        lastSelItem = view.getId();
        getImgView = rl_main_ImgArray[getView.getId()];
        getTitleView = rl_main_TITLEArray[getView.getId()];

        Intent intent = new Intent(context, Main_DetailActivity.class);
        intent.putExtra("topTitle", (rl_main_iv_TITLEArray[getView.getId()]));
        intent.putExtra("topThumb", (rl_main_iv_URLArray[getView.getId()]).toString());
        Pair<View, String> p1 = Pair.create(getView, context.getString(R.string.transition_vpchild));
        Pair<View, String> p2 = Pair.create(getImgView, context.getString(R.string.transition_vpchild));

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, p1, p2);
        getView.getContext().startActivity(intent, options.toBundle());
    }

    static void child_dragDrirectionDetect(final View view){
        if (view.getY() < 0){
            dragDrirection = "UP";
        } else {
            dragDrirection = "DOWN";
        }
    }


}