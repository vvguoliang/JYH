package com.jyh.com.jyh.Activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.jyh.com.jyh.Base.BaseActivity;
import com.jyh.com.jyh.R;

/**
 * Created by vvguoliang on 2017/8/25.
 * 个人信息
 */

public class PersonalDataInformation extends BaseActivity implements View.OnClickListener {

    private ImageView information_info_image;
    private ImageView information_work_image;
    private ImageView information_contacts_image;

    private LinearLayout information_info_lin;
    private ScrollView information_work_scr;
    private LinearLayout information_work_urgent;

    //    个人信息
    private EditText information_info_edit;
    private ImageView information_info_imge;
    private TextView information_education_text;
    private LinearLayout information_education_lin;
    private TextView information_province_text;
    private LinearLayout information_province_lin;
    private TextView information_city_text;
    private LinearLayout information_city_lin;
    private EditText information_detailed_text;
    private ImageView information_detailed_imge;
    private TextView information_marriage_text;
    private LinearLayout information_marriage_lin;

    //    工作信息
    private EditText information_work_company_edit;
    private ImageView information_work_company_image;
    private TextView information_work_company_province_text;
    private LinearLayout information_work_province_lin;
    private TextView information_work_city_text;
    private LinearLayout information_work_city_lin;
    private EditText information_work_detailed_text;
    private ImageView information_detailed_image;
    private TextView information_work_occupation_text;
    private LinearLayout information_work_occupation_lin;
    private TextView information_income_text;
    private LinearLayout information_income_lin;
    private TextView information_new_text;
    private LinearLayout information_new_lin;
    private EditText information_phone_edit;
    private ImageView information_phone_image;

    //    紧急联系人
    private TextView information_urgent_relatives;
    private LinearLayout information_urgent_relatives_line;
    private EditText information_urgent_name_edit;
    private ImageView information_urgent_name_image;
    private EditText information_urgent_phone_edit;
    private ImageView information_urgent_phone_image;
    private TextView information_urgent_colleague_text;
    private LinearLayout information_urgent_colleague_line;
    private EditText information_urgent_name_colleague;
    private ImageView information_urgent_name_colleague_image;
    private EditText information_urgent_phone_colleague;
    private ImageView information_urgent_phone_colleague_image;

    private void getFindViewById() {
        information_info_edit = findViewById( R.id.information_info_edit );
        information_info_imge = findViewById( R.id.information_info_imge );
        information_education_text = findViewById( R.id.information_education_text );
        information_education_lin = findViewById( R.id.information_education_lin );
        information_province_text = findViewById( R.id.information_province_text );
        information_province_lin = findViewById( R.id.information_province_lin );
        information_city_text = findViewById( R.id.information_city_text );
        information_city_lin = findViewById( R.id.information_city_lin );
        information_detailed_text = findViewById( R.id.information_detailed_text );
        information_detailed_imge = findViewById( R.id.information_detailed_imge );
        information_marriage_text = findViewById( R.id.information_marriage_text );
        information_marriage_lin = findViewById( R.id.information_marriage_lin );

        information_info_imge.setOnClickListener( this );
        information_detailed_imge.setOnClickListener( this );
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.act_personal_data_information );
        findViewById();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loan_title_image:
                finish();
                break;
            case R.id.information_info_text:
                getVisible( 0 );
                break;
            case R.id.information_work_text:
                getVisible( 1 );
                break;
            case R.id.information_contacts_text:
                getVisible( 2 );
                break;
            case R.id.information_info_imge:
                information_info_edit.setText( "" );
                break;
            case R.id.information_detailed_imge:
                information_detailed_text.setText( "" );
                break;
        }

    }

    @Override
    protected void findViewById() {
        TextView loan_title = findViewById( R.id.loan_title );
        loan_title.setText( this.getString( R.string.loan_name_loan_personal_information_info ) );
        findViewById( R.id.loan_title_image ).setOnClickListener( this );
        findViewById( R.id.loan_title_image ).setVisibility( View.VISIBLE );

        findViewById( R.id.information_info_text ).setOnClickListener( this );
        findViewById( R.id.information_work_text ).setOnClickListener( this );
        findViewById( R.id.information_contacts_text ).setOnClickListener( this );
        getFindViewById();
        initView();
    }

    @Override
    protected void initView() {
        getVisible( 0 );

    }

    /**
     * 导航栏变化
     *
     * @param gone
     */
    private void getVisible(int gone) {
        switch (gone) {
            case 0:
                findViewById( R.id.information_info_lin ).setVisibility( View.VISIBLE );
                findViewById( R.id.information_work_scr ).setVisibility( View.GONE );
                findViewById( R.id.information_work_urgent ).setVisibility( View.GONE );

                findViewById( R.id.information_info_image ).setVisibility( View.VISIBLE );
                findViewById( R.id.information_work_image ).setVisibility( View.INVISIBLE );
                findViewById( R.id.information_contacts_image ).setVisibility( View.INVISIBLE );
                break;
            case 1:
                findViewById( R.id.information_info_lin ).setVisibility( View.GONE );
                findViewById( R.id.information_work_scr ).setVisibility( View.VISIBLE );
                findViewById( R.id.information_work_urgent ).setVisibility( View.GONE );

                findViewById( R.id.information_info_image ).setVisibility( View.INVISIBLE );
                findViewById( R.id.information_work_image ).setVisibility( View.VISIBLE );
                findViewById( R.id.information_contacts_image ).setVisibility( View.INVISIBLE );
                break;
            case 2:
                findViewById( R.id.information_info_lin ).setVisibility( View.GONE );
                findViewById( R.id.information_work_scr ).setVisibility( View.GONE );
                findViewById( R.id.information_work_urgent ).setVisibility( View.VISIBLE );

                findViewById( R.id.information_info_image ).setVisibility( View.INVISIBLE );
                findViewById( R.id.information_work_image ).setVisibility( View.INVISIBLE );
                findViewById( R.id.information_contacts_image ).setVisibility( View.VISIBLE );
                break;
        }
    }

    private TextWatcher getTextw(final View view) {

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
                    view.setVisibility( View.VISIBLE );
                } else {
                    view.setVisibility( View.GONE );
                }
            }
        };
        return textWatcher;
    }
}
