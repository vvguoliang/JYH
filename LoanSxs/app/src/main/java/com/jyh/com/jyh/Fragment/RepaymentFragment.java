package com.jyh.com.jyh.Fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

import com.jyh.com.jyh.Base.BaseFragment;
import com.jyh.com.jyh.R;

/**
 * Created by vvguoliang on 2017/8/22.
 * 还款
 */
@SuppressLint("ValidFragment")
public class RepaymentFragment extends BaseFragment{

    private Activity mActivity;

    public RepaymentFragment() {
        super();
    }

    public RepaymentFragment(Activity activity) {
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
        return R.layout.fra_repayment;
    }

    @Override
    protected void initView() {
        TextView loan_title = (TextView) findViewById( R.id.loan_title );
        loan_title.setText( R.string.repayment_loan );
    }
}
