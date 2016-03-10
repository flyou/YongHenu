package com.flyou.simplehenu.ui.activity;

import android.animation.Animator;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.color.ColorChooserDialog;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
import com.flyou.simplehenu.R;
import com.flyou.simplehenu.base.BaseActivity;
import com.flyou.simplehenu.ui.fragment.department.DepartmentFragment;
import com.flyou.simplehenu.ui.fragment.eassy.EassyFragment;
import com.flyou.simplehenu.ui.fragment.message.SchoolNewFragment;
import com.flyou.simplehenu.ui.fragment.message.MessageFragment;
import com.flyou.simplehenu.utils.PreUtils;
import com.flyou.simplehenu.utils.SystemUtils;
import com.flyou.simplehenu.utils.ThemeUtils;
import com.flyou.simplehenu.utils.theme.ColorUiUtil;
import com.flyou.simplehenu.utils.theme.ColorView;
import com.flyou.simplehenu.utils.theme.Theme;

public class MainActivity extends BaseActivity

        implements NavigationView.OnNavigationItemSelectedListener, ColorChooserDialog.ColorCallback {
    private long exitTime = 0l;
    private ColorView mStatusBar;
    private DrawerLayout drawer;
    private Toolbar mToolBar;
    private Fragment currentFragment;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
        mStatusBar = (ColorView) findViewById(R.id.status_bar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mStatusBar.setVisibility(View.VISIBLE);
            mStatusBar.getLayoutParams().height = SystemUtils.getStatusHeight(this);
            mStatusBar.setLayoutParams(mStatusBar.getLayoutParams());
        } else {
            mStatusBar.setVisibility(View.GONE);
        }


        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);
         drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



    }

    @Override
    protected void initDate() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new SchoolNewFragment()).commit();
    }

    @Override
    protected void setListener() {

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Snackbar.make(drawer,"再按一次返回键回到桌面",Snackbar.LENGTH_SHORT).show();
//                Toast.makeText(MainActivity.this, "再按一次返回键回到桌面", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                System.exit(0);
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.nav_main:
                switchFragment(new SchoolNewFragment());
                mToolBar.setTitle(R.string.app_name);
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_message:
                switchFragment(new MessageFragment());
                mToolBar.setTitle(R.string.nav_message);
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_department:
                switchFragment(new DepartmentFragment());
                mToolBar.setTitle(R.string.nav_department);
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_essay:
                switchFragment(new EassyFragment());
                mToolBar.setTitle(R.string.nav_essay);
                drawer.closeDrawer(GravityCompat.START);
                break;
//            case R.id.nav_photo:
//                mToolBar.setTitle(R.string.nav_photo);
//                break;
            case R.id.nav_share:
                new MaterialDialog.Builder(MainActivity.this)
                        .title(R.string.nav_share)
                        .icon(new IconicsDrawable(MainActivity.this)
                                .color(ThemeUtils.getThemeColor(MainActivity.this, R.attr.colorPrimary))
                                .icon(MaterialDesignIconic.Icon.gmi_share)
                                .sizeDp(20))
                        .positiveText("必须的")
                        .content("您确定要分享给自己的小伙伴吗？")
                        .negativeText("肯定啊")
                        .onNegative(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                shareMsgBySystem(MainActivity.this, "河大在线的分享", "", "[河大在线]您的校园百事通，点我下载吧http://www.flyou.ren", null);
                                dialog.dismiss();
                            }
                        })
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(MaterialDialog dialog, DialogAction which) {
                                shareMsgBySystem(MainActivity.this, "河大在线的分享", "", "[河大在线]您的校园百事通，点我下载吧http://www.flyou.ren", null);
                                dialog.dismiss();
                            }
                        })

                        .show();
                break;
            case R.id.nav_about:
                new MaterialDialog.Builder(MainActivity.this)
                        .title(R.string.nav_about)
                        .icon(new IconicsDrawable(MainActivity.this)
                                .color(ThemeUtils.getThemeColor(MainActivity.this, R.attr.colorPrimary))
                                .icon(MaterialDesignIconic.Icon.gmi_account)
                                .sizeDp(20))
                        .positiveText("确定")
                        .content(R.string.about_account)
                        .negativeText("取消")
                        .show();
                break;
            case R.id.nav_theme:
                new ColorChooserDialog.Builder(MainActivity.this, R.string.theme)
                        .customColors(R.array.colors, null)
                        .doneButton(R.string.done)
                        .cancelButton(R.string.cancel)
                        .allowUserColorInput(false)
                        .allowUserColorInputAlpha(false)
                        .show();
                break;

        }



        return true;
    }

    @Override
    public void onColorSelection(@NonNull ColorChooserDialog dialog, @ColorInt int selectedColor) {
        if (selectedColor == ThemeUtils.getThemeColor(this, R.attr.colorPrimary))
            return;

        if (selectedColor == getResources().getColor(R.color.colorBluePrimary)) {
            setTheme(R.style.BlueTheme);
            PreUtils.setCurrentTheme(this, Theme.Blue);

        } else if (selectedColor == getResources().getColor(R.color.colorRedPrimary)) {
            setTheme(R.style.RedTheme);
            PreUtils.setCurrentTheme(this, Theme.Red);

        } else if (selectedColor == getResources().getColor(R.color.colorBrownPrimary)) {
            setTheme(R.style.BrownTheme);
            PreUtils.setCurrentTheme(this, Theme.Brown);

        } else if (selectedColor == getResources().getColor(R.color.colorGreenPrimary)) {
            setTheme(R.style.GreenTheme);
            PreUtils.setCurrentTheme(this, Theme.Green);

        } else if (selectedColor == getResources().getColor(R.color.colorPurplePrimary)) {
            setTheme(R.style.PurpleTheme);
            PreUtils.setCurrentTheme(this, Theme.Purple);

        } else if (selectedColor == getResources().getColor(R.color.colorTealPrimary)) {
            setTheme(R.style.TealTheme);
            PreUtils.setCurrentTheme(this, Theme.Teal);

        } else if (selectedColor == getResources().getColor(R.color.colorPinkPrimary)) {
            setTheme(R.style.PinkTheme);
            PreUtils.setCurrentTheme(this, Theme.Pink);

        } else if (selectedColor == getResources().getColor(R.color.colorDeepPurplePrimary)) {
            setTheme(R.style.DeepPurpleTheme);
            PreUtils.setCurrentTheme(this, Theme.DeepPurple);

        } else if (selectedColor == getResources().getColor(R.color.colorOrangePrimary)) {
            setTheme(R.style.OrangeTheme);
            PreUtils.setCurrentTheme(this, Theme.Orange);

        } else if (selectedColor == getResources().getColor(R.color.colorIndigoPrimary)) {
            setTheme(R.style.IndigoTheme);
            PreUtils.setCurrentTheme(this, Theme.Indigo);

        } else if (selectedColor == getResources().getColor(R.color.colorLightGreenPrimary)) {
            setTheme(R.style.LightGreenTheme);
            PreUtils.setCurrentTheme(this, Theme.LightGreen);

        } else if (selectedColor == getResources().getColor(R.color.colorDeepOrangePrimary)) {
            setTheme(R.style.DeepOrangeTheme);
            PreUtils.setCurrentTheme(this, Theme.DeepOrange);

        } else if (selectedColor == getResources().getColor(R.color.colorLimePrimary)) {
            setTheme(R.style.LimeTheme);
            PreUtils.setCurrentTheme(this, Theme.Lime);

        } else if (selectedColor == getResources().getColor(R.color.colorBlueGreyPrimary)) {
            setTheme(R.style.BlueGreyTheme);
            PreUtils.setCurrentTheme(this, Theme.BlueGrey);

        } else if (selectedColor == getResources().getColor(R.color.colorCyanPrimary)) {
            setTheme(R.style.CyanTheme);
            PreUtils.setCurrentTheme(this, Theme.Cyan);

        }
        final View rootView = getWindow().getDecorView();
        rootView.setDrawingCacheEnabled(true);
        rootView.buildDrawingCache(true);

        final Bitmap localBitmap = Bitmap.createBitmap(rootView.getDrawingCache());
        rootView.setDrawingCacheEnabled(false);
        if (null != localBitmap && rootView instanceof ViewGroup) {
            final View tmpView = new View(getApplicationContext());
            tmpView.setBackgroundDrawable(new BitmapDrawable(getResources(), localBitmap));
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            ((ViewGroup) rootView).addView(tmpView, params);
            tmpView.animate().alpha(0).setDuration(400).setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    ColorUiUtil.changeTheme(rootView, getTheme());
                    System.gc();
                }


                @Override
                public void onAnimationEnd(Animator animation) {
                    ((ViewGroup) rootView).removeView(tmpView);
                    localBitmap.recycle();
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            }).start();
        }
    }


    private void switchFragment(Fragment fragment) {
        if (currentFragment == null || !fragment.getClass().getName().equals(currentFragment.getClass().getName())) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, fragment).commit();
            currentFragment = fragment;
        }
    }

}
