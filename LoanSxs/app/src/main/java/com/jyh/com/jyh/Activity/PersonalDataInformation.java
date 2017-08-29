package com.jyh.com.jyh.Activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.jyh.com.jyh.Utils.ImmersiveUtils;
import com.jyh.com.jyh.View.DialogUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vvguoliang on 2017/8/25.
 * 个人信息
 */

public class PersonalDataInformation extends BaseActivity implements View.OnClickListener {

    //    个人信息
    private EditText information_info_edit;
    private ImageView information_info_imge;
    private TextView information_education_text;
    private TextView information_province_text;
    private TextView information_city_text;
    private EditText information_detailed_text;
    private ImageView information_detailed_imge;
    private TextView information_marriage_text;

    //    工作信息
    private EditText information_work_company_edit;
    private ImageView information_work_company_image;
    private TextView information_work_company_province_text;
    private TextView information_work_city_text;
    private EditText information_work_detailed_text;
    private ImageView information_detailed_image;
    private TextView information_work_occupation_text;
    private TextView information_income_text;
    private TextView information_new_text;
    private EditText information_phone_edit;
    private ImageView information_phone_image;

    //    紧急联系人
    private TextView information_urgent_relatives;
    private EditText information_urgent_name_edit;
    private ImageView information_urgent_name_image;
    private EditText information_urgent_phone_edit;
    private TextView information_urgent_colleague_text;
    private EditText information_urgent_name_colleague;
    private ImageView information_urgent_name_colleague_image;
    private EditText information_urgent_phone_colleague;

    private String[] education = new String[]{"小学", "初中", "高中/高职/技校", "大专", "本科", "硕士", "博士"};

    private String[] province = new String[]{"北京市", "天津市", "河北省", "山西省", "内蒙古", "辽宁省", "黑龙江省", "上海", "江苏省",
            "浙江省", "安微省", "福建省", "江西省", "山东省", "河南省", "湖北省", "湖南省", "广东省", "广西省", "海南省", "重庆省",
            "四川省", "贵州省", "云南省", "西藏省", "陕西省", "甘肃省", "青海省", "宁夏省", "新疆省"};
    private String[] marriage = new String[]{"未婚", "已婚", "离异", "丧偶"};
    private String[] occupation = new String[]{"销售", "维修工程", "服务员", "营业员", "司机", "工程师", "厨师", "文员", "理发师", "教练", "来购员",
            "客服", "会计", "教师", "快递员", "医生", "网店店长", "律师", "翻译", "编辑", "自由职业", "其他"};
    private String[] income = new String[]{"小于1000元", "1000～2000元", "2000～4000元", "4000～6000元", "6000～10000元", "10000以上"};
    private String[] relatives = new String[]{"父母", "配偶", "兄弟姐妹"};
    private String[] colleague = new String[]{"同学", "同事", "朋友"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.act_personal_data_information );
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
            case R.id.information_education_lin:
                DialogUtils.getInstance().showDialog( PersonalDataInformation.this, mHandler, 1, "education",
                        information_education_text.getText().toString(), getList( education ) );
                break;
            case R.id.information_province_lin:
                DialogUtils.getInstance().showDialog( PersonalDataInformation.this, mHandler, 2, "province",
                        information_province_text.getText().toString(), getList( province ) );
                break;
            case R.id.information_city_lin:
                DialogUtils.getInstance().showDialog( PersonalDataInformation.this, mHandler, 3, "city",
                        information_city_text.getText().toString(), getList( province ) );
                break;
            case R.id.information_marriage_lin:
                DialogUtils.getInstance().showDialog( PersonalDataInformation.this, mHandler, 4, "marriage",
                        information_marriage_text.getText().toString(), getList( marriage ) );
                break;
            case R.id.information_work_company_image:
                information_work_company_edit.setText( "" );
                break;
            case R.id.information_detailed_image:
                information_work_detailed_text.setText( "" );
                break;
            case R.id.information_phone_image:
                information_phone_edit.setText( "" );
                break;
            case R.id.information_work_province_lin:
                DialogUtils.getInstance().showDialog( PersonalDataInformation.this, mHandler, 5, "work_province",
                        information_work_company_province_text.getText().toString(), getList( province ) );
                break;
            case R.id.information_work_city_lin:
                DialogUtils.getInstance().showDialog( PersonalDataInformation.this, mHandler, 6, "work_city",
                        information_work_city_text.getText().toString(), getList( province ) );
                break;
            case R.id.information_work_occupation_lin:
                DialogUtils.getInstance().showDialog( PersonalDataInformation.this, mHandler, 7, "work_occupation",
                        information_work_occupation_text.getText().toString(), getList( occupation ) );
                break;
            case R.id.information_income_lin:
                DialogUtils.getInstance().showDialog( PersonalDataInformation.this, mHandler, 8, "income",
                        information_income_text.getText().toString(), getList( income ) );
                break;
            case R.id.information_new_lin:
                DialogUtils.getInstance().showDialog( PersonalDataInformation.this, mHandler, 9, "new",
                        information_new_text.getText().toString(), getList( income ) );
                break;
            case R.id.information_urgent_name_image:
                information_urgent_name_edit.setText( "" );
                break;
            case R.id.information_urgent_phone_image://通讯录

                break;
            case R.id.information_urgent_name_colleague_image:
                information_urgent_name_colleague.setText( "" );
                break;
            case R.id.information_urgent_phone_colleague_image:

                break;
            case R.id.information_urgent_relatives_line:
                DialogUtils.getInstance().showDialog( PersonalDataInformation.this, mHandler, 10, "urgent_relatives",
                        information_urgent_relatives.getText().toString(), getList( relatives ) );
                break;
            case R.id.information_urgent_colleague_line:
                DialogUtils.getInstance().showDialog( PersonalDataInformation.this, mHandler, 11, "urgent_colleague",
                        information_urgent_colleague_text.getText().toString(), getList( colleague ) );
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
        getFindViewById_Info();
        getFindViewById_Work();
        getFindViewById_Urgent();

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

