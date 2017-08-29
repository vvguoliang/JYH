package com.jyh.com.jyh.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.jyh.com.jyh.Adapter.RecyclerAdapter.PersonalDataRecyclerAdapter;
import com.jyh.com.jyh.Base.BaseActivity;
import com.jyh.com.jyh.EntityClass.PersonalDataEntity;
import com.jyh.com.jyh.R;
import com.jyh.com.jyh.RecyclerView.Action;
import com.jyh.com.jyh.RecyclerView.RefreshRecyclerView;
import com.jyh.com.jyh.Utils.ImmersiveUtils;

/**
 * Created by vvguoliang on 2017/8/24.
 * <p>
 * 个人资料
 */

public class PersonalDataActivity extends BaseActivity implements View.OnClickListener {

    private RefreshRecyclerView mRecyclerView;

    private PersonalDataRecyclerAdapter recyclerAdapter;

    private int page = 0;

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.act_personal_data );
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
        }

    }

    @Override
    protected void findViewById() {
        TextView loan_title = findViewById( R.id.loan_title );
        loan_title.setText( this.getString( R.string.loan_name_loan_personal_data ) );
        findViewById( R.id.loan_title_image ).setOnClickListener( this );
        findViewById( R.id.loan_title_image ).setVisibility( View.VISIBLE );
        initView();
    }

    @Override
    protected void initView() {
        recyclerAdapter = new PersonalDataRecyclerAdapter( this );
        recyclerAdapter.removeHeader();
        recyclerAdapter.removeFooter();

        mHandler = new Handler();

        mRecyclerView = findViewById( R.id.recycler_view );
        mRecyclerView.setSwipeRefreshColors( 0xFF437845, 0xFFE44F98, 0xFF2FAC21 );
        mRecyclerView.setLayoutManager( new LinearLayoutManager( this ) );
        mRecyclerView.setAdapter( recyclerAdapter );
        mRecyclerView.setRefreshAction( new Action() {
            @Override
            public void onAction() {
                getData( true );
            }
        } );

        mRecyclerView.setLoadMoreAction( new Action() {
            @Override
            public void onAction() {
                getData( false );
                page++;
            }
        } );

        mRecyclerView.post( new Runnable() {
            @Override
            public void run() {
                mRecyclerView.showSwipeRefresh();
                getData( true );
            }
        } );

    }

    public void getData(final boolean isRefresh) {
        mHandler.postDelayed( new Runnable() {
            @Override
            public void run() {
                if (isRefresh) {
                    page = 1;
                    recyclerAdapter.clear();
                    recyclerAdapter.addAll( getVirtualData() );
                    mRecyclerView.dismissSwipeRefresh();
                    mRecyclerView.getRecyclerView().scrollToPosition( 0 );
                } else {
//                    recyclerAdapter.addAll(getVirtualData());
//                    if (page >= 3) {
                    mRecyclerView.showNoMore();
//                    }
                }
            }
        }, 1500 );
    }

    public PersonalDataEntity[] getVirtualData() {
        return new PersonalDataEntity[]{
                new PersonalDataEntity( R.mipmap.ic_personal_data_userinfo, "个人信息", "请保证个人信息真实有效", "1", "1" ),
                new PersonalDataEntity( R.mipmap.ic_personal_data_zhima, "芝麻认证", "请先完成芝麻授信", "2", "2" ),
                new PersonalDataEntity( R.mipmap.ic_personal_data_carriers, "手机运营商", "认证后有助于提高审核通过率", "2", "3" ),
                new PersonalDataEntity( R.mipmap.ic_personal_data_bindcard, "收款银行卡", "所借款项将发放至该卡中", "2", "4" )
        };
    }
}
