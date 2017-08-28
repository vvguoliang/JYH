package com.jyh.com.jyh.View;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by vvguoliang on 2017/8/22.
 * 自定义 TextView
 */

@SuppressLint("AppCompatCustomView")
public class MyTextview extends TextView{

    public MyTextview(Context context) {
        super( context );
        initTextView(context);
    }

    public MyTextview(Context context, @Nullable AttributeSet attrs) {
        super( context, attrs );
        initTextView(context);
    }

    public MyTextview(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super( context, attrs, defStyleAttr );
        initTextView(context);
    }

    private void initTextView(Context mContext) {
//        this.setTypeface(SXSApplication.typeface);

    }
}
