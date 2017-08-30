package com.jyh.com.jyh.Fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.jyh.com.jyh.Adapter.BannerLoopAdapter;
import com.jyh.com.jyh.Base.BaseFragment;
import com.jyh.com.jyh.R;
import com.jyh.com.jyh.Utils.AlgorithmUtils;
import com.jyh.com.jyh.Utils.AppUtil;
import com.jyh.com.jyh.Utils.DisplayUtils;
import com.jyh.com.jyh.View.DialogUtils;

/**
 * Created by vvguoliang on 2017/6/23.
 * <p>
 * 借款
 */
@SuppressWarnings({"deprecation", "StatementWithEmptyBody"})
@SuppressLint({"ValidFragment", "InflateParams", "ResourceType"})
public class LoanFragment extends BaseFragment implements View.OnClickListener {

    private Activity mActivity;

    public LoanFragment() {
        super();
    }

    public LoanFragment(Activity activity) {
        super( activity );
        this.mActivity = activity;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach( context );
        this.mActivity = (Activity) context;
    }

    //图片地址集合( 项目中一般是对于的HTTP地址 )
    private List<Map<String, String>> mImageUrl = null;
    //banner中图片的集合
    private List<ImageView> mBannerImageViews = new ArrayList<>();
    //banner上点点的集合
    private List<ImageView> mBannerDots = new ArrayList<>();

    private ViewPager loan_viewpage;
    private LinearLayout loan_frame;

    @Override
    protected int getLayout() {
        return R.layout.far_loan;
    }