    private void getFindViewById_Info() {
        information_info_edit = findViewById( R.id.information_info_edit );
        information_info_imge = findViewById( R.id.information_info_imge );
        information_education_text = findViewById( R.id.information_education_text );
        information_province_text = findViewById( R.id.information_province_text );
        information_city_text = findViewById( R.id.information_city_text );
        information_detailed_text = findViewById( R.id.information_detailed_text );
        information_detailed_imge = findViewById( R.id.information_detailed_imge );
        information_marriage_text = findViewById( R.id.information_marriage_text );

        information_info_imge.setOnClickListener( this );
        information_detailed_imge.setOnClickListener( this );

        information_info_edit.addTextChangedListener( getTextw( information_info_imge ) );
        information_detailed_text.addTextChangedListener( getTextw( information_detailed_imge ) );

        findViewById( R.id.information_education_lin ).setOnClickListener( this );
        findViewById( R.id.information_province_lin ).setOnClickListener( this );
        findViewById( R.id.information_city_lin ).setOnClickListener( this );
        findViewById( R.id.information_marriage_lin ).setOnClickListener( this );
    }

    private void getFindViewById_Work() {
        information_work_company_edit = findViewById( R.id.information_work_company_edit );
        information_work_company_image = findViewById( R.id.information_work_company_image );
        information_work_company_province_text = findViewById( R.id.information_work_company_province_text );
        information_work_city_text = findViewById( R.id.information_work_city_text );
        information_work_detailed_text = findViewById( R.id.information_work_detailed_text );
        information_detailed_image = findViewById( R.id.information_detailed_image );
        information_work_occupation_text = findViewById( R.id.information_work_occupation_text );
        information_new_text = findViewById( R.id.information_new_text );
        information_phone_edit = findViewById( R.id.information_phone_edit );
        information_phone_image = findViewById( R.id.information_phone_image );
        information_income_text = findViewById( R.id.information_income_text );

        information_work_company_edit.addTextChangedListener( getTextw( information_work_company_image ) );
        information_work_detailed_text.addTextChangedListener( getTextw( information_detailed_image ) );
        information_phone_edit.addTextChangedListener( getTextw( information_phone_image ) );

        information_work_company_image.setOnClickListener( this );
        information_detailed_image.setOnClickListener( this );
        information_phone_image.setOnClickListener( this );

        findViewById( R.id.information_work_province_lin ).setOnClickListener( this );
        findViewById( R.id.information_work_city_lin ).setOnClickListener( this );
        findViewById( R.id.information_work_occupation_lin ).setOnClickListener( this );
        findViewById( R.id.information_income_lin ).setOnClickListener( this );
        findViewById( R.id.information_new_lin ).setOnClickListener( this );
    }

    private void getFindViewById_Urgent() {
        information_urgent_relatives = findViewById( R.id.information_urgent_relatives );
        information_urgent_name_edit = findViewById( R.id.information_urgent_name_edit );
        information_urgent_name_image = findViewById( R.id.information_urgent_name_image );
        information_urgent_phone_edit = findViewById( R.id.information_urgent_phone_edit );
        information_urgent_colleague_text = findViewById( R.id.information_urgent_colleague_text );
        information_urgent_name_colleague = findViewById( R.id.information_urgent_name_colleague );
        information_urgent_name_colleague_image = findViewById( R.id.information_urgent_name_colleague_image );
        information_urgent_phone_colleague = findViewById( R.id.information_urgent_phone_colleague );

        information_urgent_name_edit.addTextChangedListener( getTextw( information_urgent_name_image ) );
        information_urgent_name_colleague.addTextChangedListener( getTextw( information_urgent_name_colleague_image ) );

        information_urgent_name_image.setOnClickListener( this );
        information_urgent_name_colleague_image.setOnClickListener( this );
        findViewById( R.id.information_urgent_phone_image ).setOnClickListener( this );
        findViewById( R.id.information_urgent_phone_colleague_image ).setOnClickListener( this );
        findViewById( R.id.information_urgent_relatives_line ).setOnClickListener( this );
        findViewById( R.id.information_urgent_colleague_line ).setOnClickListener( this );

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

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage( msg );
            switch (msg.what) {
                case 1:
                    information_education_text.setText( msg.obj.toString() );
                    break;
                case 2:
                    information_province_text.setText( msg.obj.toString() );
                    break;
                case 3:
                    information_city_text.setText( msg.obj.toString() );
                    break;
                case 4:
                    information_marriage_text.setText( msg.obj.toString() );
                    break;
                case 5:
                    information_work_company_province_text.setText( msg.obj.toString() );
                    break;
                case 6:
                    information_work_city_text.setText( msg.obj.toString() );
                    break;
                case 7:
                    information_work_occupation_text.setText( msg.obj.toString() );
                    break;
                case 8:
                    information_income_text.setText( msg.obj.toString() );
                    break;
                case 9:
                    information_new_text.setText( msg.obj.toString() );
                    break;
                case 10:
                    information_urgent_relatives.setText( msg.obj.toString() );
                    break;
                case 11:
                    information_urgent_colleague_text.setText( msg.obj.toString() );
                    break;

            }
        }
    };

    private List<Map<String, Object>> getList(String[] stringline) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (String anEducation : education) {
            Map<String, Object> map = new HashMap<>();
            map.put( "boolean", "2" );
            map.put( "name", anEducation );
            mapList.add( map );
        }
        return mapList;
    }
}
