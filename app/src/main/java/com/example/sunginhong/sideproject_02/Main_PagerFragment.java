package com.example.sunginhong.sideproject_02;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Main_PagerFragment extends Fragment {
    private static final String ARG_COUNT = "count";
    private static final String ARG_PARAM2 = "param2";

    private String mCount;

    public Main_PagerFragment() {
        // Required empty public constructor
    }

    public static Main_PagerFragment newInstance(String param1) {
        Main_PagerFragment fragment = new Main_PagerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_COUNT, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCount = getArguments().getString(ARG_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_vp_cardlayout, container, false);
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getActivity().setTitle("count : " + mCount);
        }
    }
}