package com.jyh.com.jyh.Webview;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jyh.com.jyh.Base.BaseActivity;
import com.jyh.com.jyh.R;
import com.jyh.com.jyh.Utils.ImmersiveUtils;

/**
 * Created by vvguoliang on 2017/7/3.
 * <p>
 * 内部浏览器显示页面
 */

public class LoanWebViewActivity extends BaseActivity implements View.OnClickListener {

    private WebView webview;

    private String url = "";

    private ProgressBar banner_progressBar;

    private TextView title_view;

    private LinearLayout banner_linear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.act_webview );
        findViewById();
        //沉浸式状态设置
        if (ImmersiveUtils.BuildVERSION()) {
            ImmersiveUtils.getInstance().getW_add_B( this );
        }
        initView();
    }

    @Override
    protected void findViewById() {
        url = getIntent().getExtras().getString( "url" );
        findViewById( R.id.loan_title_image ).setVisibility( View.VISIBLE );
        findViewById( R.id.loan_title_image ).setOnClickListener( this );
        title_view = findViewById( R.id.loan_title );
        webview = findViewById( R.id.banner_webview );

        banner_progressBar = findViewById( R.id.banner_progressBar );

        banner_linear = findViewById( R.id.banner_linear );
        getSettings();

    }

    @Override
    protected void initView() {
        webview.setWebViewClient( webViewClient );
        webview.setWebChromeClient( webChromeClient );
        if (!TextUtils.isEmpty( url ) && !"null".equals( url )) {
            banner_linear.setVisibility( View.VISIBLE );
            webview.loadUrl( url );
        } else {
            title_view.setText( "" );
            banner_linear.setVisibility( View.GONE );
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loan_title_image:
                finish();
                break;
        }
    }

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    private void getSettings() {
        webview.setLayerType( View.LAYER_TYPE_SOFTWARE, null ); //渲染加速器
        webview.getSettings().setRenderPriority( WebSettings.RenderPriority.HIGH ); //提高渲染的优先级
        webview.removeJavascriptInterface( "searchBoxJavaBridge_" ); //防止360
        WebSettings settings = webview.getSettings();

        settings.setBlockNetworkImage( true );
        settings.setUseWideViewPort( true );
        settings.setLoadWithOverviewMode( true );
        settings.setJavaScriptEnabled( true );
        settings.setJavaScriptCanOpenWindowsAutomatically( true );
        settings.setSaveFormData( false );
        settings.setDomStorageEnabled( true );
        settings.setAllowContentAccess( true );
        settings.setAllowFileAccess( true );
        settings.setDefaultTextEncodingName( "utf-8" );
        settings.setRenderPriority( WebSettings.RenderPriority.HIGH );
        settings.setCacheMode( WebSettings.LOAD_NO_CACHE ); //LOAD_NO_CACHE设置,缓存模式LOAD_DEFAULT

        if (Build.VERSION.SDK_INT >= 19) {
            settings.setLoadsImagesAutomatically( true );
        } else {
            settings.setLoadsImagesAutomatically( false );
        }
        settings.setDatabaseEnabled( true );
        settings.setLayoutAlgorithm( WebSettings.LayoutAlgorithm.SINGLE_COLUMN );
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode( WebSettings.MIXED_CONTENT_ALWAYS_ALLOW );
        }
        webview.getSettings().setBlockNetworkImage( false );
    }

    private WebViewClient webViewClient = new WebViewClient() {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl( url );
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished( view, url );
            if (TextUtils.isEmpty( view.getTitle() ) || view.getTitle().contains( "404" ) || view.getTitle().contains( "找不到" ) ||
                    view.getTitle().contains( "about:" )) {
                title_view.setText( "" );
                banner_linear.setVisibility( View.GONE );
            } else {
                title_view.setText( view.getTitle() );
            }
        }
    };


    private WebChromeClient webChromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged( view, newProgress );
            banner_progressBar.setProgress( newProgress );
            if (newProgress == 100) {
                banner_progressBar.setVisibility( View.GONE );
            } else {
                banner_progressBar.setVisibility( View.VISIBLE );
            }
        }
    };
}