    @Override
    protected void initView() {
        TextView loan_title = (TextView) findViewById( R.id.loan_title );
        loan_title.setText( R.string.name_loan );
        loan_viewpage = (ViewPager) findViewById( R.id.loan_viewpage );
        loan_viewpage.setOnPageChangeListener( new NavigationPageChangeListener() );
        loan_frame = (LinearLayout) findViewById( R.id.loan_frame );

        findViewById( R.id.loan_button ).setOnClickListener( this );

        String key = "123456789";
        String data = "android";
        try {
            String encode = new String( Base64.encode( data.getBytes(), Base64.DEFAULT ) );
            String alog = AlgorithmUtils.encrypt( key, encode );
            Log.e( "", "=======alog======" + alog + "===encode====" + encode );

            String alog1 = AlgorithmUtils.decrypt( key, alog );
            String decode = new String( Base64.decode( alog1.getBytes(), Base64.DEFAULT ) );
            Log.e( "", "=======alog1=====" + alog1 + "====decode===" + decode );
        } catch (Exception e) {
            e.printStackTrace();
        }
        mImageUrl = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put( "path", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1503566344199&di=68c8d77046bbcc93ca21f22a75e05d8d&imgtype=0&src=http%3A%2F%2Fpic0.qiyipic.com%2Fcommon%2F20131008%2F2bb8ad6b87f24ed2b41d316acdd4606a.jpg%3Fsrc%3Dfocustat_4_20130527_3" );
        map.put( "url", "https://www.baidu.com" );
        mImageUrl.add( map );
        Map<String, String> map1 = new HashMap<>();
        map1.put( "path", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1503566344199&di=68c8d77046bbcc93ca21f22a75e05d8d&imgtype=0&src=http%3A%2F%2Fpic0.qiyipic.com%2Fcommon%2F20131008%2F2bb8ad6b87f24ed2b41d316acdd4606a.jpg%3Fsrc%3Dfocustat_4_20130527_3" );
        map1.put( "url", "https://www.baidu.com" );
        mImageUrl.add( map1 );
        Map<String, String> map2 = new HashMap<>();
        map2.put( "path", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1503566344199&di=68c8d77046bbcc93ca21f22a75e05d8d&imgtype=0&src=http%3A%2F%2Fpic0.qiyipic.com%2Fcommon%2F20131008%2F2bb8ad6b87f24ed2b41d316acdd4606a.jpg%3Fsrc%3Dfocustat_4_20130527_3" );
        map2.put( "url", "https://www.baidu.com" );
        mImageUrl.add( map2 );
        refreshBanner();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loan_button:
                DialogUtils.getInstance().getDialogPrompt( mActivity, "温馨提示",
                        "您当前还未完善个人资料\n完善后即可申请借款", "完善资料");
                break;
        }
    }


    // -------------------------------------------------------------------------
    // Banner相关
    // -------------------------------------------------------------------------
    private void refreshBanner() {

        mBannerImageViews.clear();
        mBannerDots.clear();
        loan_frame.removeAllViews();
        for (int i = 0; mImageUrl.size() > i; i++) {
            ImageView iv = new ImageView( mActivity );
            iv.setLayoutParams( new ViewGroup.LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT ) );
            iv.setScaleType( ImageView.ScaleType.CENTER_CROP );
            mBannerImageViews.add( iv );
        }

        //循环添加 点
        for (int i = 0; i < mImageUrl.size(); i++) {
            ImageView view = new ImageView( mActivity );
            int w = DisplayUtils.dip2px( mActivity, 10 );
            int margin = DisplayUtils.dip2px( mActivity, 8 );
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams( w, w );
            layoutParams.setMargins( margin, 0, 0, 0 );
            view.setLayoutParams( layoutParams );
            view.setImageResource( R.mipmap.dot_grey );
            if (i == getCurrentItem()) {
                view.setImageResource( R.mipmap.dot_white );
            }
            view.setScaleType( ImageView.ScaleType.MATRIX );
            mBannerDots.add( view );
            loan_frame.addView( view );
        }

        // 如果这样设置会一页一页的滑动过去 直接就ANR了!!!
        //banner.setCurrentItem(Integer.MAX_VALUE/2);

        //通过反射修改内部索引位置
        Class bannerClass = loan_viewpage.getClass();
        try {
            Field field = bannerClass.getDeclaredField( "mCurItem" );
            try {
                field.setAccessible( true );
                field.setInt( loan_viewpage, Integer.MAX_VALUE / 2 );
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        currentItem = Integer.MAX_VALUE / 2;
        loan_viewpage.setAdapter( new BannerLoopAdapter( mActivity, mBannerImageViews, mImageUrl ) );
        pollBanner();
    }

    private int getCurrentItem() {
        return currentItem % mBannerImageViews.size();
    }

    //-------------------------------------
    // banner 自动翻页的相关代码, 不需要删除即可
    //-------------------------------------
    private Timer bannerTimer;
    private TimerTask bannerTask;
    private int currentItem;

    private void pollBanner() {
        if (bannerTimer == null || bannerTask == null) {
            bannerTimer = new Timer();
            bannerTask = new TimerTask() {
                @Override
                public void run() {
                    currentItem = currentItem + 1;
                    bannerHandler.obtainMessage().sendToTarget();
                }

            };
            bannerTimer.schedule( bannerTask, AppUtil.getInstance().TIME );// 3秒钟自动翻页一次
        }
    }

    @SuppressLint("HandlerLeak")
    private Handler bannerHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            loan_viewpage.setCurrentItem( currentItem );
        }
    };

    class NavigationPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int position) {
            currentItem = position;
            position = position % mBannerImageViews.size();
            for (int i = 0; i < mBannerImageViews.size(); i++) {
                mBannerDots.get( i ).setImageResource( R.mipmap.dot_grey );
                if (position == i) {
                    mBannerDots.get( position ).setImageResource( R.mipmap.dot_white );
                }
            }
            //页面变动的时候重新设置定时器3秒钟后翻页  ( 防止手动滑动的时候定时器还在滑动 )
            if (bannerTimer != null && bannerTask != null) {
                bannerTask.cancel();
                bannerTask = new TimerTask() {
                    @Override
                    public void run() {
                        currentItem = currentItem + 1;
                        bannerHandler.obtainMessage().sendToTarget();
                    }
                };
                bannerTimer.schedule( bannerTask, AppUtil.getInstance().TIME );
            }
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (bannerTimer != null) {
            bannerTimer.cancel();
            bannerTimer = null;
        }
        if (bannerTask != null) {
            bannerTask.cancel();
            bannerTask = null;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        pollBanner();
    }

}
