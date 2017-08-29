package com.jyh.com.jyh.Activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jyh.com.jyh.Base.BaseActivity;
import com.jyh.com.jyh.R;
import com.jyh.com.jyh.Utils.ImmersiveUtils;
import com.jyh.com.jyh.Utils.ToatUtils;

/**
 * Created by vvguoliang on 2017/8/29.
 * 芝麻认证
 */

public class SesameActivity extends BaseActivity implements View.OnClickListener {

    private EditText sesame_name;
    private EditText sesame_id;
    private Button sesame_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.act_sesame );
        //沉浸式状态设置
        if (ImmersiveUtils.BuildVERSION()) {
            ImmersiveUtils.getInstance().getW_add_B( this );
        }
        findViewById();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loan_title_image:
                finish();
                break;
            case R.id.sesame_button:
                sesame_button.setText( SesameActivity.this.getString( R.string.loan_name_loan_sesame_ing ) );
                sesame_button.setBackgroundResource( R.drawable.button_background_gray_color );
                try {
                    Thread.sleep( 2 * 1000 );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sesame_button.setText( SesameActivity.this.getString( R.string.loan_name_loan_sesame_authentication ) );
                sesame_button.setBackgroundResource( R.drawable.button_background_color );
                ToatUtils.showShort1( SesameActivity.this, getString( R.string.loan_name_loan_sesame_no ) );
                break;
        }

    }

    @Override
    protected void findViewById() {
        TextView loan_title = findViewById( R.id.loan_title );
        loan_title.setText( this.getString( R.string.loan_name_loan_sesame ) );
        findViewById( R.id.loan_title_image ).setOnClickListener( this );
        findViewById( R.id.loan_title_image ).setVisibility( View.VISIBLE );

        sesame_name = findViewById( R.id.sesame_name );
        sesame_id = findViewById( R.id.sesame_id );
        sesame_button = findViewById( R.id.sesame_button );
        sesame_button.setOnClickListener( this );

        sesame_name.addTextChangedListener( getTextwatcher( 1 ) );
        sesame_id.addTextChangedListener( getTextwatcher( 2 ) );
    }

    @Override
    protected void initView() {

    }

    private TextWatcher getTextwatcher(final int n_id) {

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() > 0) {
                    switch (n_id) {
                        case 1:
                            if (sesame_id.length() > 0) {
                                sesame_button.setBackgroundResource( R.drawable.button_background_color );
                            } else {
                                sesame_button.setBackgroundResource( R.drawable.button_background_gray_color );
                            }
                            break;
                        case 2:
                            if (sesame_name.length() > 0) {
                                sesame_button.setBackgroundResource( R.drawable.button_background_color );
                            } else {
                                sesame_button.setBackgroundResource( R.drawable.button_background_gray_color );
                            }
                            break;
                        default:
                            sesame_button.setBackgroundResource( R.drawable.button_background_gray_color );
                            break;
                    }
                } else {
                    sesame_button.setBackgroundResource( R.drawable.button_background_gray_color );
                }
            }
        };
        return textWatcher;
    }
}
