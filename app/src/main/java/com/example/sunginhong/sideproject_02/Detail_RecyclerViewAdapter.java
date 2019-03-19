package com.example.sunginhong.sideproject_02;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.sunginhong.sideproject_02.Utils_Folder.Utils_Animation;
import com.example.sunginhong.sideproject_02.Utils_Folder.Utils_Delay;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Detail_RecyclerViewAdapter extends RecyclerView.Adapter<Detail_RecyclerViewAdapter.MyViewHolder> implements View.OnClickListener  {
    ArrayList<String[]> arrayList;
    Context c;
    private  MyViewHolder view;

    public static final int DETAIL_ITEM_COUNT = 8;
    static RelativeLayout pvArray[] = new RelativeLayout[DETAIL_ITEM_COUNT];


    public Detail_RecyclerViewAdapter (Context c, ArrayList<String[]> list) {
        arrayList = list;
        this.c = c;
    }

    @Override
    public Detail_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_detail_fragment, parent, false);
        return new Detail_RecyclerViewAdapter.MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(Detail_RecyclerViewAdapter.MyViewHolder holder, final int position) {
        view = holder;
        String[] detail = arrayList.get(position);

        holder.detail_titleview.setText(detail[0]);

        Uri libThumb = Uri.parse(detail[1]);
        Picasso.get().load(libThumb).into(holder.detail_imgview);

        holder.detail_subtitleview.setText(detail[2]);

        holder.detail_lst_rl.setId(position);
        holder.detail_lst_rl.setOnClickListener(this);
        pvArray[position] = holder.detail_lst_rl;

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        Context c;

        private RelativeLayout detail_lst_rl;
        private TextView detail_titleview;
        private TextView detail_subtitleview;
        private ImageView detail_imgview;
        View view;

        public MyViewHolder(View itemView) {
            super(itemView);
            detail_lst_rl = (RelativeLayout)itemView.findViewById(R.id.detail_lst_rl);
            detail_titleview = (TextView)itemView.findViewById(R.id.detail_titleview);
            detail_imgview = (ImageView)itemView.findViewById(R.id.detail_imgview);
            detail_subtitleview = (TextView)itemView.findViewById(R.id.detail_subtitleview);
            view = itemView;
        }
    }

    @Override
    public void onClick(View view) {

    }

    static void detailItemResumeAnim(){
        Utils_Delay.delay(1, new Utils_Delay.DelayCallback() {
            @Override
            public void afterDelay() {
                detailItemResumeAnim_index(0);
            }
        });

    }

    private static void detailItemResumeAnim_index(final int index){
        int iterations = pvArray.length;
        final int current = index+1;
        final float lstHeight_Rate = 1.5f;
        if (current <= iterations){
            Utils_Delay.delayMin(5, new Utils_Delay.DelayCallback() {
                @Override
                public void afterDelay() {
                    for (int i = 0; i < pvArray.length; i++) {
                        if (i == index){
                            Utils_Animation.TransAnim(pvArray[i], 0f, 0f, pvArray[i].getHeight()*lstHeight_Rate, 0f, 400);
                        }
                    }
                    detailItemResumeAnim_index(current);
                }
            });
        }
    }

}