package com.jyh.com.jyh.View;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by vvguoliang on 2017/8/24.
 * 自定义 button
 */

@SuppressLint("AppCompatCustomView")
public class MyButton extends Button {

    public MyButton(Context context) {
        super( context );
        initTextView( context );
    }

    public MyButton(Context context, AttributeSet attrs) {
        super( context, attrs );
        initTextView( context );
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super( context, attrs, defStyleAttr );
        initTextView( context );
    }

    private void initTextView(Context mContext) {
//        this.setTypeface(SXSApplication.typeface);

    }

}
