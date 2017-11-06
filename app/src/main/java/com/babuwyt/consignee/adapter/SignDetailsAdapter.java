package com.babuwyt.consignee.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/11/6.
 */

public class SignDetailsAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> mList;
    public SignDetailsAdapter(FragmentManager fm,ArrayList<Fragment> list) {
        super(fm);
        mList=list;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }
}
