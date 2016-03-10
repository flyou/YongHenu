package com.flyou.simplehenu.ui.activity;


import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.flyou.simplehenu.Constant;
import com.flyou.simplehenu.R;
import com.flyou.simplehenu.base.BaseActivity;
import com.flyou.simplehenu.utils.SPUtils;
import com.flyou.simplehenu.utils.UrlUtils.NewsItemPaster;
import com.flyou.simplehenu.utils.UrlUtils.departments.ComputerNewsitemShow;
import com.flyou.simplehenu.utils.UrlUtils.departments.FaNewsitemShow;
import com.flyou.simplehenu.utils.UrlUtils.departments.HuanJingNewsitemShow;
import com.flyou.simplehenu.utils.UrlUtils.departments.JkyNewsitemShow;
import com.flyou.simplehenu.utils.UrlUtils.departments.LiShiNewsitemShow;
import com.flyou.simplehenu.utils.UrlUtils.departments.LifeNewsitemShow;
import com.flyou.simplehenu.utils.UrlUtils.departments.MinSHengNewsitemShow;
import com.flyou.simplehenu.utils.UrlUtils.departments.ShangNewsitemShow;
import com.flyou.simplehenu.utils.UrlUtils.departments.WenXueNewsitemShow;
import com.flyou.simplehenu.utils.UrlUtils.departments.XinWenNewsitemShow;
import com.flyou.simplehenu.utils.UrlUtils.departments.YaoNewsitemShow;
import com.flyou.simplehenu.utils.UrlUtils.departments.YiNewsitemShow;
import com.flyou.simplehenu.utils.UrlUtils.departments.ZheXueNewsitemShow;
import com.flyou.simplehenu.widget.loading.LoadingView;


public class NewsDetailActivity extends BaseActivity implements View.OnClickListener {
    private String mNewsUrl;
    private WebView webView;
    private LoadingView mLoadView;
    private RelativeLayout failed_view;
    private Button reTry;
    private String fromClassName;
    private String mNewsTitle;
    private CollapsingToolbarLayout mToolbarLayout;
    private Toolbar mToolbar;
    private FloatingActionButton mFloatingActionButton;


