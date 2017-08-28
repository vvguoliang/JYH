package com.jyh.com.jyh.View;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by vvguoliang on 2017/8/25.
 */

@SuppressLint("AppCompatCustomView")
public class MyEditText extends EditText {

    public MyEditText(Context context) {
        super( context );
        initTextView(context);
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super( context, attrs );
        initTextView(context);
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super( context, attrs, defStyleAttr );
        initTextView(context);
    }
    private void initTextView(Context mContext) {
//        this.setTypeface(SXSApplication.typeface);

    }
}
