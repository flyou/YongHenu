package com.flyou.simplehenu.domain;

import android.support.v4.app.Fragment;

/**
 * ============================================================
 * 项目名称：SimpleHenu
 * 包名称：com.simpleflyou.simplehenu.domain
 * 文件名：FragmentName
 * 类描述：
 * 创建人：flyou
 * 邮箱：fangjaylong@gmail.com
 * 创建时间：2016/3/9 16:12
 * 修改备注：
 * 版本：@version  V1.0
 * ============================================================
 **/
public class FragmentName {
    private Fragment fragment;
    private String name;

    public FragmentName() {
    }

    public FragmentName(Fragment fragment, String name) {
        this.fragment = fragment;
        this.name = name;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
