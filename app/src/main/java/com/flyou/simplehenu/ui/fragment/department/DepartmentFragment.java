package com.flyou.simplehenu.ui.fragment.department;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.flyou.simplehenu.R;
import com.flyou.simplehenu.adapter.ViewPagerAdapter;
import com.flyou.simplehenu.base.BaseFragment;
import com.flyou.simplehenu.domain.FragmentName;

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
public class DepartmentFragment extends BaseFragment {


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
        fragmentList.add(new FragmentName(new JiaoKeYuanFragment(),context.getString(R.string.department_jiaoyu)));
        fragmentList.add(new FragmentName(new MinShengFragment(),context.getString(R.string.department_minsheng)));
        fragmentList.add(new FragmentName(new WenXueFragment(),context.getString(R.string.department_fa)));
        fragmentList.add(new FragmentName(new YaoXueFragment(),context.getString(R.string.department_yao)));
        fragmentList.add(new FragmentName(new YiXueFragment(),context.getString(R.string.department_yi)));
        fragmentList.add(new FragmentName(new HuanJingFragment(),context.getString(R.string.department_huanjing)));
        fragmentList.add(new FragmentName(new JiSuanJiFragment(),context.getString(R.string.department_jisuanji)));
        fragmentList.add(new FragmentName(new ShengMingFragment(),context.getString(R.string.department_sheng)));
        fragmentList.add(new FragmentName(new ZheXueFragment(),context.getString(R.string.department_zhe)));
        fragmentList.add(new FragmentName(new LiShiFragment(),context.getString(R.string.department_lishi)));
        fragmentList.add(new FragmentName(new XinWenFragment(),context.getString(R.string.department_xinwen)));
        fragmentList.add(new FragmentName(new ShangFragment(),context.getString(R.string.department_shang)));
        fragmentList.add(new FragmentName(new LiShiFragment(),context.getString(R.string.department_lishi)));

        viewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager(), fragmentList));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }



}
