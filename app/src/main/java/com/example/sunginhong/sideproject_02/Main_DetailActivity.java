package com.example.sunginhong.sideproject_02;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.ChangeBounds;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;

public class Main_DetailActivity extends AppCompatActivity {

    static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_detail);

        ChangeBounds bounds = new ChangeBounds();
        bounds.setDuration(400);
        bounds.setInterpolator(new DecelerateInterpolator(1.5f));
        getWindow().setSharedElementEnterTransition(bounds);

        ImageView detatil_topiv = (ImageView)findViewById(R.id.detatil_topiv);

        Intent intent = getIntent();

        String thumbImg_Link = intent.getStringExtra("thumbImg_Link");
        Picasso.get().load(thumbImg_Link).into(detatil_topiv);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
//        finish();
        supportFinishAfterTransition();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
