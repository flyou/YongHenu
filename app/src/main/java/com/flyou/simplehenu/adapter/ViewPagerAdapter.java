package com.flyou.simplehenu.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.flyou.simplehenu.domain.FragmentName;

import java.util.List;

/**
 * ============================================================
 * 项目名称：DrawLayout-ToolBar-
 * 包名称：com.flyou.lovefood.adapter
 * 文件名：ViewPagerAdapter
 * 类描述：
 * 创建人：flyou
 * 邮箱：fangjaylong@gmail.com
 * 创建时间：2016/3/3 15:39
 * 修改备注：
 * 版本：@version  V1.0
 * ============================================================
 **/
public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<FragmentName>fragmentList;

    public ViewPagerAdapter(FragmentManager fm, List<FragmentName> fragmentList) {

        super(fm);

        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentList.get(position).getName();
    }
}
