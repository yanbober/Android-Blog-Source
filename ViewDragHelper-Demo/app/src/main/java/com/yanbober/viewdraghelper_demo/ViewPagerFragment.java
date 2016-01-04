package com.yanbober.viewdraghelper_demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ViewPagerFragment extends android.support.v4.app.Fragment {
    public static final String ARG_KEY = "cur_type";
    public static final int ARG_VALUE_0 = 0;
    public static final int ARG_VALUE_1 = 1;
    public static final int ARG_VALUE_2 = 2;

    private int mType;
    private View mParentView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mType = getArguments().getInt(ARG_KEY);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int resourceId = -1;
        switch (mType) {
            case ARG_VALUE_0:
                resourceId = R.layout.fragment_view_pager_layout;
                break;
            case ARG_VALUE_1:
                resourceId = R.layout.fragment_view_pager1_layout;
                break;
            case ARG_VALUE_2:
                resourceId = R.layout.fragment_view_pager2_layout;
                break;
            default:
                resourceId = R.layout.fragment_view_pager_layout;
                break;
        }

        mParentView = LayoutInflater.from(container.getContext()).inflate(resourceId, container, false);
        return mParentView;
    }
}
