package com.jyh.com.jyh.Adapter;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;
import java.util.Map;

import com.jyh.com.jyh.R;
import com.jyh.com.jyh.Webview.LoanWebViewActivity;

/**
 * vvguolaing 2017-6-24
 * Viewpage 适配器
 */
@SuppressWarnings("deprecation")
public class BannerLoopAdapter extends PagerAdapter implements View.OnClickListener {

    private List<ImageView> list = null;

    private Context context;

    private List<Map<String, String>> mapList;

    private int position = 0;

    public BannerLoopAdapter(Context context, List<ImageView> _list, List<Map<String, String>> mapList) {
        this.list = _list;
        this.context = context;
        this.mapList = mapList;
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        //    ((ViewPager) container).removeView((View)object);

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //对ViewPager页号求模取出View列表中要显示的项
        position %= list.size();
        this.position = position;
        if (position < 0) {
            position = list.size() + position;
        }
        final ImageView view = list.get( position );
        //如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException。
        ViewParent vp = view.getParent();
        if (vp != null) {
            ViewGroup parent = (ViewGroup) vp;
            parent.removeView( view );
        }
        Glide.with( context )
                .load( mapList.get( position ).get( "path" ) )
                .listener( new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object o, Target<Drawable> target, boolean b) {
                        view.setImageResource( R.mipmap.ic_loan_banner_default );
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable drawable, Object o, Target<Drawable> target, DataSource dataSource, boolean b) {
                        return false;
                    }
                } )
                .into( view );
        view.setScaleType( ImageView.ScaleType.FIT_XY );
        view.setOnClickListener( this );
        container.addView( view );
        return view;
    }


    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return (arg0 == arg1);
    }

    @Override
    public void onClick(View v) {
        if (mapList != null && !TextUtils.isEmpty( mapList.get( position ).get( "url" ) )) {
            Intent intent = new Intent( context, LoanWebViewActivity.class );
            intent.putExtra( "url", mapList.get( position ).get( "url" ) );
            context.startActivity( intent );
        }
    }
}


