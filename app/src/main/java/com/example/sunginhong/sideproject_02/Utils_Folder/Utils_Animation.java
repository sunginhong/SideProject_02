package com.example.sunginhong.sideproject_02.Utils_Folder;

import android.content.Context;
import android.view.View;
import android.view.animation.*;

public class Utils_Animation {
    protected Context context;

    public Utils_Animation(){
    }

    public interface DelayCallback {
        void afterDelay();
    }

    public static void TransAnim(View view, float startX, float endX, float startY, float endY, int duration) {
        TranslateAnimation anim = new TranslateAnimation(
                startX, endX,
                startY, endY );
        anim.setFillAfter(true);
        anim.setInterpolator(new DecelerateInterpolator((float) 1.5));
        anim.setDuration(duration);
        view.startAnimation(anim);
    }

    public static void AlphaAnim(View view, float startAlpha, float endAlpha, int duration) {
        Animation anim = new AlphaAnimation( startAlpha, endAlpha );
        anim.setFillAfter(true);
        anim.setInterpolator(new DecelerateInterpolator((float) 1.5));
        anim.setDuration(duration);
        view.startAnimation(anim);
    }

    public static void SclaeAnim(View view, float startScaleX, float endScaleX, float startScaleY,float endScaleY, float originX, float originY, int duration) {
        ScaleAnimation anim = new ScaleAnimation( startScaleX, endScaleX, startScaleY, endScaleY, Animation.RELATIVE_TO_SELF, originX, Animation.RELATIVE_TO_SELF, originY  );
        anim.setFillAfter(true);
        anim.setInterpolator(new DecelerateInterpolator((float) 1.5));
        anim.setDuration(duration);
        view.startAnimation(anim);
    }

    public static void SclaeAlphaAnim(View view, float startScaleX, float endScaleX, float startScaleY,float endScaleY, float originX, float originY, float startAlpha, float endAlpha, int duration) {
        ScaleAnimation anim1 = new ScaleAnimation( startScaleX, endScaleX, startScaleY, endScaleY, Animation.RELATIVE_TO_SELF, originX, Animation.RELATIVE_TO_SELF, originY  );
        Animation anim2 = new AlphaAnimation( startAlpha, endAlpha );

        AnimationSet setAnim = new AnimationSet(true);
        setAnim.setFillAfter(true);
        setAnim.setInterpolator(new DecelerateInterpolator((float) 1.5));
        setAnim.setDuration(duration);
        setAnim.addAnimation(anim1);
        setAnim.addAnimation(anim2);
        view.setAnimation(setAnim);
    }

    public static void TransScaleAnim(View view, float startX, float endX, float startY, float endY, float startScaleX, float endScaleX, float startScaleY,float endScaleY, int duration) {
        ScaleAnimation anim1 = new ScaleAnimation( startScaleX, endScaleX, startScaleY, endScaleY, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f  );
        TranslateAnimation anim2 = new TranslateAnimation(
                startX, endX,
                startY, endY );

        AnimationSet setAnim = new AnimationSet(true);
        setAnim.setFillAfter(true);
        setAnim.setInterpolator(new DecelerateInterpolator((float) 1.5));
        setAnim.setDuration(duration);
        setAnim.addAnimation(anim1);
        setAnim.addAnimation(anim2);
        view.setAnimation(setAnim);
    }

    public static void TransAlphaAnim(View view, float startX, float endX, float startY, float endY, float startAlpha, float endAlpha, int duration) {
        Animation anim1 = new AlphaAnimation( startAlpha, endAlpha );
        TranslateAnimation anim2 = new TranslateAnimation(
                startX, endX,
                startY, endY );

        AnimationSet setAnim = new AnimationSet(true);
        setAnim.setFillAfter(true);
        setAnim.setInterpolator(new DecelerateInterpolator((float) 1.5));
        setAnim.setDuration(duration);
        setAnim.addAnimation(anim1);
        setAnim.addAnimation(anim2);
        view.setAnimation(setAnim);
    }


