package com.jyh.com.jyh.Fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

import com.jyh.com.jyh.Base.BaseFragment;
import com.jyh.com.jyh.R;

/**
 * Created by vvguoliang on 2017/8/22.
 *
 * 我的
 */
@SuppressLint("ValidFragment")
public class PersonalCenterFragment extends BaseFragment {

    private Activity mActivity;

    public PersonalCenterFragment() {
        super();
    }

    public PersonalCenterFragment(Activity activity) {
        super(activity);
        this.mActivity = activity;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach( context );
        this.mActivity = (Activity) context;
    }
    @Override
    protected int getLayout() {
        return R.layout.fra_personalcenter;
    }

    @Override
    protected void initView() {
        TextView loan_title = (TextView) findViewById( R.id.loan_title );
        loan_title.setText( R.string.name_personal_center );
    }
}
