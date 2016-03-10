package com.flyou.simplehenu.ui.fragment.department;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
import com.flyou.simplehenu.Constant;
import com.flyou.simplehenu.R;
import com.flyou.simplehenu.adapter.NewsRecycleViewAdapter;
import com.flyou.simplehenu.base.BaseFragment;
import com.flyou.simplehenu.domain.News;
import com.flyou.simplehenu.ui.activity.NewsDetailActivity;
import com.flyou.simplehenu.utils.SPUtils;
import com.flyou.simplehenu.utils.ThemeUtils;
import com.flyou.simplehenu.utils.UrlUtils.NewsUrl;
import com.flyou.simplehenu.utils.UrlUtils.NewsUrlPaster;
import com.flyou.simplehenu.utils.UrlUtils.departments.ComputerUrlPaster;
import com.flyou.simplehenu.utils.UrlUtils.departments.FaUrlPaster;
import com.flyou.simplehenu.utils.UrlUtils.departments.HuanJingUrlPaster;
import com.flyou.simplehenu.utils.UrlUtils.departments.JkyUrlPaster;
import com.flyou.simplehenu.utils.UrlUtils.departments.LiShiUrlPaster;
import com.flyou.simplehenu.utils.UrlUtils.departments.MinShengUrlPaster;
import com.flyou.simplehenu.utils.UrlUtils.departments.ShangUrlPaster;
import com.flyou.simplehenu.utils.UrlUtils.departments.WenXueUrlPaster;
import com.flyou.simplehenu.utils.UrlUtils.departments.XinWenUrlPaster;
import com.flyou.simplehenu.utils.UrlUtils.departments.YaoUrlPaster;
import com.flyou.simplehenu.utils.UrlUtils.departments.YiUrlPaster;
import com.flyou.simplehenu.utils.UrlUtils.departments.ZheXueUrlPaster;
import com.flyou.simplehenu.widget.loading.LoadingView;

import java.io.File;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;

/**
 * ============================================================
 * 项目名称：SimpleHenu
 * 包名称：com.simpleflyou.simplehenu.ui.fragment.department
 * 文件名：FaXueFragment
 * 类描述：
 * 创建人：flyou
 * 邮箱：fangjaylong@gmail.com
 * 创建时间：2016/3/9 16:23
 * 修改备注：
 * 版本：@version  V1.0
 * ============================================================
 **/
