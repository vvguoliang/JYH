package com.jyh.com.jyh.View;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.jyh.com.jyh.Activity.PersonalDataActivity;
import com.jyh.com.jyh.R;
import com.jyh.com.jyh.Utils.AppUtil;
import com.jyh.com.jyh.View.dialogUtils.BottomDialog;

/**
 * Created by vvguoliang on 2017/8/25.
 * 公共 弹出框
 */

@SuppressWarnings("ConstantConditions")
public class DialogUtils {
    /**
     * 单例对象实例
     */
    private static class DialogUtilsHolder {
        static final DialogUtils INSTANCE = new DialogUtils();
    }

    public static DialogUtils getInstance() {
        return DialogUtils.DialogUtilsHolder.INSTANCE;
    }

    /**
     * private的构造函数用于避免外界直接使用new来实例化对象
     */
    private DialogUtils() {
    }

    public void getDialogPrompt(final Context context, String title, String comtext, String buttonstring) {
        PublicPromptDialog.Builder builder = new PublicPromptDialog.Builder( context );
        builder.setTitle( title );
        builder.setContext( comtext );
        builder.setItems( buttonstring, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent( context, PersonalDataActivity.class );
                context.startActivity( intent );
                dialogInterface.dismiss();
            }
        } );
        builder.create().show();
    }

    // 提示对话框方法
    public void showDialog(final Activity context, final String name, final String btn_take, final String btn_pick,
                           final Handler mHanler, final int species) {
        final BottomDialog bottomDialog = new BottomDialog(context, R.layout.dialog_buttom_listview);
        bottomDialog.getWindow().setWindowAnimations(R.style.AnimBottom);
        bottomDialog.setWidthHeight( AppUtil.getInstance().Dispay(context)[0], 0);
        bottomDialog.getWindow().setGravity( Gravity.BOTTOM);

        ListView bottom_dialog_listView = (ListView) bottomDialog.findViewById( R.id.bottom_dialog_listView );



        if (!context.isFinishing()) {
            bottomDialog.show();
        }
    }
}
