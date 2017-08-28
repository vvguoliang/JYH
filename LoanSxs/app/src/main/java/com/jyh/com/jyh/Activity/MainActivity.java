package com.jyh.com.jyh.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Display;
import android.view.KeyEvent;
import android.view.ViewTreeObserver;
import android.view.Window;

import com.jyh.com.jyh.Base.BaseActivity;
import com.jyh.com.jyh.Fragment.LoanFragment;
import com.jyh.com.jyh.Fragment.PersonalCenterFragment;
import com.jyh.com.jyh.Fragment.RepaymentFragment;
import com.jyh.com.jyh.R;
import com.jyh.com.jyh.Utils.ImmersiveUtils;
import com.jyh.com.jyh.Utils.ToatUtils;
import com.jyh.com.jyh.View.MainActivityView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@SuppressWarnings("StatementWithEmptyBody")
public class MainActivity extends BaseActivity implements MainActivityView.OnItemClickListener {

    private MainActivityView mainActivityView;

    private int[] titles = {R.string.name_loan, R.string.repayment_loan, R.string.name_personal_center};
    private int[] selectedImage = {R.mipmap.ic_loan_loan_background, R.mipmap.ic_loan_repayment_background,
            R.mipmap.ic_loan_my_background};
    private int[] unSelectedImage = {R.mipmap.ic_loan_loan_foreground, R.mipmap.ic_loan_repayment_foreground,
            R.mipmap.ic_loan_my_foreground};

    private int mHeight;
    private boolean isGetHeight = true;

    private int mSelectPosition = 0;

    private List<Fragment> listfragment = new ArrayList<>();

    private List<Fragment> listnewftagment = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature( Window.FEATURE_NO_TITLE);
        setImmerStateBar(false);
        setContentView(R.layout.activity_main);
        findViewById();
    }

    @Override
    protected void findViewById() {
        int position = getIntent().getIntExtra("selectPosition", -1);
        if (position == -1) {
            mSelectPosition = 0;
        } else {
            mSelectPosition = position;
        }
        initView();

        if (position == -1) {
        } else {
            showFragment(position);
        }
        //沉浸式状态设置
        if (ImmersiveUtils.BuildVERSION()) {
            ImmersiveUtils.getInstance().getW_add_B(this);
        }
    }

    @Override
    protected void initView() {
        for (int i = 0; i <= 3; i++) {
            listfragment.add(null);
        }
        listnewftagment.add(new LoanFragment(MainActivity.this));
        listnewftagment.add(new RepaymentFragment(MainActivity.this));
        listnewftagment.add(new PersonalCenterFragment(MainActivity.this));

        // 获取屏幕宽度
        Display dm = getWindowManager().getDefaultDisplay();
        final int screenWith = dm.getWidth();
        mainActivityView = findViewById(R.id.act_main_tab);
        // 初始化获取底部导航自身高度
        final ViewTreeObserver vt = mainActivityView.getViewTreeObserver();
        vt.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                if (isGetHeight) {
                    mHeight = mainActivityView.getHeight();
                    mainActivityView.setLayout(titles, selectedImage, unSelectedImage, screenWith, mHeight, MainActivity.this);
                    mainActivityView.setColorLing(mSelectPosition);
                    mainActivityView.setOnItemClickListener(MainActivity.this);
                    isGetHeight = false;
                }
                return true;
            }
        });
        showFragment(mSelectPosition);
    }

    @Override
    public void onItemClick(int position) {
        showFragment(position);
    }

    /**
     * 动态添加和显示fragment
     *
     * @param position
     */
    private void showFragment(int position) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        hideFragment(transaction);
        setImmerStateBar(false);
        for (int i = 0; i < listfragment.size(); i++) {
            if (position == i) {
                if (listfragment.get(i) == null) {
                    listfragment.remove(i);
                    listfragment.add(i, listnewftagment.get(i));
                    if (!listnewftagment.get(i).isAdded()) {
                        transaction.add(R.id.cat_main_fragment_content, listfragment.get(i));
                    } else {
                        transaction.show(listfragment.get(i));
                    }
                } else {
                    transaction.show(listfragment.get(i));
                }
                //沉浸式状态设置
                if (ImmersiveUtils.BuildVERSION()) {
                    ImmersiveUtils.getInstance().getW_add_B(this);
                }
            }
        }
        transaction.commitAllowingStateLoss();
    }


    /**
     * 隐藏所有fragment
     *
     * @param transaction
     */
    private void hideFragment(FragmentTransaction transaction) {
        for (int i = 0; i < listfragment.size(); i++) {
            if (listfragment.get(i) != null) {
                transaction.hide(listfragment.get(i));
            }
        }
    }

    /**
     * 连续按两次返回键就退出
     */
    private int keyBackClickCount = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            switch (keyBackClickCount++) {
                case 0:
                    ToatUtils.showShort1(this, "再按一次退出");
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            keyBackClickCount = 0;
                        }
                    }, 3000);
                    break;
                case 1:
                    finish();
                    break;
                default:
                    break;
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
