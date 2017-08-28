package com.jyh.com.jyh.View;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.jyh.com.jyh.Activity.PersonalDataActivity;

/**
 * Created by vvguoliang on 2017/8/25.
 * 公共 弹出框
 */

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
}
