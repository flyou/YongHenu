package com.flyou.simplehenu.ui.fragment.eassy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.flyou.simplehenu.Constant;
import com.flyou.simplehenu.R;
import com.flyou.simplehenu.base.BaseFragment;

/**
 * ============================================================
 * 项目名称：SimpleHenu
 * 包名称：com.simpleflyou.simplehenu.ui.fragment.eassy
 * 文件名：EassyFragment
 * 类描述：
 * 创建人：flyou
 * 邮箱：fangjaylong@gmail.com
 * 创建时间：2016/3/9 16:56
 * 修改备注：
 * 版本：@version  V1.0
 * ============================================================
 **/
public class EassyFragment extends BaseFragment {
    private WebView mWebView;
    private ProgressDialog mProgressDialog;
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_eassy;
    }

    @Override
    protected void initData() {
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setBlockNetworkImage(false);
        webSettings.setDefaultTextEncodingName("utf-8");
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setTextSize(WebSettings.TextSize.NORMAL);
        webSettings.setDomStorageEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if( url.startsWith("http:") || url.startsWith("https:") ) {
                    return false;
                }

                // Otherwise allow the OS to handle things like tel, mailto,sms , etc.
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity( intent );
                return true;
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
                super.onPageFinished(view, url);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();

                Toast.makeText(context, "加载失败", Toast.LENGTH_SHORT).show();
                super.onReceivedError(view, errorCode, description, failingUrl);
            }
        });


        mWebView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
        mWebView.loadUrl(Constant.HENU_MEIWEN);
    }

    @Override
    protected void initView() {
        mWebView = (WebView) rootView.findViewById(R.id.webView);
        mProgressDialog= ProgressDialog.show(context,null,"正在加载数据",false,true);
    }

    @Override
    public void onDestroyView() {
        mWebView.destroy();
        super.onDestroyView();
    }

}