    public static void ModulateAlphaAnim(View view, float value, float rangeA, float rangeB, float rangeC, float rangeD){
        float fromHigh = 0;
        float fromLow = 0;
        float toHigh = 0;
        float toLow = 0;
        float result = 0;
        Double resultF = 0.0;

        fromLow = rangeA;
        fromHigh = rangeB;
        toLow = rangeC;
        toHigh = rangeD;

        result = toLow + (((value - fromLow) / (fromHigh - fromLow)) * (toHigh - toLow));
//        resultF = Double.parseDouble(String.format("%.2f", result));
        view.setAlpha(result);
    }

    public static void ModulatetTransXAnim(View view, float value, float rangeA, float rangeB, float rangeC, float rangeD){
        float fromHigh = 0;
        float fromLow = 0;
        float toHigh = 0;
        float toLow = 0;
        float result = 0;
        Double resultF = 0.0;

        fromLow = rangeA;
        fromHigh = rangeB;
        toLow = rangeC;
        toHigh = rangeD;

        result = toLow + (((value - fromLow) / (fromHigh - fromLow)) * (toHigh - toLow));
        view.setTranslationX(result);
    }

    public static void ModulatetTransYAnim(View view, float value, float rangeA, float rangeB, float rangeC, float rangeD){
        float fromHigh = 0;
        float fromLow = 0;
        float toHigh = 0;
        float toLow = 0;
        float result = 0;
        Double resultF = 0.0;

        fromLow = rangeA;
        fromHigh = rangeB;
        toLow = rangeC;
        toHigh = rangeD;

        result = toLow + (((value - fromLow) / (fromHigh - fromLow)) * (toHigh - toLow));
        view.setTranslationY(result);
    }

    public static void ModulatetScaleAnim(View view, float value, float rangeA, float rangeB, float rangeC, float rangeD){
        float fromHigh = 0;
        float fromLow = 0;
        float toHigh = 0;
        float toLow = 0;
        float result = 0;
        Double resultF = 0.0;

        fromLow = rangeA;
        fromHigh = rangeB;
        toLow = rangeC;
        toHigh = rangeD;

        result = toLow + (((value - fromLow) / (fromHigh - fromLow)) * (toHigh - toLow));
        view.setScaleX(result);
        view.setScaleY(result);
    }

    public static void ModulatetAlphaAnim(View view, float value, float rangeA, float rangeB, float rangeC, float rangeD){
        float fromHigh = 0;
        float fromLow = 0;
        float toHigh = 0;
        float toLow = 0;
        float result = 0;
        Double resultF = 0.0;

        fromLow = rangeA;
        fromHigh = rangeB;
        toLow = rangeC;
        toHigh = rangeD;

        result = toLow + (((value - fromLow) / (fromHigh - fromLow)) * (toHigh - toLow));
        view.setAlpha(result);
    }

    public static void ModulatetScaleRotationYAnim(View view, float value, float scale_rangeA, float scale_rangeB, float scale_rangeC, float scale_rangeD, float rotate_rangeA, float rotate_rangeB, float rotate_rangeC, float rotate_rangeD){
        float Scale_fromHigh = 0;
        float Scale_fromLow = 0;
        float Scale_toHigh = 0;
        float Scale_toLow = 0;
        float Scale_result = 0;

        float Rotate_fromHigh = 0;
        float Rotate_fromLow = 0;
        float Rotate_toHigh = 0;
        float Rotate_toLow = 0;
        float Rotate_result = 0;

        Double resultF = 0.0;

        Scale_fromLow = scale_rangeA;
        Scale_fromHigh = scale_rangeB;
        Scale_toLow = scale_rangeC;
        Scale_toHigh = scale_rangeD;

        Rotate_fromLow = rotate_rangeA;
        Rotate_fromHigh = rotate_rangeB;
        Rotate_toLow = rotate_rangeC;
        Rotate_toHigh = rotate_rangeD;

        Scale_result = Scale_toLow + (((value - Scale_fromLow) / (Scale_fromHigh - Scale_fromLow)) * (Scale_toHigh - Scale_toLow));
        view.setScaleX(Scale_result);
        view.setScaleY(Scale_result);

        Rotate_result = Rotate_toLow + (((value - Rotate_fromLow) / (Rotate_fromHigh - Rotate_fromLow)) * (Rotate_toHigh - Rotate_toLow));
        view.setRotationY(Rotate_result);
    }

}