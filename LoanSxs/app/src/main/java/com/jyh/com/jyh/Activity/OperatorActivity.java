package com.jyh.com.jyh.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jyh.com.jyh.Base.BaseActivity;
import com.jyh.com.jyh.Http.DataCallBack;
import com.jyh.com.jyh.Http.OkHttpManager;
import com.jyh.com.jyh.R;
import com.jyh.com.jyh.Utils.ImmersiveUtils;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Request;

/**
 * Created by vvguoliang on 2017/8/29.
 * <p>
 * 运营商
 */

@SuppressWarnings("MalformedRegex")
public class OperatorActivity extends BaseActivity implements View.OnClickListener, DataCallBack {

    private EditText operator_phone_text;
    private EditText operator_service_text;
    private Button operator_button;
    private ImageView operator_image;
    private TextView operator_text;
    private TextView operator_text_text;
    private String loan_operator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.act_operator );
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
            case R.id.operator_button:
                OkHttpManager.getAsync( "https://tcc.taobao.com/cc/json/mobile_tel_segment.htm?tel=" +
                        operator_phone_text.getText().toString(), "", this );
                break;
            case R.id.operator_text_text:
                break;
        }

    }

    @Override
    protected void findViewById() {
        TextView loan_title = findViewById( R.id.loan_title );
        loan_title.setText( this.getString( R.string.loan_name_loan_operator_phone_verification ) );
        findViewById( R.id.loan_title_image ).setOnClickListener( this );
        findViewById( R.id.loan_title_image ).setVisibility( View.VISIBLE );

        operator_text_text = findViewById( R.id.operator_text_text );

        operator_image = findViewById( R.id.operator_image );
        operator_text = findViewById( R.id.operator_text );
        operator_phone_text = findViewById( R.id.operator_phone_text );
        operator_service_text = findViewById( R.id.operator_service_text );
        operator_button = findViewById( R.id.operator_button );
        operator_button.setOnClickListener( this );

        operator_service_text.addTextChangedListener( getTextwatcher() );
        operator_text_text.setOnClickListener( this );
    }

    @Override
    protected void initView() {

    }

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
                    operator_button.setBackgroundResource( R.drawable.button_background_color );
                } else {
                    operator_button.setBackgroundResource( R.drawable.button_background_gray_color );
                }
            }
        };
        return textWatcher;
    }

    @Override
    public void requestFailure(Request request, String name, IOException e) {

    }

    @Override
    public void requestSuccess(String result, String name) throws Exception {
        String[] results = result.split( "\\{" );
        JSONObject object = new JSONObject( "{" + results[1] );
        switch (object.optString( "catName" )) {
            case "中国移动":
                operator_image.setImageResource( R.mipmap.ic_operator_cmcc );
                operator_text.setText( object.optString( "catName" ) );
                loan_operator = this.getString( R.string.loan_name_loan_operator_cmcc_text );
                break;
            case "中国联通":
                operator_image.setImageResource( R.mipmap.ic_operator_unicom );
                operator_text.setText( object.optString( "catName" ) );
                loan_operator = this.getString( R.string.loan_name_loan_operator_unicom_text );
                break;
            case "中国电信":
                operator_image.setImageResource( R.mipmap.ic_operator_telecom );
                operator_text.setText( object.optString( "catName" ) );
                loan_operator = this.getString( R.string.loan_name_loan_operator_telecom_text );
                break;
        }
        SpannableStringBuilder builder = new SpannableStringBuilder( loan_operator );
        ForegroundColorSpan redSpan = new ForegroundColorSpan( Color.parseColor( "#4AA0F1" ) );
        builder.setSpan( redSpan, loan_operator.length() - 12, loan_operator.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
        operator_text_text.setText( builder );
    }
}
