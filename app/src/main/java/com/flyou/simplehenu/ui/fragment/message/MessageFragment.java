package com.flyou.simplehenu.ui.fragment.message;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;

import com.flyou.simplehenu.R;
import com.flyou.simplehenu.adapter.ViewPagerAdapter;
import com.flyou.simplehenu.base.BaseFragment;
import com.flyou.simplehenu.domain.FragmentName;
import com.flyou.simplehenu.ui.fragment.main.EduMessageFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================
 * 项目名称：SimpleHenu
 * 包名称：com.simpleflyou.simplehenu.ui.fragment
 * 文件名：SchoolNewFragment
 * 类描述：
 * 创建人：flyou
 * 邮箱：fangjaylong@gmail.com
 * 创建时间：2016/3/8 19:40
 * 修改备注：
 * 版本：@version  V1.0
 * ============================================================
 **/
public class MessageFragment extends BaseFragment {

    private SwipeRefreshLayout swipeRefreshLayout;
    private TabLayout tabLayout;

    private ViewPager viewPager;
    private List<FragmentName> fragmentList;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_message;
    }

    @Override
    protected void initView() {

        tabLayout= (TabLayout) rootView.findViewById(R.id.tab_layout);
        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);

    }

    @Override
    protected void initData() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new FragmentName(new SchoolMessageFragment(),context.getString(R.string.message_school)));
        fragmentList.add(new FragmentName(new EduMessageFragment(),context.getString(R.string.message_edu)));

        viewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager(), fragmentList));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }



}