public class LiShiFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    public ProgressDialog mProgressDialog;
    private LoadingView loadingView;
    private RelativeLayout failedView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private NewsRecycleViewAdapter adapter;
    private LinearLayoutManager layoutManager;
    private List<News> newsList;
    private int currentPage = 1;
    private boolean isRefresh = true;
    private String mClassName;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView() {

        loadingView = (LoadingView) rootView.findViewById(R.id.loadingView);
        loadingView.setLoadingText("正在加载新闻数据，请稍后");
        loadingView.setVisibility(View.VISIBLE);

        failedView = (RelativeLayout) rootView.findViewById(R.id.failed_view);

        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);

        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);


    }

    @Override
    protected void initData() {
        mClassName = getClass().getSimpleName();
        getDataFromDisk();

    }


    @Override
    public void onRefresh() {
        isRefresh = true;
        currentPage = 1;
        if (failedView.isShown()) {
            failedView.setVisibility(View.GONE);
        }
        new getDataFromNet().execute(NewsUrl.GetUrlByClassName(mClassName, 1));

    }

    private void getDataFromDisk() {
        Gson gson = new Gson();
        String jsonString = (String) SPUtils.get(getActivity(), mClassName, "");

        if (TextUtils.isEmpty(jsonString)) {
//            从网络获取数据
            isRefresh = true;
            new getDataFromNet().execute(NewsUrl.GetUrlByClassName(mClassName, 1));
        } else {

            newsList = gson.fromJson(jsonString, new TypeToken<List<News>>() {
            }.getType());
            loadingView.setVisibility(View.GONE);
            setAdapter(newsList);

        }
    }

    private void getMore() {
        currentPage++;
        mProgressDialog = ProgressDialog.show(context, null, "正在加载数据……", false, true);
        isRefresh = false;

        new getDataFromNet().execute(NewsUrl.GetUrlByClassName(mClassName, currentPage));
    }

    //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^处理数据加载^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    public class getDataFromNet extends AsyncTask<String, List<News>, List<News>> {


        @Override
        protected List<News> doInBackground(String... params) {


            List<News> newses = null;
            switch (mClassName) {

                case "JiaoKeYuanFragment":
                    newses = JkyUrlPaster.GetShow(params[0]);
                    break;
                case "MinShengFragment":
                    newses = MinShengUrlPaster.GetShow(params[0]);
                    break;
                case "FaXueFragment":
                    newses = FaUrlPaster.GetShow(params[0]);
                    break;
                case "WenXueFragment":
                    newses = WenXueUrlPaster.GetShow(params[0]);
                    break;
                case "YaoXueFragment":
                    newses = YaoUrlPaster.GetShow(params[0]);
                    break;

                case "YiXueFragment":
                    newses = YiUrlPaster.GetShow(params[0]);
                    break;
                case "HuanJingFragment":
                    newses = HuanJingUrlPaster.GetShow(params[0]);
                    break;
                case "JiSuanJiFragment":
                    newses = ComputerUrlPaster.GetShow(params[0]);
                    break;
                case "ShengMingFragment":
                    newses = MinShengUrlPaster.GetShow(params[0]);
                    break;
                case "ZheXueFragment":
                    newses = ZheXueUrlPaster.GetShow(params[0]);
                    break;
                case "ShangFragment":
                    newses = ShangUrlPaster.GetShow(params[0]);
                    break;
                case "LiShiFragment":
                    newses = LiShiUrlPaster.GetShow(params[0]);
                    break;
                case "XinWenFragment":
                    newses = XinWenUrlPaster.GetShow(params[0]);
                    break;
                case "SchoolMessageFragment":
                    newses = NewsUrlPaster.getAllNews(params[0]);
                    break;
                case "EduMessageFragment":
                    newses = NewsUrlPaster.getAllNews(params[0]);
                    break;
                case "PersonalMessageFragment":
                    newses = NewsUrlPaster.getAllNews(params[0]);
                    break;
            }

            return newses;
        }

        @Override
        protected void onPostExecute(List<News> newses) {

            if (mProgressDialog != null) {
                mProgressDialog.dismiss();
            }
            if (swipeRefreshLayout.isRefreshing()) {
//            设置加载动画消失
                swipeRefreshLayout.setRefreshing(false);
            }
            if (loadingView.isShown()) {
                loadingView.setVisibility(View.GONE);
            }

            if (isRefresh) {
                //            缓存数据到本地
                Gson gson = new Gson();
                String newsJson = gson.toJson(newses);
                SPUtils.put(getActivity(), mClassName, newsJson);
            }
            setAdapter(newses);

            super.onPostExecute(newses);
        }
    }

    /*
    * 设置适配器
    * */
    private void setAdapter(List<News> newses) {

        if (newses == null || newses.size() == 0) {
            Snackbar.make(recyclerView, "请求内容为空哦", Snackbar.LENGTH_SHORT).show();
            return;
        }
        if (isRefresh) {

            newsList = newses;
            adapter = new NewsRecycleViewAdapter(context, newsList);

            recyclerView.setItemAnimator(new SlideInLeftAnimator());

            recyclerView.setAdapter(new ScaleInAnimationAdapter(adapter));

            recyclerView.addOnScrollListener(new onRecycleViewScollListener());

            adapter.setOnItemClickLitener(new onItiemClickListener());
        } else {
            newsList.addAll(newses);
            adapter.notifyDataSetChanged();
        }

    }

    /*
    * 滑动事件的监听
    * */
    public class onRecycleViewScollListener extends RecyclerView.OnScrollListener {
        private int lastVisibleItem = 0;
        private boolean isSlidingToLast = false;

        public onRecycleViewScollListener() {
            super();
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

            super.onScrollStateChanged(recyclerView, newState);
            if (isSlidingToLast) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == adapter.getItemCount()) {

                    getMore();
                }
            }
        }


        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (dy > 0) {
                isSlidingToLast = true;
            } else {
                isSlidingToLast = false;
            }
            lastVisibleItem = layoutManager.findLastVisibleItemPosition();
        }
    }

    private class onItiemClickListener implements NewsRecycleViewAdapter.OnItemClickLitener {
        @Override
        public void onItemClick(View view, int position) {

            Intent intent = new Intent(context, NewsDetailActivity.class);
            News item = newsList.get(position);

            intent.putExtra(Constant.SCHOOL_NEWS_TITLE, item.getTitle());
            intent.putExtra(Constant.SCHOOL_NEWS_URL, item.getUrl());
            intent.putExtra(Constant.DEPARTMENT_NAME, mClassName);
            context.startActivity(intent);

        }

        @Override
        public void onItemLongClick(View view, final int position) {
            new MaterialDialog.Builder(context)
                    .title(R.string.nav_share)
                    .icon(new IconicsDrawable(context)
                            .color(ThemeUtils.getThemeColor(context, R.attr.colorPrimary))
                            .icon(MaterialDesignIconic.Icon.gmi_share)
                            .sizeDp(20))
                    .positiveText("确定")
                    .content("您确定要分享此新闻吗？")
                    .negativeText("取消")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(MaterialDialog dialog, DialogAction which) {
                            shareMsgBySystem(context, "河大在线的分享", "", newsList.get(position).getTitle() + "[河大在线]http://www.flyou.ren", null);
                            dialog.dismiss();
                        }
                    })

                    .show();
        }
    }

    public static void shareMsgBySystem(Context context, String activityTitle, String msgTitle, String msgText,
                                        String imgPath) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        if (imgPath == null || imgPath.equals("")) {
            intent.setType("text/plain"); // 纯文本
        } else {
            File f = new File(imgPath);
            if (f != null && f.exists() && f.isFile()) {
                intent.setType("image/png");
                Uri u = Uri.fromFile(f);
                intent.putExtra(Intent.EXTRA_STREAM, u);
            }
        }
        intent.putExtra(Intent.EXTRA_SUBJECT, msgTitle);
        intent.putExtra(Intent.EXTRA_TEXT, msgText);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(intent, activityTitle));
    }
}