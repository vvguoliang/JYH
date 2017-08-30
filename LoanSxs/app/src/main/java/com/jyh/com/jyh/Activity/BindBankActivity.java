package com.jyh.com.jyh.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jyh.com.jyh.Base.BaseActivity;
import com.jyh.com.jyh.R;
import com.jyh.com.jyh.Utils.AppUtil;
import com.jyh.com.jyh.Utils.ImmersiveUtils;
import com.jyh.com.jyh.View.DialogUtils;

/**
 * Created by vvguoliang on 2017/8/30.
 * 绑定银行卡
 */

public class BindBankActivity extends BaseActivity implements View.OnClickListener {

    private EditText bind_bank_personal;
    private ImageView bind_bank_personal_image;
    private EditText bind_bank_id;
    private ImageView bind_bank_id_image;
    private TextView bind_bank_opening_text;
    private EditText bind_bank_opening_number;
    private ImageView bind_bank_opening_number_image;
    private TextView bind_bank_opening_phone_number;
    private Button bind_bank_opening_phone_button;

    private String[] opening = new String[]{"中国银行", "中国建设银行", "中国农业银行", "中国工商银行", "招商银行", "中国民生银行", "中国光大银行", "广东发展银行", "华夏银行", "上海浦东发展银行", "兴业银行", "中信银行", "中信银行", "平安银行"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.act_bind_bank );
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
            case R.id.bind_bank_personal_image:
                bind_bank_personal.setText( "" );
                break;
            case R.id.bind_bank_id_image:
                bind_bank_id.setText( "" );
                break;
            case R.id.bind_bank_opening_number_image:
                bind_bank_opening_number.setText( "" );
                break;
            case R.id.bind_bank_opening_phone_button:
                break;
            case R.id.bind_bank_opening_line:
                DialogUtils.getInstance().showDialog( BindBankActivity.this, mHandler, 1, "opening",
                        bind_bank_opening_text.getText().toString(), AppUtil.getInstance().getList( opening ) );
                break;
        }

    }

    @Override
    protected void findViewById() {
        TextView loan_title = findViewById( R.id.loan_title );
        loan_title.setText( this.getString( R.string.loan_name_loan_bank ) );
        findViewById( R.id.loan_title_image ).setOnClickListener( this );
        findViewById( R.id.loan_title_image ).setVisibility( View.VISIBLE );

        bind_bank_personal = findViewById( R.id.bind_bank_personal );
        bind_bank_personal_image = findViewById( R.id.bind_bank_personal_image );
        bind_bank_id = findViewById( R.id.bind_bank_id );
        bind_bank_id_image = findViewById( R.id.bind_bank_id_image );
        bind_bank_opening_text = findViewById( R.id.bind_bank_opening_text );
        bind_bank_opening_number = findViewById( R.id.bind_bank_opening_number );
        bind_bank_opening_number_image = findViewById( R.id.bind_bank_opening_number_image );
        bind_bank_opening_phone_number = findViewById( R.id.bind_bank_opening_phone_number );
        bind_bank_opening_phone_button = findViewById( R.id.bind_bank_opening_phone_button );

        initView();
    }

    @Override
    protected void initView() {
        bind_bank_personal.addTextChangedListener( AppUtil.getInstance().getTextw( bind_bank_personal_image ) );
        bind_bank_id.addTextChangedListener( AppUtil.getInstance().getTextw( bind_bank_id_image ) );
        bind_bank_opening_number.addTextChangedListener( AppUtil.getInstance().getTextw( bind_bank_opening_number_image ) );

        bind_bank_personal_image.setOnClickListener( this );
        bind_bank_id_image.setOnClickListener( this );
        bind_bank_opening_number_image.setOnClickListener( this );
        bind_bank_opening_phone_button.setOnClickListener( this );

        findViewById( R.id.bind_bank_opening_line ).setOnClickListener( this );

        bind_bank_opening_number.addTextChangedListener( getTextwatcher() );
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage( msg );
            switch (msg.what) {
                case 1:
                    bind_bank_opening_text.setText( msg.obj.toString() );
                    if (bind_bank_opening_number.getText().length() > 0) {
                        bind_bank_opening_phone_button.setBackgroundResource( R.drawable.button_background_color );
                    } else {
                        bind_bank_opening_phone_button.setBackgroundResource( R.drawable.button_background_gray_color );
                    }
                    break;
            }
        }
    };

    private TextWatcher getTextwatcher() {

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
                    if (bind_bank_opening_text.getText().length() > 0) {
                        bind_bank_opening_phone_button.setBackgroundResource( R.drawable.button_background_color );
                    } else {
                        bind_bank_opening_phone_button.setBackgroundResource( R.drawable.button_background_gray_color );
                    }
                } else {
                    bind_bank_opening_phone_button.setBackgroundResource( R.drawable.button_background_gray_color );
                }
            }
        };
        return textWatcher;
    }

}