    @Override
    protected void initView() {
        setContentView(R.layout.activity_news_detial);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        failed_view = (RelativeLayout) findViewById(R.id.failed_view);
        reTry = (Button) findViewById(R.id.reTry);
        mLoadView = (LoadingView) findViewById(R.id.loadingView);
        mLoadView.setLoadingText("正在拼命加载数……");
        webView = (WebView) findViewById(R.id.webView);
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.fab);


    }

    @Override
    protected void initDate() {
        //        获取传来的参数
        Intent intent = getIntent();
        mNewsTitle = intent.getStringExtra(Constant.SCHOOL_NEWS_TITLE);

        mToolbarLayout.setTitle(mNewsTitle);



        mNewsUrl = intent.getStringExtra(Constant.SCHOOL_NEWS_URL);

        fromClassName = intent.getStringExtra(Constant.DEPARTMENT_NAME);


        //        设置webView
        mLoadView.setVisibility(View.VISIBLE);
        webView.setBackgroundColor(Color.WHITE);
        WebSettings webSettings = webView.getSettings();
        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setBlockNetworkImage(false);
        webSettings.setDefaultTextEncodingName("utf-8");
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setTextSize(WebSettings.TextSize.NORMAL);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                mLoadView.setVisibility(View.GONE);
                super.onPageFinished(view, url);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(NewsDetailActivity.this, "加载失败", Toast.LENGTH_SHORT).show();
                super.onReceivedError(view, errorCode, description, failingUrl);
            }
        });
        webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);

        String itemNews = (String) SPUtils.get(NewsDetailActivity.this, mNewsUrl, "");
        if (!TextUtils.isEmpty(itemNews)) {

            failed_view.setVisibility(View.GONE);
            mLoadView.setVisibility(View.VISIBLE);

            webView.loadDataWithBaseURL(mNewsUrl, itemNews, "text/html", "utf-8", null);

        } else {
            new GetNewsHtmlBody().execute(mNewsUrl);
        }


    }

    @Override
    protected void setListener() {
        reTry.setOnClickListener(this);

        mFloatingActionButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:

                shareMsgBySystem(NewsDetailActivity.this, "河大在线的分享", "", "我使[河大在线]http://www.flyou.ren给你分享了一个新闻+【" + mNewsTitle + "," + mNewsUrl + "】", null);

                break;
            case R.id.reTry:
                new GetNewsHtmlBody().execute(mNewsUrl);
                failed_view.setVisibility(View.GONE);

                mLoadView.setVisibility(View.VISIBLE);
                break;
        }
    }

    class GetNewsHtmlBody extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            String newsbodyHtml = "";
            switch (fromClassName) {
                case "SchoolNewFragment":
                    newsbodyHtml = new NewsItemPaster().getNewsbody(params[0]);
                    break;
                case "JiaoKeYuanFragment":
                    newsbodyHtml = JkyNewsitemShow.getNewsbody(params[0]);
                    break;
                case "MinShengFragment":
                    newsbodyHtml = MinSHengNewsitemShow.getNewsbody(params[0]);
                    break;
                case "FaXueFragment":
                    newsbodyHtml = FaNewsitemShow.getNewsbody(params[0]);
                    break;
                case "WenXueFragment":
                    newsbodyHtml = WenXueNewsitemShow.getNewsbody(params[0]);
                    break;
                case "YaoXueFragment":
                    newsbodyHtml = YaoNewsitemShow.getNewsbody(params[0]);
                    break;

                case "YiXueFragment":
                    newsbodyHtml = YiNewsitemShow.getNewsbody(params[0]);
                    break;
                case "HuanJingFragment":
                    newsbodyHtml = HuanJingNewsitemShow.getNewsbody(params[0]);
                    break;
                case "JiSuanJiFragment":
                    newsbodyHtml = ComputerNewsitemShow.getNewsbody(params[0]);
                    break;
                case "ShengMingFragment":
                    newsbodyHtml = LifeNewsitemShow.getNewsbody(params[0]);
                    break;
                case "ZheXueFragment":
                    newsbodyHtml = ZheXueNewsitemShow.getNewsbody(params[0]);
                    break;
                case "LiShiFragment":
                    newsbodyHtml = LiShiNewsitemShow.getNewsbody(params[0]);
                    break;
                case "XinWenFragment":
                    newsbodyHtml = XinWenNewsitemShow.getNewsbody(params[0]);
                    break;
                case "ShangFragment":
                    newsbodyHtml = ShangNewsitemShow.getNewsbody(params[0]);
                    break;

                case "SchoolMessageFragment":
                    newsbodyHtml = new NewsItemPaster().getNewsbody(params[0]);
                    break;
                case "EduMessageFragment":
                    newsbodyHtml = new NewsItemPaster().getNewsbody(params[0]);
                    break;
                case "PersonalMessageFragment":
                    newsbodyHtml = new NewsItemPaster().getNewsbody(params[0]);
                    break;
            }


            return newsbodyHtml;
        }

        @Override
        protected void onPostExecute(String s) {

//使用基本地址加载url 参数： 请求的地址（可以为网站的root地址、要加载的html片段、显示的格式、编码、错误页面）

            if (TextUtils.isEmpty(s)) {
                failed_view.setVisibility(View.VISIBLE);

                mLoadView.setVisibility(View.GONE);
            } else {
                failed_view.setVisibility(View.GONE);
                mLoadView.setVisibility(View.VISIBLE);
//                将html片段缓存到本地
                SPUtils.put(NewsDetailActivity.this, mNewsUrl, s);
                webView.loadDataWithBaseURL(mNewsUrl, s, "text/html", "utf-8", null);
            }
            super.onPostExecute(s);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (webView.canGoBack()) {
                webView.goBack();
            } else {
                finish();
            }

        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.destroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
