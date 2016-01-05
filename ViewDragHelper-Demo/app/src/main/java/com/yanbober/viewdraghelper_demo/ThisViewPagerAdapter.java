package com.yanbober.viewdraghelper_demo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class ThisViewPagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<String> mTitleList = new ArrayList<>();

    public ThisViewPagerAdapter(FragmentManager fm) {
        super(fm);

        mTitleList.add("列表统计");
        mTitleList.add("详情说明");
        mTitleList.add("其他杂项");
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new ViewPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ViewPagerFragment.ARG_KEY, position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position);
    }
}
