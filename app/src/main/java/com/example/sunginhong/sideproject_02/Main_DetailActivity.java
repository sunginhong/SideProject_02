package com.example.sunginhong.sideproject_02;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.ChangeBounds;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.sunginhong.sideproject_02.Utils_Folder.Utils_Calc;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Main_DetailActivity extends AppCompatActivity {

    static Context context;
    RecyclerView rvView;

    static ImageView detatil_topiv;
    private TextView detatil_toptv;
    static int detail_header_HEIGHT_dp = 56;
    static RelativeLayout detail_header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_detail);

        ChangeBounds bounds = new ChangeBounds();
        bounds.setDuration(400);
        bounds.setInterpolator(new DecelerateInterpolator(1.5f));
        getWindow().setSharedElementEnterTransition(bounds);

        detail_header = (RelativeLayout)findViewById(R.id.detail_header);
        detail_header.setY(Utils_Calc.dpToPx(-detail_header_HEIGHT_dp));
        detatil_topiv = (ImageView)findViewById(R.id.detatil_topiv);
        detatil_topiv.setY(0);
        detatil_toptv = (TextView)findViewById(R.id.detatil_toptv);
        Intent intent = getIntent();

        String topTitle = intent.getStringExtra("topTitle");
        detatil_toptv.setText(topTitle);
        TextView detail_header_title = (TextView) findViewById(R.id.detail_header_title);
        detail_header_title.setText(topTitle);

        String topThumb = intent.getStringExtra("topThumb");
        Picasso.get().load(topThumb).into(detatil_topiv);

        final ArrayList<String[]> values = new ArrayList<String[]>();
        if(values.size() == 0) {
            values.add(new String[]{"DetailView", "https://cdn.dribbble.com/users/116499/screenshots/6198798/barky1.png", "TITLE0"});
            values.add(new String[]{"DetailView", "https://cdn.dribbble.com/users/1622978/screenshots/6195951/dribble_buenaventura_estudio_3.jpg", "TITLE1"});
            values.add(new String[]{"DetailView", "https://cdn.dribbble.com/users/131414/screenshots/6196358/tutorid-1.png", "TITLE2"});
            values.add(new String[]{"DetailView", "https://cdn.dribbble.com/users/2155131/screenshots/6196058/time_management_2x.jpg", "TITLE3"});
            values.add(new String[]{"DetailView", "https://cdn.dribbble.com/users/94598/screenshots/6188943/records-21.png", "TITLE4"});
            values.add(new String[]{"DetailView", "https://cdn.dribbble.com/users/68544/screenshots/6187298/gatorade_dr.png", "TITLE5"});
            values.add(new String[]{"DetailView", "https://cdn.dribbble.com/users/1842173/screenshots/6196210/majicafull-06_2x.png", "TITLE6"});
            values.add(new String[]{"DetailView", "https://cdn.dribbble.com/users/90377/screenshots/6196270/loveurs_mockup_front_flat_white.png", "TITLE7"});
        }

        Detail_RecyclerViewAdapter mAdapter = new Detail_RecyclerViewAdapter(this, values);
        rvView = (RecyclerView) findViewById(R.id.detail_rvView);

        rvView.setAdapter(mAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvView.setLayoutManager(llm);
        rvView.setNestedScrollingEnabled(false);
        rvView.setHasFixedSize(false);
    }

    @Override
    protected void onResume() {
        Detail_RecyclerViewAdapter.detailItemResumeAnim();
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
