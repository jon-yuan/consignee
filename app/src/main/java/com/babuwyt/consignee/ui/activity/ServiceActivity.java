package com.babuwyt.consignee.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.babuwyt.consignee.R;
import com.babuwyt.consignee.base.BaseActivity;
import com.babuwyt.consignee.finals.BaseURL;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by lenovo on 2017/11/7.
 */
@ContentView(R.layout.activity_service)
public class ServiceActivity extends BaseActivity {
    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    @ViewInject(R.id.webview)
    WebView webview;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBar(true);
        init();

    }

    private void init() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        setWebview();

    }

    private void setWebview() {
        WebSettings webSettings = webview.getSettings();
        //支持缩放，默认为true。
        webSettings.setSupportZoom(true);
        //调整图片至适合webview的大小
        webSettings.setUseWideViewPort(true);
        // 缩放至屏幕的大小
        webSettings.setLoadWithOverviewMode(true);
        //设置默认编码
        webSettings.setDefaultTextEncodingName("utf-8");
        ////设置自动加载图片
        webSettings.setLoadsImagesAutomatically(true);
//        webview.loadData(xieyi, "text/html; charset=UTF-8", null);
        webview.setWebChromeClient(new WebChromeClient());
        webview.setWebViewClient(new WebViewClient());
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl(BaseURL.BASE_URL+"appcommonLegalAgreement/getLegalAgreementHtml/APPLegalAgreement");
    }

    @Override
    protected void onDestroy() {
        if (webview!= null) {
            webview.loadUrl(null);
            webview.clearHistory();
            ((ViewGroup) webview.getParent()).removeView(webview);
            webview.destroy();
            webview= null;
        }
        super.onDestroy();
    }
}
