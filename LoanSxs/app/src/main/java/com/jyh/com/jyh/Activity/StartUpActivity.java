package com.jyh.com.jyh.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.jyh.com.jyh.Base.BaseActivity;
import com.jyh.com.jyh.R;

/**
 * Created by vvguoliang on 2017/9/4.
 * 启动页面
 */

public class StartUpActivity extends BaseActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.act_start_up );

        new Handler().postDelayed( new Runnable() {
            @Override
            public void run() {
                startActivity( new Intent( StartUpActivity.this, MainActivity.class ) );
                finish();
            }
        }, 3 * 1000 );


    }

    @Override
    public void onClick(View view) {

    }

    @Override
    protected void findViewById() {

    }

    @Override
    protected void initView() {

    }
}
